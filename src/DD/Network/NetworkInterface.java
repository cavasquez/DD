package DD.Network;

import java.net.InetAddress;
import java.net.UnknownHostException;

import DD.Chat.ChatSystem;
import DD.CombatSystem.CombatSystem;
import DD.Message.Message;
import DD.Message.NetworkMessage;

/*****************************************************************************************************
* NetworkInterface will be an interface through which GameSystem, ChatSystem and any other System
* will be able to communicate with ClientSystem or ServerSystem. It will also specify the methods
* and attributes that ClientSystem and ServerSystem will need to support.
* 
* sendMessage() will be used by all the Systems to give NetworkSystem a message to send to the Client/
* Server.
* 
* getMessage() will be used by the Socket threads to give NetworkSystem a NetworkSystem to interpret
* and (if Server) send to all the other clients.
* 
* @author Carlos Vasquez
******************************************************************************************************/

public interface NetworkInterface
{  
	/************************************ Class Methods *************************************/
	/* Send message on part of some part of the system (ChatSystem, CombatSystem, etc.) */
	public abstract boolean sendMessage(int sender, int receiver, Message message);
	
	/* Get message from server or client */
	public abstract boolean getMessage(int listenerID, NetworkMessage message);
	
	/* Get the server or client to start listeners or senders */
	public abstract void start();
	
	/* Ideally for Server to stop ListenerSpawner */
	public abstract void stop();
	
	/* For both client and server to clean up */
	public abstract void terminate();
	
} /* end Network class  */
