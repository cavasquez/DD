package DD.Message;

import java.net.InetAddress;

/*****************************************************************************************************
* AddUserMessage is a message send by the Server to the client informing it that there is a new peer
* in the network. It contains the username of the peer and its associated playerID
* 
* @author Carlos Vasquez
******************************************************************************************************/

public class AddUserMessage extends Message
{
	private static final long serialVersionUID = 1513964380888153680L;
	/************************************ Class Attributes *************************************/
	private int playerID;
	private String username;
	private InetAddress ip;
	
	/************************************ Class Methods *************************************/
	public AddUserMessage(int playerID, String username, InetAddress ip) 
	{
		super(Message.ADD_USER_MESSAGE);
		this.playerID = playerID;
		this.username = username;
		this.ip = ip;
	} /* end AddUserMessage constructor */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public int getPlayerID()
	{
		return playerID;
	} /* end getPlayerID method */
	
	public String getUsername()
	{
		return username;
	} /* end getUsername method */
	
	public InetAddress getIP()
	{
		return ip;
	} /* end getIP method */
	
} /* end AddUserMessage class */
