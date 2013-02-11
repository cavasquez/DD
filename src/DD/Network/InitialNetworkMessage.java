package DD.Network;

/*****************************************************************************************************
 * InitialNetworkMessage is the first message sent to the server or the client and validates if the 
 * client has a valid username.
 ******************************************************************************************************/

public class InitialNetworkMessage extends Message
{
	/************************************ Class Attributes *************************************/
	private String username;
	private boolean valid; 
	
	/************************************ Class Methods *************************************/
	public InitialNetworkMessage(String username, boolean valid)
	{
		super(Message.INITIAL_MESSAGE);
		this.username = username;
		this.valid = valid;
		
	} /* end InitialNetworkMessage constructor */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public String getUsername()
	{
		return username;
	} /* end getUsername method */
	
	public boolean getValid()
	{	
		return valid;
	} /* end getValid method */
	
} /* end InitialNetworkMessage */
