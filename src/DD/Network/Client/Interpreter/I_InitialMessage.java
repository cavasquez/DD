package DD.Network.Client.Interpreter;

import java.io.IOException;
import java.net.Socket;
import DD.Network.Client.ClientListener;
import DD.Network.Message.ClientListenerReadyMessage;
import DD.Network.Message.InitialMessage;
import DD.Network.Message.NetworkMessage;
import DD.Network.Server.ListenerSpawner;

/*****************************************************************************************************
 * I_InitialMessage will pass combat messages to InitialSystem
 ******************************************************************************************************/

public class I_InitialMessage extends ClientInterpreter
{
	@Override
	public void interpret(NetworkMessage message)
	{
		InitialMessage im = (InitialMessage) message.getMessage();
		if (im.getValid())
		{/* The sent username was invalid (already exists). Kill sender */
			//TODO: communicate to lobby (and then to user) that username was invalid and try again
			system.setSender(null);
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
				Socket socket = new Socket(system.getServerIP(), ListenerSpawner.port);
				ClientListener listener = new ClientListener(socket);
				listener.run();
				system.setListener(listener);
			} /* end try */
			catch (IOException e) 
			{
				// TODO Communicate to user that Client could not connect to Server
				e.printStackTrace();
				success = false;
			} /* end catch */
			
			if (success)
			{/* successfully connected, send server ClientListenerReadyMessage */
				ClientListenerReadyMessage clrm = new ClientListenerReadyMessage(system.getClientID());
			} /*  end if*/
			
		} /* end else */
		
	} /* end Interpret method */
	
} /* end I_InitialMessage class */
