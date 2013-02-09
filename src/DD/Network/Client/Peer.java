package DD.Network.Client;

import java.net.InetAddress;

/*****************************************************************************************************
 * Peer is a data structure that will be used by PeerTable. Peer will hold some peer information 
 * including peerID, username, and the IP address associated with that peer. 
 ******************************************************************************************************/

public class Peer 
{
	/************************************ Class Attributes *************************************/
	public int peerID;
	public String username;
	public InetAddress ip;
	
	/************************************ Class Methods *************************************/
	public Peer(int peerID, String username, InetAddress ip)
	{
		this.peerID = peerID;
		this.username = username;
		this.ip = ip;
	} /* end Peer constructor */
	
} /* end Peer class */
