package DD.Network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import DD.Message.NetworkMessage;

/*****************************************************************************************************
* Sender will provide the tools that ClientSender and ServerSender will need to send a message.
* 
* @author Carlos Vasquez
******************************************************************************************************/

public class Sender extends NetworkSocket
{
	/************************************ Class Attributes *************************************/
	protected static final int sleepTime = 200;		/* Time thread is spent sleeping */ 
	protected ObjectOutputStream output = null;
	
	/************************************ Class Methods *************************************/
	public Sender(Socket socket)
	{
		super(socket);
		createStreams();

	} /* end Server constructor */
	
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
	
	protected void createStreams()
	{
		try 
		{
			output = new ObjectOutputStream(socket.getOutputStream());
		} /* end try */ 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /* end catch */
	} /* end createStreams method */
	
	protected void closeStreams()
	{
		try 
		{
			output.close();
		} /* end try */ 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /* end catch */
		
	} /* end closeServerSocket method */

	@Override
	public void run()  {} /* Sender should not be used as a thread */
	
} /* end Sender class */
