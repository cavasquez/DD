package DD.CombatSystem.Interpreter;

import DD.CombatSystem.CombatMessage;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.CombatValidationMessage;

/*****************************************************************************************************
 * CombatInterpreter will be used by CombatSystem to interpret every action. CombatInterpreter will
 * have two methods: validate and interpret. 
 * 
 * Interpret takes in CombatSystem so it can gain access to the Characters and Map.
 ******************************************************************************************************/

public interface CombatInterpreter 
{
	/************************************ Class Methods *************************************/
	public CombatValidationMessage validate(CombatMessage cm);
	public void interpret(CombatMessage cm);
	
} /* end CombatInterpreter class */
