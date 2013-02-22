package DD.Chat.Interpreter;

import DD.Chat.ChatSystem;
import DD.Message.ChatMessage;

/*****************************************************************************************************
 * I_ProcessMessage will process the chat message, check for any special commands, and route them to the
 * appropriate Interpreter based on it's findings.
 * 
 * Currently, the commands allowed are:
 * slash whisper username message
 * slash w username message
 * 
 * slash roll #D# (case does not matter)
 * slash r #D# (case does not matter)
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_ProcessMessage extends ChatInterpreter
{
	@Override
	public void interpret(ChatMessage cm)
	{
		String message = cm.getMessage();
		Character firstChar = message.charAt(0);
		
		/* Check for the special character*/
		if ( firstChar.compareTo('/') == 0)
		{/* Special Character found */
			//TODO: take care of special cases
		} /* end if */
		else
		{/* No special character found. Send to ChatBox */
			cm.setAction(ChatSystem.SEND_TO_NETWORK_SYSTEM);
			system.process(cm);
		} /* end else */
		
	} /* end interpret method */
	
} /* end I_ProcesMessage */
