package DD.Message;

/*****************************************************************************************************
 * Message will be used to subclass into various messages that will need to be sent through the network.
 * Providing this class will allow for ease of storage in the NetworkMessage.
 * 
 * There is a MessageType so that messages can easily be identified when sent across the network without
 * having to be casted and checked.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class Message 
{
	/************************************ Class Constants *************************************/
	private static int I = 0;
	public static enum Type
	{
		NETWORK_MESSGE(I++),
		COMBAT_MESSAGE(I++),
		COMBAT_VALIDATION_MESSAGE(I++),
		INITIAL_MESSAGE(I++),
		ADD_USER_MESSAGE(I++),
		QUEUE_MESSAGE(I++),
		NEW_LISTENER_MESSAGE(I++),
		CLIENT_LISTENER_READY_MESSAGE(I++),
		CHOOSE_TARGET_MESSAGE(I++),
		TARGET_SELECTED_MESSAGE(I++),
		CHAT_MESSAGE(I++);
		
		public final int index;
		public static final int NUM_OF_MESSAGES= I;
		
		Type (int index)
		{
			this.index = index;
		} /* end TargetCount index */
		
	} /* end Action enum */
	public static final int NETWORK_MESSAGE = 0;				/* Message that wraps messages to be sent through the network */
	public static final int COMBAT_MESSAGE = 1;					/* Message to be given to CombatSystem */
	public static final int CHAT_MESSAGE = 2;					/* Message to be sent through the network that contains chat messages */
	public static final int COMBAT_VALIDATION_MESSAGE = 3;		/* Message returned by CombatSystem to ActionBox */ 
	public static final int INITIAL_MESSAGE = 4;				/* First Message sent to client/server */
	public static final int ADD_USER_MESSAGE = 5;				/* Message sent when a new user joins the network */
	public static final int QUEUE_MESSAGE = 6;					/* Message held by MessageQueue */
	public static final int NEW_LISTENER_MESSAGE = 7;			/* Message telling NetworkSystem there is a new listener*/
	public static final int CLIENT_LISTENER_READY_MESSAGE = 8;	/* Message telling the server ClientListener is up */
	public static final int CHOOSE_TARGET_MESSAGE = 9;			/* Message telling TargetingSystem to choose a target */
	public static final int TARGET_SELECTED_MESSAGE = 10;		/* Message telling an ability its targets*/
	public static final int NUM_OF_MESSAGES = 11;				/* Total number of messages */
	
	/************************************ Class Attributes *************************************/
	protected int messageType;
	
	/************************************ Class Methods *************************************/
	protected Message(int messageType)
	{/* Message is protected so that no one can make a Message object.
	 	It exists soley to be subclassed. */
		this.messageType = messageType;
	} /* end Message constructor */
	
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public int getMessageType()
	{
		return(messageType);
	} /* end getMessageType method */
	
	public static boolean messageExists(Message message)
	{
		boolean exists = false;
		int messageType = message.getMessageType();
		if (messageType > 0 && messageType < NUM_OF_MESSAGES) exists = true;
		
		return exists;
	} /* end messageExists method */
	
} /* end Message class */
