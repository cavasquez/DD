package DD.Network.Server;

import java.util.ArrayList;

import DD.Chat.ChatSystem;
import DD.Network.Message;
import DD.Network.Network;
import DD.Network.NetworkMessage;
import DD.Network.NetworkSystem;
import DD.Network.Server.Interpreter.*;

/*****************************************************************************************************
 * ServerSystem will be in charge of routing the messages received by the server and distributing
 * them to all the clients(except for the sender).
 * 
 * For the servers sake, it will be a singleton to choke entry and retain knowledge of Sockets.
 * 
 * The server will use interpreters which will hold the server logic for different kinds of messages.
 ******************************************************************************************************/

public class ServerSystem implements Network
{
	/************************************ Class Constants *************************************/
	private static volatile ServerSystem instance = null;
	
	/************************************ Class Attributes *************************************/
	private static volatile UserTable userList;
	private static volatile ServerInterpreter[] system = null;
	
	/************************************ Class Methods *************************************/
	private ServerSystem() 
	{
		system = new ServerInterpreter[Message.NUM_OF_MESSAGES];
		system[Message.COMBAT_MESSAGE] = new I_CombatMessage();
		system[Message.CHAT_MESSAGE] = new I_ChatMessage();
		system[Message.INITIAL_MESSAGE] = new I_InitialMessage();
		
	} /* end ServerSystem constructor */
	
	@Override
	public void sendMessage(int sender, int receiver, Message message)
	{
		/* A system needs a message sent to all clients */
		/* Wrap message in a NetworkMessage */
		NetworkMessage send = new NetworkMessage(sender, receiver, message);
		ArrayList<User>users = userList.getUserList();
		
		if (receiver == Network.EVERYONE)
		{/* send message to everyone */
			for (User user : users)
			{/* don't send it back to the server. in fact, you can't because it has no Server thread. */
				if (user.playerID != Network.GM_USER_ID)(user.server).sendMessage(send);
			} /* end for loop */
		} /* end if */
		else
		{ /* send to specific client */
			
			for (User user : users)
			{
				if (user.playerID == receiver) (user.server).sendMessage(send);
			} /* end for loop */
		} /* end else */
	} /* end sendMessage method */
	
	public boolean addUser(int serverID, String username, Server server)
	{
		return userList.addUser(serverID, username, server);
	} /* end addUser method */
	
	public boolean addServer(int serverID, Server server)
	{
		return addServer(serverID, server);
	} /* end addServer method */
	
	public boolean addUsername(int serverID, String username)
	{
		return userList.addUsername(serverID, username);
	} /* end addUsername */
	
	public boolean removeUser(int serverID)
	{
		return userList.removeUser(serverID);
	} /* end removeServer method */
	
	public static boolean validMessage(int type)
	{
		/* Check to see if the message is supported by the system */
		boolean valid = false;
		
		if 
		(
			type == Message.COMBAT_MESSAGE ||
			type == Message.CHAT_MESSAGE ||
			type == Message.INITIAL_MESSAGE
		)
		{
			valid = true;
		} /* end if */
		
		return valid;
	} /* end validMessage method */
	
	public static void interpret(int serverID, NetworkMessage message)
	{
		/* Assume all messages are of correct type and legally formatted */
		system[message.getType()].interpret(serverID, message);
	} /* end interpret */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public static ServerSystem getInstance()
	{
		if (instance == null) instance = new ServerSystem();
		return instance;
	} /* end getInstance method */
	
	@Override
	public boolean getMessage(int serverID, NetworkMessage message)
	{
		boolean error = false;
		/* get message from a client. Check for validity and if valid, interpret. */
		if (validMessage(message.getType())) interpret(serverID, message);
		else error = true;
		
		return error;
	} /* end getMessage method */
	
} /* end ServerSystem class */
