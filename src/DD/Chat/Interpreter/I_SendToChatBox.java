package DD.Chat.Interpreter;

import DD.Chat.ChatBox;
import DD.Message.ChatMessage;

/*****************************************************************************************************
 * I_SendToChatBox will send the message to the ChatBox
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_SendToChatBox extends ChatInterpreter
{	
	@Override
	public void interpret(ChatMessage message)
	{
		ChatBox.getMessage(message);
		/* Assume ChatBox has been initialized */
	} /* end interpret method */
	
} /* end I_SendToChatBox class */
