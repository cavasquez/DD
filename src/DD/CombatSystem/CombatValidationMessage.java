package DD.CombatSystem;

import DD.Network.Message.Message;

/*****************************************************************************************************
 * CombatValidation will be the message returned by CombatSystem to ActionBox to let ActionBox know 
 * if the action it requested is valid. If it is, the message will then be sent through the network.
 ******************************************************************************************************/

public class CombatValidationMessage extends Message
{
	/************************************ Class Attributes *************************************/
	private boolean valid;		/* flag to tell ActionBox if the requested action and message is valid */
	private String error;		/* String with description of error if there is an error */ 
	
	/************************************ Class Methods *************************************/
	public CombatValidationMessage(boolean valid, String error)
	{
		super(Message.COMBAT_VALIDATION_MESSAGE);
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
