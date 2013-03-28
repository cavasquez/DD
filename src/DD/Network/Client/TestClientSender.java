package DD.Network.Client;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import DD.Message.ChatMessage;
import DD.Message.NetworkMessage;

public class TestClientSender
{
	public static void main(String[] args)
	{
		InetAddress ip = null;
		ClientSender sender = null;
		try {
			ip = InetAddress.getByName("10.69.5.11");
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sender = new ClientSender(new Socket(ip, 6546));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		NetworkMessage message;
		ChatMessage chat;
		chat = new ChatMessage(0, 0, 0, "hey");
		message = new NetworkMessage(0,0, chat);
		System.out.println("ser? " + Serializable.class.isInstance(message));
		sender.sendMessage(message);
	}
}
