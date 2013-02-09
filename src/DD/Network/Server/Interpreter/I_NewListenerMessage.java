package DD.Network.Server.Interpreter;

import DD.Network.Message.NetworkMessage;
import DD.Network.Message.NewListenerMessage;
import DD.Network.Server.ClientTable;
import DD.Network.Server.ServerListener;

/*****************************************************************************************************
 * I_NewListenerMessage will create a new entry in the clientList for the new ServerListener. 
 * This happens when the server connects with a new client.
 ******************************************************************************************************/

public class I_NewListenerMessage extends ServerInterpreter
{
	@Override
	public void interpret(int listenerID, NetworkMessage message)
	{
		NewListenerMessage nlm = (NewListenerMessage) message.getMessage();
		system.addListener(nlm.getListenerID(), (ServerListener)nlm.getListener(), nlm.getIp());
	} /* end interpret method */
} /* end I_NewListenerMessage */
