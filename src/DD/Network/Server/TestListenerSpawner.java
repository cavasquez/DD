package DD.Network.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import DD.Network.MessageQueue;
import DD.System.DDSystem;

public class TestListenerSpawner 
{
	public static void main(String[] args)
	{
	
			DDSystem system = new DDSystem();
			
			ListenerSpawner spawner = new ListenerSpawner();
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			String mssg = "";
//				mssg = bufferedReader.readLine();
			
			spawner.startAccepting();
			spawner.start();
			
			System.out.println("Waiting on ListenerSpawner");
			try {
				mssg = bufferedReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			spawner.close();
			
			while(spawner.isAlive());
			System.out.println("done");
		
	}
}
