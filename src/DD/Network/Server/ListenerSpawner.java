package DD.Network.Server;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.SocketException;

import DD.Network.Network;

/*****************************************************************************************************
 * ListenerSpawner will be responsible for creating ServerSocket's for every client while in the lobby.
 * It should be terminated after leaving the lobby.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class ListenerSpawner extends Thread
{
	/************************************ Class Constants *************************************/
	private static final int sleepTime = 1000;
	
	/************************************ Class Attributes *************************************/
	private ServerSocket serverSocket = null;
	private boolean acceptingConnections;
	private boolean closed;
	
	/************************************ Class Methods *************************************/
	public ListenerSpawner()
	{
		super("ListenerSpawnerThread");
		acceptingConnections = true;
		closed = false;
	} /* end ListenerSpawner */
	
	public void close()
	{
		/* put an end to this thread */
		closed = true;
		stopAccepting();
		
	} /* end close */
	
	public void startAccepting()
	{
		acceptingConnections = true;
	} /* end startAccepting method */
	
	public void stopAccepting()
	{
		/* Stop the server from accepting new connections */
		acceptingConnections = false;
		try 
		{
			serverSocket.close();
		} /* end try */
		catch (IOException e) 
		{
			System.out.println("ServerSpawner: serverSocket does not exist");
		} /* end catch */
	} /* end stopAccepting method */
	
	/************************************ Important Method *************************************/
	public void run()
	{
		/* First, make a new server socket to get a new client */
		try
		{
			System.out.println("trying to listen on port");
			serverSocket = new ServerSocket(Network.SERVER_PORT, Network.BACKLOG);
			System.out.println("Listening on port " + serverSocket.getLocalPort());
		} /* end try */
		catch (IOException e)
		{
			System.out.println("Failed to listen on port: " + Integer.toString(serverSocket.getLocalPort()));
			System.out.println("Closing the ListenerSpawner");
			closed = true;
		} /* end catch */
		
		/* Now that the server socket has been made, wait for a connection
		 * and then start a ServerListener. */
		
		while (!closed)
		{
			if (acceptingConnections)
			{	
				/* Accepting connections. Loop */
				while (acceptingConnections)
				{
					try 
					{
						new ServerListener(serverSocket.accept()).start();
						System.out.println("Sender successfully connected");
					} /* end try */ 
					catch (IOException e) 
					{
						if(!acceptingConnections) System.out.println("Successfully stopped ListenerSpawner");
						else 
						{
							/* unexpected behavior, terminate thread */
							acceptingConnections = false;
							closed = true;
						} /* end else */
					} /* end catch */
					
				} /* end acceptingConnections */
				
			} /* end if */
			
			else
			{
				/* Not accepting connections, however not closed. Sleep */
				try 
				{
					Thread.sleep(sleepTime);
				} /* end try */
				catch (InterruptedException e) 
				{
					System.out.println("ListenerSpawner Interrupted from sleep");
				} /* end catch */
				
			} /* end if */
			
			
		} /* end while loop */
		
		/* Done. Clean up */		
		if (serverSocket != null)
		{
			try 
			{
				serverSocket.close();
			} /* end try */ 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} /* end catch */
			serverSocket = null;
		} /* end if */
		
	} /* end run method */
	
} /* end ListenerSpawner class */
