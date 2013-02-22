package DD.Chat;

import DD.Message.ChatMessage;
import DD.Network.Network;
import DD.Network.NetworkSystem;
import DD.SlickTools.Entity;

/*****************************************************************************************************
 * MessageBox will be the box through which the player will communicate (chat) with the rest of the
 * players. It is strictly input and will send the message to ChatSystem for interpretation and
 * network access.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class MessageBox extends Entity
{
	/************************************ Class Methods *************************************/
	private NetworkSystem network = null;
	private ChatSystem chat = null;
	
	/************************************ Class Methods *************************************/
	public MessageBox(int id) 
	{
		super(id);
		network = new NetworkSystem();
		chat = new ChatSystem();
		// TODO: these systems should be passed in by the starting process
		
	} /* end MessageBox constructor */

	public void sendMessage(String message)
	{/* Get user input and send the message wrapped in ChatMessage to ChatSystem */
		ChatMessage cm = new ChatMessage(network.getUserID(), Network.EVERYONE, ChatSystem.PROCESS_MESSAGE, message);
		chat.process(cm);
		
	} /* end sendMessage method */
	
} /* end MessageBox method */
