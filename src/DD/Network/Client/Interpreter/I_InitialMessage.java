package DD.Network.Client.Interpreter;

import DD.Message.InitialMessage;
import DD.Message.NetworkMessage;


/*****************************************************************************************************
 * I_InitialMessage will check to see if the username was correct and then add itself to the peerList
 * and set up a listener for the server to connect to.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_InitialMessage extends ClientInterpreter
{
	@Override
	public void interpret(NetworkMessage message)
	{
		InitialMessage im = (InitialMessage) message.getMessage();
		if (!im.getValid())
		{/* The sent username was invalid (already exists). Kill sender */
			//TODO: communicate to lobby (and then to user) that username was invalid and try again
			system.stop();
			System.out.println("Username Exists!");
		} /* end if */
		else
		{/* The sent username was valid. Add the provided ID to ClientSystem and the peerList */
			system.setClientID(im.getID());
			system.addUser(im.getID(), im.getUsername(), null); /* Client does not need to know it's ip */
			System.out.println("I_InitialMessage: added the user " + im.getUsername() + " with uid " + im.getID());
			
		} /* end else */
		
	} /* end Interpret method */
	
} /* end I_InitialMessage class */
