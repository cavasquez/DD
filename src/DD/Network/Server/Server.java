package DD.Network.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Queue;

import DD.Chat.ChatSystem;
import DD.Network.InitialNetworkMessage;
import DD.Network.Message;
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
		/* The first message should always contain the username of
		 * the player. We will need to validate this. */
		NetworkMessage message = getSocketMessage();
		ServerSystem.getinstance().addUser(serverID, ((InitialNetworkMessage)message.getMessage()).getUsername(), this);
		
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
	
	public boolean sendMessage(Message message)
	{ /* Called by CombatSystem or ChatSystem. */
		/* sendMessage will check to see if the message is of valid type.
		 * if it is, it will wrap the message in a NetworkMessage and
		 * add it to the messageList. Then, it will set a flag that
		 * there is a message waiting to be send. */
		boolean valid = false;
		
		if (Message.messageExists(message)) valid = true;
		if (valid)
		{
			messageList.offer(new NetworkMessage(message));
			sending = true;
		} /* end message */
		return valid;
	} /* end sendMessage method */
	
	private void getMessage(NetworkMessage message)
	{ /* We got a message from the stream. Process it. */
		/* Since this is the server, we have to redirect this message to 
		 * everyone else. However, we do not want to re-send it to the
		 * sender.  */
		
	} /* end getMessage method */
	
} /* end Server class */
