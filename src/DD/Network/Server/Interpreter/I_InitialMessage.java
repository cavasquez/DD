package DD.Network.Server.Interpreter;

import DD.Network.InitialMessage;
import DD.Network.NetworkMessage;
import DD.Network.Server.ServerSystem;

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
			
		} /* end if */
		else
		{
			
		} /* end else */
		
	} /* end interpret method */
	
} /* end I_InitialMessage */
