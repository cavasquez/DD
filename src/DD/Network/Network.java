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
	public static final int EVERYONE = -1;		/* a message sent to EVERYONE will be to -1 */
	public static final int GM_USER_ID = -2;	/* GM (Server) will always have an ID of -1 */
	public static final int SELF = -3;			/* Send message to self (for Clients) */
	public static final int PORT = 8080;		/* Port that ServerSocket connects to */	

	/************************************ Class Attributes *************************************/
	protected InetAddress serverIP;
	protected CombatSystem cs;
	protected ChatSystem chat;
	
	/************************************ Class Methods*************************************/
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
	
	public void setCombatSystem(CombatSystem cs)
	{
		this.cs = cs;
	} /* end setCombatSystem method */
	
	public void setChatSystem(ChatSystem chat)
	{
		this.chat = chat;
	} /* end setChatSystem method */
	
} /* end Network class */
