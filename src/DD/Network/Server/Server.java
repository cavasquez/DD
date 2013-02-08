package DD.Network.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;
import DD.Network.NetworkMessage;

public class Server extends Thread
{
	/************************************ Class Attributes *************************************/
	private Socket socket = null;
	private ObjectInputStream input = null;
	private ObjectOutputStream output = null;
	private volatile boolean sending;
	private volatile boolean done;						/* done should be called when thread is to be closed */
	private boolean working;							/* thread is doing work */
	private int serverID;								/* Unique ID for server thread */
	private volatile Queue<NetworkMessage> messageList;	/* messages to be sent */
	
	/************************************ Class Methods *************************************/
	public Server(Socket socket, int serverID)
	{
		super("DDServerThread" + Integer.toHexString(serverID));
		this.socket = socket;
		this.done = false;
		this.sending = false;
		this.working = true;
		this.serverID = serverID;
	} /* end Server constructor */
	
	public void run() 
	{
		createStreams(); 
		NetworkMessage message = null;
		
		/* the first thing a server should do is add itself to the ServerSystems userList */
		ServerSystem.getInstance().addServer(serverID, this);
		
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
	
	private void createStreams()
	{
		try 
		{
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
		} /* end try */ 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /* end catch */
	} /* end createStreams method */

	private void closeStreams()
	{
		try 
		{
			input.close();
			output.close();
		} /* end try */ 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /* end catch */
		
	} /* end closeServerSocket method */
	
	private NetworkMessage getSocketMessage()
	{
		NetworkMessage message = null;
		
		try
		{
			message = (NetworkMessage) input.readObject();
		} /* end try */
		catch(IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		} /* end catch */
		
		return(message);
	} /* end getSocketMessage method */
	
	private void sendSocketMessage(NetworkMessage message)
	{
		try 
		{
			output.writeObject(message);
			output.flush();
		} /* end try */
		catch (IOException e) 
		{
			e.printStackTrace();
		} /* end catch */
		
	} /* end sendSocketMessage method */
	
	private void getMessage(NetworkMessage message)
	{ /* We got a message from the stream. Process it. */
		ServerSystem.getInstance().getMessage(serverID, message);
		
	} /* end getMessage method */
	
	public void sendMessage(NetworkMessage message)
	{
		messageList.offer(message);
		sending = true;
	} /* end sendMessage method */
	
} /* end Server class */
