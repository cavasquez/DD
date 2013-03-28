package DD.Network;

import java.io.IOException;
import java.net.Socket;

import DD.Message.NetworkMessage;

/*****************************************************************************************************
* Listener will be used to listen for messages from the Server or client. This will provide the basic 
* tools used by ServerListener and ClientListener.
* 
* Listener will need to utilize ServerSocket
* 
* @author Carlos Vasquez
******************************************************************************************************/

public abstract class Listener extends NetworkSocket
{	
	/************************************ Class Methods *************************************/
	public Listener(Socket socket)
	{
		super(socket);
	} /* end Listener constructor */
	
	public abstract void run();
	
	protected NetworkMessage getSocketMessage()
	{
		NetworkMessage message = null;
		
		try
		{
			message = (NetworkMessage) input.readObject();
		} /* end try */
		catch(IOException e)
		{
			System.out.println("IOException in Listener");
			e.printStackTrace();
		} /* end catch */
		catch(ClassNotFoundException e)
		{
			System.out.println("ClassNotFoundException in Listener");
			e.printStackTrace();
		} /* end catch */
		
		return(message);
	} /* end getSocketMessage method */


} /* end Listener class */
