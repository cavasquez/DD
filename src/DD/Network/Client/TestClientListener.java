package DD.Network.Client;

import java.io.Console;
import java.io.IOException;
import java.net.ServerSocket;

import DD.Message.ChatMessage;
import DD.Message.NetworkMessage;

public class TestClientListener 
{
	public static void main(String[] args)
	{
			Console console = System.console();
			ClientListener listener = null;
			ServerSocket sSocket;
			try {
				sSocket = new ServerSocket(6546);
				listener = new ClientListener(sSocket.accept());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			NetworkMessage message = null;
			ChatMessage chat = null;
			for (int i = 0; i < 5; i++)
			{
				message = listener.test();
				chat =(ChatMessage) message.getMessage();
				System.out.println(chat.getMessage());
			}
		
	}

}
