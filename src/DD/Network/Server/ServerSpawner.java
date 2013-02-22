package DD.Network.Server;

import java.io.IOException;
import java.net.ServerSocket;

/*****************************************************************************************************
 * ServerSpawner will be responsible for creating ServerSocket's for every client while in the lobby.
 * It should be terminated after leaving the lobby.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class ServerSpawner extends Thread
{
	/************************************ Class Constants *************************************/
	public static final int port = 8080;
	
	/************************************ Class Attributes *************************************/
	private ServerSocket serverSocket = null;
	private boolean acceptingConnections;
	private boolean closed;
	
	/************************************ Class Methods *************************************/
	public ServerSpawner()
	{
		super("ServerSpawnerThread");
		acceptingConnections = true;
		closed = false;
	} /* end ServerSpawner */
	
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
				} /* end catch */
				
			} /* end acceptingConnections */
			
		} /* end while loop */
		
	} /* end run method */
	
} /* end ServerSpawner class */
