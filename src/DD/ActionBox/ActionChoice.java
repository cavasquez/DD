package DD.ActionBox;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import DD.Character.Character;
import DD.Character.Abilities.Ability;
import DD.SlickTools.RenderComponent;

/*****************************************************************************************************
 * The ActionChoice class will represent the many actions available to the player in the ActionBox class.
 * For example, the default Standard action should be represented in the ActionChoice method. It will be
 * subclass some variation of the RenderComponent classes (dependent on the GUI implementers). The 
 * ActionChoice class should be clicked on (or hotkeyed) to display the available actions the Player
 * can perform for the given action. This will be done by adding new components to the ActionBox method.
 * 
 * Each ActionChoice should be defined by an ActionType which is defined as a constant in the ActionBox
 * class. The ActionType is equivalent to the objects ID. This will be important when determining
 * what feats/abilities will be added as Ability object.. We will draw these from the Character class.
 * 
 * The id should also be used when determining where to render the ActionChoice in the ActionBox.  
 ******************************************************************************************************/

public class ActionChoice extends RenderComponent
{
	/************************************ Class Attributes *************************************/
	int numOfSubActions; /* Number of subActions available */
	String display;
	boolean actionPerformed;
	
	/************************************ Class Methods *************************************/
	/* Remember, ID is the same as ActionType */
	public ActionChoice(int id, String display)
	{
		super(id);
		this.display = display;
		actionPerformed = false;
		
	} /* end ActionChoice constructor */

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) 
	{
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) 
	{
		
	}
	
	protected void performAction()
	{ /* Logic that should take place when action is clicked on.
	 	performAction needs to get all the appropriate abilities from
	 	the Character and make them available in ActionBox.*/
		Ability subActions[] = Character.getSubActions(this.id);
		
		/*TODO: Need to add the subActions to ActionBox's subActions */
	} /* end performAction method */
	
} /* end ActionChoice class */
