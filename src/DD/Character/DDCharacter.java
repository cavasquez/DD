package DD.Character;

import java.util.ArrayList;
import DD.ActionBox.Dice;
import DD.ActionBox.CombatSystem.TargetingSystem.Coordinate;
import DD.Character.Abilities.Ability;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.SlickTools.*;

/*****************************************************************************************************
 * The Character class will act as an interface to CharacterSheet as well as play the role of the Model
 * in the Model View Controller scheme for DD combat (although in reality, it is more of a Model Controller
 * mix). It will provide the CombatSystem any necessary information and represent the Character being 
 * played by the player. It will hold some extra information such as turn, current speed, etc. that are 
 * needed to keep up the game.
 ******************************************************************************************************/

public class DDCharacter extends Entity
{
	/************************************ Class Constants *************************************/
	public static enum ACType
	{
		NORMAL,
		FLAT_FOOTED,
		TOUCH;
		
	} /* end ACType enum */
	/************************************ Class Attributes *************************************/
	private CharacterSheet sheet;
	private boolean hasTurn = false;
	private int currentHP;
	private int currentSpeed;
	private ArrayList<Ability> abilities = null;	
	boolean moved; 									/* player moved this turn */
	private boolean movedDiagonal;					/* player has moved diagonally this turn */
	int attackCount;								/* number of attacks made this turn */
	int maxAttacks;
	boolean engaged;								/* Engaged in combat with another character */
	private Coordinate coordinate;					/* Characters location on board */
	private int characterID;						/* This ID should be different form Entity ID and should be the same across network */
	
	/* Turn related attributes */
	private boolean hasStandardAction;
	private boolean hasMoveAction;
	private boolean hasFreeAction;
	private boolean hasSwiftAction;
	private boolean hasFullRoundAction;
	private boolean hasImmediateAction;
	private boolean hasStartEndFullRoundAction;
	
	/************************************ Class Methods *************************************/
	public DDCharacter(int id) 
	{
		super(id);
		hasTurn = false;
		moved = false;
		attackCount = 0;
		engaged = false;
		movedDiagonal = true;
		
	} /* end Character Constructor */
	
	public CharacterSheet getCharacterSheet() 
	{
		return sheet;
	}
	
	public void setCharacterSheet(CharacterSheet sheet)
	{
		this.sheet = sheet;
	}
	
	public void resetCharacter()
	{ /* TODO: generate */
		
	} /* end resetCharacter method */
	
	public void addAbility(Ability ability)
	{/* Character does have addComponent, however abilities have a specific purpose than 
	 	the standard component object. Thus, they have their own array and add method. */
		ability.setOwnerEntity(this);
		abilities.add(ability);	
	} /* end addAbility methods */
	
	/* Combat Related Methods */
	
	public void startTurn()
	{ /* It is the start of the characters turn. Reset the appropriate variables. */
		hasTurn = true;
		moved = false;
		attackCount = 0;
		
		hasStandardAction = true;
		hasMoveAction = true;
		hasFreeAction = true;
		hasSwiftAction = true;
		hasFullRoundAction = true;
		hasImmediateAction = true;
		hasStartEndFullRoundAction = true;
		
	} /* end startTurn method */
	
	public void endTurn()
	{ /* It is the end of the characters turn. Modify the appropriate variables. */
		hasTurn = false;
	} /* end endTurn method */
	
	/************************************ Combat Mechanisms *************************************/
	public boolean defend(int attack, int damage, ACType attackType)
	{/* The character must attempt to defend against an attack.
	 	Attack type identifies if the attack is regular, touch, etc. 
	 	TODO: check for flat-footed, etc. */
		/* TODO: implement */
		boolean returner = false;
		
		if (attack >= sheet.getACArmor())
		{
			returner = true;
			getHit(damage);
		} /* end if */
		
		return(returner); /*  */
	} /* end getAttacked method */
	
	public void getHit(int hit)
	{ /* The character is hit. Deal with calculations, checks, and damage reduction. */
		/* TODO: implement */
		/* TODO: if hp drops to 0 or less, apply conditions. */
		/* TODO: send message from here */
		/* TODO: take care of damage reduction */
		currentHP -= hit;
	} /* end getHit method */
	
	/************************************ Turn Mechanisms *************************************/
	public void endHasStandardAction() 
	{
		hasStandardAction = false;
	} /* end getHasStandardAction method */

	public void endHasMoveAction() 
	{
		hasMoveAction = false;
	} /* end getHasMoveAction method */

	public void endHasFreeAction() 
	{
		hasFreeAction = false;
	} /* end getHasFreeAction method */

	public void endHasSwiftAction() 
	{
		hasSwiftAction = false;
	} /* end getHasSwiftAction method */

	public void endHasFullRoundAction() 
	{
		hasFullRoundAction = false;
	} /* end getHasFullRoundAction method */

	public void endHasImmediateAction() 
	{
		hasImmediateAction = false;
	} /* end getHasImmediateAction method */

	public void endHasStartEndFullRoundAction() 
	{
		hasStartEndFullRoundAction = false;
	} /* end getHasStardEndFullAction method */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public static Ability[] getAbilities(int actionType)
	{ /* Return the Characters usable abilities */
		/* TODO: generate */
		Ability[] returner = null;
		
		return(returner);
	} /* end getSubActions method */
	
	@Override
	public int getId()
	{
		return this.id;
	} /* end getId method */
	
	public boolean getMovedDiagonal()
	{
		return movedDiagonal;
	} /* end getMovedDiagonal method */
	
	public Coordinate getCoordinate()
	{
		return coordinate;
	} /* end getCoordinate method */
	
	public int getWeaponReach()
	{ /* TODO: Implement this method */
		return 5;
	} /* end getWeaponReach method */
	
	public Dice.DieSize getAttackDie()
	{ /* TODO: Implement, this is dependent on equipment */
		return Dice.DieSize.D6;
	} /* end getAttackDie method */
	
	public Dice.DieSize getDamageDie()
	{ /* TODO: Implement, this is dependent on equipment */
		return Dice.DieSize.D6;
	} /* end getDamageDie method */
	
	public int[] getAttack()
	{ /* This should return an array with all the attack modifiers */
		//TODO: Implement
		return null;
	} /* end getAttack method */
	
	public int[] getDamange()
	{ /* This should return an array with all the damage modifiers */
		//TODO: Implement
		return null;
	} /* end getAttack method */
	
	public int getCharacterID()
	{
		return characterID;
	} /* end getCharacterID method */
	
	public int getCurrentSpeed()
	{
		return currentSpeed;
	} /* end getCurrentSpeed method */
	
	public boolean getHasStandardAction() 
	{
		return hasStandardAction;
	} /* end getHasStandardAction method */

	public boolean getHasMoveAction() 
	{
		return hasMoveAction;
	} /* end getHasMoveAction method */

	public boolean getHasFreeAction() 
	{
		return hasFreeAction;
	} /* end getHasFreeAction method */

	public boolean getHasSwiftAction() 
	{
		return hasSwiftAction;
	} /* end getHasSwiftAction method */

	public boolean getHasFullRoundAction() 
	{
		return hasFullRoundAction;
	} /* end getHasFullRoundAction method */

	public boolean getHasImmediateAction() 
	{
		return hasImmediateAction;
	} /* end getHasImmediateAction method */

	public boolean getHasStartEndFullRoundAction() 
	{
		return hasStartEndFullRoundAction;
	} /* end getHasStardEndFullAction method */
	
	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
	public void setCoordiante(Coordinate coordinate)
	{
		this.coordinate = coordinate;
	} /* end setCoordinate method */
	
	public void setCurrentSpeed(int currentSpeed)
	{
		this.currentSpeed = currentSpeed;
	} /* end setCurrentSpeed method */
	
	public void setCharacterID(int characterID)
	{
		this.characterID = characterID;
	} /* end setCharacterID method */
	
} /* Character class */
