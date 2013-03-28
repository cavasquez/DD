package DD.Network.Client.Interpreter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import DD.Message.InitialMessage;
import DD.Message.NetworkMessage;
import DD.Network.Network;
import DD.Network.Client.ClientListener;

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
			system.setSender(null);
			System.out.println("Username Exists!");
		} /* end if */
		else
		{/* The sent username was valid. Add the provided ID to ClientSystem and the peerList */
			system.setClientID(im.getID());
			system.addUser(im.getID(), im.getUsername(), null); /* Client does not need to know it's ip */
			
			/* Phase 2: */
			/* Now that the client knows it is a part of the system, it will need to set up a listener and 
			 * tell the server that it's listener is ready. */
			boolean success = true;
			try 
			{
				ServerSocket serverSocket = new ServerSocket(Network.PORT);
				ClientListener listener = new ClientListener(serverSocket.accept());
				listener.run();
				system.setListener(listener);
			} /* end try */
			catch (IOException e) 
			{
				// TODO Communicate to user that Client could not connect to Server
				e.printStackTrace();
				success = false;
			} /* end catch */
			
			if (!success)
			{/* Failed to connect to server */
				//TODO: deal with error
			} /*  end if*/
			
		} /* end else */
		
	} /* end Interpret method */
	
} /* end I_InitialMessage class */
