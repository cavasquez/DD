package DD.Network.Client.Interpreter;

import DD.Chat.ChatSystem;
import DD.Message.ChatMessage;
import DD.Message.NetworkMessage;

/*****************************************************************************************************
 * I_ChatMessage will pass combat messages to ChatSystem
 ******************************************************************************************************/

public class I_ChatMessage extends ClientInterpreter
{
	/************************************ Class Attributes *************************************/
	ChatSystem chat = null;
	
	/************************************ Class Methods *************************************/
	public I_ChatMessage()
	{
		chat = new ChatSystem();
		/* Assume that ChatBox and MessageBox have already been made and set */
	} /* end ChatMessage constructor */
	
	@Override
	public void interpret(NetworkMessage message)
	{
		ChatMessage cm = (ChatMessage) message.getMessage();
		chat.process(cm);
	} /* end Interpret method */
	
} /* end I_ChatMessage class */
