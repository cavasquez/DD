package DD.Network.Server.Interpreter;

import DD.CombatSystem.CombatSystem;
import DD.Message.CombatMessage;
import DD.Message.NetworkMessage;
import DD.Network.Network;

/*****************************************************************************************************
 * I_CombatMessage will be used to interpret and process all CombatMessages sent to the Server
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_CombatMessage extends ServerInterpreter
{
	@Override
	public void interpret(int listenerID, NetworkMessage message) 
	{
		/* Communicate the move to peers */
		system.sendMessage(Network.GM_USER_ID, Network.EVERYONE, message, message.getSender());
		
		/* Run the combat message through the combat system */
		cs.process((CombatMessage)message.getMessage());
		
	} /* end interpret method */

} /* end I_CombatMessage method */
