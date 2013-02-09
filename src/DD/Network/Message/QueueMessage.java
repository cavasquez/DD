package DD.Network.Message;

/*****************************************************************************************************
* QueueMessage will be used by MessageQueue to hold the listenerID and NetworkMessag to be sent to
* the NetworkSystem being used.
******************************************************************************************************/

public class QueueMessage extends Message
{
	/************************************ Class Attributes *************************************/
	private int listenerID;
	private NetworkMessage message = null;
	
	/************************************ Class Methods *************************************/
	public QueueMessage(int listenerID, NetworkMessage message)
	{
		super(QUEUE_MESSAGE);
		this.listenerID = listenerID;
		this.message = message;
	} /* end QueueMessage constructor */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public int getListenerID()
	{
		return listenerID;
	} /* end getLIstenerID method */
	
	public NetworkMessage getMessage()
	{
		return message;
	} /* end getMessage method */
	
} /* end QueueMessage class */
