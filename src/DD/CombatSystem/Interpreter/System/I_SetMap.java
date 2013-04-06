package DD.CombatSystem.Interpreter.System;

import DD.CombatSystem.Interpreter.CombatInterpreter;
import DD.MapTool.Map;
import DD.Message.CombatMessage;
import DD.Message.CombatValidationMessage;

/*****************************************************************************************************
 * The Interpreter for SetMap. This will reconfigure the CombatSystem and other related systems to
 * use the given map;
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_SetMap extends CombatInterpreter
{
	/************************************ Class Constants *************************************/
	private static int I = 0;
	public static final int MAP = I++;		/* Map */
	public static final int BODY_SIZE = I;
	
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
		cs.setMap((Map) cm.getBodyData()[MAP]);
		ts.setMap((Map) cm.getBodyData()[MAP]);
		
	} /* end interpret method */

} /* end I_SetMap class */
