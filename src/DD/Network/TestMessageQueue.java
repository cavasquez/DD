package DD.Network;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.ServerSocket;

import DD.Message.ChatMessage;
import DD.Message.Message;
import DD.Message.NetworkMessage;
import DD.Network.Client.TestClientListener;
import DD.System.DDSystem;

public class TestMessageQueue 
{
	public static void main(String[] args)
	{
			
			boolean test1 = true;
			boolean test2 = false;
			boolean test3 = false;
			
			DDSystem system = new DDSystem();
			
			MessageQueue.getInstance().start();
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			String mssg = "";
//				mssg = bufferedReader.readLine();
			
			System.out.println("Waiting on MessageQueue");
			try {
				mssg = bufferedReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			MessageQueue.getInstance().close();
		
			while(MessageQueue.getInstance().isAlive()) {}
			System.out.println("test done");
	}
}
