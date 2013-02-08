package DD.Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	/************************************ Class Methods *************************************/
	public Listener(Socket socket, int socketID)
	{
		super(socket, socketID);
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
	
	protected void getMessage(NetworkMessage message)
	{ /* We got a message from the stream. Process it. */
		NetworkSystem.getInstance().getMessage(socketID, message);
		
	} /* end getMessage method */

} /* end Listener class */
