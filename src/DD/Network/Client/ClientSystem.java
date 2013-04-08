package DD.Network.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import DD.Chat.ChatSystem;
import DD.CombatSystem.CombatSystem;
import DD.Message.InitialMessage;
import DD.Message.Message;
import DD.Message.NetworkMessage;
import DD.Network.MessageQueue;
import DD.Network.Network;
import DD.Network.NetworkInterface;
import DD.Network.Client.Interpreter.*;

/*****************************************************************************************************
 * ClientSystem will be in charge of routing the messages received by the server and processing them.
 * Furthermore, it will send messages from the client and send them through the socket to the server.
 * 
 * The client will use interpreters which will hold the server logic for different kinds of messages.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class ClientSystem extends Network implements NetworkInterface 
{	
	/************************************ Class Attributes *************************************/
	private PeerTable peerList;
	private ClientInterpreter[] system = null;
	@SuppressWarnings("unused")
	private MessageQueue queue = null;			/* reference to MessageQueue thread. Will need to be cleaned up */
	private ClientListener listener = null;
	private ClientSender sender = null;
	private int clientID;						/* ID provided by server */
	private InetAddress serverIP = null;		/* ip address of the server */
	private ListenerSpawner spawner;
	
	/************************************ Class Methods *************************************/
	public ClientSystem() 
	{
		peerList = new PeerTable();
		
		system = new ClientInterpreter[Message.Type.NUM_OF_MESSAGES];
		system[Message.Type.COMBAT_MESSAGE.index] = new I_CombatMessage();
		system[Message.Type.CHAT_MESSAGE.index] = new I_ChatMessage();
		system[Message.Type.INITIAL_MESSAGE.index] = new I_InitialMessage();
		system[Message.Type.NEW_LISTENER_MESSAGE.index] = new I_NewListenerMessage();
		system[Message.Type.ADD_USER_MESSAGE.index] = new I_AddUserMessage();
		
	} /* end ServerSystem constructor */
	
	public void interpret(int listenerID, NetworkMessage message)
	{
		/* Assume all messages are of correct type and legally formatted.
		 * In any case, messages are always given by the ClientListener */
		system[message.getType().index].interpret(message);
	} /* end interpret */
	
	@Override
	public boolean sendMessage(int sender, int receiver, Message message)
	{
		/* Send message to Server. */
		NetworkMessage send = new NetworkMessage(sender, receiver, message);
		this.sender.sendMessage(send);
		return true;
	} /* end sendMessage method */
	
	public static boolean validMessage(int type)
	{
		/* Check to see if the message is supported by the system. Used only for getMessage */
		boolean valid = false;
		
		if 
		(
			type == Message.Type.COMBAT_MESSAGE.index ||
			type == Message.Type.CHAT_MESSAGE.index ||
			type == Message.Type.INITIAL_MESSAGE.index ||
			type == Message.Type.NEW_LISTENER_MESSAGE.index ||
			type == Message.Type.ADD_USER_MESSAGE.index
		)
		{
			valid = true;
		} /* end if */
		
		return valid;
	} /* end validMessage method */
	

	@Override
	public void start() 
	{
		/* First, get a listener ready for contact with the server */
		try 
		{
			spawner = new ListenerSpawner();
			spawner.start();
			
			while(spawner.getSocketReady() != true)
			{
//				/* do nothing until listener is set up */
//				try 
//				{
//					Thread.sleep(100);
//				} /* end try */
//				catch (InterruptedException e) 
//				{
//					System.out.println("interrupted in ClientSysetm at start while waiting for listener");
//				} /* end catch */
			} /* end while loop */
			
			/* Now try to connect to server */
			System.out.println("serverIP: " + serverIP);
			sender = new ClientSender(new Socket(serverIP, Network.SERVER_PORT));
			sender.sendMessage(new NetworkMessage(Network.GM_USER_ID, 0, new InitialMessage(username, false, clientID)));
		} /* end try */
		catch (IOException e) 
		{
			/* failure to connect */
			System.out.println("Failed to connecto to server");
		} /* end catch */
		
		/* Finally, start the MessageQueue */
		MessageQueue.getInstance().start();
		
	} /* end start method */

	@Override
	public void stop() 
	{
		spawner.stopAccepting();
		sender.close();
		sender = null;
		//TODO: finish
	} /* end stop method */

	@Override
	public void terminate() 
	{
		// TODO Auto-generated method stub
		
	} /* end terminate method */
	
	/************************************ peerList related methods**********************************/
	public boolean addUser(int peerID, String username, InetAddress ip)
	{
		return peerList.addPeer(peerID, username, ip);
	} /* end addUser method */
	
	public boolean removePeer(int peerID)
	{
		return peerList.removePeer(peerID);
	} /* end removePeer method*/
	
	public void printUsers()
	{
		peerList.print();
	} /* end printUsers */
	
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
	
	public PeerTable getClientList()
	{
		return peerList;
	} /* end getUserList */
	
	public Integer getClientID()
	{
		return clientID;
	} /* end getUserList */
	
	public InetAddress getServerIP()
	{
		return serverIP;
	} /* end getServerIP method */
	
	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
	public void setListener(ClientListener listener)
	{
		this.listener = listener;
	} /* end setListener method */
	
	public void setSender(ClientSender sender)
	{
		this.sender = sender;
	} /* end setSender method */
	
	public void setClientID(int clientID)
	{
		this.clientID = clientID;
	} /* end setSender method */
	
	public void setServerIP(InetAddress serverIP)
	{
		this.serverIP = serverIP;
	} /* end setServerIP method */
	
	public boolean setServerIP(String ip)
	{
		boolean returner = false;
		try {
			setServerIP(InetAddress.getByName(ip));
			returner = true;
		} /* end try */
		catch (UnknownHostException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /* end catch */
		
		return returner;
	} /* end setServerIP method */


	@Override
	public void setCombatSystem(CombatSystem cs) 
	{
		// TODO Auto-generated method stub
		
	} /* end setCombatSystem method */

	@Override
	public void setChatSystem(ChatSystem chat)
	{
		// TODO Auto-generated method stub
		
	} /* end setChatSystem method */
	
} /* end ServerSystem class */
