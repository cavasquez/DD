package DD.Chat.Interpreter;

import DD.Chat.ChatSystem;
import DD.Message.ChatMessage;

/*****************************************************************************************************
 * ChatInterpreter will be used by ChatSystem to interpret every ChatMessage. 
 * 
 * The Interpreters will be named I_Action as an easy to follow scheme for what each action does.
 ******************************************************************************************************/

public abstract class ChatInterpreter 
{
	/************************************ Class Attributes *************************************/
	protected static ChatSystem system;
	
	/************************************ Class Methods *************************************/
	public void interpret(ChatMessage message) {}

} /* end ChatInterpreter class */
