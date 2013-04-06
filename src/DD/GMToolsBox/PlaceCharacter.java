package DD.GMToolsBox;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

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
	private boolean delete;
	private boolean place;
	private GMToolsBox.Holder type;
	
	/************************************ Class Methods *************************************/
	public PlaceCharacter(int id, CharacterSheet sheet, GMToolsBox gmt, GMToolsBox.Holder type) 
	{
		super(id, CombatSystem.ActionType.SYSTEM, CombatSystem.Action.PLACE_CHARACTER, sheet.getName(), "place");
		this.sheet = sheet;
		this.gmt = gmt;
		this.type = type;
		delete = false;
		place = false;
	} /* end PlaceCharacter class */

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
	{
		
	} /* end update method */
	
	@Override
	protected void action() throws SlickException 
	{
		/* delete or action should be flagged during update (ie, this will have to override update).
		 * The PlaceCharacter button should really be thought of as 2 buttons */
		if (place) place();
		else if(delete) delete();
	} /* end action method */
	
	private void place() throws SlickException
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
	} /* end place method */

	private void delete()
	{
		removeCharacter();
		
		//TODO: save sheet? give to corresponding player to save? tell corresponding player to save?
	} /* end delete method */
	
	private void removeCharacter()
	{
		/* remove character from holder */
		gmt.removeCharacter(type, this.id);
		
		/* remove PlaceCharacter object from the list in GMToolsBox */
		gmt.removeComponent(this.id);
	} /* end remove method */
	
	@Override
	public void obtainTarget(TargetSelectedMessage tsm) throws SlickException 
	{
		if (tsm.getTargets() == null)
		{
			/* We can only place a character on a nonempty square */
			Integer[] body = new Integer[I_PlaceCharacter.BODY_SIZE];
			body[I_PlaceCharacter.CHARACTER_ID] = gmt.getNewCharacterID();
			body[I_PlaceCharacter.POS_X] = tsm.getPosition().x;
			body[I_PlaceCharacter.POS_Y] = tsm.getPosition().y;
			CombatMessage cm = new CombatMessage
					(
						null,
						null,
						CombatSystem.ActionType.SYSTEM,
						CombatSystem.Action.PLACE_CHARACTER,
						sheet,
						body
					);
			sendToInterpreter(cm);
			
			/* remove the character from the holder */
			removeCharacter();
			
			done(null);
		} /* end if */
		
	} /* end obtainTarget method */
	
	public void testDelete() throws SlickException
	{
		delete();
	}
	public void testPlace() throws SlickException
	{
		place();
	}

} /* end PlaceCharacter class */
