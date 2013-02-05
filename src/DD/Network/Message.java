package DD.Network;

/*****************************************************************************************************
 * Message will be used to subclass into various messages that will need to be sent through the network.
 * Providing this class will allow for ease of storage in the NetworkMessage.
 ******************************************************************************************************/

public class Message 
{
	/************************************ Class Constants *************************************/
	public static final int NETWORK_MESSAGE = 0;
	public static final int COMBAT_MESSAGE = 1;
	public static final int CHAT_MESSAGE = 2;
	
	/************************************ Class Attributes *************************************/
	protected int messageType;
	
	/************************************ Class Methods *************************************/
	/* No constructor is provided because Message should never be made. */
	
} /* end Message class */
