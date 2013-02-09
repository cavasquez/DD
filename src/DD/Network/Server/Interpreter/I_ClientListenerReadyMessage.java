package DD.Network.Server.Interpreter;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import DD.Network.NetworkSystem;
import DD.Network.Sender;
import DD.Network.Message.ClientListenerReadyMessage;
import DD.Network.Message.NetworkMessage;
import DD.Network.Server.Client;
import DD.Network.Server.ClientTable;
import DD.Network.Server.ListenerSpawner;
import DD.Network.Server.ServerSender;

/*****************************************************************************************************
 * I_ClientListenerReadyMessage will create a ServerSender to connect to the clients ClientListener
 * and then add it to the tale.
 ******************************************************************************************************/

public class I_ClientListenerReadyMessage extends ServerInterpreter
{
	public void interpret(int listenerID, NetworkMessage message)
	{
		ClientTable clientList = system.getClientList();
		ArrayList<Client> clients = clientList.getClientList();
		ClientListenerReadyMessage clrm = (ClientListenerReadyMessage) message.getMessage();
		
		for (Client client : clients)
		{
			if(client.clientID != null)
			{
				if (client.clientID != NetworkSystem.GM_USER_ID && client.clientID == clrm.getPlayerID()) 
				{/* Client found. Create socket and connect to client */
					boolean connected = true;
					Socket senderSocket = null;
					try 
					{
						senderSocket = new Socket(client.ip, ListenerSpawner.port);
					} /* end try */ 
					catch (IOException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
						connected = false;
					} /* end catch */
					
					if (connected)
					{
						/* Successfully connected. Create ServerSender, open streams, and add to Client list */
						ServerSender sender = new ServerSender(senderSocket);
						sender.setUp();
						client.listenerID = sender.getID();
						client.sender = sender;
					} /* end if */
					
				} /* end if */
			} /* end if */
			
		} /* end if */
		
	} /* end interpret method */
	
} /* end I_ClientListenerReadyMessage class */
