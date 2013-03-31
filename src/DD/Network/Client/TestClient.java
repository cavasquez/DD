package DD.Network.Client;

import DD.System.DDSystem;

public class TestClient 
{
	public static void main(String[] args) 
	{
		DDSystem system = new DDSystem();
		system.client();
		system.ns.setUsername("Fernando");
		system.ns.setServerIP("10.69.5.11");
		system.ns.start();
	} /* end main */
} /* end TestClient class */
