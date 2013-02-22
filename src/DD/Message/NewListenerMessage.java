package DD.Message;

import java.net.InetAddress;

import DD.Network.Listener;

/*****************************************************************************************************
* NewListenerMessage is a message that tells NetworkServer there is a new Listener available and that
* it should configure itself accordingly.
* 
* @author Carlos Vasquez
******************************************************************************************************/

public class NewListenerMessage extends Message
{
	/************************************ Class Attributes *************************************/
	private int listenerID;
	private Listener listener = null;
	private InetAddress ip = null;
	
	/************************************ Class Methods *************************************/
	public NewListenerMessage(int listenerID, Listener listener, InetAddress ip)
	{
		super(NEW_LISTENER_MESSAGE);
		this.listenerID = listenerID;
		this.listener = listener;
		this.ip = ip;
	} /* end NewListenerMessage constructor */

	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public int getListenerID() 
	{
		return listenerID;
	} /* end getListenerID method */

	public Listener getListener() 
	{
		return listener;
	} /* end getListener method */

	public InetAddress getIp() 
	{
		return ip;
	} /* end getIP method */
	
} /* end NewListenerMessage class */
