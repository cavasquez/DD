package DD.Network.Client;

import java.io.Console;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import DD.Message.ChatMessage;
import DD.Message.NetworkMessage;
import DD.Message.Message;

public class TestClientListener 
{
	public static void main(String[] args)
	{
			Console console = System.console();
			ClientListener listener = null;
			ServerSocket sSocket;
			Message asd = (Message) new ChatMessage(0,0,0,"hi");
			System.out.println("is ser " + Serializable.class.isInstance(asd));
			try {
				sSocket = new ServerSocket(6546);
				System.out.println("waiting for connection");
				listener = new ClientListener(sSocket.accept());
				System.out.println("connected");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			NetworkMessage message = null;
			ChatMessage chat = null;


			System.out.println("waiting for message");
			message = listener.test();
			System.out.println("message received");
			System.out.println("message " + message);
			chat = (ChatMessage) message.getMessage();
			System.out.println(chat.getMessage());
			
			
//			for (int i = 0; i < 5; i++)
//			{
//				System.out.println("waiting for message");
//				message = listener.test();
//				System.out.println("message received");
//				System.out.println("message " + message);
//				//chat = (ChatMessage) message.getMessage();
//				//System.out.println(chat.getMessage());
//			}
		
	}

}
