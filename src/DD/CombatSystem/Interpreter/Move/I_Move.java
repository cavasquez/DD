package DD.CombatSystem.Interpreter.Move;

import DD.Character.DDCharacter;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.Interpreter.CombatInterpreter;
import DD.CombatSystem.TargetingSystem.Coordinate;
import DD.MapTool.CharacterObjects;
import DD.MapTool.ObjectsPriorityStack;
import DD.Message.CombatMessage;
import DD.Message.CombatValidationMessage;

/*****************************************************************************************************
 * Interpreter for Move
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_Move extends CombatInterpreter
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
	public CombatValidationMessage validate(CombatMessage cm) 
	{
		// TODO Auto-generated method stub
		CombatValidationMessage returner = new CombatValidationMessage(true, null); // TODO: Check for validity
		return returner;
	} /* end validate method */

	@Override
	public void interpret(CombatMessage cm) 
	{
		if(cm.getTarget() == null)
		{/* There is no Character on the square. we can move there */
			//TODO: check for attacks of opportunity
			//TODO: check for special blocks such as traps
			
			/* First, get the character to be moved */
			DDCharacter mover = cs.getCharacter(cm.getSource());
			CharacterObjects charObj = (CharacterObjects) cs.getMap().objectsStack[mover.getCoordinate().x][mover.getCoordinate().y].peek();
			
			/* Check for diagonal moves */
			int diagonalPenalty = 1;
			if((mover.getCoordinate().x != cm.getBody()[X_COORDINATE]) && (mover.getCoordinate().y != cm.getBody()[Y_COORDINATE]))
			{
				if(!mover.getMovedDiagonal())
				{ /* Character has not moved diagonal yet, so we do not apply penalty and set flag */
					mover.movedDiagonal();
				} /* end if */
				else
				{ /* Character has moved diagonal, apply penalty */
					diagonalPenalty = 2;
				} /* end else */
			} /* end if */
			
			/* Get speed penalty, decrement speed and place the character. */
			ObjectsPriorityStack stack = cs.getMap().objectsStack[cm.getBody()[X_COORDINATE]][cm.getBody()[Y_COORDINATE]];
			int movePenalty = stack.peek().getMovePenalty();
			mover.setCurrentSpeed(mover.getCurrentSpeed() - (movePenalty * diagonalPenalty)); /* set new speed */
			
			/* Place the character */
			/* Remove the characters container from its old position */
			cs.getMap().objectsStack[mover.getCoordinate().x][mover.getCoordinate().y].remove(charObj);
			
			/* Next, place the charObj in the new position */
			cs.getMap().place(cm.getBody()[X_COORDINATE], cm.getBody()[Y_COORDINATE], charObj);
			
			/* Finally, update the movers coordinates */
			mover.setCoordiante(new Coordinate(cm.getBody()[X_COORDINATE], cm.getBody()[Y_COORDINATE]));

		} /* end if */
		
	} /* end interpret method */
	
} /* end I_StandardAttack class */
