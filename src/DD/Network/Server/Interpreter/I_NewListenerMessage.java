package DD.Network.Server.Interpreter;

import DD.Network.Message.NetworkMessage;
import DD.Network.Message.NewListenerMessage;
import DD.Network.Server.ClientTable;
import DD.Network.Server.ServerListener;

/*****************************************************************************************************
 * I_NewListenerMessage will create a new entry in the clientList for the new ServerListener. 
 ******************************************************************************************************/

public class I_NewListenerMessage extends ServerInterpreter
{
	@Override
	public void interpret(int listenerID, NetworkMessage message)
	{
		ClientTable clientList = system.getClientList();
		NewListenerMessage nlm = (NewListenerMessage) message.getMessage();
		
		clientList.addListener(nlm.getListenerID(), (ServerListener)nlm.getListener(), nlm.getIp());
	} /* end interpret method */
} /* end I_NewListenerMessage */
