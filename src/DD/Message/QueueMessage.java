package DD.Message;

/*****************************************************************************************************
* QueueMessage will be used by MessageQueue to hold the listenerID and NetworkMessag to be sent to
* the NetworkSystem being used.
* 
* @author Carlos Vasquez
******************************************************************************************************/

public class QueueMessage extends Message
{
	private static final long serialVersionUID = 1246287758811541644L;
	/************************************ Class Attributes *************************************/
	private int listenerID;
	private NetworkMessage message = null;
	
	/************************************ Class Methods *************************************/
	public QueueMessage(int listenerID, NetworkMessage message)
	{
		super(Type.QUEUE_MESSAGE);
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
