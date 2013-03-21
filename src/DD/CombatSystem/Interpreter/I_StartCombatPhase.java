package DD.CombatSystem.Interpreter;

import DD.ActionBox.Dice;
import DD.Character.DDCharacter;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.CombatSystem.CombatSystem;
import DD.Message.CombatMessage;
import DD.Message.CombatValidationMessage;

/*****************************************************************************************************
 * I_StartCombatPhase will start the CombatPhase in the CombatSystem. This means that there was no 
 * Combat to begin with and we will now begin ordering the combatants based on their initiative rolls
 * and Dex modifier. 
 * 
 * In regards to the initiative "rolls", the interpreter will actually roll for each player.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_StartCombatPhase extends CombatInterpreter
{
	/************************************ Class Methods *************************************/
	@Override
	public CombatValidationMessage validate(CombatMessage cm) 
	{
		// TODO Auto-generated method stub
		return null;
	} /* end validate method */

	@Override
	public void interpret(CombatMessage cm) 
	{
		DDCharacter[] list = CombatSystem.getCharacterList();
		Dice dice = new Dice(20);
		
		if (list.length > 0)
		{
			BinarySearchTree head = new BinarySearchTree(list[0].getCharacterID(), dice.roll(1), list[0].getCharacterSheet().rawStats[CharacterSheet.ABILITY_DEXTERITY][CharacterSheet.ABILITY_MODIFIER]);
			
			/* Now, create the tree/order */
			for (int i = 1; i < list.length; i++)
			{
				head.add(list[i].getCharacterID(), dice.roll(1), list[i].getCharacterSheet().rawStats[CharacterSheet.ABILITY_DEXTERITY][CharacterSheet.ABILITY_MODIFIER]);
			} /* end for loop */
		} /* end if */
		
	} /* end interpret method */

} /* end I_StartCombatPhase class */
