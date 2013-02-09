package DD.Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import DD.Network.Message.NetworkMessage;

/*****************************************************************************************************
* Listener will be used to listen for messages from the Server or client. This will provide the basic 
* tools used by ServerListener and ClientListener.
* 
* Listener will need to utilize ServerSocket
******************************************************************************************************/

public abstract class Listener extends NetworkSocket
{
	/************************************ Class Attributes *************************************/
	private ServerSocket sSocket;
	private MessageQueue queue= null;
	
	/************************************ Class Methods *************************************/
	public Listener(Socket socket)
	{
		super(socket);
	} /* end Server constructor */
	
	public void run() {} /* end run method */
	
	protected NetworkMessage getSocketMessage()
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

} /* end Listener class */