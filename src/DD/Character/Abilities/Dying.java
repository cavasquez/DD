package DD.Character.Abilities;

import org.newdawn.slick.SlickException;
import DD.CombatSystem.CombatSystem;
import DD.Message.CombatMessage;

/*****************************************************************************************************
 * The Dying ability is called whenever the player falls to or below 0 health. it should decrement 
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class Dying extends Ability
{
	/************************************ Class Methods *************************************/
	public Dying(int id) 
	{
		super(id, CombatSystem.ActionType.SYSTEM, CombatSystem.Action.DYING, "Dying", "Character is Dying");
		done = false;
	} /* end Constructor */

	@Override
	protected void action() throws SlickException 
	{
		CombatMessage cm = new CombatMessage
				(
					character.getCharacterID(),
					null,
					CombatSystem.ActionType.SYSTEM,
					CombatSystem.Action.DYING,
					null
				);
		sendToInterpreter(cm);
		done();
	} /* end action method */

} /* end Dying class */
