package DD.Network.Server.Interpreter;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import DD.Message.AddUserMessage;
import DD.Message.ClientListenerReadyMessage;
import DD.Message.NetworkMessage;
import DD.Network.Network;
import DD.Network.NetworkSystem;
import DD.Network.Server.Client;
import DD.Network.Server.ClientTable;
import DD.Network.Server.ServerSender;

/*****************************************************************************************************
 * I_ClientListenerReadyMessage will create a ServerSender to connect to the clients ClientListener
 * and then add it to the table. Furthermore, we will communicate to the client it's brothers (peers).
 ******************************************************************************************************/

public class I_ClientListenerReadyMessage extends ServerInterpreter
{
	@Override
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
						senderSocket = new Socket(client.ip, Network.PORT);
					} /* end try */ 
					catch (IOException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
						connected = false;
					} /* end catch */
					
					if (connected)
					{
						/* Successfully connected. Create ServerSender, open streams, and add to Client list.
						 * Then communicate to the ClientListener it's new peers */
						ServerSender sender = new ServerSender(senderSocket);
						sender.setUp();
						system.addSender(client.clientID, sender.getID(), sender);
						
						/* Communicate to the client it's peers */
						ArrayList<Client> brothers = clientList.getClientList();
						for(Client brother : brothers)
						{/* Send the listener peer info for all the current peers (including the server) */
							if (brother.listenerID != listenerID)
							{
								AddUserMessage am = new AddUserMessage(brother.clientID, brother.username, brother.ip);
								system.sendMessage(Network.GM_USER_ID, client.clientID, am);
							} /* end if */
							
						} /* end for loop */
						
					} /* end if */
					
					else
					{
						//TODO: Deal with connection loss
						System.out.println("Failed to Connect to listener" + Integer.toString(client.clientID));
					} /* end else */
					
				} /* end if */
				
				else
				{
					//TODO: deal with bad clientID
					System.out.println("Client ID does not exists");
				} /* end if */
			} /* end if */
			
		} /* end if */
		
	} /* end interpret method */
	
} /* end I_ClientListenerReadyMessage class */
