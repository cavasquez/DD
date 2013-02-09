package DD.Network.Server;

import java.util.ArrayList;
import DD.Network.MessageQueue;
import DD.Network.Network;
import DD.Network.Message.Message;
import DD.Network.Message.NetworkMessage;
import DD.Network.Server.Interpreter.I_ChatMessage;
import DD.Network.Server.Interpreter.I_ClientListenerReadyMessage;
import DD.Network.Server.Interpreter.I_CombatMessage;
import DD.Network.Server.Interpreter.I_InitialMessage;
import DD.Network.Server.Interpreter.I_NewListenerMessage;
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
	/************************************ Class Attributes *************************************/
	private static ClientTable clientList;
	private static ServerInterpreter[] system = null;
	private static int clientID;						/* Unique ID to be assigned to clients */
	@SuppressWarnings("unused")
	private MessageQueue queue = null;					/* reference to MessageQueue thread. Will need to be cleaned up */
	
	/************************************ Class Methods *************************************/
	public ServerSystem() 
	{
		clientID = 0;
		
		system = new ServerInterpreter[Message.NUM_OF_MESSAGES];
		system[Message.COMBAT_MESSAGE] = new I_CombatMessage();
		system[Message.CHAT_MESSAGE] = new I_ChatMessage();
		system[Message.INITIAL_MESSAGE] = new I_InitialMessage();
		system[Message.NEW_LISTENER_MESSAGE] = new I_NewListenerMessage();
		system[Message.CLIENT_LISTENER_READY_MESSAGE] = new I_ClientListenerReadyMessage();
		
		system[Message.COMBAT_MESSAGE].setServerSystem(this);
		
		/* TODO: get username from wherever it's made, and then:
		 * addUser(Network.GEM_USER_ID, username, null); */
	} /* end ServerSystem constructor */
	
	public static void interpret(int listenerID, NetworkMessage message)
	{
		/* Assume all messages are of correct type and legally formatted.
		 * In any case, messages are always given by the ServerListener */
		system[message.getType()].interpret(listenerID, message);
	} /* end interpret */
	
	@Override
	public boolean sendMessage(int sender, int receiver, Message message)
	{
		/* Send message to everyone unless a receiver is specified. The sender
		 * and receiver are clientIDs */
		NetworkMessage send = new NetworkMessage(sender, receiver, message);
		ArrayList<Client>clients = clientList.getClientList();
		boolean sent = false;
		
		if (Message.messageExists(message))
		{
			if (receiver == Network.EVERYONE)
			{/* send message to everyone */
				for (Client client : clients)
				{/* don't send it back to the server. in fact, you can't because it has no Server thread. */
					if (client.clientID != Network.GM_USER_ID) (client.sender).sendMessage(send);
				} /* end for loop */
			} /* end if */
			else
			{ /* send to specific client */
				
				for (Client client : clients)
				{
					if (client.clientID == receiver) (client.sender).sendMessage(send);
				} /* end for loop */
			} /* end else */
			
			sent = true;
		} /* end if */
		
		return sent;
	} /* end sendMessage method */
	
	public boolean sendMessage(int sender, int receiver, Message message, int exclude)
	{
		/* Send message to everyone except the excluded client (in such cases that client was originator) */
		NetworkMessage send = new NetworkMessage(sender, receiver, message);
		ArrayList<Client>clients = clientList.getClientList();
		boolean sent = false;
		
		if (Message.messageExists(message))
		{
			for (Client client : clients)
			{/* don't send it back to the server. in fact, you can't because it has no Server thread. */
				if (client.clientID != Network.GM_USER_ID && client.clientID != exclude) (client.sender).sendMessage(send);
			} /* end for loop */
			sent = true;
		} /* if */
		
		return sent;
	} /* end sendMessage method */
	
	public boolean removeClient(int clientID)
	{
		boolean success = false;
		Client client = clientList.removeClient(clientID);
		
		if (client != null)
		{ /* successfully removed client from list. Terminate socket and kill thread. */
			success = true;
			client.sender.close();
			client.listener.close();
		} /* end if */
		
		/* if client = null then clientID never existed */
		
		return success;
	} /* end removeServer method */
	
	public static boolean validMessage(int type)
	{
		/* Check to see if the message is supported by the system. Used only for getMessage */
		boolean valid = false;
		
		if 
		(
			type == Message.COMBAT_MESSAGE ||
			type == Message.CHAT_MESSAGE ||
			type == Message.INITIAL_MESSAGE ||
			type == Message.NEW_LISTENER_MESSAGE ||
			type == Message.CLIENT_LISTENER_READY_MESSAGE
		)
		{
			valid = true;
		} /* end if */
		
		return valid;
	} /* end validMessage method */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	@Override
	public boolean getMessage(int listenerID, NetworkMessage message)
	{
		boolean error = false;
		/* get message from a client. Check for validity and if valid, interpret. */
		if (validMessage(message.getType()) && message.getMessageType() == Message.NETWORK_MESSAGE) interpret(listenerID, message);
		else error = true;
		
		return error;
	} /* end getMessage method */
	
	public ClientTable getClientList()
	{
		return clientList;
	} /* end getUserList */
	
	public int getNewClientID()
	{
		return clientID++;
	} /* end getNewClientID method */
			
	
} /* end ServerSystem class */
