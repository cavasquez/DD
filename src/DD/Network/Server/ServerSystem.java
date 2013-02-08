package DD.Network.Server;

import java.util.ArrayList;

import DD.Chat.ChatSystem;
import DD.Network.Network;
import DD.Network.Message.Message;
import DD.Network.Message.NetworkMessage;
import DD.Network.Server.Interpreter.I_ChatMessage;
import DD.Network.Server.Interpreter.I_CombatMessage;
import DD.Network.Server.Interpreter.I_InitialMessage;
import DD.Network.Server.Interpreter.ServerInterpreter;

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
		
		/* TODO: get username from wherever it's made, and then:
		 * addUser(Network.GEM_USER_ID, username, null); */
	} /* end ServerSystem constructor */
	
	public static void interpret(int socketID, NetworkMessage message)
	{
		/* Assume all messages are of correct type and legally formatted */
		system[message.getType()].interpret(socketID, message);
	} /* end interpret */
	
	@Override
	public void sendMessage(int sender, int receiver, Message message)
	{
		/* Send message to everyone unless a receiver is specified */
		NetworkMessage send = new NetworkMessage(sender, receiver, message);
		ArrayList<User>users = userList.getUserList();
		
		if (receiver == Network.EVERYONE)
		{/* send message to everyone */
			for (User user : users)
			{/* don't send it back to the server. in fact, you can't because it has no Server thread. */
				if (user.socketID != Network.GM_USER_ID) (user.server).sendMessage(send);
			} /* end for loop */
		} /* end if */
		else
		{ /* send to specific client */
			
			for (User user : users)
			{
				if (user.socketID == receiver) (user.server).sendMessage(send);
			} /* end for loop */
		} /* end else */
	} /* end sendMessage method */
	
	public void sendMessage(int sender, int receiver, Message message, int exclude)
	{
		/* Send message to everyone except the excluded client (in such cases that client was originator) */
		NetworkMessage send = new NetworkMessage(sender, receiver, message);
		ArrayList<User>users = userList.getUserList();
		
		for (User user : users)
		{/* don't send it back to the server. in fact, you can't because it has no Server thread. */
			if (user.socketID != Network.GM_USER_ID && user.socketID != exclude) (user.server).sendMessage(send);
		} /* end for loop */

	} /* end sendMessage method */
	
	public boolean addUser(int socketID, String username, Server server)
	{
		return userList.addUser(socketID, username, server);
	} /* end addUser method */
	
	public boolean addServer(int socketID, Server server)
	{
		return addServer(socketID, server);
	} /* end addServer method */
	
	public boolean addUsername(int socketID, String username)
	{
		return userList.addUsername(socketID, username);
	} /* end addUsername */
	
	public boolean removeUser(int socketID)
	{
		boolean success = false;
		User user = userList.removeUser(socketID);
		
		if (user != null)
		{ /* successfully removed user from list. Terminate socket and kill thread. */
			success = true;
			user.server.close();
		} /* end if */
		
		/* if user = null then socketID never existed */
		
		return success;
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
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public static ServerSystem getInstance()
	{
		if (instance == null) instance = new ServerSystem();
		return instance;
	} /* end getInstance method */
	
	@Override
	public boolean getMessage(int socketID, NetworkMessage message)
	{
		boolean error = false;
		/* get message from a client. Check for validity and if valid, interpret. */
		if (validMessage(message.getType()) && message.getMessageType() == Message.NETWORK_MESSAGE) interpret(socketID, message);
		else error = true;
		
		return error;
	} /* end getMessage method */
	
	public UserTable getUserList()
	{
		return userList;
	} /* end getUserList */
	
} /* end ServerSystem class */
