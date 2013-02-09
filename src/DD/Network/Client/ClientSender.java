package DD.Network.Client;

import java.net.Socket;

import DD.Network.Sender;

/*****************************************************************************************************
 * ClientSender will be used by ClientSystem to send messages to the server
 ******************************************************************************************************/

public class ClientSender extends Sender
{
	public ClientSender(Socket socket)
	{
		super(socket);
	} /* end ClientSender constructor */
	
} /* end ClientSender class */
