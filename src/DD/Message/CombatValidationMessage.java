package DD.Message;

/*****************************************************************************************************
 * CombatValidation will be the message returned by CombatSystem to ActionBox to let ActionBox know 
 * if the action it requested is valid. If it is, the message will then be sent through the network.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class CombatValidationMessage extends Message
{
	private static final long serialVersionUID = 517568380650625414L;
	/************************************ Class Attributes *************************************/
	private boolean valid;		/* flag to tell ActionBox if the requested action and message is valid */
	private String error;		/* String with description of error if there is an error */ 
	
	/************************************ Class Methods *************************************/
	public CombatValidationMessage(boolean valid, String error)
	{
		super(Type.COMBAT_VALIDATION_MESSAGE);
		this.valid = valid;
		this.error = error;
		
	} /* end CombatValidationMessage Constructor */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public boolean getValid()
	{
		return(this.valid);
	} /* end getValid method */
	
	public String getError()
	{
		return(this.error);
	} /* end getError method */
	
} /* end CombatValidationMessage class */
