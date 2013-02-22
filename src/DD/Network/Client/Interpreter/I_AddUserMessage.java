package DD.Network.Client.Interpreter;

import DD.Message.AddUserMessage;
import DD.Message.NetworkMessage;

/*****************************************************************************************************
 * I_AddUserMessage add the new user(peer) to the peerList
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_AddUserMessage extends ClientInterpreter
{
	@Override
	public void interpret(NetworkMessage message)
	{
		AddUserMessage aum = (AddUserMessage) message.getMessage();
		
		if (!system.addUser(aum.getPlayerID(), aum.getUsername(), aum.getIP()))
		{
			//TODO: deal with a user that cant be added
		} /* end if */		
		
	} /* end interpret method */
	
} /* end I_AddUserMessage */
