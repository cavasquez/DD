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
 * class. This will be important when determining what feats/abilities will be added as Ability object. 
 * We will get these from the Character class.
 * 
 * The actionType should also be used when determining where to render the ActionChoice in the ActionBox.  
 ******************************************************************************************************/

public class ActionChoice extends RenderComponent
{
	/************************************ Class Attributes *************************************/
	int numOfSubActions; 	/* Number of subActions available */
	int actionType;			/* Number that refers to the type of action this ActionChoice represents (standard, move, etc.) */
	String display;
	boolean actionPerformed;
	
	/************************************ Class Methods *************************************/
	/* Remember, ID is the same as ActionType */
	public ActionChoice(int id, int actionType, String display)
	{
		super(id);
		this.actionType = actionType;
		this.display = display;
		actionPerformed = false;
		
	} /* end ActionChoice constructor */

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) 
	{/* TODO: generate */
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) 
	{ /* TODO: generate */
		
	}
	
	protected void performAction()
	{ /* Logic that should take place when action is clicked on.
	 	performAction needs to get all the appropriate abilities from
	 	the Character and make them available in ActionBox by creating
	 	adding the ability to the SubAction array list in ActionBox.*/
		Ability abilities[] = Character.getAbilities(this.actionType);
		
		for (Ability ability : abilities)
		{
			((ActionBox)owner).addSubAction(ability);
		} /* end for loop */
		
		/*TODO: Need to add the subActions to ActionBox's subActions */
	} /* end performAction method */
	
} /* end ActionChoice class */