package DD.Network.Server;

import java.net.Socket;

import DD.Network.Listener;
import DD.Network.MessageQueue;
import DD.Network.NetworkSystem;
import DD.Network.Message.NetworkMessage;
import DD.Network.Message.NewListenerMessage;

/*****************************************************************************************************
 * ServerListener will be a thread dedicated to Listening for messages from a client and giving the
 * message to MessageQueue to be sent to the Server.
 ******************************************************************************************************/

public class ServerListener extends Listener
{
	/************************************ Class Methods *************************************/
	public ServerListener(Socket socket) 
	{
		super(socket);
	} /* end ServerListener constructor */
	
	public void run() 
	{ /* Threads process */
		createStreams(); 
		message = new NetworkMessage(NetworkSystem.GM_USER_ID, this.socketID, new NewListenerMessage(this.socketID, this, this.socket.getInetAddress()));
		MessageQueue.getInstance().enqueuMessage(this.socketID, message);
		
		/* Before ServerListener does anything else, it must let ServerSystem know of it's existence. 
		 * This is done by passing the message through MessageQueue. */
		
		while (!done)
		{
			this.message = getSocketMessage();
			MessageQueue.getInstance().enqueuMessage(this.socketID, message);
			
		} /* end done loop */
		
		/* Done. Close streams. */
		closeStreams();
	} /* end run method */

} /* end ServerListener class */
