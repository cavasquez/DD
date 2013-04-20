package DD.Network;

import DD.Chat.ChatSystem;
import DD.CombatSystem.CombatSystem;
import DD.Message.NetworkMessage;

/*****************************************************************************************************
 * MessageInterpreter will be used by ServerSystem and ClientSystem to interpret every message. 
 * CombatInterpreter will have two methods: validate and interpret. 
 * 
 * The interpreter should have an equivalent ability for which it is interpreting. The interpreter will
 * be named I_AbilityName so as to provide an easy and obvious scheme.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public abstract class NetworkInterpreter 
{
	/************************************ Class Attributes *************************************/
	static protected CombatSystem cs;
	static protected ChatSystem chat;
	
	/************************************ Class Methods *************************************/
	static public void setCombatSystem(CombatSystem cs)
	{
		NetworkInterpreter.cs = cs;
	} /* end setCombatSystem method */
	
	static public void setChatSystem(ChatSystem chat)
	{
		NetworkInterpreter.chat = chat;
	} /* end setCombatSystem method */

} /* end MessageInterpreter class */
