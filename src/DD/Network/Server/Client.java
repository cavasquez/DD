package DD.Network.Server;

import java.net.InetAddress;

/*****************************************************************************************************
 * Client contains releveat network information used when communicating with the client.
 ******************************************************************************************************/

public class Client 
{
	/************************************ Class Attributes *************************************/
	public Integer clientID;		/* ID assigned to client by Server */
	public String username;			/* Clients uesrname */
	public Integer senderID;		/* ID of socket that is sending messages to socket */
	public Integer listenerID;		/* ID of socket that is listening for messages from the client */
	public ServerSender sender;		/* Reference to thread that is sending messages to the client */
	public ServerListener listener;	/* Reference to the thread that is listening for messages from the client */
	public InetAddress ip;			/* ip address of the client */
	
	/************************************ Class Methods *************************************/
	public Client
	(
		Integer clientID,
		String username,
		Integer senderID,
		Integer listenerID, 
		ServerSender sender,
		ServerListener listener,
		InetAddress ip
	)
	{
		this.clientID = clientID;
		this.username = username;
		this.senderID = senderID;
		this.listenerID = listenerID;
		this.sender = sender;
		this.listener = listener;
		this.ip = ip;
	} /* end Client constructor */
	
} /* end Client class */
