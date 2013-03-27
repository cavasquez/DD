package DD.Character.Abilities;

import org.newdawn.slick.SlickException;

import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.CombatSystem.Action;
import DD.CombatSystem.CombatSystem.ActionType;
import DD.Message.CombatMessage;
import DD.Message.CombatValidationMessage;
import DD.Message.TargetSelectedMessage;

/*****************************************************************************************************
 * TargetAbility is a more specialized ability that allows the player to target an object.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public abstract class TargetAbility extends Ability
{

	public TargetAbility(int id, ActionType actionType, Action action, String name, String description) 
	{
		super(id, actionType, action, name, description);
		// TODO Auto-generated constructor stub
	} /* end TargetAbility constructor */
	
	public abstract void obtainTarget(TargetSelectedMessage tsm) throws SlickException;
	/* obtainTarget will be called on by the TargetSystem signaling that a target(s) has
 	been chosen */
	
	@Override
	public void sendToInterpreter(CombatMessage cm) throws SlickException
	{
		/* Send the message to the proper interpreter and send it through the network */
		/* First, send to network */
		//TODO: send to network
		
		if(done)
		{
			/* We are done, so we must reformat the message */
			/* First, tell CombatSystem that action is terminating */
			CombatMessage endAction = new CombatMessage
				(
					cm.getSource(),
					null,
					actionType,
					CombatSystem.Action.END_ACTION,
					null
				);
			//TODO: "Unclick" ie reset action box. By now, interpreter should have changed turn states
			//TODO: Check for validity of cvm
			CombatValidationMessage cvm = cs.process(endAction);
		} /* end if */
		else
		{
			/* re-activate ability */
			//TODO: Check for validity of cvm
			CombatValidationMessage cvm = cs.process(cm);
			activate();
		} /* end if */
	} /* end sendToInterpreter method */
	
	@Override
	public void done() throws SlickException
	{
		/* This action should be performed when the character clicks done */
		done(character.getCharacterID());
	} /* end done method */
	
	public void done(Integer characterID) throws SlickException
	{
		/* This action should be performed when the character clicks done */
		done = true;
		ts.clearTargets();
		CombatMessage cm = new CombatMessage
			(
				characterID,
				null, 
				actionType,
				action,
				null
			);
		sendToInterpreter(cm); /* this should send a message to the CombatSystem telling it we are done */
	} /* end done */

} /* end TargetAbility method */
