package DD.Network.Client;

import java.util.ArrayList;

import DD.Network.Network;
import DD.Network.Message.Message;
import DD.Network.Message.NetworkMessage;

/*****************************************************************************************************
 * ClientSystem will be in charge of routing the messages received by the server and distributing
 * them to all the clients(except for the sender).
 ******************************************************************************************************/

public class ClientSystem implements Network
{
	/************************************ Class Constants *************************************/
	private static ClientSystem instance = null;
	
	/************************************ Class Attributes *************************************/

	
	/************************************ Class Methods *************************************/
	public ClientSystem() {}
	
	@Override
	public boolean sendMessage(int sender, int receiver, Message message) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean getMessage(int socketID, NetworkMessage message) {
		return false;
		// TODO Auto-generated method stub
		
	}

}
