package DD.Character.Abilities.DefaultAbilities.Standard;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import DD.ActionBox.ActionBox;
import DD.Character.Abilities.Ability;

/*****************************************************************************************************
 * The StandardAttack Ability is one of the default abilities available to all players in game. It 
 * consumes one attack and, by definition, allows for a movement action. The update method should first
 * check if the ability has been clicked and then render the attack targets FF tactics style. The
 * next update should check if a target has been clicked. If a target has been clicked, we perform the
 * attack (action()) method by passing in the attacked target (Character object).
 ******************************************************************************************************/

public class StandardAttack extends Ability
{
	/************************************ Class Attributes *************************************/
	boolean userRolls;		/* Determine if the user wishes to roll or have the program roll */
	Character character;	/* We need this so we can render the FF style squares near the character */
	
	/************************************ Class Methods *************************************/
	public StandardAttack(int id, int abilityType, String name, String description) 
	{
		super(id, abilityType, name, description);
	} /* end StandardAttack constructor */

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) {
		// TODO Auto-generated method stub
		
	} /* end render method */

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		// TODO Auto-generated method stub
		
	} /* end update method */
	
	private String action(Character target)
	{ /* This method needs to be used in update */
		/* TODO: Should check for flat-footed and sneak attacks */
		/* TODO: Implement user roll later */
		String returner = ""; /* Description of roll */
		
		
		
		return (returner);
	} /* end action method */
	
} /* end StandardAttack method */
