package DD.Network;

/*****************************************************************************************************
 * Message will be used to subclass into various messages that will need to be sent through the network.
 * Providing this class will allow for ease of storage in the NetworkMessage.
 * 
 * There is a MessageType so that messages can easily be identified when sent across the network without
 * having to be casted and checked.
 ******************************************************************************************************/

public class Message 
{
	/************************************ Class Constants *************************************/
	public static final int NETWORK_MESSAGE = 0;	/* Message that wraps messages to be sent through the network */
	public static final int COMBAT_MESSAGE = 1;		/* Message to be given to CombatSystem */
	public static final int CHAT_MESSAGE = 2;		/* Message to be sent through the network that contains chat messages */
	public static final int COMBAT_VALIDATION_MESSAGE = 4;	/* Message returned by CombatSystem to ActionBox */ 
	
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
	
} /* end Message class */
