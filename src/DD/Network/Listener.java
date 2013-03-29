package DD.Network;

import java.io.IOException;
import java.io.ObjectInputStream;
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
	/************************************ Class Attributes *************************************/
	protected ObjectInputStream input = null;
	
	/************************************ Class Methods *************************************/
	public Listener(Socket socket)
	{
		super(socket);
		createStreams();
	} /* end Listener constructor */
	
	protected abstract void alertNetwork();
	
	public void run()
	{
		/* Open stream */
		if (input == null) createStreams(); 
		
		/* First, inform the NetworkSystem that a Listener was created  */
		alertNetwork();
		
		/* Now, listen for messages from the Server */
		while (!done)
		{/* Threads process */
			this.message = getSocketMessage();
			while(this.message != null)
			{
				MessageQueue.getInstance().enqueuMessage(this.socketID, message);
				this.message = getSocketMessage();
			} /* end message null loop */
			
			/* While the socket is doing nothing, sleep to preserver resources */
			try 
			{
				Thread.sleep(sleepTime);
			} /* end try */ 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} /* end catch */
			
		} /* end done loop */
		
		/* Done. Close streams. */
		closeStreams();
	} /* end run method */
	
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
			//e.printStackTrace();
			System.out.println("Disconnected from sender");
			return(null);
		} /* end catch */
		catch(ClassNotFoundException e)
		{
			System.out.println("ClassNotFoundException in Listener");
			e.printStackTrace();
		} /* end catch */
		
		return(message);
	} /* end getSocketMessage method */
	
	protected void createStreams()
	{
		try 
		{
			if(input == null) input = new ObjectInputStream(socket.getInputStream());
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
			input.close();
		} /* end try */ 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /* end catch */
		
	} /* end closeServerSocket method */


} /* end Listener class */
