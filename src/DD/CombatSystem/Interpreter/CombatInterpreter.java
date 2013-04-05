package DD.CombatSystem.Interpreter;

import DD.ActionBox.ActionBox;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.TargetingSystem.TargetingSystem;
import DD.Message.CombatMessage;
import DD.Message.CombatValidationMessage;
import DD.Network.NetworkSystem;

/*****************************************************************************************************
 * CombatInterpreter will be used by CombatSystem to interpret every action. CombatInterpreter will
 * have two methods: validate and interpret. 
 * 
 * The interpreter should have an equivalent Ability object for whatever it is interpreting. The 
 * interpreter will be named I_AbilityName so as to provide an easy and obvious scheme.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public abstract class CombatInterpreter 
{
	/************************************ Class Attributes *************************************/
	protected static CombatSystem cs = null;
	protected static TargetingSystem ts = null;
	protected static ActionBox ab = null;		/* Used by some interpreters to modify the ActionBox */
	
	/************************************ Class Methods *************************************/
	public abstract CombatValidationMessage validate(CombatMessage cm);
	public abstract void interpret(CombatMessage cm);
	
	/****************************************************************************************
	 ************************************ Setter Methods ************************************
	 ****************************************************************************************/
	public static void setCombatSystem(CombatSystem cs)
	{
		CombatInterpreter.cs = cs;
	} /* end setCombatSystem method */
	
	public static void setTargetingSystem(TargetingSystem ts)
	{
		CombatInterpreter.ts = ts;
	} /* end setCombatSystem method */
	
	public static void setActionBox(ActionBox ab)
	{
		CombatInterpreter.ab = ab;
	} /* end setActionBox method */	
	
} /* end CombatInterpreter class */
