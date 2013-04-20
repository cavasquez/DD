package DD.Network.Server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import DD.Chat.ChatSystem;
import DD.CombatSystem.CombatSystem;
import DD.Message.Message;
import DD.Message.NetworkMessage;
import DD.Network.MessageQueue;
import DD.Network.Network;
import DD.Network.NetworkInterface;
import DD.Network.Client.ClientSystem;
import DD.Network.Server.Interpreter.I_ChatMessage;
import DD.Network.Server.Interpreter.I_ClientListenerReadyMessage;
import DD.Network.Server.Interpreter.I_CombatMessage;
import DD.Network.Server.Interpreter.I_InitialMessage;
import DD.Network.Server.Interpreter.I_NewListenerMessage;
import DD.Network.Server.Interpreter.ServerInterpreter;

/*****************************************************************************************************
 * ServerSystem will be in charge of routing the messages received by the server and distributing
 * them to all the clients. It will also accept messages from the client and route them to all the peers
 * except the sender client. 
 * 
 * The server will use interpreters which will hold the server logic for different kinds of messages.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class ServerSystem extends Network implements NetworkInterface 
{	
	/************************************ Class Attributes *************************************/
	private ClientTable clientList;
	private ServerInterpreter[] system = null;
	private int clientID;						/* Unique ID to be assigned to clients */
	private ListenerSpawner spawn;
	
	/************************************ Class Methods *************************************/
	public ServerSystem() 
	{
		clientID = 0;
		clientList = new ClientTable();
		system = new ServerInterpreter[Message.Type.NUM_OF_MESSAGES];
		system[Message.Type.COMBAT_MESSAGE.index] = new I_CombatMessage();
		system[Message.Type.CHAT_MESSAGE.index] = new I_ChatMessage();
		system[Message.Type.INITIAL_MESSAGE.index] = new I_InitialMessage();
		system[Message.Type.NEW_LISTENER_MESSAGE.index] = new I_NewListenerMessage();
		system[Message.Type.CLIENT_LISTENER_READY_MESSAGE.index] = new I_ClientListenerReadyMessage();
		
		ServerInterpreter.setServerSystem(this);
		
		/* TODO: get username from wherever it's made, and then:
		 * addClient(Network.GEM_USER_ID, username, null); */
	} /* end ServerSystem constructor */
	
	private void interpret(int listenerID, NetworkMessage message)
	{
		/* Assume all messages are of correct type and legally formatted.
		 * In any case, messages are always given by the ServerListener */
		system[message.getType().index].interpret(listenerID, message);
	} /* end interpret */
	
	@Override
	public boolean sendMessage(int sender, int receiver, Message message)
	{
		/* Send message to everyone unless a receiver is specified. The sender
		 * and receiver are clientIDs */
		NetworkMessage send = new NetworkMessage(sender, receiver, message);
		ArrayList<Client>clients = clientList.getClientList();
		boolean sent = false;
		
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
				System.out.println("clientID" + client.clientID + " sender " + client.sender);
				if (client.clientID == receiver) (client.sender).sendMessage(send);
			} /* end for loop */
		} /* end else */
		
		sent = true;
	
		return sent;
	} /* end sendMessage method */
	
	public boolean sendMessage(int sender, int receiver, Message message, int exclude)
	{
		/* Send message to everyone except the excluded client (in such cases that client was originator) */
		NetworkMessage send = new NetworkMessage(sender, receiver, message);
		ArrayList<Client>clients = clientList.getClientList();
		boolean sent = false;
		
		for (Client client : clients)
		{/* don't send it back to the server. in fact, you can't because it has no Server thread. */
			if (client.clientID != Network.GM_USER_ID && client.clientID != exclude) (client.sender).sendMessage(send);
		} /* end for loop */
		sent = true;

		return sent;
	} /* end sendMessage method */
	
	public boolean validMessage(int type)
	{
		/* Check to see if the message is supported by the system. Used only for getMessage */
		boolean valid = false;
		
		if 
		(
			type == Message.Type.COMBAT_MESSAGE.index ||
			type == Message.Type.CHAT_MESSAGE.index ||
			type == Message.Type.INITIAL_MESSAGE.index ||
			type == Message.Type.NEW_LISTENER_MESSAGE.index ||
			type == Message.Type.CLIENT_LISTENER_READY_MESSAGE.index
		)
		{
			valid = true;
		} /* end if */
		
		return valid;
	} /* end validMessage method */
	
	@Override
	public void start() 
	{
		/* Start listening for new senders from clients. */
		spawn = new ListenerSpawner();
		spawn.startAccepting();
		spawn.start();
		MessageQueue.getInstance().start();
		
	} /* end start method */

	@Override
	public void stop() 
	{
		/* Stop listening for new senders from clients. */
		if (spawn != null )
		{
			spawn.close();
			spawn = null;
		} /* end if */
		
	} /* end stop method */

	@Override
	public void terminate() 
	{
		/* 1. Send a termination message to all clients.
		 * 2. Stop all threads currently working for the Server. */
		MessageQueue.getInstance().close();
		stop();
		
	} /* end terminate method */
	
	/************************************ clineList Related Methods ******************************/
	public boolean removeClient(int clientID)
	{
		boolean success = false;
		Client client = clientList.removeClient(clientID);
		
		if (client != null)
		{ /* successfully removed client from list. Terminate socket and kill thread. */
			success = true;
			if(client.sender != null) client.sender.close();
			if (client.listener != null) client.listener.close();
		} /* end if */
		
		/* if client = null then clientID never existed */
		
		return success;
	} /* end removeServer method */
	
	public boolean addListener(int listenerID, ServerListener listener, InetAddress ip )
	{
		return clientList.addListener(listenerID, listener, ip);
	} /* end addListener method */
	
	public boolean addSender(int clientID, int senderID, ServerSender sender)
	{
		return clientList.addSender(senderID, senderID, sender);
	} /* end addSender method */
	
	public boolean addUsername(int listenerID, int clientID, String username)
	{
		return clientList.addUsername(listenerID, clientID, username);
	} /* end addUsername method */
	
	public InetAddress getListenerIP(int listenerID)
	{
		return clientList.getListenerIP(listenerID);
	} /* end getListenerIP method */
	
	public void printUsers()
	{
		clientList.print();
	} /* end printClients method */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	@Override
	public boolean getMessage(int listenerID, NetworkMessage message)
	{
		boolean error = false;
		/* get message from a client. Check for validity and if valid, interpret. */
		if (validMessage(message.getType().index) && message.getMessageType() == Message.Type.NETWORK_MESSAGE) interpret(listenerID, message);
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
	
	
	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
			
	
} /* end ServerSystem class */
