package DD.Message;

/*****************************************************************************************************
 * InitialMessage is the first message sent to the server or the client and validates if the 
 * client has a valid username.
 ******************************************************************************************************/

public class InitialMessage extends Message
{
	/************************************ Class Attributes *************************************/
	private String username;
	private boolean valid; 
	private Integer id;
	
	/************************************ Class Methods *************************************/
	public InitialMessage(String username, boolean valid, Integer id)
	{
		super(Message.INITIAL_MESSAGE);
		this.username = username;
		this.valid = valid;
		this.id = id;
		
	} /* end InitiaMessage constructor */
	
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
	
	public Integer getID()
	{
		return id;
	} /* end getID method */
	
} /* end InitialMessage */
