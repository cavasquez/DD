package DD.Network.Server.Interpreter;

import java.util.ArrayList;

import DD.Network.Network;
import DD.Network.Message.AddUserMessage;
import DD.Network.Message.InitialMessage;
import DD.Network.Message.NetworkMessage;
import DD.Network.Server.Client;
import DD.Network.Server.ClientTable;

/*****************************************************************************************************
 * I_InitialMessage will be used to interpret and process all InitialMessages sent to the Server.
 * 
 * Because this is the first message sent by the client, we know that the client knows nothing, nor
 * does the client have a clientID (socketID). If the username is valid, we will add it to ServerSystems
 * userList and then update it with the userList (minus the server) so that it can know who is online.
 * 
 * InitialMessage will also need to update the peers to the presence of the new client.
 ******************************************************************************************************/

public class I_InitialMessage extends ServerInterpreter
{
	@Override
	public void interpret(int listenerID, NetworkMessage message) 
	{
		InitialMessage im = (InitialMessage) (message.getMessage());
		int newID = system.getNewClientID();
		ClientTable clientList = system.getClientList();
		
		if (clientList.addUsername(listenerID, newID, im.getUsername()))
		{
			/* Username was successfully added to userList. Now, give the client
			 * it's new serverID, inform it of it's peers, and inform the peers
			 * of their new brother. */
			
			/* Tell client it's serverID */
			
			InitialMessage confirmation = new InitialMessage(Integer.toString(newID), true);
			system.sendMessage(Network.GM_USER_ID, newID, confirmation);
			
			/* Communicate to the client it's peers */
			ArrayList<Client> clients = clientList.getClientList();
			AddUserMessage am = null;
			for(Client client : clients)
			{/* Send message to everyone except the server */
				if (client.clientID != Network.GM_USER_ID && client.listenerID != listenerID)
				{
					am = new AddUserMessage(newID, client.username);
					system.sendMessage(Network.GM_USER_ID, newID, am);
				} /* end if */
				
			} /* end for loop */
			
			/* Communicate to the peers their new brother */
			am = new AddUserMessage(newID, im.getUsername());
			system.sendMessage(Network.GM_USER_ID, Network.EVERYONE, am, newID);
			
		} /* end if */
		else
		{
			/* Username already exists. Process failed. Alert the client and disconnect.
			 * Then, remove Server from userList */
			InitialMessage rejection = new InitialMessage(null, false);
			system.sendMessage(Network.GM_USER_ID, newID, rejection);
			system.removeClient(listenerID); /* removeUser kills the Server thread */
			
		} /* end else */
		
	} /* end interpret method */
	
} /* end I_InitialMessage */
