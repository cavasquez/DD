package DD.Network;

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
	public static final int NETWORK_MESSAGE = I++;				/* Message that wraps messages to be sent through the network */
	public static final int COMBAT_MESSAGE = I++;				/* Message to be given to CombatSystem */
	public static final int CHAT_MESSAGE = I++;					/* Message to be sent through the network that contains chat messages */
	public static final int COMBAT_VALIDATION_MESSAGE = I++;	/* Message returned by CombatSystem to ActionBox */ 
	public static final int INITIAL_MESSAGE = I++;				/* First Message sent to client/server */
	public static final int NUM_OF_MESSAGES = I;				/* Total number of messages */
	
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
