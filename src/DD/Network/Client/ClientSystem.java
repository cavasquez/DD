package DD.Network.Client;

import java.util.ArrayList;

import DD.Network.Network;
import DD.Network.Message.Message;
import DD.Network.Message.NetworkMessage;

/*****************************************************************************************************
 * ClientSystem will be in charge of routing the messages received by the server and distributing
 * them to all the clients(except for the sender).
 * 
 * ClientSystem will be a singleton to minimize memory footprint and to retain knowledge of Sockets.
 ******************************************************************************************************/

public class ClientSystem implements Network
{
	/************************************ Class Constants *************************************/
	private static ClientSystem instance = null;
	
	/************************************ Class Attributes *************************************/
	private static volatile ArrayList<> userList;
	
	/************************************ Class Methods *************************************/
	private ClientSystem() {}
	
	public static ClientSystem getInstance()
	{
		if (instance == null) instance = new ClientSystem();
		return instance;
	} /* end getInstance method */
	
	@Override
	public void sendMessage(int sender, int receiver, Message message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getMessage(int socketID, NetworkMessage message) {
		// TODO Auto-generated method stub
		
	}

}
