package DD.Network.Server;

import java.net.InetAddress;
import java.util.ArrayList;


/*****************************************************************************************************
 * ClientTable will hold all relevant network information that will be used by NetwrokSystem to send
 * messages to clients and receiving messages from clients. It should also be noted that the server
 * will have a slot in the clientList
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class ClientTable 
{
	/************************************ Class Attributes *************************************/
	private static ArrayList<Client> clientList = null;
	
	/************************************ Class Methods *************************************/
	public ClientTable()
	{
		clientList = new ArrayList<Client>();
	} /* end UserTable */
	
	public boolean addUser
	(
		int userID, 
		String username,
		int senderID,
		int listenerID,
		ServerSender sender,
		ServerListener listener,
		InetAddress ip
	)
	{
		boolean userExists = false;
		
		/* Check to see if the username exists. */
		for (Client client : clientList)
		{
			if (client.username != null)
			{ /* there might be a null username from addServer */
				if (client.username.compareTo(username) == 0) userExists = true;
			}
		} /* end for loop */
		
		if(!userExists) clientList.add(new Client(userID, username, senderID, listenerID, sender, listener, ip));
		
		return userExists;
	} /* end addUser method */
	
	public boolean addListener(int listenerID, ServerListener listener, InetAddress ip )
	{
		/* Add a listener without a username or sender info. Note that listener
		 * corresponds to clients sender */
		boolean listenerExists = false;
		
		/* Check to see if the listener exists. */
		for (Client client : clientList)
		{
			if (client.listenerID == listenerID) listenerExists = true;
		} /* end for loop */
		
		if(!listenerExists) clientList.add(new Client(null, null, null, listenerID, null, listener, ip));
		
		return listenerExists;
		
	} /* end addServer */
	
	public boolean addSender(int clientID, int senderID, ServerSender sender)
	{
		/* Add a sender (the final step when connecting to a client) */
		boolean senderExists = false;
		
		for (Client client : clientList)
		{
			if (client.senderID != null)
			{ /* there might be a null username from addServer */
				if (client.senderID == senderID) senderExists = true;
			} /* end if */
		} /* end for loop */
		
		if(!senderExists)
		{
			senderExists = false; /* use as flag to see if client was added */
			for (Client client : clientList)
			{
				if (client.clientID == clientID) 
				{
					client.senderID = senderID;
					client.sender = sender;
					senderExists = true;
				} /* end if */
			} /* end for loop */
		} /* end if */
		
		return senderExists;
	} /* end addSender method */
	
	public boolean addUsername(int listenerID, int clientID, String username)
	{
		boolean userExists = false;
		
		/* Check to see if the username exists. */
		for (Client client : clientList)
		{
			if (client.username != null)
			{ /* there might be a null username from addServer */
				if (client.username.compareTo(username) == 0) userExists = true;
			} /* end if */
		} /* end for loop */
		
		if(!userExists)
		{
			userExists = false; /* use as flag to see if client was added */
			for (Client client : clientList)
			{
				if (client.listenerID == listenerID) 
				{
					client.clientID = clientID;
					client.username = username;
					userExists = true;
				} /* end if */
			} /* end for loop */
		} /* end if */
		
		return userExists;
	} /* end addUsername */
	
	public Client removeClient(int clientID)
	{
		Client returner = null;
		
		for (Client client : clientList)
		{
			if (client.clientID == clientID)
			{ 
				int index = clientList.indexOf(client);
				returner = client;
				clientList.remove(index);
			} /* end if */
		} /* end for loop */
		
		return returner;
	} /* end removeUser method */
	
	public ArrayList<Client> getClientList()
	{
		return(clientList);
	} /* end getUserList */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public InetAddress getListenerIP(int listenerID)
	{
		InetAddress ip = null;
		for (Client client : clientList)
		{
			if (client.listenerID != null)
			{
				if (client.listenerID == listenerID) ip = client.ip;
				
			} /* end if */
			
		} /* end for loop */
		
		return ip;
	} /* end getListener IP */
	
} /* end UserTable */
