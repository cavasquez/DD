package DD.Message;

import java.io.Serializable;

/*****************************************************************************************************
 * Message will be used to subclass into various messages that will need to be sent through the network.
 * Providing this class will allow for ease of storage in the NetworkMessage.
 * 
 * There is a MessageType so that messages can easily be identified when sent across the network without
 * having to be casted and checked.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class Message implements Serializable
{
	private static final long serialVersionUID = 1L;
	/************************************ Class Constants *************************************/
	private static int I = 0;
	public static enum Type
	{
		COMBAT_MESSAGE(I++),							/* Message to be sent through the network that contains chat messages */
		CHAT_MESSAGE(I++),
		INITIAL_MESSAGE(I++),							/* First Message sent to client/server */
		NEW_LISTENER_MESSAGE(I++),						/* Message telling NetworkSystem there is a new listener*/
		ADD_USER_MESSAGE(I++),							/* Message sent when a new user joins the network */
		NETWORK_MESSAGE(I++),							/* Message that wraps messages to be sent through the network */
		COMBAT_VALIDATION_MESSAGE(I++),					/* Message returned by CombatSystem to ActionBox */ 
		QUEUE_MESSAGE(I++),								/* Message held by MessageQueue */
		CLIENT_LISTENER_READY_MESSAGE(I++),				/* Message telling the server ClientListener is up */
		CHOOSE_TARGET_MESSAGE(I++),						/* Message telling TargetingSystem to choose a target */
		TARGET_SELECTED_MESSAGE(I++);					/* Message telling an ability its targets*/
		
		
		public final int index;
		public static final int NUM_OF_MESSAGES = I;	/* Total number of messages */
		
		Type (int index)
		{
			this.index = index;
		} /* end TargetCount index */
		
	} /* end Action enum */
	
	/************************************ Class Attributes *************************************/
	protected Type messageType;
	
	/************************************ Class Methods *************************************/
	protected Message(Type messageType)
	{/* Message is protected so that no one can make a Message object.
	 	It exists soley to be subclassed. */
		this.messageType = messageType;
	} /* end Message constructor */
	
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public Type getMessageType()
	{
		return(messageType);
	} /* end getMessageType method */
	
} /* end Message class */
