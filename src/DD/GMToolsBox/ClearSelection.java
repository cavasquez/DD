package DD.GMToolsBox;

import org.newdawn.slick.SlickException;

import DD.Character.Abilities.TargetAbility;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.CombatSystem.Action;
import DD.CombatSystem.CombatSystem.ActionType;
import DD.Message.TargetSelectedMessage;

/*****************************************************************************************************
 * DClears target blocks
 * 
 * @author Carlos Vasquez / Michael VanWie
 ******************************************************************************************************/

public class ClearSelection extends TargetAbility
{
	public ClearSelection(int id) 
	{
		super(id, CombatSystem.ActionType.SYSTEM, null, "Clear Selection", "Clears target blocks");
		// TODO Auto-generated constructor stub
	} /* end constructor */

	@Override
	public void obtainTarget(TargetSelectedMessage tsm) throws SlickException{} /* Do nothing */

	@Override
	protected void action() throws SlickException 
	{
		ts.clearTargets();		
	} /* end action method */

} /* end cleearSelection class */
