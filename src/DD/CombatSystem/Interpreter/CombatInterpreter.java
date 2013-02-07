package DD.CombatSystem.Interpreter;

import DD.CombatSystem.CombatMessage;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.CombatValidationMessage;

/*****************************************************************************************************
 * CombatInterpreter will be used by CombatSystem to interpret every action. CombatInterpreter will
 * have two methods: validate and interpret. 
 * 
 * The interpreter should have an equivalent ability for which it is interpreting. The interpreter will
 * be named I_AbilityName so as to provide an easy and obvious scheme.
 ******************************************************************************************************/

public interface CombatInterpreter 
{
	/************************************ Class Methods *************************************/
	public CombatValidationMessage validate(CombatMessage cm);
	public void interpret(CombatMessage cm);
	
} /* end CombatInterpreter class */
