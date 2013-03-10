package DD.ActionBox;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import DD.Character.Abilities.Ability;
import DD.SlickTools.RenderComponent;

/*****************************************************************************************************
 * The SubAction class will be a conatainer that holds an Ability. The purpose of the SubAction class
 * is to display the ability and activate it. The activation may be different depending on the ability,
 * however, all SubActions need to render the ability choice in the ActionBox and activate the ability
 * if the SubAction is clicked on.
 * 
 * The rendering will be done by adding the ability to a special variable in the Character class that 
 * will be called during render.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class SubAction extends RenderComponent
{
	/************************************ Class Attributes *************************************/
	Ability ability = null;
	boolean clicked;
	
	/************************************ Class Methods *************************************/
	public SubAction(int id, Ability ability) 
	{
		super(id);
		this.ability = ability;
		clicked = false;
	} /* end SubAction constructor */
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		// TODO Auto-generated method stub
		
	} /* end update method */

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) {
		// TODO Auto-generated method stub
		/* TODO: implement the abilities renderSubAction method */
	} /* end render method */
	
	public void activate()
	{
		/* This method should be called upon click */
	} /* end activate method */
	
	public void click() throws SlickException
	{ /* set abilityClicked to true and activate ability. Furthermore, unclick any other clicked SubActions*/ 
		this.unclickSubActions();
		clicked = true;
		ability.activate();
	} /* end clickSubACtions method */
	
	public void unclick()
	{ /* set abilityClicked to false and deactivate ability */ 
		clicked = false;
		ability.deactivate();
	} /* end unclickSubACtions method */
	
	private void unclickSubActions()
	{ /* set abilityClicked to false for all subActions in ActionBox */ 
		((ActionBox) owner).unclickSubActions();
	} /* end unclickSubACtions method */
	
	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
	public void setAbility(Ability ability)
	{
		this.ability = ability;
	} /* end setAbility method */
	
} /* end SubAction class */
