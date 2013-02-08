package DD.Network;

import DD.Network.Client.ClientSystem;
import DD.Network.Server.ServerSystem;

/*****************************************************************************************************
* NetworkSystem will create a singleton and be the interface through which GameSystem and ChatSystem 
* (along with any other systems) will communicate with ClientSystem or ServerSystem based on whether
* the user is a client or a server.
* 
* It will also be a singleton so as to not violate the singleton pattern utilized by ClientSystem or
* ServerSystem.
******************************************************************************************************/

public class NetworkSystem implements Network
{
	/************************************ Class Constants *************************************/
	private static int NUM_OF_NETWORKS = 0;
	public static final int SERVER = NUM_OF_NETWORKS++;
	public static final int CLIENT = NUM_OF_NETWORKS++;
	
	/************************************ Class Attributes *************************************/
	private static int networkType;
	private static NetworkSystem instance = null;
	
	/************************************ Class Methods *************************************/
	private NetworkSystem() {}
	
	public static NetworkSystem getInstance()
	{
		if (instance == null) instance = new NetworkSystem();
		return instance;
	} /* end getInstance method */
	
	@Override
	public void sendMessage(int sender, int receiver, Message message) 
	{
		if (networkType == SERVER) (ServerSystem.getInstance()).sendMessage(sender, receiver, message);
		else if (networkType == SERVER) (ClientSystem.getInstance()).sendMessage(sender, receiver, message);
		
	} /* end sendMessage method */
	
	private boolean networkTypeExists(int networkType)
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
	public boolean getMessage(int socketID, NetworkMessage message) 
	{
		boolean exists = true;
		if (networkType == SERVER) (ServerSystem.getInstance()).getMessage(socketID, message);
		else if (networkType == CLIENT) (ClientSystem.getInstance()).getMessage(socketID, message);
		else exists = false;
		
		return exists;
		
	} /* end getMessage method */
	
	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
	public boolean setNetworkType(int type)
	{
		boolean exists = false;
		if (networkTypeExists(type))
		{
			networkType = type;
		}
		
		return exists;
	} /* end setNetworkType */
	
} /* end Network class */
