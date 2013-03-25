package DD.GMToolsBox;

import org.newdawn.slick.SlickException;
import DD.Character.Abilities.TargetAbility;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.Interpreter.Standard.I_StandardAttack;
import DD.CombatSystem.Interpreter.System.I_RemoveCharacter;
import DD.CombatSystem.TargetingSystem.TargetingSystem;
import DD.Message.ChooseTargetMessage;
import DD.Message.CombatMessage;
import DD.Message.TargetSelectedMessage;
import DD.Network.Network;

/*****************************************************************************************************
 * RemoveCharacter will give the GM the ability to remove a Character from the board (ie form play) and
 * then add the Character to the GMTools holder.
 * 
 * TODO: VERY IMPORTANT!! THIS METHOD STILL NEEDS A BUTTON TO MARK IT AS DONE AND THEN NOT DONE AGAIN(So it can be used again)!
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class RemoveCharacter extends TargetAbility
{
	/************************************ Class Attributes *************************************/
	private GMToolsBox gmt;			/* Give PlaceCharacter access tot he GMToolBox */
	
	/************************************ Class Methods *************************************/
	public RemoveCharacter(int id) 
	{
		super(id, CombatSystem.ActionType.SYSTEM, CombatSystem.Action.REMOVE_CHARACTER, "Remove Character", "Removes character from the map.");
		// TODO Auto-generated constructor stub
	} /* end RemoveCharacter constructor */

	@Override
	protected void action() throws SlickException 
	{ 
		ChooseTargetMessage tcm = new ChooseTargetMessage
				(
					TargetingSystem.TargetCount.SINGLE,
					TargetingSystem.TargetShape.ALL,
					TargetingSystem.TargetSelection.SELECTED,
					false,
					null,
					0,
					this
				);
		ts.chooseTarget(tcm);
		
	} /* end action method */

	@Override
	public void obtainTarget(TargetSelectedMessage tsm) throws SlickException 
	{
		if (tsm.getTargets() != null)
		{
			/* We can only remove an existing character */
			Integer[] body = new Integer[I_StandardAttack.BODY_SIZE];
			body[I_RemoveCharacter.CHARACTER_ID] = gmt.getNewCharacterID();
			body[I_RemoveCharacter.POS_X] = tsm.getPosition().x;
			body[I_RemoveCharacter.POS_Y] = tsm.getPosition().y;
			CombatMessage cm = new CombatMessage
					(
						null,
						null,
						CombatSystem.ActionType.SYSTEM,
						CombatSystem.Action.REMOVE_CHARACTER,
						body
					);
			sendToInterpreter(cm);
			
			/* Place the character into the holder. */
			CharacterSheet sheet = cs.getCharacter(cm.getBody()[I_RemoveCharacter.CHARACTER_ID]).getSheet();
			/* Note that the GM will only ever have mobs and no other player will have mobs */
			if(sheet.getNetID() == Network.GM_USER_ID) gmt.addCharacter(GMToolsBox.Holder.MOB, sheet);
			else gmt.addCharacter(GMToolsBox.Holder.PLAYER, sheet);
			
			this.sendToInterpreter(cm);
			done(null);
		} /* end if */
		
	} /* end obtainTarget method */

} /* end RemoveCharacter class */
