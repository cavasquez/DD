package DD.Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Queue;

/*****************************************************************************************************
* DDSocket is an experimental socket that will attempt to use one thread to control both listening
* and sending.
******************************************************************************************************/

public abstract class DDSocket extends Thread
{
//
//	/************************************ Class Attributes *************************************/
//	protected Socket socket = null;
//	protected ObjectInputStream input = null;
//	protected ObjectOutputStream output = null;
//	protected volatile boolean sending;
//	protected volatile boolean done;						/* done should be called when thread is to be closed */
//	protected boolean working;							/* thread is doing work */
//	protected int socketID;								/* Unique ID for thread */
//	protected volatile Queue<NetworkMessage> messageList;	/* messages to be sent */
//	
//	/************************************ Class Methods *************************************/
//	@SuppressWarnings("unchecked")
//	public DDSocket(Socket socket, int socketID)
//	{
//		super("DDSocketThread" + Integer.toHexString(socketID));
//		this.socket = socket;
//		this.done = false;
//		this.sending = false;
//		this.working = true;
//		this.socketID = socketID;
//		this.messageList = (Queue<NetworkMessage>) new ArrayList<NetworkMessage>();
//	} /* end Server constructor */
//	
//	public void run() {} /* end run method */
//	
//	protected void createStreams()
//	{
//		try 
//		{
//			input = new ObjectInputStream(socket.getInputStream());
//			output = new ObjectOutputStream(socket.getOutputStream());
//		} /* end try */ 
//		catch (IOException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} /* end catch */
//	} /* end createStreams method */
//
//	protected void closeStreams()
//	{
//		try 
//		{
//			input.close();
//			output.close();
//		} /* end try */ 
//		catch (IOException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} /* end catch */
//		
//	} /* end closeServerSocket method */
//	
//	protected NetworkMessage getSocketMessage()
//	{
//		NetworkMessage message = null;
//		
//		try
//		{
//			message = (NetworkMessage) input.readObject();
//		} /* end try */
//		catch(IOException | ClassNotFoundException e)
//		{
//			e.printStackTrace();
//		} /* end catch */
//		
//		return(message);
//	} /* end getSocketMessage method */
//	
//	protected void sendSocketMessage(NetworkMessage message)
//	{
//		try 
//		{
//			output.writeObject(message);
//			output.flush();
//		} /* end try */
//		catch (IOException e) 
//		{
//			e.printStackTrace();
//		} /* end catch */
//		
//	} /* end sendSocketMessage method */
//	
//	protected void getMessage(NetworkMessage message)
//	{ /* We got a message from the stream. Process it. */
//		NetworkSystem.getInstance().getMessage(socketID, message);
//		
//	} /* end getMessage method */
//	
//	public void sendMessage(NetworkMessage message)
//	{
//		messageList.offer(message);
//		sending = true;
//	} /* end sendMessage method */
//
//	public void close()
//	{
//		/* thread is done */
//		done = true;
//	} /* end close method */
} /* end DDSocket */
