package DD.CombatSystem.Interpreter;

import DD.Character.DDCharacter;
import DD.Message.CombatMessage;
import DD.Message.CombatValidationMessage;

/*****************************************************************************************************
 * Interpreter for EndTurn
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_EndTurn extends CombatInterpreter
{

	@Override
	public CombatValidationMessage validate(CombatMessage cm) 
	{
		// TODO Auto-generated method stub
		CombatValidationMessage returner = new CombatValidationMessage(true, null); // TODO: Check for validity
		return returner;
	} /* end validate method */

	@Override
	public void interpret(CombatMessage cm) 
	{
		/* Current player ended their turn. We must do the following:
		 * 1. Increment the turn count
		 * 2. End previous characters turn
		 * 3. Start the characters new turn.
		 * 4. Set new Character to ActionBox */
		int turn = cs.getTurn();
		/* Part 1 */
		cs.setTurn(turn + 1);
		
		/* Part 2 */
		int numOfPlayers = cs.getCharacterList().length;
		int currentCharacterID = cs.getOrder().get(turn % numOfPlayers);
		DDCharacter currentCharacter = cs.getCharacter(currentCharacterID);
		currentCharacter.endTurn();
		
		/* Part 3 */
		currentCharacterID = cs.getOrder().get((turn + 1) % numOfPlayers);
		currentCharacter = cs.getCharacter(currentCharacterID);
		currentCharacter.startNewTurn();
		
		/* Part 4 */
		if (ab.hasCharacter(currentCharacterID)) ab.setCharacter(currentCharacter);
		else
		{
			ab.setCharacter(null);
			//TODO: modify action box to state in which it displays nothing?
		} /* end else */
		
	} /* end interpret method */

} /* end I_EndTurn class */
