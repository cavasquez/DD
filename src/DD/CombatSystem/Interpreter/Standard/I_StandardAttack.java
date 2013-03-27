package DD.CombatSystem.Interpreter.Standard;

import DD.Character.DDCharacter;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.Interpreter.CombatInterpreter;
import DD.Message.CombatMessage;
import DD.Message.CombatValidationMessage;

/*****************************************************************************************************
 * Interpreter for StandardAttack
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_StandardAttack extends CombatInterpreter
{
	/************************************ Class Constants *************************************/
	private static int I = 0;
	public static final int ATTACK_ROLL = I++;		/* First Attack Roll */
	public static final int CONFIRM_ATTACK = I++;	/* Roll to confirm crit or miss if they occur */
	public static final int DAMAGE_ROLL = I++;		/* Roll for damage */
	public static final int CRIT_DAMAGE = I++;		/* Will hold damage from crit including base damage (first damage) */
	public static final int BODY_SIZE = I;
	
	/************************************ Class Methods *************************************/
	public I_StandardAttack()
	{
		
	} /* end I_StandardAttack constructor */

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
		int attack = cm.getBody()[ATTACK_ROLL];	/* the attack against the opponent */
		int damage = cm.getBody()[DAMAGE_ROLL];	/* the damage against the opponent */
		
		/* Add modifier to rolls */
		int[] attackModifiers = cs.getCharacter(cm.getSource()).getAttack();
		int[] damageModifiers = cs.getCharacter(cm.getSource()).getDamange();
		if (attackModifiers != null) for (int i = 0; i < attackModifiers.length; i++) attack += attackModifiers[i];
		if (damageModifiers != null) for (int i = 0; i < damageModifiers.length; i++) damage += damageModifiers[i];
		
		if (cm.getTarget() != null) cs.getCharacter(cm.getTarget()[0]).defend(attack, damage, DDCharacter.ACType.NORMAL);
		/* if cmTarget is null, player attacked the air */
	} /* end interpret method */
	
} /* end I_StandardAttack class */
