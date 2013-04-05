package DD.GUI.InputBox;

import DD.SlickTools.BoxInterface;
import DD.SlickTools.ImageRenderComponent;

/*****************************************************************************************************
 * BoxInterface will take in user input, display it, and allow for it to be retrieved.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class InputBox extends BoxInterface
{
	/************************************ Class Attributes *************************************/
	protected String input;		/* users input */
	protected boolean focus;	/* flag to check for focus */
	
	/************************************ Class Methods *************************************/
	public InputBox(int id, float length, float width) 
	{
		super(id, length, width);
		focus = false;
		input = "";
		this.addComponent(new InputListener());
		this.addComponent(new Focus());
		
	} /* end InputBox constructor */
	
	/****************************************************************************************
	 ************************************ Setter Methods ************************************
	 ****************************************************************************************/
	public void clearInput()
	{
		input = "";
	} /* end clearInput method */
	
	/****************************************************************************************
	 ************************************ Getter Methods ************************************
	 ****************************************************************************************/
	public String getInput()
	{
		return input;
	} /* end getInput method */

} /* end InputBox method */
