package DD.Network;

import DD.Message.Message;
import DD.Message.NetworkMessage;

/*****************************************************************************************************
* Network will be an interface through which GameSystem, ChatSystem and any other System
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
	public static final int GM_USER_ID = -2;	/* GM (Server) will always have an ID of -1 */
	public static final int SELF = -3;			/* Send message to self (for Clients) */
	public static final int PORT = 8080;		/* Port that ServerSocket connects to */	
	
	/************************************ Class Methods *************************************/
	/* Send message on part of some part of the system (ChatSystem, CombatSystem, etc.) */
	public boolean sendMessage(int sender, int receiver, Message message);
	
	/* Get messaeg from server or client */
	public boolean getMessage(int listenerID, NetworkMessage message);
	
} /* end Network class  */
