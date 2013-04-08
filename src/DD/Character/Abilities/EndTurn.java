package DD.Character.Abilities;

import org.newdawn.slick.SlickException;

import DD.CombatSystem.CombatSystem;
import DD.Message.CombatMessage;

/*****************************************************************************************************
 * EndTurn allows the player to end their turn.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class EndTurn extends Ability
{
	/************************************ Class Methods *************************************/
	public EndTurn(int id)
	{
		super(id, CombatSystem.ActionType.SYSTEM, CombatSystem.Action.END_TURN, "End Turn", "End the players turn.");
	} /* end EndTurn constructor */

	@Override
	protected void action() throws SlickException 
	{
		/* First, clear the ActionBox of it's components (since there should be none left) */
		owner.removeAllComponents();
		
		/* Lastly, send the message to the interpreter */
		CombatMessage cm = new CombatMessage
			(
				character.getCharacterID(),
				null, 
				actionType,
				action,
				null
			);
		
		sendToInterpreter(cm);
		done();
	} /* end action() method */
} /* end EndTurn method */
