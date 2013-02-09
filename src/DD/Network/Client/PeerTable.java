package DD.Network.Client;

import java.net.InetAddress;
import java.util.ArrayList;

/*****************************************************************************************************
 * PeerTable will hold all the Peer objects used by ClientSystem and provide it with some methods to
 * modify and get Peer information.
 ******************************************************************************************************/

public class PeerTable 
{
	/************************************ Class Attributes *************************************/
	private ArrayList<Peer> peerList = null;
	
	/************************************ Class Methods *************************************/
	public PeerTable()
	{
		peerList = new ArrayList<Peer>();
	} /* end PeerTable constructor */
	
	public boolean addPeer(int peerID, String username, InetAddress ip)
	{
		boolean valid = true;
		
		/* check to see that no peer with the same credentials exist */
		for (Peer peer : peerList)
		{
			if 
			(
				peer.peerID == peerID &&
				peer.username.matches(username) &&
				peer.ip.equals(ip)
			)
			{
				valid = false;
			} /* end if */
		} /* end for loop */
		
		if (valid) peerList.add(new Peer(peerID, username, ip));
		
		return valid;
	} /* end addPeer method */
	
	public boolean removePeer(int peerID)
	{
		boolean removed = false;
		
		for (Peer peer : peerList)
		{
			if(peer.peerID == peerID) 
			{
				int index = peerList.indexOf(peer);
				peerList.remove(index);
				removed = true;
				
			} /* end if */
			
		} /* end loop */
		
		return removed;
	} /* end removePeer method */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public Integer getPeerID(String username)
	{
		Integer peerID = null;
		for(Peer peer : peerList)
		{
			if (peer.username.equals(username)) peerID = peer.peerID;
		} /* end for loop */
		
		return peerID;
	} /* end getPeerID method */
	
	public InetAddress getIP(int peerID)
	{
		InetAddress ip = null;
		for(Peer peer : peerList)
		{
			if (peer.peerID == peerID) ip = peer.ip;
		} /* end for loop */
		
		return ip;
	} /* end getPeerID method */
	
} /* end PeerTable class */
