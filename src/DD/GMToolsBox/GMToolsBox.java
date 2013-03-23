package DD.GMToolsBox;
 
import java.util.ArrayList;  
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import DD.ActionBox.ActionChoice;
import DD.Character.*;
import DD.Character.Abilities.Ability;
import DD.Character.Abilities.DefaultAbilities.Move.Move;
import DD.Character.Abilities.DefaultAbilities.Standard.StandardAttack;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.SlickTools.BoxInterface;
import DD.SlickTools.Component;

/*****************************************************************************************************
 * The GMToolsBox class will hold the GM abilities. It will provide the GM with many of his necessary
 * tools such as the ability to start combat or place characters on the board.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class GMToolsBox extends BoxInterface 
{
	/************************************ Class Constants *************************************/
	private static int I= 0;
	private static enum Action
	{
		START_COMBAT_PHASE(I++);
		
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
	
	public static enum Holder
	{
		/* The enum that will correlate with the holder ArrayList */
		MOB(I++),
		PLAYER(I++);
		
		public final int index;
		public static final int NUM_OF_HOLDERS = I;
		
		Holder (int index)
		{
			this.index = index;
		} /* end TargetCount index */
		
		public int index()
		{
			return index;
		} /* end index for enum */
		
	} /* end Action enum */

	/************************************ Class Attributes *************************************/
	private int characterID; 							/* Unique ID's for each character */
	private Queue<Integer> recycledCIDs;				/* ID's of objects that have given up their id (thus the ID can be used again */
	private ArrayList<ArrayList<CharacterSheet>> holder;/* The holder array will hold either mob characters or player characters NOT on the field*/
	private Set<Integer> charactersInPlay;				/* A set containing the ID's of all the characters in play. It should mirror the one in the CombatSystem */
	
	/************************************ Button Images *************************************/
	Image startCombatPhaseButton= null;
	
	
	public GMToolsBox(int id, float length, float width) throws SlickException
	{
		super(id, length, width);
		
		recycledIds = new LinkedList<Integer>();
		holder.add(Holder.MOB.index, new ArrayList<CharacterSheet>());
		holder.add(Holder.PLAYER.index, new ArrayList<CharacterSheet>());
		
		startCombatPhaseButton= new Image("Images/GMToolsBox/FreeAction.png");
		
		int shift = startCombatPhaseButton.getHeight();
		Vector2f boxPosition = new Vector2f(660f, 10f);
		this.setPosition(boxPosition);
		
		/* To begin with, the basic ActionChoices need to be available. */
		StartCombatPhase startCombatPhase = new StartCombatPhase(this.id);
		StandardAttack standardAttack = new StandardAttack(this.id);
		this.addComponent(new ActionChoice(this.id, Action.START_COMBAT_PHASE.index, "Start Combat Phase", startCombatPhaseButton, position.x, position.y, startCombatPhase));
		
	} /* end GMToolsBox constructor */
	
	public void addCharacter(Holder type, CharacterSheet sheet)
	{
		/* Add a character to the holder. Add PlaceCharacter object. */
		holder.get(type.index).add(sheet);
		
	} /* end addCharacter method */
	
	public void removeCharacter(Holder type, CharacterSheet sheet)
	{
		/* remove character from holder */
		holder.get(type.index).remove(sheet);
	}
	
	public void removeCharacter(Holder type, int index)
	{
		/* remove character from holder */
		holder.get(type.index).remove(index);

	} /* end removeCharacter method */
	
	public void clearHolder(Holder type)
	{
		/* remove all characters from the holder */
		holder.get(type.index).clear();
		
	} /* end clearHolder */
	
	public Integer getNewCharacterID()
	{
		Integer id = this.characterID;
		if((id = recycledIds.poll()) == null) id = this.characterID++; /* Take a recycled id. If none exists, take an ID from id and increment it */
		charactersInPlay.add(id);
		return id;
	} /* end addCharacterToPlay method */
	
	public void removeCharacterID(Integer id)
	{
		charactersInPlay.remove(id);
		recycledCIDs.offer(id);
		
	} /* end removeCharacter method */
	
	/****************************************************************************************
	 ************************************ Getter Methods ************************************
	 ****************************************************************************************/
	
	/****************************************************************************************
	 ************************************ Setter Methods ************************************
	 ****************************************************************************************/
	
	
} /* end GMToolsBox method */
