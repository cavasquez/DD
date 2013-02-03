package DD.ActionBox;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import DD.Character.Abilities.Ability;
import DD.SlickTools.RenderComponent;

/*****************************************************************************************************
 * The SubAction class will be a Conatainer that holds an Ability. The purpose of the SubAction class
 * is to display the ability and activate it. The activation may be different depending on the ability,
 * however, all SubActions need to render the ability choice in the ActionBox and activate the ability
 * if the SubAction is clicked on.
 ******************************************************************************************************/

public class SubAction extends RenderComponent
{
	/************************************ Class Attributes *************************************/
	Ability ability = null;
	
	/************************************ Class Methods *************************************/
	public SubAction(int id) 
	{
		super(id);
		ability = null;
	} /* end SubAction constructor */

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) {
		// TODO Auto-generated method stub
		
	} /* end render method */

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		// TODO Auto-generated method stub
		
	} /* end update method */
	
	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
	public void setAbility(Ability ability)
	{
		this.ability = ability;
	} /* end setAbility method */
	
} /* end SubAction class */
