package DD.Network;

import DD.Message.Message;
import DD.Message.NetworkMessage;
import DD.Network.Client.ClientSystem;
import DD.Network.Server.ServerSystem;


/*****************************************************************************************************
* NetworkSystem will create a singleton and be the interface through which GameSystem and ChatSystem 
* (along with any other systems) will communicate with ClientSystem or ServerSystem based on whether
* the user is a client or a server.
* 
* @author Carlos Vasquez
******************************************************************************************************/

public class NetworkSystem implements Network
{
	/************************************ Class Constants *************************************/
	private static int NUM_OF_NETWORKS = 0;
	public static final int SERVER = NUM_OF_NETWORKS++;
	public static final int CLIENT = NUM_OF_NETWORKS++;
	
	/************************************ Class Attributes *************************************/
	private static int networkType;
	private static Network network;
	
	/************************************ Class Methods *************************************/
	public NetworkSystem() 
	{}
	
	@Override
	public boolean sendMessage(int sender, int receiver, Message message)
	{
		return network.sendMessage(sender, receiver, message);
	} /* end sendMessage method */
	
	private static boolean networkTypeExists(int networkType)
	{
		boolean exists = false;
		if ( networkType == SERVER ||
				networkType == CLIENT)
		{
			exists = true;
		} /* end if */
		
		return exists;
	} /* end networkTypeExists method */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	@Override
	public boolean getMessage(int listenerID, NetworkMessage message) 
	{ /* Get message from client. (This is how client passes messages to NetworkSyste/ServerSystem to interpret). */
		return network.getMessage(listenerID, message);
		
	} /* end getMessage method */
	
	public int getUserID()
	{
		Integer id = null;
		if (networkType == SERVER) id = Network.GM_USER_ID;
		else if (networkType == CLIENT) id = ((ClientSystem)network).getClientID();
		
		return id;
	} /* end getUserID */
	
	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
	public static boolean setNetworkType(int type)
	{
		boolean exists = false;
		if (networkTypeExists(type))
		{
			networkType = type;
			if (networkType == SERVER) network = new ServerSystem();
			else if (networkType == CLIENT) network = new ClientSystem();
		}
		
		return exists;
	} /* end setNetworkType */
	
} /* end Network class */
