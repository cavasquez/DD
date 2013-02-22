package DD.Character.Abilities.DefaultAbilities.Standard;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import DD.ActionBox.ActionBox;
import DD.Character.Abilities.Ability;

/*****************************************************************************************************
 * The StandardAttack Ability is one of the default abilities available to all players in game. It 
 * consumes one attack and, by definition, allows for a movement action. The update method should render
 * a FF tactics style square selection for selecting targets. The selected target will need to be passed
 * to the action method.
 * 
 * @author Carlos Vasquez
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
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		// TODO Auto-generated method stub
		
	} /* end update method */
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) {
		// TODO Auto-generated method stub
		
	} /* end render method */
	
	private String action(Character target)
	{ /* This method needs to be used in update */
		/* TODO: Should check for flat-footed and sneak attacks */
		/* TODO: Implement user roll later */
		String returner = ""; /* Description of roll */
		
		
		return (returner);
	} /* end action method */
	
} /* end StandardAttack method */
