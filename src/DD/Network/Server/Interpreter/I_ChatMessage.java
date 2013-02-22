package DD.Network.Server.Interpreter;

import DD.Chat.ChatSystem;
import DD.Message.ChatMessage;
import DD.Message.NetworkMessage;
import DD.Network.Network;

/*****************************************************************************************************
 * I_ChatMessage will be used to interpret and process all ChatMessages sent to the Server
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_ChatMessage extends ServerInterpreter
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
	public void interpret (int listenerID, NetworkMessage message) 
	{
		/* Check the receiver and send it to the appropriate clients */
		ChatMessage cm = (ChatMessage) message.getMessage();
		
		if (cm.getReceiver() == Network.GM_USER_ID)
		{ /* Message should to GM (Servers ChatSystem) thus it does
		 	not go through the NetworkSystem and should just go
		 	straight to ChatSystem. */
			chat.process(cm);
			
		} /* end if */
		else
		{ /* Message should go to a particular person or everyone.
		 	Regardless, sendMessage knows how to deal with it. */
			system.sendMessage(cm.getSender(), cm.getReceiver(), cm);
			chat.process(cm);
			
		} /* end else if */

	} /* end interpret method */

} /* end I_ChatMessage */
