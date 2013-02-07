package DD.Network.Server;

import java.util.ArrayList;

import DD.Chat.ChatSystem;

/*****************************************************************************************************
 * ServerSystem will be in charge of routing the messages received by the server and distributing
 * them to all the clients(except for the sender).
 * 
 * For the servers sake, it will be a singleton to choke entry.
 ******************************************************************************************************/

public class ServerSystem implements NetworkSystem
{
	/************************************ Class Constants *************************************/
	private static ServerSystem instance = null;
	public static int TEMP_USER_ID = -1;
	
	/************************************ Class Attributes *************************************/
	private static volatile ArrayList<UserTable> userList;
	
	/************************************ Class Methods *************************************/
	private ServerSystem() {};
	
	public static ServerSystem getinstance()
	{
		if (instance == null) instance = new ServerSystem();
		return instance;
	} /* end getInstance method */
	
	public boolean addUser(int userID, String username, Server server)
	{
		boolean userExists = false;
		
		/* Check to see if the username exists. */
		for (UserTable user : userList)
		{
			if (user.username == username) userExists = true;
		} /* end for loop */
		
		if(!userExists) userList.add(new UserTable(userID, username, server));
		
		return userExists;
	} /* end addUser method */

} /* end ServerSystem class */
