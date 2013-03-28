package DD.Network.Client;

import java.net.Socket;

import DD.Message.NetworkMessage;
import DD.Message.NewListenerMessage;
import DD.Network.Listener;
import DD.Network.MessageQueue;
import DD.Network.Network;

/*****************************************************************************************************
 * ClientListener will be a thread dedicated to Listening for messages from the server and giving the
 * message to MessageQueue to be sent to the Client.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class ClientListener extends Listener
{
	/************************************ Class Methods *************************************/
	public ClientListener(Socket socket) 
	{
		super(socket);
	} /* end ClientListener constructor */
	
	public void run() 
	{ 
		/* Open stream */
		if (input == null) createStreams(); 
		
		/* First, inform the client that a Listener was created  */
		this.message = new NetworkMessage(Network.SELF, Network.SELF, new NewListenerMessage(this.socketID, this, this.socket.getInetAddress()));
		MessageQueue.getInstance().enqueuMessage(this.socketID, message);
		
		/* Now, listen for messages from the Server */
		while (!done)
		{/* Threads process */
			this.message = getSocketMessage();
			MessageQueue.getInstance().enqueuMessage(this.socketID, message);
			
		} /* end done loop */
		
		/* Done. Close streams. */
		closeStreams();
	} /* end run method */

} /* end ClientListener class */
