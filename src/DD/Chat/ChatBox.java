package DD.Chat;

import DD.Message.ChatMessage;
import DD.SlickTools.Entity;

/*****************************************************************************************************
 * ChatBox will display all ChatMessages received by the client/server on the GUI. It will also keep
 * track of all previous messages so the user can go through older messages.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class ChatBox extends Entity
{
	/************************************ Class Methods *************************************/
	public ChatBox(int id) 
	{
		super(id);
		// TODO Auto-generated constructor stub
	} /* end ChatBox constructor */

	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public static void getMessage(ChatMessage cm)
	{/* get ChatMessage from ChatBox and process it. */
		//TODO: Carll render
	} /* end getMessage method */
	
} /* end ChatBox class */
