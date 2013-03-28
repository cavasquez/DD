package DD.Network.Client;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.ServerSocket;

import DD.Message.ChatMessage;
import DD.Message.Message;
import DD.Message.NetworkMessage;

public class TestDriver {
	
	public static void main(String[] args)
	{
			Console console = System.console();
			TestClientListener listener = null;
			ServerSocket sSocket;
			ChatMessage chat = null;
			NetworkMessage message = null;
			boolean test1 = false;
			boolean test2 = true;
			boolean test3 = false;
			Message asd = (Message) new ChatMessage(0,0,0,"hi");
			System.out.println("is ser " + Serializable.class.isInstance(asd));
			try {

				sSocket = new ServerSocket(6546);
				System.out.println("waiting for connection");
				listener = new TestClientListener(sSocket.accept());
				listener.setTest(test1, test2, test3);
				System.out.println("connected");
				
				listener.start();
				
				
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
				String mssg = "";
//					mssg = bufferedReader.readLine();
				
				System.out.println("Waiting on listener");
				while(listener.isAlive());
				System.out.println("Listener is done");
				//System.out.println(listener.);
					
				
		
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

}
