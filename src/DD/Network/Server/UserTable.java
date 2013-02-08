package DD.Network.Server;

import java.util.ArrayList;

/*****************************************************************************************************
 * UserTable is a structure that will hold data that references a player id, the players username,
 * and the Server thread associated with that player.
 * 
 * Note that the playerID is the same as the Servers serverID (since there is a one to one relationship
 * between Servers and players).
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
			if (user.playerID == serverID) serverExists = true;
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
				if (user.playerID == serverID) 
				{
					user.username = username;
					userExists = true;
				} /* end if */
			} /* end for loop */
		} /* end if */
		
		return userExists;
	} /* end addUsername */
	
	public boolean removeUser(int serverID)
	{
		boolean removed = false;
		int index = -1;
		
		for (User user : userList)
		{
			if (user.playerID == serverID)
			{ 
				index = userList.indexOf(user);
				userList.remove(index);
				removed = true;
			} /* end if */
		} /* end for loop */
		
		return removed;
	} /* end removeUser method */
	
	public ArrayList<User> getUserList()
	{
		return(userList);
	} /* end getUserList */
	
} /* end UserTable */
