package DD.Network.Message;


/*****************************************************************************************************
* AddUserMessage is a message send by the Server to the client informing it that there is a new peer
* in the network. It contains the username of the peer and its associated playerID
******************************************************************************************************/

public class AddUserMessage extends Message
{
	/************************************ Class Attributes *************************************/
	private int playerID;
	private String username;
	
	/************************************ Class Methods *************************************/
	public AddUserMessage(int playerID, String username) 
	{
		super(Message.ADD_USER_MESSAGE);
		this.playerID = playerID;
		this.username = username;
	} /* end AddUserMessage constructor */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public int getPlayerID()
	{
		return playerID;
	} /* end getPlayerID method */
	
	public String getUsername()
	{
		return username;
	} /* end getUsername method */
	
} /* end AddUserMessage class */
