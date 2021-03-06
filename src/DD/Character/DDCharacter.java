package DD.Character;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.Image;

import DD.ActionBox.Dice;
import DD.Character.Abilities.Ability;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.Character.Equipment.Weapon;
import DD.CombatSystem.TargetingSystem.Coordinate;
import DD.MapTool.World;
import DD.SlickTools.*;

/*****************************************************************************************************
 * The Character class will act as an interface to CharacterSheet as well as play the role of the Model
 * in the Model View Controller scheme for DD combat (although in reality, it is more of a Model Controller
 * mix). It will provide the CombatSystem any necessary information and represent the Character being 
 * played by the player. It will hold some extra information such as turn, current speed, etc. that are 
 * needed to keep up the game.
 ******************************************************************************************************/

public class DDCharacter extends CharacterEntity implements Serializable
{
	private static final long serialVersionUID = -3339921524023083469L;

	/************************************ Class Constants *************************************/
	public static enum ACType
	{
		NORMAL,
		FLAT_FOOTED,
		TOUCH;
		
	} /* end ACType enum */
	/************************************ Class Attributes *************************************/
	protected CharacterSheet sheet;
	private boolean hasTurn = false;
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
	
	public void writeMe(String path){
		File theDir = new File(path+"Characters");
		
		if (!theDir.exists())
		{
			theDir.mkdir();  
		}		
		try{
			FileOutputStream fileOut = new FileOutputStream(path+""+"Characters"+"/"+sheet.getName()+".ser");
			ObjectOutputStream out =  new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public CharacterSheet load(String path, String name)
	{
		CharacterSheet returner = null;
		DDCharacter loadedObject = null;
		try {
			FileInputStream fileIn = new FileInputStream(path +  "Characters" + "/" + name + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loadedObject = (DDCharacter) in.readObject();
			in.close();
			fileIn.close();
			returner = loadedObject.getSheet();
		} catch (IOException i) {
			i.printStackTrace();

		} catch (ClassNotFoundException c) {
			c.printStackTrace();

		}
		return returner;
	} /* end load method */
	
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
		sheet.setCurrentHP(sheet.getHP());
	} /* end resetCharacter method */
	
	public void addAbility(Ability ability)
	{/* Character does have addComponent, however abilities have a more specific purpose than 
	 	the standard component object. Thus, they have their own array and add method. */
		ability.setOwnerEntity(this);
		abilities.add(ability);	
	} /* end addAbility methods */
	
	/* Combat Related Methods */
	
	public void startNewTurn()
	{ /* It is the start of the characters turn. Reset the appropriate variables. */
		hasTurn = true;
		moved = false;
		attackCount = 0;
		movedDiagonal = false;
		currentSpeed = sheet.getSpeed(); // TODO: needs to get speed from character sheet. defaulting to 30
		
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
	
	/************************************ Slick Mechanisms *************************************/
	
	
	/************************************ Combat Mechanisms *************************************/
	public boolean defend(int attack, int damage, ACType attackType)
	{/* The character must attempt to defend against an attack.
	 	Attack type identifies if the attack is regular, touch, etc. 
	 	TODO: check for flat-footed, etc. */
		/* TODO: implement */
		boolean returner = false;
		boolean wasDying = isDying();
		boolean wasDead = isDead();
		/*sheet.getACTotal();
		sheet.getFlatfootTotal();
		sheet.getTouchTotal();
		*/
		if (attack >= getAC())
		{
			/* Character was hit */
			returner = true;
			getHit(damage);
			this.addComponent(new CombatStatus(Integer.toString(damage))); /* Alert players of damage */
		} /* end if */
		else
		{/* Missed hit, print the  miss */
			/* missed hit */
			this.addComponent(new CombatStatus("missed"));
		} /* end else */
		return(returner); /*  */
	} /* end getAttacked method */
	
	public void getHit(int hit)
	{ /* The character is hit. Deal with calculations, checks, and damage reduction. */
		/* TODO: implement */
		/* TODO: if hp drops to 0 or less, apply conditions. */
		/* TODO: take care of damage reduction */
		sheet.setCurrentHP(sheet.getCurrentHP() - hit);
	} /* end getHit method */
	
	public void movedDiagonal()
	{
		movedDiagonal = true;
	} /* end movedDiagonal method */
	
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
	
	//@brandon
	//Returns the reach of both weapons if equipped
	public int[] getWeaponReach()
	{
		ArrayList<Weapon> weapons = sheet.EquippedWeapon;
		int[] reach = new int[weapons.size()];
	
		for (int i = 0; i < weapons.size(); i++) 
		{
			reach[i] = weapons.get(i).getReach();
		}
	
	
	
	
	
	return reach;
	} /* end getWeaponReach method */
	
	public Dice.DieSize getAttackDie()
	{ 
		return Dice.DieSize.D20;
	} /* end getAttackDie method */
	
	//@brandon
	//returns weapons die both main hand and off hand
	public Dice.DieSize[] getDamageDie()
	{ 
		ArrayList<Weapon> weapons = sheet.EquippedWeapon;
		Dice.DieSize[] dice = new Dice.DieSize[weapons.size()];
		
		for (int i = 0; i < weapons.size(); i++) 
		{
			dice[i] = weapons.get(i).getDamage();
		}
		
		return dice;
	} /* end getDamageDie method */
	
	//@brandon
	//We cannot do this until we implement AttackModifiers
	public int[] getAttack()
	{ /* This should return an array with all the attack modifiers */
		//TODO: Implement
		return null;
	} /* end getAttack method */
	
	//cannot do yet
	public int[] getDamange()//dont worry now
	{ /* This should return an array with all the damage modifiers */
		//TODO: Implement
		return null;
	} /* end getAttack method */
	
	public int[] getCritRange()
	{
		ArrayList<Weapon> weapons = sheet.EquippedWeapon;
		int returner[] = new int[weapons.size()];
		
		for (int i = 0; i < weapons.size(); i++) 
		{
			returner[i] = weapons.get(i).getCritRange();
		}
		
		return returner;
	} /* end getCritRange method */
	
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
	
	public int getCurrentHP()
	{
		return sheet.getCurrentHP();
	} /* end getCurrentHP method */
	
	public int getAC()
	{
		return sheet.getACTotal();
	}/* end getNormalAC method */
	
	public int getACArmor()
	{
		return sheet.getACArmor();
	} /* end getArmorAC method */
	
	public boolean getHasTurn() 
	{
		return hasTurn;
	}
	
	public CharacterSheet getSheet()
	{
		return sheet;
	}
	
	public boolean isDying()
	{
		return (sheet.getCurrentHP() < 0);
	} /* end isDying method */
	
	public boolean isDead()
	{
		boolean returner = false;
		if ((sheet.getCurrentHP() < 0) && ((sheet.getCurrentHP() * -1) >= sheet.getConInherent())) returner = true;
		return returner;
	} /* end isDead method */
	
	public DDImage getImage()
	{
		return sheet.getImage();
	} /* end getImage method */
	
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
