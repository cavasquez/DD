package DD.Network.Client.Interpreter;

import DD.Message.ClientListenerReadyMessage;
import DD.Message.NetworkMessage;
import DD.Message.NewListenerMessage;
import DD.Network.Network;
import DD.Network.Client.ClientListener;

/*****************************************************************************************************
 * I_NewListenerMessage will add the new listener to ClientSystem and communicate to the server that
 * the listener is ready. 
 ******************************************************************************************************/

public class I_NewListenerMessage extends ClientInterpreter
{
	@Override
	public void interpret(NetworkMessage message)
	{
		NewListenerMessage nlm = (NewListenerMessage) message.getMessage();
		
		/* Tell give the ClientSystem the listener */
		system.setListener((ClientListener)nlm.getListener());
		
		/* Send the ClientListenerReadyMessage to the server */
		ClientListenerReadyMessage clrm = new ClientListenerReadyMessage(system.getClientID());
		system.sendMessage(system.getClientID(), Network.GM_USER_ID, clrm);
		
	} /* end interpret method */
	
} /* end I_NewListenerMessage */