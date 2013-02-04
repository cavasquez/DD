package DD.ActionBox;

import java.util.ArrayList;
import java.util.Queue;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import DD.Character.Character;
import DD.Character.Abilities.Ability;
import DD.SlickTools.BoxInterface;
import DD.SlickTools.Component;
import DD.SlickTools.Entity;
import DD.SlickTools.RenderComponent;

/*****************************************************************************************************
 * The ActionBox class represents the Action Box in the GUI. It will hold possible player actions as well
 * as game logic. It is here where the combat system will be implemented. As per Pathfinder combat, the
 * actions that the player is allowed to perform are as follows:
 * 1. Standard Action
 * 2. Movement Action
 * 3. Full-round Action
 * 4. Swift Action
 * 5. Immediate Action
 * 6. Free Action
 * 
 * More information can be found about Pathfinder combat here:
 * http://www.d20pfsrd.com/gamemastering/combat
 * 
 * The class will depend heavily on drawing the Action choices from the Character class which should
 * provide an interface to obtain "default" actions as well as "sub" actions. That is, a default
 * action a player might have would be attack. However, a feat would not be a default action. These 
 * actions, however, need to be available in the ActionBox for the player to be able to decide on an
 * action to perform.
 * 
 * The "extra" actions will be some subtype of the Component class that will be added to the SubAction
 * array list so that they can be updated and rendered accordingly, based on the action chosen. 
 ******************************************************************************************************/

public class ActionBox extends BoxInterface 
{
	/************************************ Class Constants *************************************/
	public static final int STANDARD_ACTION = 1;
	public static final int MOVE_ACTION = 2;
	public static final int FULL_ROUND_ACTION = 3;
	public static final int SWIFT_ACTION = 4;
	public static final int IMMEDIATE_ACTION = 5;
	public static final int FREE_ACTION = 6;
	public static final int NUM_OF_ACTIONS = 6;
	
	/************************************ Class Attributes *************************************/
	protected ArrayList<Integer> subActions;		/* integer array list that holds the id of the subActions */
	protected static Character character = null;
	
	public ActionBox(int id, float length, float width, Character character) 
	{
		super(id, length, width);
		ActionBox.character = character;
		subActions = null;
	} /* end ActionBox constructor */
	
	public void addSubAction(Ability ability)
	{ /* Convert the provided Ability into a SubAction and add it to the components ArrayList*/
		SubAction subAction = new SubAction(this.id);	/* the id is set to any arbitrary id. It will be reset in the addComponent method */
		subActions.add(this.addComponent(subAction));	/* Add the SubAction and add it's id to the subActions ArrayList */
	} /* end addSubAction method */
	
	public void removeSubAction(int id)
	{ /* remove the provided subAction from the components array list */
		
	} /* end removeSubAction method */
	
	public void clearSubActions()
	{ /* removes all Sub Actions from subAction and components*/
		
	} /* end clearSubACtions method */
	
	public void unclickSubActions()
	{ /* set abilityClicked to false for all subActions in ActionBox */ 
		for (Component component : components)
		{
			
		} /* end for loop */
	} /* end unclickSubACtions method */

} /* end ActionBox method */
