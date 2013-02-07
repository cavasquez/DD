package DD.Chat;

import DD.Network.Message;

/*****************************************************************************************************
 * Chat message will provide a way for MessageBox (the view) to communicate with ChatSystem (the controller)
 * and for the server to send chat messages to the ChatBox. It will contain the basic necessary
 * data that is needed when sending and getting messages.
 ******************************************************************************************************/

public class ChatMessage extends Message
{
	/************************************ Class Constants *************************************/
	public static final int SEND_TO_ALL = -1;
	
	/************************************ Class Attributes *************************************/
	private int sender;
	private int receiver;
	private String message;
	
	/************************************ Class Methods *************************************/
	protected ChatMessage(int sender, int receiver, String message) 
	{
		super(Message.CHAT_MESSAGE);
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		
	} /* end ChatMessage constructor */

	/****************************************************************************************
	 ************************************ Getter Methods ************************************
	 ****************************************************************************************/
	public int getSender() 
	{
		return sender;
	} /* end getSender method */

	public int getReceiver() 
	{
		return receiver;
	} /* end getReceiver method */

	public String getMessage() 
	{
		return message;
	} /* end getMessage method */

} /* end ChatMessage */
