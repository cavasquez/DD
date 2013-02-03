package DD.Character;

import java.util.ArrayList;

import DD.Character.Abilities.Ability;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.SlickTools.*;

public class Character extends Entity
{
	/************************************ Class Attributes *************************************/
	private CharacterSheet character;
	private boolean hasTurn = false;
	private int currentHP;
	private int currentSpeed;
	private ArrayList<Ability> abilities = null;	
	boolean moved; 									/* player moved this turn */
	int attackCount;								/* number of attacks made this turn */
	int maxAttacks;
	boolean engaged;								/* Engaged in combat with another character */
	
	/************************************ Class Methods *************************************/
	public Character(int id) 
	{
		super(id);
		hasTurn = false;
		moved = false;
		attackCount = 0;
		engaged = false;
		
	} /* end Character Constructor */
		
	public void resetCharacter()
	{
		
	} /* end resetCharacter method */
	
	public void addAbility(Ability ability)
	{/* Character does have addComponent, however abilities have a specific purpose than 
	 	the standard component object. Thus, they have their own array and add method. */
		ability.setOwnerEntity(this);
		abilities.add(ability);	
	} /* end addAbility methods */
	
	/* Combat Related Methods */
	
	public void startTurn()
	{
		hasTurn = true;
		moved = false;
		attackCount = 0;
	} /* end startTurn method */
	
	public void endTurn()
	{
		hasTurn = false;
	} /* end endTurn method */
	
	public boolean defend(int attack, int attackType)
	{ /* The character must attempt to defend against an attack.
	 	Attack type identifies if the attack is regular, touch, etc. 
	 	TODO: check for flat-footed, etc. */
		boolean returner = false;
		
		if (attack >= character.getAP(attackType)) returner = true;
		
		return(returner); /*  */
	} /* end getAttacked method */
	
	public void getHit(int hit)
	{
		/* TODO: if hp drops to 0 or less, apply conditions. */
		/* TODO: send message from here */
		/* TODO: take care of damage reduction */
		currentHP -= hit;
	} /* end getHit method */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public static Ability[] getAbilities(int actionType)
	{ /*  */
		Ability[] returner = null;
		
		return(returner);
	} /* end getSubActions method */
	
} /* Character class */
