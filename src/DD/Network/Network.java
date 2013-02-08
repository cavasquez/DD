package DD.Network;

import java.util.ArrayList;

import DD.Chat.ChatSystem;

/*****************************************************************************************************
* Networkwill be an interface through which GameSystem, ChatSystem and any other System
* will be able to communicate with ClientSystem or ServerSystem. It will also specify the methods
* and attributes that ClientSystem and ServerSystem will need to support.
* 
* sendMessage() will be used by all the Systems to give NetworkSystem a message to send to the Client/
* Server.
* 
* getMessage() will be used by the Socket threads to give NetworkSystem a NetworkSystem to interpret
* and (if Server) send to all the other clients.
******************************************************************************************************/

public interface Network
{  
	/************************************ Class Constants *************************************/
	public static final int EVERYONE = -1;		/* a message sent to EVERYONE will be to -1 */
	public static int GM_USER_ID = -2;			/* GM (Server) will always have an ID of -1 */
	
	/************************************ Class Methods *************************************/
	public void sendMessage(int sender, int receiver, Message message);
	public boolean getMessage(int socketID, NetworkMessage message);
	
} /* end Network class  */
