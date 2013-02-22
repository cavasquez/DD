package DD.Chat.Interpreter;

import DD.Chat.ChatSystem;
import DD.Message.ChatMessage;
import DD.Network.NetworkSystem;

/*****************************************************************************************************
 * I_SendToNetworkSystem will send the provided message to the NetworkSystem. Note that this class
 * is only ever invoked once by the sender. Once it gets to NetworkSystem, the ChatMessage is set to
 * go straight to the ChatBox
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_SendToNetworkSystem extends ChatInterpreter
{
	/************************************ Class Attributes *************************************/
	NetworkSystem network = null;
	
	/************************************ Class Methods *************************************/
	public I_SendToNetworkSystem()
	{
		network = new NetworkSystem();
		/* Assume that NetworkSystem's networkType has already been 
		 * set prior to ChatSystems initialization */
	} /* end I_SendToNetworkSystem constructor */
	
	@Override
	public void interpret(ChatMessage message)
	{
		message.setAction(ChatSystem.SEND_TO_CHAT_BOX);
		network.sendMessage(message.getSender(), message.getReceiver(), message);
		
	} /* end interpret method */
	
} /* end I_SendToNetworkSystem class */
