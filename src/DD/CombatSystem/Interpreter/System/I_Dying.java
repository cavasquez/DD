package DD.CombatSystem.Interpreter.System;

import DD.Character.DDCharacter;
import DD.CombatSystem.Interpreter.CombatInterpreter;
import DD.Message.CombatMessage;
import DD.Message.CombatValidationMessage;

/*****************************************************************************************************
 * Interpreter for Dying
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_Dying extends CombatInterpreter
{
	/************************************ Class Methods *************************************/
	@Override
	public CombatValidationMessage validate(CombatMessage cm) 
	{
		// TODO Auto-generated method stub
				CombatValidationMessage returner = new CombatValidationMessage(true, null); // TODO: Check for validity
				return returner;
	} /* end validate method */

	@Override
	public void interpret(CombatMessage cm) 
	{
		/* Player will always suffer -1 damage when dying */
		/* MaxValue/2 for attack to prevent overflow but make it feasilbly unblockable */
		/* Target is always source player */
		cs.getCharacter(cm.getSource()).defend(Integer.MAX_VALUE/2, 1, DDCharacter.ACType.NORMAL);
		
	} /* end interpret method */

} /* end I_Dying class */
