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
	/************************************ Class Attributes *************************************/
	public static final int sleepTime = 100;		/* Thread sleeps for the provided milliseconds */  
	
	/************************************ Class Attributes *************************************/
	private NetworkSystem system = null;
	private Queue<QueueMessage> messageQueue = null;
	private boolean hasMessage;
	private boolean done;
	private static MessageQueue instance = null;
	
	/************************************ Class Methods *************************************/
	private MessageQueue()
	{
		super("MessageQueue Thread");
		system = new NetworkSystem();
		messageQueue = new LinkedList<QueueMessage>();
		hasMessage = false;
		done = false;
	} /* end MessageQueue constructor */
	
	public static MessageQueue getInstance()
	{
		if (instance == null) instance = new MessageQueue();
		return instance;
	} /* end getOmstamce method */
	
	public void run()
	{
		QueueMessage message = null;
		while(!done )
		{
			while((message = messageQueue.poll()) != null)
			{
				system.getMessage(message.getListenerID(), message.getMessage());
			} /* end loop for hasMessage */
			
			hasMessage = false;	/* flag to check that queue has message */
			try {
				sleep(sleepTime);
			} /* end try */
			catch (InterruptedException e) 
			{
				if (!hasMessage)
				{/* was interrupted for reasons other than enqueueing message */
					e.printStackTrace();
				} /* end if */
				
			} /* end catch */
			
		} /* end done loop */
		
	} /* end run method */
	
	public void enqueuMessage(int listenerID, NetworkMessage message)
	{
		messageQueue.offer(new QueueMessage(listenerID, message));
		hasMessage = true;
	} /* end enqueueMessage method */
	
} /* end MessageQueue class */
