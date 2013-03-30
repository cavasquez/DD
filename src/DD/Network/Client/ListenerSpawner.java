package DD.Network.Client;

import java.io.IOException;
import java.net.ServerSocket;

import DD.Network.Network;

/*****************************************************************************************************
 * ListenerSpawner will wait for a connection from the Server.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class ListenerSpawner extends Thread
{
	/************************************ Class Constants *************************************/
	private static final int sleepTime = 1000;
	
	/************************************ Class Attributes *************************************/
	private ServerSocket serverSocket = null;
	private boolean socketReady;
	
	/************************************ Class Methods *************************************/
	public ListenerSpawner()
	{
		super("ListenerSpawnerThread");
		socketReady = false;
	} /* end ListenerSpawner constructor */
	
	public void run()
	{
		ServerSocket serverSocket = null;
		try 
		{
			serverSocket = new ServerSocket(Network.CLIENT_PORT);
			System.out.println("successfully opeened port " + serverSocket.getLocalPort());
		} /* end try */
		catch (IOException e) 
		{
			System.out.println("Failed to open port");
		} /* end catch */
		
		if (serverSocket != null)
		{
			socketReady = true;
			ClientListener listener = null;
			try 
			{
				listener = new ClientListener(serverSocket.accept());
				System.out.println("sucecssfully listened");
				listener.start();
			}  /* end try */
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} /* end catch */
		} /* end if */
		
	} /* end run method */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public boolean getSocketReady()
	{
		return socketReady;
	} /* end getSocketReady method */
	
} /* end ListenerSpawner class */
