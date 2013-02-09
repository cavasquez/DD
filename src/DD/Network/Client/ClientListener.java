package DD.Network.Client;

import java.net.Socket;

import DD.Network.Listener;
import DD.Network.MessageQueue;

/*****************************************************************************************************
 * ClientListener will be a thread dedicated to Listening for messages from the server and giving the
 * message to MessageQueue to be sent to the Client.
 ******************************************************************************************************/

public class ClientListener extends Listener
{
	/************************************ Class Methods *************************************/
	public ClientListener(Socket socket) 
	{
		super(socket);
	} /* end ClientListener constructor */
	
	public void run() 
	{ /* Threads process */
		createStreams(); 
		
		while (!done)
		{
			this.message = getSocketMessage();
			MessageQueue.getInstance().enqueuMessage(this.socketID, message);
			
		} /* end done loop */
		
		/* Done. Close streams. */
		closeStreams();
	} /* end run method */

} /* end ClientListener class */
