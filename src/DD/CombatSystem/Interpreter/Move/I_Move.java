package DD.CombatSystem.Interpreter.Move;

import DD.ActionBox.CombatSystem.TargetingSystem.Coordinate;
import DD.Character.DDCharacter;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.Interpreter.CombatInterpreter;
import DD.MapTool.CharacterObjects;
import DD.MapTool.ObjectsPriorityStack;
import DD.Message.CombatMessage;
import DD.Message.CombatValidationMessage;

/*****************************************************************************************************
 * Interpreter for Move
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_Move implements CombatInterpreter
{
	/************************************ Class Constants *************************************/
	private static int I = 0;
	public static final int X_COORDINATE = I++;
	public static final int Y_COORDINATE = I++;
	public static final int ROLL = I++;			/* Roll for special squares such as traps */
	public static final int bodySize = I;
	
	/************************************ Class Methods *************************************/
	public I_Move()
	{
		
	} /* end  I_Move constructor */

	@Override
	public CombatValidationMessage validate(CombatMessage cm) {
		// TODO Auto-generated method stub
		return null;
	} /* end validate method */

	@Override
	public void interpret(CombatMessage cm) 
	{
		if(cm.getTarget() == null)
		{/* There is no Character on the square. we can move there */
			//TODO: check for attacks of opportunity
			//TODO: check for special blocks such as traps
			
			/* First, get the character to be moved */
			CharacterObjects charObj = (CharacterObjects) CombatSystem.getMap().objectsStack[cm.getBody()[X_COORDINATE]][cm.getBody()[Y_COORDINATE]].peek();
			DDCharacter mover = charObj.ddchar;
			
			
			/* Check for diagonal moves */
			int diagonalPenalty = 1;
			if(mover.getCoordinate().x != cm.getBody()[X_COORDINATE] && mover.getCoordinate().y != cm.getBody()[Y_COORDINATE]) diagonalPenalty = 2;
			
			/* decrement speed and place the character. */
			ObjectsPriorityStack stack = CombatSystem.getMap().objectsStack[cm.getBody()[X_COORDINATE]][cm.getBody()[Y_COORDINATE]];
			int movePenalty = stack.peek().getMovePenalty();
			mover.setCurrentSpeed(mover.getCurrentSpeed() - (movePenalty * diagonalPenalty)); /* set new speed */
			
			/* Place the character */
			/* Remove the characters container from its old position */
			CombatSystem.getMap().objectsStack[cm.getBody()[X_COORDINATE]][cm.getBody()[Y_COORDINATE]].remove(charObj);
			
			/* Next, place the charObj in the new position */
			CombatSystem.getMap().placeObjects(cm.getBody()[X_COORDINATE], cm.getBody()[Y_COORDINATE], charObj);
			
			/* Finally, update the movers coordinates */
			mover.setCoordiante(new Coordinate(cm.getBody()[X_COORDINATE], cm.getBody()[Y_COORDINATE])); /* set new coordinate */
			
		} /* end if */
		
	} /* end interpret method */
	
} /* end I_StandardAttack class */
