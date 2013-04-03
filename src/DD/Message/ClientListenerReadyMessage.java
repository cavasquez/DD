package DD.Message;

/*****************************************************************************************************
 * ClientListenerReadyMessage is a message sent strictly by clients to the server telling the server
 * that the Client is ready to have a ServerSender connect to it.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class ClientListenerReadyMessage extends Message
{
	private static final long serialVersionUID = 1025917258358821686L;
	/************************************ Class Attributes *************************************/
	protected int playerID;
	
	/************************************ Class Methods *************************************/
	public ClientListenerReadyMessage(int playerID)
	{
		super(Type.CLIENT_LISTENER_READY_MESSAGE);
		this.playerID = playerID;
	} /* end ClietnListenerReadyMessage constructor */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public int getPlayerID()
	{
		return playerID;
	} /* end getPlayerID method */
	
} /* end ClientListenerReadyMessage */
