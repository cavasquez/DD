package DD.Network.Server.Interpreter;

import java.io.IOException;
import java.net.Socket;

import DD.Message.AddUserMessage;
import DD.Message.InitialMessage;
import DD.Message.NetworkMessage;
import DD.Network.Network;
import DD.Network.Server.ClientTable;
import DD.Network.Server.ServerSender;

/*****************************************************************************************************
 * I_InitialMessage will be used to interpret and process all InitialMessages sent to the Server.
 * 
 * Because this is the first message sent by the client, we know that the client knows nothing, nor
 * does the client have a clientID (socketID). If the username is valid, we will add it to ServerSystems
 * userList and then update it with the userList (minus the server) so that it can know who is online.
 * 
 * InitialMessage will also need to update the peers to the presence of the new client.
 * 
 * @author Carlos Vasquez
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
			/* Username was successfully added to userList. Now, connect to the clients listener,
			 * give the clients it's new serverID, inform it's peers of their new brother. */
			System.out.println("I_InitialMessage: added the user " + im.getUsername() + " with uid " + newID + " and listener " + listenerID);
			/* Connect to clients listener */
			Socket socket = null;
			try 
			{
				socket = new Socket(system.getListenerIP(listenerID), Network.CLIENT_PORT);
				ServerSender sender = new ServerSender(socket);
				clientList.addSender(newID, sender.getID(), sender);
				
				/* Successfully connected to client listener */
			} /* end try */
			catch (IOException e) 
			{
				/* Failed to connect to client */
				System.out.println("Failed to connect to client listener");
				//TODO: fix
			} /* end catch */
			
			/* Tell client it's serverID */
			InitialMessage confirmation = new InitialMessage(im.getUsername(), true, newID);
			system.sendMessage(Network.GM_USER_ID, newID, confirmation);
			
			/* Communicate to the peers their new brother */
			AddUserMessage am = new AddUserMessage(newID, im.getUsername(), system.getListenerIP(listenerID));
			system.sendMessage(Network.GM_USER_ID, Network.EVERYONE, am, newID);
			
		} /* end if */
		else
		{
			/* Username already exists. Process failed. Alert the client and disconnect.
			 * Then, remove Server from userList */
			InitialMessage rejection = new InitialMessage(null, false, null);
			system.sendMessage(Network.GM_USER_ID, newID, rejection);
			system.removeClient(listenerID); /* removeClient kills the Server thread */
		} /* end else */
		
	} /* end interpret method */
	
} /* end I_InitialMessage */
