package DD.CombatSystem.Interpreter.Standard;

import DD.Character.DDCharacter;
import DD.CombatSystem.Interpreter.CombatInterpreter;
import DD.Message.CombatMessage;
import DD.Message.CombatValidationMessage;

/*****************************************************************************************************
 * Interpreter for StandardAttack
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_StandardAttack implements CombatInterpreter
{
	/************************************ Class Constants *************************************/
	private static int I = 0;
	public static final int attackRoll = I++;
	public static final int damageRoll = I++;
	public static final int bodySize = I;
	
	/************************************ Class Attributes *************************************/
	
	/************************************ Class Attributes *************************************/
	public I_StandardAttack()
	{
		
	} /* end I_StandardAttack constructor */

	@Override
	public CombatValidationMessage validate(CombatMessage cm) 
	{
		// TODO Auto-generated method stub
		return null;
	} /* end validate method */

	@Override
	public void interpret(CombatMessage cm) 
	{
		int attack = cm.getBody()[attackRoll];	/* the attack against the opponent */
		int damage = cm.getBody()[damageRoll];	/* the damage against the opponent */
		
		/* Add modifier to rolls */
		int[] attackModifiers = cm.getSource().getAttack();
		int[] damageModifiers = cm.getSource().getDamange();
		for (int i = 0; i < attackModifiers.length; i++) attack += attackModifiers[i];
		for (int i = 0; i < damageModifiers.length; i++) damage += damageModifiers[i];
		
		cm.getTarget()[0].defend(attack, damage, DDCharacter.ACType.NORMAL);
	} /* end interpret method */
	
} /* end I_StandardAttack class */
