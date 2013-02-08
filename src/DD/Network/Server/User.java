package DD.Network.Server;

/*****************************************************************************************************
 * User will be used by UserTable to help create the UserTable structure.
 ******************************************************************************************************/

public class User 
{
	/************************************ Class Attributes *************************************/
	public int playerID;
	public String username;
	public Server server;
	
	/************************************ Class Methods *************************************/
	public User(int playerID, String username, Server server)
	{
		this.playerID = playerID;
		this.username = username;
		this.server = server;
	} /* end UserTable method */
	
} /* end UserTable class */
