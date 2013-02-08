package DD.Network.Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;
import DD.Network.DDSocket;
import DD.Network.Message.NetworkMessage;

/*****************************************************************************************************
 * Server is a thread that will be dedicated to servicing one client. The services are as follows:
 * 
 * 1. Listen for messages. If a message is received, pass it to ServerSystem.
 * 
 * 2. Send a message passed from ServerSystem to the client.
 ******************************************************************************************************/

public class Server extends DDSocket
{
	/************************************ Class Attributes *************************************/
	private Socket socket = null;
	private ObjectInputStream input = null;
	private ObjectOutputStream output = null;
	private volatile boolean sending;
	private volatile boolean done;						/* done should be called when thread is to be closed */
	private boolean working;							/* thread is doing work */
	private int socketID;								/* Unique ID for thread */
	private volatile Queue<NetworkMessage> messageList;	/* messages to be sent */
	
	/************************************ Class Methods *************************************/
	public Server(Socket socket, int socketID)
	{
		super(socket, socketID);

	} /* end Server constructor */
	
	public void run() 
	{
		createStreams(); 
		NetworkMessage message = null;
		
		/* the first thing a server should do is add itself to the ServerSystems userList */
		ServerSystem.getInstance().addServer(socketID, this);
		
		while (!done || working)
		{
			while (!sending || working)
			{ /* Server is not sending any messages, thus it is blocking for messages */
				message = getSocketMessage();
				
				/* If we get a message, we are working */
				working = true;
				getMessage(message);
				working = false;
			} /* end sending loop */
			
			if (sending)
			{
				working = true;
				while (messageList.peek() != null)
				{
					sendSocketMessage(messageList.remove());
				} /* end loop */
				sending = false;
				working = false;
			} /* end if */
			
		} /* end done loop */
		
		/* Done. Close streams. */
		closeStreams();
	} /* end run method */
	
} /* end Server class */