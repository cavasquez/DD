package DD.Character.Abilities;

import org.newdawn.slick.SlickException;

import DD.ActionBox.Dice;
import DD.Character.DDCharacter;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.CombatSystem.Action;
import DD.CombatSystem.CombatSystem.ActionType;
import DD.Message.CombatMessage;

/*****************************************************************************************************
 * StartCombatPhase is a special ability that should only be provided to the GM(ie the server).
 * It will allow the GM to begin a new CombatPhase.
 * 
 * The Body will consist of the CharacterID's in order
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class StartCombatPhase extends Ability 
{

	public StartCombatPhase(int id) 
	{
		super(id, CombatSystem.ActionType.SYSTEM, CombatSystem.Action.START_COMBAT_PHASE, "Start Combat Phase", "Game will roll for initiative and order players accordingly.");
		// TODO Auto-generated constructor stub
	} /* end StartCombatPhase constructor */

	@Override
	protected void action() throws SlickException 
	{
		DDCharacter[] list = cs.getCharacterList();
		Dice dice = new Dice(20);
		
		if (list.length > 0)
		{
			/* Create the head of the tree */
			BinarySearchTree head = new BinarySearchTree(list[0].getCharacterID(), dice.roll(1), list[0].getCharacterSheet().rawStats[CharacterSheet.ABILITY_DEXTERITY][CharacterSheet.ABILITY_MODIFIER]);
			
			/* Now, create the tree/order */
			for (int i = 1; i < list.length; i++)
			{
				head.add(list[i].getCharacterID(), dice.roll(1), list[i].getCharacterSheet().rawStats[CharacterSheet.ABILITY_DEXTERITY][CharacterSheet.ABILITY_MODIFIER]);
			} /* end for loop */
			
			/* Make the combat message. The body will consist of the CharacterID's in the correct order. */
			CombatMessage cm = new CombatMessage
				(
					null,
					null, 
					actionType,
					action,
					head.toArray()
				);
			/* Since StartCombatPhase is a "special" ability, it will not go through sendToInterpreter
			 * as usual. Nor will it call done() */
			// sendToInterpreter(cm); 
			cs.process(cm);
			
		} /* end if */
		
	} /* end action method */

} /* end StartCombatPhase method */
