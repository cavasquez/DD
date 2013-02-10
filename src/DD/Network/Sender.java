package DD.Network;

import java.io.IOException;
import java.net.Socket;

import DD.Message.NetworkMessage;

/*****************************************************************************************************
* Sender will provide the tools that ClientSender and ServerSender will need to send a message.
******************************************************************************************************/

public abstract class Sender extends NetworkSocket
{
	/************************************ Class Attributes *************************************/
	protected static final int sleepTime = 200;		/* Time thread is spent sleeping */ 
	
	/************************************ Class Methods *************************************/
	public Sender(Socket socket)
	{
		super(socket);

	} /* end Server constructor */
	
	public void run() {} /* end run method */
	
	protected void sendSocketMessage(NetworkMessage message)
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
	
	public void sendMessage(NetworkMessage message)
	{
		sendSocketMessage(message);
	} /* end sendMessage method */
	
	public void setUp()
	{
		createStreams();
	} /* end setUp method*/
	
} /* end Sender class */
