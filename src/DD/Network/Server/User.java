package DD.Network.Server;


/*****************************************************************************************************
 * User will be used by UserTable to help create the UserTable structure.
 ******************************************************************************************************/

public class User 
{
	/************************************ Class Attributes *************************************/
	public int socketID;
	public String username;
	public Server server;
	
	/************************************ Class Methods *************************************/
	public User(int socketID, String username, Server server)
	{
		this.socketID = socketID;
		this.username = username;
		this.server = server;
	} /* end UserTable method */
	
} /* end UserTable class */
