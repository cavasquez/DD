package DD.GMToolsBox;

import org.newdawn.slick.SlickException;

import DD.Character.Abilities.Ability;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.CombatSystem.CombatSystem;
import DD.Message.CombatMessage;

/*****************************************************************************************************
 * AddCharacter will be called by the Client to send a character to the Server.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class AddCharacter extends Ability
{
	/************************************ Class Attributes *************************************/
	private CharacterSheet sheet;	/* characters data */
	
	/************************************ Class Methods *************************************/
	public AddCharacter(int id, CharacterSheet sheet) 
	{
		super(id, CombatSystem.ActionType.SYSTEM, CombatSystem.Action.ADD_CHARACTER, sheet.getName(), "add");
		this.sheet = sheet;
	} /* end PlaceCharacter class */

	@Override
	public void action() throws SlickException 
	{
		/* We can only place a character on a nonempty square */
		CombatMessage cm = new CombatMessage
				(
					null,
					null,
					CombatSystem.ActionType.SYSTEM,
					CombatSystem.Action.ADD_CHARACTER,
					sheet,
					null
				);
		sendToServer(cm);
		done();
		
	} /* end action method */
	
} /* end AddCharacter class */
