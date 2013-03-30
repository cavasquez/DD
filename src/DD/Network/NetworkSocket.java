package DD.Network;

import java.net.Socket;
import DD.Message.NetworkMessage;

/*****************************************************************************************************
* NetworkSocket will contain the basic tools that Listener and Client will be using. It extends Thread
* so that Listener can be threaded.
* 
* @author Carlos Vasquez
******************************************************************************************************/

public abstract class NetworkSocket extends Thread
{
	/************************************ Class Constants *************************************/
	public static final int sleepTime = 200;	/* Time for which thread will be asleep */
	
	/************************************ Class Attributes *************************************/
	protected Socket socket = null;
	protected int socketID;						/* Unique ID for thread */
	protected boolean done;						/* Thread is done */
	protected boolean working;					/* flag stating that socket is working */
	protected NetworkMessage message = null;	
	protected static volatile int nextID = 0;	/* keeps track of the next id */
	
	/************************************ Class Methods *************************************/
	public NetworkSocket(Socket socket)
	{
		super("DDSocketThread" + Integer.toHexString(NetworkSocket.nextID++));
		this.socketID = nextID-1;
		this.socket = socket;
		this.done = false;
		this.working = true;
	} /* end Server constructor */
	
	public final void close()
	{
		/* Kill thread */
		done = true;
		closeStreams();
	} /* end close method */
	
	public abstract void run();
	
	protected abstract void createStreams();

	protected abstract void closeStreams();

	
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public int getID()
	{
		return socketID;
	} /* end getID method */
	
} /* end NetworkSocket class */
