package DD.ActionBox;
 
import java.util.ArrayList;  
import java.util.Set;
import java.util.TreeSet;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import DD.Character.*;
import DD.Character.Abilities.Ability;
import DD.Character.Abilities.EndTurn;
import DD.Character.Abilities.DefaultAbilities.Move.Move;
import DD.Character.Abilities.DefaultAbilities.Standard.StandardAttack;
import DD.SlickTools.BoxInterface;
import DD.SlickTools.Component;

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
 * action to perform. ActionBox will be the View component for DD combat. It will communicate with 
 * CombatSystem (the controller).
 * 
 * The "extra" actions will be some sub-type of the Component class that will be added to the SubAction
 * array list so that they can be updated and rendered accordingly, based on the action chosen. 
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class ActionBox extends BoxInterface 
{
	/************************************ Class Constants *************************************/
	private static int I= 0;
	public static enum Action
	{
		STANDARD_ACTION (I++),
		MOVE_ACTION(I++),
		FULL_ROUND_ACTION (I++),
		SWIFT_ACTION (I++),
		IMMEDIATE_ACTION (I++),
		FREE_ACTION (I++),
		END_TURN(I++);
		
		public final int index;
		public static final int NUM_OF_ACTIONS = I;
		
		Action (int index)
		{
			this.index = index;
		} /* end TargetCount index */
		
		public int index()
		{
			return index;
		} /* end index for enum */
		
	} /* end Action enum */

	/************************************ Class Attributes *************************************/
	protected ArrayList<Integer> subActions;		/* integer array list that holds the id of the subActions */
	protected DDCharacter character = null;	/* The character performing the actions */
	protected Set<Integer> playersCharacters = null;/* A set that contains the players characters */
	
	/************************************ Button Images *************************************/
	Image freeAction = null;
	Image fullRoundAction = null;
	Image immediateAction = null;
	Image moveAction = null;
	Image standardAction = null;
	Image swiftAction = null;
	Image standardAttack = null;
	Image endMove = null;
	Image endTurnButton = null;
	
	public ActionBox(int id, float length, float width) throws SlickException
	{
		super(id, length, width);
		subActions = new ArrayList<Integer>();
		playersCharacters = new TreeSet<Integer>();
		
		freeAction = new Image("Images/ActionBox/FreeAction.png");
		fullRoundAction = new Image("Images/ActionBox/FullRoundAction.png");
		immediateAction = new Image("Images/ActionBox/ImmediateAction.png");
		moveAction = new Image("Images/ActionBox/MoveAction.png");
		standardAction = new Image("Images/ActionBox/StandardAction.png");
		swiftAction = new Image("Images/ActionBox/SwiftAction.png");
		endMove = new Image("Images/ActionBox/EndMove.png");
		endTurnButton = new Image("Images/ActionBox/EndMove.png"); //TODO: make button
		
		int shift = freeAction.getHeight();
		Vector2f boxPosition = new Vector2f(660f, 10f);
		this.setPosition(boxPosition);
		
		/* To begin with, the basic ActionChoices need to be available. */
	
		this.addComponent(new ActionChoice(this.id, Action.STANDARD_ACTION.index, "Standard Action", standardAction, position.x, position.y, new StandardAttack(this.id)));
		Move move = new Move(this.id);
		this.addComponent(new ActionChoice(this.id, Action.MOVE_ACTION.index, "Move Action", moveAction, position.x, position.y + shift, move));
		this.addComponent(new ActionChoice(this.id, Action.FULL_ROUND_ACTION.index, "Full Round Action", fullRoundAction, position.x, position.y + shift*2));
		this.addComponent(new ActionChoice(this.id, Action.SWIFT_ACTION.index, "Swift Action", swiftAction, position.x, position.y + shift*3));
		this.addComponent(new ActionChoice(this.id, Action.IMMEDIATE_ACTION.index, "Immediate Action", immediateAction, position.x, position.y + shift*4));
		this.addComponent(new ActionChoice(this.id, Action.FREE_ACTION.index, "Free Action", freeAction, position.x, position.y + shift*5));
		this.addComponent(new ActionChoice(this.id, Action.END_TURN.index, "End Turn", endTurnButton, position.x, position.y + shift*5, new EndTurn(this.id)));
		
		this.addComponent(new ActionChoice(this.id, 1000, "End Move", endMove, position.x, position.y + shift*6, move));
		//this.addComponent(new ActionChoice(this.id, 2000, "Start New Turn", startNewTurn, position.x, position.y + shift*6));
		
	} /* end ActionBox constructor */
	
	public void addSubAction(Ability ability)
	{ /* Convert the provided Ability into a SubAction and add it to the components ArrayList*/
		SubAction subAction = new SubAction(this.id, ability);	/* the id is set to any arbitrary id. It will be reset in the addComponent method */
		subActions.add(this.addComponent(subAction));	/* Add the SubAction and add it's id to the subActions ArrayList */
	} /* end addSubAction method */
	
	public void removeSubAction(int id)
	{ /* remove the provided subAction from the components array list */
		this.removeComponent(id);
	} /* end removeSubAction method */
	
	public void clearSubActions()
	{ /* removes all SubActions from subAction */
		for (Integer subAction : subActions) this.removeComponent(subAction);
	} /* end clearSubACtions method */
	
	public void unclickSubActions()
	{ /* Set abilityClicked to false for all subActions in ActionBox. */
		Component holder = null;
		for (Integer subAction : subActions) 
		{ /* unclick */
			holder = getComponent(subAction);
			((SubAction)holder).unclick();
		} /* end for loop */
		
	} /* end unclickSubACtions method */
	
	public void addCharacter(int id)
	{
		/* Add characters id to set */
		playersCharacters.add(id);
	}/* end addCharacter method */
	
	public void removeCharacter(int id)
	{
		playersCharacters.remove(id);
	} /* end removeCharacter method */
	
	public boolean hasCharacter(int id)
	{
		/* Check to see if ActionBox "owns" character */
		return playersCharacters.contains(id);
	} /* end hasCharacter method */
	
	/****************************************************************************************
	 ************************************ Getter Methods ************************************
	 ****************************************************************************************/
	public DDCharacter getCharacter()
	{
		return this.character;
	} /* end getCharacter method */
	
	/****************************************************************************************
	 ************************************ Setter Methods ************************************
	 ****************************************************************************************/
	public void setCharacter(DDCharacter character)
	{
		this.character = character;
		Ability.setOwnerCharacter(character);
	} /* end setCharacter method */
	
} /* end ActionBox method */
