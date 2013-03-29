package DD.Network.Client;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import DD.Message.ChatMessage;
import DD.Message.NetworkMessage;

public class TestClientSender
{
	public static void main(String[] args) throws InterruptedException
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
		
		boolean test1 = false;
		boolean test2 = true;
		
		if(test1)
		{
			NetworkMessage message;
			ChatMessage chat;
			chat = new ChatMessage(0, 0, 0, "hey");
			message = new NetworkMessage(0,0, chat);
			System.out.println("ser? " + Serializable.class.isInstance(message));
			sender.sendMessage(message);
			
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			String mssg = "";
			
			while ( mssg.matches("done") != true)
			{
				
				try {
					mssg = bufferedReader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				chat = new ChatMessage(0, 0, 0, mssg);
				message = new NetworkMessage(0,0, chat);
				sender.sendMessage(message);
			}
		}
		
		else if (test2)
		{
			NetworkMessage message;
			ChatMessage chat;
			chat = new ChatMessage(0, 0, 0, "hey");
			message = new NetworkMessage(0,0, chat);
			System.out.println("ser? " + Serializable.class.isInstance(message));
			sender.sendMessage(message);
			String mssg;
			
			for (int i = 0; i < 50; i++)
			{
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mssg = Integer.toString(i);
				chat = new ChatMessage(0, 0, 0, mssg);
				message = new NetworkMessage(0,0, chat);
				System.out.println(i+ " ser? " + Serializable.class.isInstance(message));
				sender.sendMessage(message);
			} 
			
			mssg = "done";
			chat = new ChatMessage(0, 0, 0, mssg);
			message = new NetworkMessage(0,0, chat);
			sender.sendMessage(message);
			System.out.println("message: " + mssg);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			try {
				mssg = bufferedReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
		
	}
}
