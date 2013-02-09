package DD.Network.Server;

import java.io.IOException;
import java.net.ServerSocket;


/*****************************************************************************************************
 * ListenerSpawner will be responsible for creating ServerSocket's for every client while in the lobby.
 * It should be terminated after leaving the lobby.
 ******************************************************************************************************/

public class ListenerSpawner extends Thread
{
	/************************************ Class Constants *************************************/
	public static final int port = 8080;
	
	/************************************ Class Attributes *************************************/
	private ServerSocket serverSocket = null;
	private boolean acceptingConnections;		
	private boolean closed;						/* flag to kill thread */
	
	/************************************ Class Methods *************************************/
	public ListenerSpawner()
	{
		super("ListenerSpawnerThread");
		acceptingConnections = true;
		closed = false;
	} /* end ListenerSpawner */
	
	public void close()
	{
		closed = true;
		
	} /* end close */
	
	public void startAccepting()
	{
		acceptingConnections = true;
	} /* end startAccepting method */
	
	public void stopAccepting()
	{
		acceptingConnections = false;
	} /* end stopAccepting method */
	
	/************************************ Important Method *************************************/
	public void run()
	{
		try
		{
			serverSocket = new ServerSocket(port);
		} /* end try */
		catch (IOException e)
		{
			System.out.println("Failed to listen on port: " + Integer.toString(port));
		} /* end catch */
		
		while (!closed)
		{
			while (acceptingConnections)
			{
				try 
				{
					new ServerListener(serverSocket.accept()).run();
				} /* end try */ 
				catch (IOException e) 
				{
					e.printStackTrace();
					acceptingConnections = false;
					closed = true;
				} /* end catch */
				
			} /* end acceptingConnections */
			
		} /* end while loop */
		
	} /* end run method */
	
} /* end ListenerSpawner class */
