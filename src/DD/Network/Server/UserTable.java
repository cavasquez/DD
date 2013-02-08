package DD.Network.Server;

import java.util.ArrayList;


/*****************************************************************************************************
 * UserTable is a structure that will hold data that references a socket id, the players username,
 * and the Server thread associated with that player.
 * 
 * Note that the socketID is a unique ID the server gives out for every socket that connects to it. 
 * Thus, there is a one to one correspondence to socketID and client. This will be used when declaring
 * senders and receivers in the NetworkMessage.
 ******************************************************************************************************/

public class UserTable 
{
	/************************************ Class Attributes *************************************/
	private ArrayList<User> userList = null;
	
	/************************************ Class Methods *************************************/
	public UserTable()
	{
		userList = new ArrayList<User>();
	} /* end UserTable */
	
	public boolean addUser(int serverID, String username, Server server)
	{
		boolean userExists = false;
		
		/* Check to see if the username exists. */
		for (User user : userList)
		{
			if (user.username != null)
			{ /* there might be a null username from addServer */
				if (user.username.compareTo(username) == 0) userExists = true;
			}
		} /* end for loop */
		
		if(!userExists) userList.add(new User(serverID, username, server));
		
		return userExists;
	} /* end addUser method */
	
	public boolean addServer(int serverID, Server server )
	{
		/* add a server without a username */
		boolean serverExists = false;
		
		/* Check to see if the server exists. */
		for (User user : userList)
		{
			if (user.socketID == serverID) serverExists = true;
		} /* end for loop */
		
		if(!serverExists) userList.add(new User(serverID, null, server));
		
		return serverExists;
		
	} /* end addServer */
	
	public boolean addUsername(int serverID, String username)
	{
		boolean userExists = false;
		
		/* Check to see if the username exists. */
		for (User user : userList)
		{
			if (user.username != null)
			{ /* there might be a null username from addServer */
				if (user.username.compareTo(username) == 0) userExists = true;
			} /* end if */
		} /* end for loop */
		
		if(!userExists)
		{
			userExists = false; /* use as flag to see if user was added */
			for (User user : userList)
			{
				if (user.socketID == serverID) 
				{
					user.username = username;
					userExists = true;
				} /* end if */
			} /* end for loop */
		} /* end if */
		
		return userExists;
	} /* end addUsername */
	
	public User removeUser(int serverID)
	{
		int index = -1;
		User returner = null;
		
		for (User user : userList)
		{
			if (user.socketID == serverID)
			{ 
				index = userList.indexOf(user);
				returner = user;
				userList.remove(index);
			} /* end if */
		} /* end for loop */
		
		return returner;
	} /* end removeUser method */
	
	public ArrayList<User> getUserList()
	{
		return(userList);
	} /* end getUserList */
	
} /* end UserTable */
