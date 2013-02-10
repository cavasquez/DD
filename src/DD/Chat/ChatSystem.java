package DD.Chat;

import DD.Chat.Interpreter.*;
import DD.Message.ChatMessage;

/*****************************************************************************************************
 * ChatSystem will take in ChatMessages from the MessageBox, interpret it, and route it through the 
 * network or will receive a ChatMessage and send it to the ChatBox to display. 
 * 
 * Having a ChatSystem will allow us to extend functionality by adding such things as /whisper or
 * similar features.
 ******************************************************************************************************/

public class ChatSystem 
{
	/************************************ Class Constants *************************************/
	private static int I = 0;
	public static final int PROCESS_MESSAGE = I++;
	public static final int WHISPER_MESSAGE = I++;
	public static final int ROLL_MESSAGE = I++;
	public static final int SEND_TO_CHAT_BOX = I++;
	public static final int SEND_TO_NETWORK_SYSTEM = I++;
	public static int NUM_OF_INTERPRETERS = I;
	
	/************************************ Class Attributes *************************************/
	private static ChatInterpreter[] system;		/* The core of CombatSystem */
	private static ChatBox chatBox= null;
	private static MessageBox messageBox = null;
	
	/************************************ Class Methods *************************************/
	public ChatSystem() 
	{
		system = new ChatInterpreter[NUM_OF_INTERPRETERS];
		system[PROCESS_MESSAGE] = new I_ProcessMessage();
		system[WHISPER_MESSAGE] = new I_WhisperMessage();
		system[ROLL_MESSAGE] = new I_RollMessage();
		system[SEND_TO_CHAT_BOX] = new I_SendToChatBox();
		system[SEND_TO_NETWORK_SYSTEM] = new I_SendToNetworkSystem();

	} /* end ChatSystem constructor */
	
	public void process(ChatMessage message)
	{/* Note that the ChatMessage sent by MessageBox is expected to be: 
	 	ChatMessage(userID, ChatMessage.SEND_TO_ALL, ChatSystem.PROCESS_MESSAGE, message)
	 	and will be assumed to have been constructed as instructed. */
		interpret(message);
	} /* end process method */
	
	private void interpret(ChatMessage message)
	{
		system[message.getAction()].interpret(message);
	} /* end interpret method */
	
	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
	public void setChatBox(ChatBox chatBox)
	{
		ChatSystem.chatBox = chatBox;
	} /* end setChatBox method */
	
	public void setMessageBox(MessageBox messageBox)
	{
		ChatSystem.messageBox = messageBox;
	} /* end setMessageBox method */
} /* end ChatSystem */
