package DD.Network.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

import DD.System.DDSystem;

public class TestServer 
{
	public static void main(String[] args) 
	{
		DDSystem system = new DDSystem();
		system.server();
		system.ns.setUsername("Carlos");
		try 
		{
			system.ns.setServerIP(InetAddress.getLocalHost().getHostAddress());
		} /* end try */
		catch (UnknownHostException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /* end catch */
		system.ns.start();
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String mssg;
		try {
			mssg = bufferedReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		system.ns.printUsers();
		
		try {
			mssg = bufferedReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} /* end main */

} /* end TestServer */
