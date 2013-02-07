package DD.Network;

/*****************************************************************************************************
 * NetworkMessage will be used to pass messages across the network. It will contain a message that
 * should be sent to the Server who will in turn send the message to all the clients excluding the 
 * sender. The message contained in NetworkMessage will either be CombatMessage or ChatMessage. The
 * Message type should be declared in the message so that it can be easily routed on the Server and 
 * Client side.
 ******************************************************************************************************/

public class NetworkMessage extends Message
{
	/************************************ Class Attributes *************************************/
	private Message message;
	
	/************************************ Class Methods *************************************/
	public NetworkMessage(Message message)
	{
		super(Message.NETWORK_MESSAGE);
		this.message = message;
	} /* end Constructor */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public int getType()
	{
		return message.getMessageType();
	} /* end getType */
	
	public Message getMessage()
	{
		return message;
	} /* end getMessage method */
	
} /* end NetworkMessage */
