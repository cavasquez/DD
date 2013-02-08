package DD.Network.Server.Interpreter;

import java.util.ArrayList;

import DD.Network.Network;
import DD.Network.Message.AddUserMessage;
import DD.Network.Message.InitialMessage;
import DD.Network.Message.NetworkMessage;
import DD.Network.Server.Server.ServerSystem;
import DD.Network.Server.Server.User;
import DD.Network.Server.Server.UserTable;

/*****************************************************************************************************
 * I_InitialMessage will be used to interpret and process all InitialMessages sent to the Server.
 * 
 * Because this is the first message sent by the client, we know that the client knows nothing, nor
 * does the client have a clientID (socketID). If the username is valid, we will add it to ServerSystems
 * userList and then update it with the userList (minus the server) so that it can know who is online.
 * 
 * InitialMessage will also need to update the peers to the presence of the new client.
 ******************************************************************************************************/

public class I_InitialMessage implements ServerInterpreter
{

	@Override
	public void interpret(int serverID, NetworkMessage message) 
	{
		InitialMessage im = (InitialMessage) (message.getMessage());
		
		if (ServerSystem.getInstance().addUsername(serverID, im.getUsername()))
		{
			/* Username was successfully added to userList. Now, give the client
			 * it's new serverID, inform it of it's peers, and inform the peers
			 * of their new brother. */
			
			/* Tell client it's serverID */
			InitialMessage confirmation = new InitialMessage(Integer.toString(serverID), true);
			ServerSystem.getInstance().sendMessage(Network.GM_USER_ID, serverID, confirmation);
			
			/* Communicate to the client it's peers */
			ArrayList<User> users = ServerSystem.getInstance().getUserList().getUserList();
			AddUserMessage am = null;
			for(User user : users)
			{
				if (user.socketID != Network.GM_USER_ID && user.socketID != serverID)
				{
					am = new AddUserMessage(user.socketID, user.username);
					ServerSystem.getInstance().sendMessage(Network.GM_USER_ID, serverID, am);
				} /* end if */
				
			} /* end for loop */
			
			/* Communicate to the peers their new brother */
			am = new AddUserMessage(serverID, im.getUsername());
			ServerSystem.getInstance().sendMessage(Network.GM_USER_ID, Network.EVERYONE, am, serverID);
			
		} /* end if */
		else
		{
			/* Username already exists. Process failed. Alert the client and disconnect.
			 * Then, remove Server from userList */
			InitialMessage rejection = new InitialMessage(null, false);
			ServerSystem.getInstance().sendMessage(Network.GM_USER_ID, serverID, rejection);
			ServerSystem.getInstance().removeUser(serverID); /* removeUser kills the Server thread */
			
		} /* end else */
		
	} /* end interpret method */
	
} /* end I_InitialMessage */
