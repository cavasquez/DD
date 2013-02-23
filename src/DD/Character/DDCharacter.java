package DD.Character;

import java.util.ArrayList;
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
	} /* end startTurn method */
	
	public void endTurn()
	{ /* It is the end of the characters turn. Modify the appropriate variables. */
		hasTurn = false;
	} /* end endTurn method */
	
	
	/************************************ Combat Mechanisms *************************************/
	public boolean defend(int attack, int attackType)
	{/* The character must attempt to defend against an attack.
	 	Attack type identifies if the attack is regular, touch, etc. 
	 	TODO: check for flat-footed, etc. */
		/* TODO: implement */
		boolean returner = false;
		
		if (attack >= sheet.getAP()) returner = true;
		
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
	
} /* Character class */
