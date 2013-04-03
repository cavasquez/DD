package DD.Network.Server;

import java.net.Socket;

import DD.Network.Sender;

/*****************************************************************************************************
 * ServerSender will be used by ServerSystem to send messages to the client
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class ServerSender extends Sender
{
	public ServerSender(Socket socket) 
	{
		super(socket);
	} /* end ServerSender class */

} /* end ServerSender class */
