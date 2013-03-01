package DD.CombatSystem.Interpreter;

import DD.ActionBox.ActionBox;
import DD.Character.DDCharacter;
import DD.CombatSystem.CombatSystem;
import DD.Message.CombatMessage;
import DD.Message.CombatValidationMessage;

/*****************************************************************************************************
 * I_EndAction will be a slightly different type of interpreter. Instead of interpreting an ability or action,
 * I_EndAction will be called at the end of any action and check to see (and modify) the remaining actions
 * the character may perform.
 * 
 * The index of the Action enum from ActionBox are to be used when giving a value to ACTION_PERFORMED.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_EndAction implements CombatInterpreter
{
	
	/************************************ Class Methods *************************************/
	@Override
	public CombatValidationMessage validate(CombatMessage cm) 
	{
		// TODO Auto-generated method stub
		return null;
	} /* end validate method */
	
	@Override
	public void interpret(CombatMessage cm) 
	{
		DDCharacter character = CombatSystem.getCharacter(cm.getSource());
		
		switch(cm.getAction())
		{
			case FREE:
				character.endHasFreeAction();
				break;
			case FULL_ROUND:
				character.endHasFullRoundAction();
				character.endHasMoveAction();
				character.endHasStandardAction();
				break;
			case FULL_ROUND_ENDSTART:
				character.endHasMoveAction();
				character.endHasStandardAction();
				character.endHasFullRoundAction();
				break;
			case IMMEDIATE:
				character.endHasImmediateAction();
				break;
			case STANDARD:
				character.endHasStandardAction();
				character.endHasFullRoundAction();
				character.endHasStartEndFullRoundAction();
				break;
			case MOVE:
				character.endHasMoveAction();
				character.endHasFullRoundAction();
				character.endHasStartEndFullRoundAction();
				break;
			case SWIFT:
				character.endHasSwiftAction();
				break;
	
		} /* end switch */
		
	} /* end interpret method */
	
} /* end I_EndAction class */