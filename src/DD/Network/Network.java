package DD.Network;

import java.net.InetAddress;
import java.net.UnknownHostException;

import DD.Chat.ChatSystem;
import DD.CombatSystem.CombatSystem;

/*****************************************************************************************************
* Network will contain all network related constants and provide some of the basic methods used by
* ServerSystem, NetworkSystem, and ClientSystem
* 
* @author Carlos Vasquez
******************************************************************************************************/

public abstract class Network implements NetworkInterface
{
	/************************************ Class Constants *************************************/
	public static final int EVERYONE = -1;					/* a message sent to EVERYONE will be to -1 */
	public static final int GM_USER_ID = -2;				/* GM (Server) will always have an ID of -1 */
	public static final int SELF = -3;						/* Send message to self (for Clients) */
	public static final int SERVER_PORT = 9516;				/* Port used to connect to server */
	public static final int CLIENT_PORT = 8069;				/* Port used to connect to client */
	public static final int BACKLOG= 10;					/* Port used to start listener */
	
	/************************************ Class Attributes *************************************/
	protected InetAddress serverIP;
	protected CombatSystem cs;
	protected ChatSystem chat;
	protected String username;
	
	/************************************ Class Methods*************************************/
	public abstract void printUsers();
	
	/****************************************************************************************
	 ************************************ Getter Methods ************************************
	 ****************************************************************************************/
	
	/****************************************************************************************
	 ************************************ Setter Methods ************************************
	 ****************************************************************************************/	
	public void setServerIP(InetAddress serverIP)
	{
		this.serverIP = serverIP;
	} /* end setServerIP method */
	
	public boolean setServerIP(String ip)
	{
		boolean returner = false;
		try 
		{
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
	
	public void setUsername(String username)
	{
		this.username = username;
	} /* end setUsername method */
	
} /* end Network class */
