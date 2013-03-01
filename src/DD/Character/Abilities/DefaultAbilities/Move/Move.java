package DD.Character.Abilities.DefaultAbilities.Move;

import org.newdawn.slick.SlickException;
import DD.ActionBox.Dice;
import DD.ActionBox.CombatSystem.TargetingSystem.TargetingSystem;
import DD.Character.Abilities.Ability;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.Interpreter.Move.*;
import DD.Message.ChooseTargetMessage;
import DD.Message.CombatMessage;
import DD.Message.TargetSelectedMessage;

/*****************************************************************************************************
 * Move allows the character to move on the board an amount that mirrors the Characters speed.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class Move extends Ability
{
	/************************************ Class Methods *************************************/
	public Move(int id) 
	{
		super(id, CombatSystem.ActionType.MOVE, "Move", "Move up do characters speed");
		done = false;
		// TODO Auto-generated constructor stub
	} /* end move constructor */
	
	protected void action() throws SlickException
	{ /* This method needs to be used in update */
		
		if ((character.getCurrentSpeed() <= 0 && !character.getHasMoveAction()))
		{/* check to see that we can continue moving */
			done = true;
			CombatMessage cm = new CombatMessage
					(
							character.getCharacterID(),
							null, 
							CombatSystem.ActionType.MOVE,
							CombatSystem.Action.MOVE,
							null
					);
			sendToInterpreter(cm);
		} /* end if */
		else
		{
			ChooseTargetMessage tcm = new ChooseTargetMessage
					(
						TargetingSystem.TargetCount.SINGLE,
						TargetingSystem.TargetShape.MOVE,
						TargetingSystem.TargetSelection.SELECTED,
						false,
						character.getCoordinate(),
						character.getCurrentSpeed(),
						this
					);
			ts.chooseTarget(tcm);
		} /* end else */
		
	} /* end action method */
	
	@Override
	public void obtainTarget(TargetSelectedMessage tsm) throws SlickException
	{ 
		/* create the combat message that will take care of this ability */
		Integer[] target = null;
		if (tsm.getTargets() != null )
		{
			target = new Integer[1];		/* There is only ever one target */
			target[0] = tsm.getTargets()[0].getCharacterID();
		}
		
		Dice dice = new Dice();
		
		int[] body = new int[I_Move.bodySize];
		body[I_Move.X_COORDINATE] = tsm.getPosition().x;
		body[I_Move.Y_COORDINATE] = tsm.getPosition().y;
		body[I_Move.ROLL] = dice.roll(Dice.DieSize.D20);	/* Roll a d20 just in case there is a special block that needs a check */
		
		CombatMessage cm = new CombatMessage
				(
						character.getCharacterID(),
						target, /* The target will be the null if there is no character in the square */
						CombatSystem.ActionType.MOVE,
						CombatSystem.Action.MOVE,
						body
				);
		sendToInterpreter(cm);
		
	} /* end obtainTarget method */
	
	protected void doneMoving()
	{/* Should be used by the "done" button. */
		done = true;
		ts.clearTargets();
	} /* end doneMoving method */

} /* end Move class */
