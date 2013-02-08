package DD.Chat;

import java.util.ArrayList;

import DD.Network.UserTable;
import DD.Network.Server.Server;

/*****************************************************************************************************
 * ChatSystem will take in ChatMessages from the MessageBox, interpret it, and route it through the 
 * network or will receive a ChatMessage and send it to the ChatBox to display. 
 * 
 * Having a ChatSystem will allow us to extend functionality by adding such things as /whisper or
 * similar features.
 * 
 * ChatSystem will be a singleton to provide a single point of entry (mostly for the server).
 ******************************************************************************************************/

public class ChatSystem 
{
	/************************************ Class Constants *************************************/
	private static volatile ChatSystem instance = null;
		
	/************************************ Class Methods *************************************/
	private ChatSystem() {};
	
	public static ChatSystem getinstance()
	{
		if (instance == null) instance = new ChatSystem();
		return instance;
	} /* end getInstance method */
	
} /* end ChatSystem */
