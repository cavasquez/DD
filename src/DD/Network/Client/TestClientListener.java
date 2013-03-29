package DD.Network.Client;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import DD.Message.ChatMessage;
import DD.Message.NetworkMessage;
import DD.Message.Message;


public class TestClientListener extends ClientListener
{
	boolean test1;
	boolean test2;
	boolean test3;
	public TestClientListener(Socket socket) {
		super(socket);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run()
	{
		ChatMessage chat = null;
		//if (input == null ) createStreams(); 
		
		/* Now, listen for messages from the Server */
		while (!done)
		{/* Threads process */
			if(test1)
			{
				NetworkMessage nm;
				nm = getSocketMessage();
				System.out.println("input: " + input);
				System.out.println("message received");
				System.out.println("message " + nm);
				chat = (ChatMessage) nm.getMessage();
				System.out.println(chat.getMessage());
				if(chat.getMessage().matches("done") == true)
				{
					System.out.println("done is true");
						done = true;
				}
				
			}
			
			else if(test2)
			{
				NetworkMessage nm;
				nm = getSocketMessage();
				while(nm != null )
				{
					System.out.println("input: " + input);
					System.out.println("message received");
					System.out.println("message " + nm);
					chat = (ChatMessage) nm.getMessage();
					System.out.println(chat.getMessage());
					if(chat.getMessage().matches("done") == true)
					{
						System.out.println("done is true");
						done = true;
					}
					
						nm = getSocketMessage();
					
				
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			System.out.println("Thread is done!");
//		
		} /* end done loop */
		
		/* Done. Close streams. */
		closeStreams();
	}
	public void setTest(boolean test1, boolean test2, boolean test3)
	{
		this.test1 = test1;
		this.test2 = test2;
		this.test3 = test3;
	} /* end setTest method */
	public static void main(String[] args)
	{
			Console console = System.console();
			TestClientListener listener = null;
			ServerSocket sSocket;
			ChatMessage chat = null;
			NetworkMessage message = null;
			boolean test1 = false;
			boolean test2 = true;
			Message asd = (Message) new ChatMessage(0,0,0,"hi");
			System.out.println("is ser " + Serializable.class.isInstance(asd));
			try {
				if (test1)
				{
					sSocket = new ServerSocket(6546);
					System.out.println("waiting for connection");
					listener = new TestClientListener(sSocket.accept());
					System.out.println("connected");
					String mssg = "";
					
					
					message = listener.getSocketMessage();
					System.out.println("message received");
					System.out.println("message " + message);
					chat = (ChatMessage) message.getMessage();
					mssg = chat.getMessage();
					System.out.println(mssg);
					
					
					
				}
				
				else if(test2)
				{
					sSocket = new ServerSocket(6546);
					System.out.println("waiting for connection");
					listener = new TestClientListener(sSocket.accept());
					System.out.println("connected");
					
					listener.start();
					
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
					String mssg = "";
//					mssg = bufferedReader.readLine();
					
					System.out.println("Waiting on listener");
					while(listener.isAlive());
					System.out.println("Listener is done");
					//System.out.println(listener.);
					
				}
		
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

}
