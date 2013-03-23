package DD.GMToolsBox;

import org.newdawn.slick.SlickException;
import DD.Character.Abilities.TargetAbility;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.Interpreter.Standard.I_StandardAttack;
import DD.CombatSystem.Interpreter.System.I_PlaceCharacter;
import DD.CombatSystem.TargetingSystem.TargetingSystem;
import DD.Message.ChooseTargetMessage;
import DD.Message.CombatMessage;
import DD.Message.TargetSelectedMessage;

/*****************************************************************************************************
 * PlaceCharacter will five the GM the ability to place the held character as well as remove the character
 * from play.Furthermore, if the character is a main character, it should serialize the character data.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class PlaceCharacter extends TargetAbility
{
	/************************************ Class Attributes *************************************/
	private CharacterSheet sheet;	/* characters data */
	private GMToolsBox gmt;			/* Give PlaceCharacter access tot he GMToolBox */
	
	/************************************ Class Methods *************************************/
	public PlaceCharacter(int id, CharacterSheet sheet, GMToolsBox gmt) 
	{
		super(id, CombatSystem.ActionType.SYSTEM, CombatSystem.Action.PLACE_CHARACTER, sheet.getName(), "place");
		this.sheet = sheet;
		this.gmt = gmt;
	} /* end PlaceCharacter class */

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
		Integer[] body = new Integer[I_StandardAttack.BODY_SIZE];
		body[I_PlaceCharacter.CHARACTER_ID] = gmt.getNewCharacterID();
		body[I_PlaceCharacter.POS_X] = tsm.getPosition().x;
		body[I_PlaceCharacter.POS_Y] = tsm.getPosition().y;
		CombatMessage cm = new CombatMessage
				(
					character.getCharacterID(),
					null,
					CombatSystem.ActionType.STANDARD,
					CombatSystem.Action.STANDARD_ATTACK,
					sheet,
					body
				);
		sendToInterpreter(cm);
		done();
		
	} /* end obtainTarget method */

} /* end PlaceCharacter class */
