package DD.CombatSystem.Interpreter.System;

import org.newdawn.slick.SlickException;

import DD.CombatSystem.Interpreter.CombatInterpreter;
import DD.Message.CombatMessage;
import DD.Message.CombatValidationMessage;

/*****************************************************************************************************
 * I_RemoveCharacter will simply remove a character from the map. It may also force the corresponding 
 * owner of the character sheet to save it (serialize it).
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_RemoveCharacter extends CombatInterpreter
{
	/************************************ Class Constants *************************************/
	private static int I = 0;
	public static final int CHARACTER_ID = I++;		/* Characters ID */
	public static final int POS_X = I++;			/* X coordinate */
	public static final int POS_Y = I++;			/* Y Coordinate */
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
		/* Remove the character from the CombatSystem */
		cs.removeCharacter(cm.getBody()[CHARACTER_ID]);
		
		/* Modify the combat order */
		cs.removeFromOder(cm.getBody()[CHARACTER_ID]);
		
		/* remove the character from the map (assume character is top most element) */
		try 
		{
			cs.getMap().remove(cm.getBody()[POS_X], cm.getBody()[POS_Y]);
		} /* end try */
		catch (SlickException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /* end catch */
		
	} /* end interpret method */

} /* end I_RemoveCharacter class */
