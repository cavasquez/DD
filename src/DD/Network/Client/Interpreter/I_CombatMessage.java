package DD.Network.Client.Interpreter;

import DD.CombatSystem.CombatSystem;
import DD.Message.CombatMessage;
import DD.Message.NetworkMessage;
import DD.Trash.NetworkInterpreter;

/*****************************************************************************************************
 * I_CombatMessage will pass combat messages to CombatSystem
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_CombatMessage extends ClientInterpreter
{
	@Override
	public void interpret(NetworkMessage message)
	{
		CombatSystem cs = system.getCombatSystem();
		
		/* Run the combat message through the combat system */
		cs.process((CombatMessage)message.getMessage());
	} /* end Interpret method */
	
} /* end I_CombatMessage class */
