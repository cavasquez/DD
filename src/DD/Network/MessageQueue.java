package DD.Network;

import java.util.LinkedList;
import java.util.Queue;
import DD.Message.NetworkMessage;
import DD.Message.QueueMessage;

/*****************************************************************************************************
* MessageQueue will be a singleton that interacts with ServerSystem to give the ServerSystem 
* new messages from the ServerListeners. 
* 
* The purpose for this implementation is to control access to the ServerSystem without making 
* ServerSystem into a singleton (which is mused more frequently by other processes in the same thread),
* while maintaining control of messages. ServerListeners will give MessageQueue their new messages
* and MessageQueue will keep pushing these messages to ServerSystem until it's Queue is empty. 
* 
* After the Queue is empty, MessageQueue will wait.
* 
* I justify my use of singleton with little protection by stating that the only resource being shared 
* is the messageQueue queue. No outsider can remove from it, only add to it. This makes the harm to data
* impossible?
* 
* @author Carlos Vasquez
******************************************************************************************************/

public class MessageQueue extends Thread
{
	/************************************ Class Constants *************************************/
	public static final int sleepTime = 100;		/* Thread sleeps for the provided milliseconds */  
	
	/************************************ Class Attributes *************************************/
	private NetworkSystem system = null;
	private volatile Queue<QueueMessage> messageQueue = null;
	private boolean hasMessage;
	private boolean done;
	private static MessageQueue instance = null;
	private QueueMessage message = null;
	
	/************************************ Class Methods *************************************/
	private MessageQueue()
	{
		super("MessageQueue Thread");
		system = new NetworkSystem();
		messageQueue = new LinkedList<QueueMessage>();
		hasMessage = false;
		done = false;
	} /* end MessageQueue constructor */
	
	public static synchronized MessageQueue getInstance()
	{
		if (instance == null) instance = new MessageQueue();
		return instance;
	} /* end getOmstamce method */
	
	public void run()
	{
		
		while(!done )
		{
			try
			{
				while(sendMessage()) {} /* loop through the queue and send the message */			
			} /* end try */
			catch(NullPointerException e)
			{
				
			} /* end catch */			
			
			hasMessage = false;	/* flag to check that queue has message */
			try 
			{
				Thread.sleep(sleepTime);
			} /* end try */
			catch (InterruptedException e) 
			{
				if (!hasMessage)
				{/* was interrupted for reasons other than enqueueing message */
					e.printStackTrace();
				} /* end if */
				
			} /* end catch */
			
		} /* end done loop */
		
		/* Done. Clean up */
		message = null;
		messageQueue = null;
		instance = null;
		system = null;
	
	} /* end run method */
	
	private synchronized boolean sendMessage()
	{
		/* sendMessage provides a way for MessQueue to synchronize access to the queue
		 * and send a message to the NetworkSystem */
		boolean returner = false;
		if ((message = messageQueue.poll()) != null)
		{
			/* If there is a message to be send, tell the Network and then respond with true. */
			system.getMessage(message.getListenerID(), message.getMessage());
			returner = true;
		} /* end if */
		return returner;
	} /* end sendMessage method */
	
	public synchronized void enqueuMessage(int listenerID, NetworkMessage message)
	{
		/* enqueueMessage synchronizes access to the queue and provides threads a way
		 * of putting messages into the queue */
		messageQueue.offer(new QueueMessage(listenerID, message));
		hasMessage = true;
	} /* end enqueueMessage method */
	
	public void close()
	{
		done = true;
	} /* end close method */
	
	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/	
	public void setNetworkSystem(NetworkSystem system)
	{
		this.system = system;
	} /* end setNetworkSystem method */
	
} /* end MessageQueue class */
