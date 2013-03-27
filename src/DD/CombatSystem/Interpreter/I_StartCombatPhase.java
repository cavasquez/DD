package DD.CombatSystem.Interpreter;

import java.util.ArrayList;
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
		CombatValidationMessage returner = new CombatValidationMessage(true, null); // TODO: Check for validity
		return returner;
	} /* end validate method */

	@Override
	public void interpret(CombatMessage cm) 
	{
		/* Reset the CombatSystems turn to 0 and give it the new order. */
		cs.setTurn(0);
		Integer[] temp = cm.getBody(); 
		ArrayList<Integer> order = new ArrayList<Integer>();
		for(int i = 0; i < temp.length; i++) order.add(i, temp[i]);
		cs.setOrder(order);
		
		/* Lastly, give the first character their turn via ActionBox */
		if(ab.hasCharacter(temp[0])) ab.setCharacter(cs.getCharacter(temp[0]));
		else ab.setCharacter(null); /* If player does not have turn, set character to null */
		
		/* Give the first player their turn */
		cs.getCharacter(temp[0]).startNewTurn();
		
	} /* end interpret method */

} /* end I_StartCombatPhase class */
