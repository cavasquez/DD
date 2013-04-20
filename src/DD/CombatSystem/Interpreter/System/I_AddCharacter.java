package DD.CombatSystem.Interpreter.System;

import DD.Character.CharacterSheet.CharacterSheet;
import DD.CombatSystem.Interpreter.CombatInterpreter;
import DD.GMToolsBox.GMToolsBox;
import DD.Message.CombatMessage;
import DD.Message.CombatValidationMessage;

/*****************************************************************************************************
 * I_AddCharacter will add a character to the GMToolsBox
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_AddCharacter extends CombatInterpreter
{
	/************************************ Class Constants *************************************/
	private static int I = 0;
	public static final int SHEET = I++;		/* Characters ID */
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
		System.out.println("Interpreting AddCharacter");
		GMToolsBox gmtb = cs.getGMToolsBox();
		if(gmtb != null)
		{
			/* Assume adding player */
			gmtb.addCharacter(GMToolsBox.Holder.PLAYER, cm.getCharacterData());
		} /* end if */
		
	} /* end interpret method */
	
} /* end I_AddCharacter class */
