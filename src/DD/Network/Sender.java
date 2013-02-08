package DD.Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;
import DD.Network.Message.NetworkMessage;

/*****************************************************************************************************
* Sender will provide the tools that ClientSender and ServerSender will need to send a message.
******************************************************************************************************/

public abstract class Sender extends NetworkSocket
{

        /************************************ Class Attributes *************************************/
	
	/************************************ Class Methods *************************************/
	public Sender(Socket socket, int socketID)
	{
		super(socket, socketID);
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
		messageList.offer(message);
		sending = true;
	} /* end sendMessage method */
	
} /* end Sender class */
