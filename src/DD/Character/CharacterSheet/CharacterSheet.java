package DD.Character.CharacterSheet;

import java.util.ArrayList;
import java.util.Random;

import DD.ActionBox.Dice;
import DD.Character.CharacterSheet.Race.Race;


/*****************************************************************************************************
 * This class holds the characters most important information. It contains all the information that
 * concerns a character. The Character Sheet itself is modeled after the Pathfinder character. Thus,
 * there might be some differing elements from other D&D games.
 ******************************************************************************************************/

public class CharacterSheet 
{
	/*THE DICE!*/
	Dice dice = new Dice();
	/************************************ Class Constants *************************************/

	
	
	public static final int ABILITY_COUNT = 5;		/* The number of abilities possessed by a character */
	public static final int ABILITY_FACTORS = 5;
	/* The number of factors that affect the ability score */
	/*
			 * row index 1 = fort
			 * row index 2 = reflex
			 * row index 3 = willpower
			 * 
			 * Y:
			 * column index 0 = total
			 * column index 1 ...class base.. 
			 * column index 2 = ability
			 * column index 3 = enhance
			 */

	
	/************************************ Class Attributes *************************************/
	
	/******** Basic Info *********/
	private String name;
	private String player;
	private int race;
	private int[] languages;
	private int size;
	private int gender;
	private int height;
	private int weight;
	private int age;
	private int alignments;
	private int deity;
	private String background;
	private String occupation;
	
	/********* Ability *********/
	
	public static final int ABILITY_STRENGTH = 0;
	public static final int ABILITY_DEXTERITY = 1;
	public static final int ABILITY_CONSTITUTION = 2;
	public static final int ABILITY_INTELLIGENCE = 3;
	public static final int ABILITY_WISDOM = 4;
	public static final int ABILITY_CHARISMA = 5;
	public static final int ABILITY_TOTAL = 0;
	public static final int ABILITY_MODIFIER = 1; // how to get?
	public static final int ABILITY_BASE = 2;
	public static final int ABILITY_INHERENT = 3; // how to get?
	public static final int ABILITY_ENHANCE = 4; // how to get?
	public static final int ABILITY_MISC = 5;    //what is this?
	/* 
	 * each row is Stat names
	 * X:
	 * row index 0,: = str
	 * row index 1,: = dex
	 * row index 2,: = con
	 * row index 3,: = intel
	 * row index 4,: = wis
	 * row index 5,: = cha
	 * Y:
	 * column index 0 = Total of stat
	 * column index 1 = mod
	 * column index 2 = base
	 * column index 3 = inherent
	 * column index 4 = enhance
	 * column index 5 = misc
	 */
	private int[][] rawStats;
	public void setStrTotal()//enchance set to 0
	{
		base =rawStats[ABILITY_STRENGTH][ABILITY_BASE];
		inher =rawStats[ABILITY_STRENGTH][ABILITY_INHERENT];
		enhance = 0;
		misc = 0;
		
		rawStats[ABILITY_STRENGTH][ABILITY_TOTAL]= base + inher + enhance + misc;
		
	}
	public void setStrBase()
	{
		stat = abilityRoll();
		rawStats[ABILITY_STRENGTH][ABILITY_BASE] = stat;
	}
	public void setStrInher(Race race)
	{
		stat = race.getStr();
		rawStats[ABILITY_STRENGTH][ABILITY_INHERENT] = stat;
	}
	//NEED TO DO THE SAME TO THE REST AS I DID TO STR FOR SETTERS
	public void setDex(Race race)
	{
		stat = race.getDex();
		rawStats[ABILITY_DEXTERITY][ABILITY_TOTAL] = stat;
	}
	public void setCon(Race race)
	{
		 stat = race.getCon();
		rawStats[ABILITY_CONSTITUTION][ABILITY_TOTAL] = stat;
	}
	public void setInt(Race race)
	{
		stat = race.getIntel();
		rawStats[ABILITY_INTELLIGENCE][ABILITY_TOTAL] = stat;
	}
	public void setWis(Race race)
	{
		stat = race.getWis();
		rawStats[ABILITY_WISDOM][ABILITY_TOTAL] = stat;
	}
	public void setCha(Race race)
	{
		stat = race.getcha();
		rawStats[ABILITY_CHARISMA][ABILITY_TOTAL] = stat;
	}
	/*GETTERS*/
	
	//STR
	public int getStrTotal()
	{
		stat =rawStats[ABILITY_STRENGTH][ABILITY_TOTAL];
		return stat;
	}
	public int getStrMod()
	{
		stat =rawStats[ABILITY_STRENGTH][ABILITY_MODIFIER];
		return stat;
	}
	public int getStrBase()
	{
		stat =rawStats[ABILITY_STRENGTH][ABILITY_BASE];
		return stat;
	}
	public int getStrInherent()
	{
		stat =rawStats[ABILITY_STRENGTH][ABILITY_INHERENT];
		return stat;
	}
	public int getStrEnhance()
	{
		stat =rawStats[ABILITY_STRENGTH][ABILITY_ENHANCE];
		return stat;
	}
	
	//DEX
	public int getDexTotal()
	{
		stat =rawStats[ABILITY_DEXTERITY][ABILITY_TOTAL];
		return stat;
	}
	public int getDexMod()
	{
		stat =rawStats[ABILITY_DEXTERITY][ABILITY_MODIFIER];
		return stat;
	}
	public int getDexBase()
	{
		stat =rawStats[ABILITY_DEXTERITY][ABILITY_BASE];
		return stat;
	}
	public int getDexInherent()
	{
		stat =rawStats[ABILITY_DEXTERITY][ABILITY_INHERENT];
		return stat;
	}
	public int getDexEnhance()
	{
		stat =rawStats[ABILITY_DEXTERITY][ABILITY_ENHANCE];
		return stat;
	}
	
	//Con
	public int getConTotal()
	{
		stat =rawStats[ABILITY_CONSTITUTION][ABILITY_TOTAL];
		return stat;
	}
	public int getConMod()
	{
		stat =rawStats[ABILITY_CONSTITUTION][ABILITY_MODIFIER];
		return stat;
	}
	public int getConBase()
	{
		stat =rawStats[ABILITY_CONSTITUTION][ABILITY_BASE];
		return stat;
	}
	public int getConInherent()
	{
		stat =rawStats[ABILITY_CONSTITUTION][ABILITY_INHERENT];
		return stat;
	}
	public int getConEnhance()
	{
		stat =rawStats[ABILITY_CONSTITUTION][ABILITY_ENHANCE];
		return stat;
	}
	
	//Int
	public int getIntTotal()
	{
		stat =rawStats[ABILITY_INTELLIGENCE][ABILITY_TOTAL];
		return stat;
	}
	public int getIntMod()
	{
		stat =rawStats[ABILITY_INTELLIGENCE][ABILITY_MODIFIER];
		return stat;
	}
	public int getIntBase()
	{
		stat =rawStats[ABILITY_INTELLIGENCE][ABILITY_BASE];
		return stat;
	}
	public int getIntInherent()
	{
		stat =rawStats[ABILITY_INTELLIGENCE][ABILITY_INHERENT];
		return stat;
	}
	public int getIntEnhance()
	{
		stat =rawStats[ABILITY_INTELLIGENCE][ABILITY_ENHANCE];
		return stat;
	}
	
	//WIS
	public int getWisTotal()
	{
		stat =rawStats[ABILITY_WISDOM][ABILITY_TOTAL];
		return stat;
	}
	public int getWisMod()
	{
		stat =rawStats[ABILITY_WISDOM][ABILITY_MODIFIER];
		return stat;
	}
	public int getWisBase()
	{
		stat =rawStats[ABILITY_WISDOM][ABILITY_BASE];
		return stat;
	}
	public int getWisInherent()
	{
		stat =rawStats[ABILITY_WISDOM][ABILITY_INHERENT];
		return stat;
	}
	public int getWisEnhance()
	{
		stat =rawStats[ABILITY_WISDOM][ABILITY_ENHANCE];
		return stat;
	}
	public int getChaTotal()
	{
		stat =rawStats[ABILITY_WISDOM][ABILITY_TOTAL];
		return stat;
	}
	public int getChaMod()
	{
		stat =rawStats[ABILITY_WISDOM][ABILITY_MODIFIER];
		return stat;
	}
	public int getChaBase()
	{
		stat =rawStats[ABILITY_CHARISMA][ABILITY_BASE];
		return stat;
	}
	public int getChaInherent()
	{
		stat =rawStats[ABILITY_CHARISMA][ABILITY_INHERENT];
		return stat;
	}
	public int getChaEnhance()
	{
		stat =rawStats[ABILITY_CHARISMA][ABILITY_ENHANCE];
		return stat;
	}
	
	//END OF ABILITY
	/********* HITPOINTS *********/
	private int hitpoints;//work on this later
	
	//END OF HITPOINTS
	
	/********************************
	 ***** Attacks And Defense ******
	 ********************************/								
	
	public static final int DEFENSE_AC = 0;
	public static final int DEFENSE_TOUCH = 1;
	public static final int DEFENSE_FLATFOOT = 2;
	public static final int DEFENSE_TOTAL = 0;
	public static final int DEFENSE_ARMOR = 1;
	public static final int DEFENSE_SHIELD = 2;
	public static final int DEFENSE_DEX = 3;
	public static final int DEFENSE_SIZE = 4;
	public static final int DEFENSE_DODGE = 5;
	public static final int DEFENSE_NATURAL = 6;
	public static final int DEFENSE_DEFLECT = 7;
	/*
	 * 
	 * X:
	 * row index 0 = ac
	 * row index 1 = touch
	 * row index 2 = flatfoot
	 * 
	 * Y:
	 * column index 0 = total
	 * column index 1 = armor 
	 * column index 2 = shield
	 * column index 3 = dex
	 * column index 4 = size
	 * column index 5 = dodge
	 * column index 6 = natural
	 * column index 7 = deflect
	 */
	int dex;
	
	private int[][] armorClass;
	//have not implemented armor Penalty yet
	//AC
	public void setACArmor(/*need armor Class to be made*/)
	{
		armorClass[DEFENSE_AC][DEFENSE_ARMOR]= 0;
	}
	public void setACShield(/*Need shield Class to be made*/)
	{
		armorClass[DEFENSE_AC][DEFENSE_SHIELD]= 0;
	}
	public void setACDex()
	{
		dex = rawStats[ABILITY_DEXTERITY][ABILITY_MODIFIER];
		armorClass[DEFENSE_AC][DEFENSE_DEX]= dex;
	}
	public void setACSize(Race race)
	{
		 size = race.getSize();
		armorClass[DEFENSE_AC][DEFENSE_SIZE]= size;
	}
	public void setACDodge(/*need armor and feats Class*/)
	{
		
		armorClass[DEFENSE_AC][DEFENSE_DODGE]= 0;
	}
	public void setACNaturalArmor(Race race/*need Skills and feats Class also*/)
	{
		
		armorClass[DEFENSE_AC][DEFENSE_NATURAL]= 0;
	}
	public void setACDeflect(/*may need Feats to determine*/)
	{
		
		armorClass[DEFENSE_AC][DEFENSE_DEFLECT]= 0;
	}
	public void setACTotal()
	{
		int armor = getACArmor();
		int shield = getACShield();
		int dex = getDexMod();
		int size = getACSize();
		int dodge = getACDodge();
		int deflect = getACDeflect();
		armorClass[DEFENSE_AC][DEFENSE_TOTAL]= 10 + armor + shield + dex + size + dodge + deflect;
	}
	public int getACTotal()
	{
		
		stat = armorClass[DEFENSE_AC][DEFENSE_TOTAL];
		return stat;
	}
	public int getACArmor()
	{
		
		stat = armorClass[DEFENSE_AC][DEFENSE_ARMOR];
		return stat;
	}
	public int getACShield()
	{
		
		stat = armorClass[DEFENSE_AC][DEFENSE_SHIELD];
		return stat;
	}
	public int getACDex()
	{
		
		stat = armorClass[DEFENSE_AC][DEFENSE_DEX];
		return stat;
	}
	public int getACSize()
	{
		
		stat = armorClass[DEFENSE_AC][DEFENSE_SIZE];
		return stat;
	}
	public int getACDodge()
	{
		
		stat = armorClass[DEFENSE_AC][DEFENSE_DODGE];
		return stat;
	}
	public int getACNatural()
	{
		
		stat = armorClass[DEFENSE_AC][DEFENSE_NATURAL];
		return stat;
	}
	public int getACDeflect()
	{
		
		stat = armorClass[DEFENSE_AC][DEFENSE_DEFLECT];
		return stat;
	}
	
	
	//TOUCH
	
	public void setTouchDex()
	{
		dex = rawStats[ABILITY_DEXTERITY][ABILITY_MODIFIER];
		armorClass[DEFENSE_TOUCH][DEFENSE_DEX]= dex;
	}
	public void setTouchSize(Race race)
	{
		 size = race.getSize();
		armorClass[DEFENSE_TOUCH][DEFENSE_SIZE]= size;
	}
	public void setTouchDodge(/*need armor and feats Class*/)
	{
		
		armorClass[DEFENSE_TOUCH][DEFENSE_DODGE]= 0;
	}
	
	public void setTouchDeflect(/*may need Feats to determine*/)
	{
		
		armorClass[DEFENSE_TOUCH][DEFENSE_DEFLECT]= 0;
	}
	public void setTouchTotal()
	{
		int dodge = getTouchDodge();
		int dex = getDexMod();
		int size = getTouchSize();
		int deflect = getTouchDeflect();
		armorClass[DEFENSE_TOUCH][DEFENSE_TOTAL]= 10 + dodge + dex + size + deflect;
	}
	public int getTouchTotal()
	{
		
		stat = armorClass[DEFENSE_TOUCH][DEFENSE_TOTAL];
		return stat;
	}
	public int getTouchDex()
	{
		
		stat = armorClass[DEFENSE_TOUCH][DEFENSE_DEX];
		return stat;
	}
	public int getTouchSize()
	{
		
		stat = armorClass[DEFENSE_TOUCH][DEFENSE_SIZE];
		return stat;
	}
	public int getTouchDodge()
	{
		
		stat = armorClass[DEFENSE_TOUCH][DEFENSE_DODGE];
		return stat;
	}
	public int getTouchDeflect()
	{
		
		stat = armorClass[DEFENSE_TOUCH][DEFENSE_DEFLECT];
		return stat;
	}
	
	//FlatFoot
	public void setFlatfootArmor(/*need armor Class to be made*/)
	{
		armorClass[DEFENSE_FLATFOOT][DEFENSE_ARMOR]= 0;
	}
	public void setFlatfootShield(/*Need shield Class to be made*/)
	{
		armorClass[DEFENSE_FLATFOOT][DEFENSE_SHIELD]= 0;
	}
	public void setFlatfootSize(Race race)
	{
		 size = race.getSize();
		armorClass[DEFENSE_FLATFOOT][DEFENSE_SIZE]= size;
	}
	public void setFlatfootNaturalArmor(Race race/*need Skills and feats Class also*/)
	{
		
		armorClass[DEFENSE_FLATFOOT][DEFENSE_NATURAL]= 0;
	}
	public void setFlatfootDeflect(/*may need Feats to determine*/)
	{
		
		armorClass[DEFENSE_FLATFOOT][DEFENSE_DEFLECT]= 0;
	}
	public void setFlatfootTotal()
	{
		int armor = getFlatfootArmor();
		int shield = getFlatfootShield();
	
		int size = getFlatfootSize();
		int natural = getFlatfootNatural();
		int deflect = getFlatfootDeflect();
		armorClass[DEFENSE_FLATFOOT][DEFENSE_TOTAL]= 10 + armor + shield  + size  + deflect + natural;
	}
	public int getFlatfootTotal()
	{
		
		stat = armorClass[DEFENSE_FLATFOOT][DEFENSE_TOTAL];
		return stat;
	}
	public int getFlatfootArmor()
	{
		
		stat = armorClass[DEFENSE_FLATFOOT][DEFENSE_ARMOR];
		return stat;
	}
	public int getFlatfootShield()
	{
		
		stat = armorClass[DEFENSE_FLATFOOT][DEFENSE_SHIELD];
		return stat;
	}
	public int getFlatfootSize()
	{
		
		stat = armorClass[DEFENSE_FLATFOOT][DEFENSE_SIZE];
		return stat;
	}
	public int getFlatfootDeflect()
	{
		
		stat = armorClass[DEFENSE_FLATFOOT][DEFENSE_DEFLECT];
		return stat;
	}
	public int getFlatfootNatural()
	{
		
		stat = armorClass[DEFENSE_FLATFOOT][DEFENSE_NATURAL];
		return stat;
	}
	
	/************************************ Saving Throws Setters/Getters *************************************/
	/*Setters*/
	//FIX ME don't have sets for columns yet just totals
		
		/*
		 * NOTE ON BASE SAVE (CLASS BASE)
		 * Base Save is the number given to you by your class(es). 
			Ex: 1st Level Fighter FORT +2 / REF +0 / WILL +0 
				1st Level Rogue FORT +0 / REF +2 / WILL +0 
				1st Level Wizard FORT +0 / REF +0 / WILL +2 
				1st Level Monk FORT +2 / REF +2 / WILL +2
	Multiclass Ex: - 1st Fighter/1st Monk FORT +4 / REF +2 / WILL +2
		 * 
		 * so each character's class has it's own fort ref and will
		 */
		
		
		/*SavingThrows
		 * X:
		 * row index 1 = fort
		 * row index 2 = reflex
		 * row index 3 = willpower
		 * 
		 * Y:
		 * column index 0 = total
		 * column index 1 ...class base.. 
		 * column index 2 = ability
		 * column index 3 = enhance
		 */
	
	
	public static final int SAVE_FORT = 0;
	public static final int SAVE_REF = 1;
	public static final int SAVE_WILL = 2;
	
	public static final int SAVE_TOTAL = 0;
	public static final int SAVE_CLASSBASE = 1;
	public static final int SAVE_ABILITY = 2;
	public static final int SAVE_ENHANCE = 3;
	 
	private int[][] savingthrows;
	public void setFort(CharacterClass clas)
	{
		stat = clas.getFort();
		savingthrows[SAVE_FORT][ SAVE_TOTAL] = stat;
	}
	public void setRef(CharacterClass clas)
	{
		stat = clas.getRef();
		savingthrows[SAVE_REF][ SAVE_TOTAL] = stat;
	}
	public void setWill(CharacterClass clas)
	{
		stat = clas.getWill();
		savingthrows[SAVE_WILL][ SAVE_TOTAL] = stat;
	}
	
	/*Getters*/
	//FIX ME dont have gets for columns yet
	
	public int getFort()
	{
		stat =savingthrows[SAVE_FORT][ SAVE_TOTAL]; 
		return stat;
	}
	public int getRef()
	{
		stat =savingthrows[SAVE_REF][ SAVE_TOTAL]; 
		return stat;
	}
	public int getWill()
	{
		stat =savingthrows[SAVE_WILL][ SAVE_TOTAL]; 
		return stat;
	}
	
	public void setBase(int stat, int statValue)
	{
		
		rawStats[stat][ABILITY_BASE] = statValue; 
		
	}
	public int getBase(int stat)
	{
		int returner =rawStats[stat][ABILITY_BASE];
		return returner;
	}
	public void setInher()
	{
		//no idea what this is
	}
	public void setMod(int stat, int statValue)
	{
		
		rawStats[stat][ABILITY_MODIFIER] =(statValue - 10 )/ 2; 
		
	}
	public int getMod(int stat)
	{
		
		int r =rawStats[stat][ABILITY_MODIFIER]; 
		return r;
	}
	
	
	/*
	 * ATTACKS
	 */
	
	/*
	 * X:
	 * row index 0 = Melee
	 * row index 1 = Ranged
	 * row index 2 = CMB
	 * Row index 3 = CMD **has a -10
	 * 
	 * Y:
	 * column index 0 = total
	 * column index 1 .. base attack.. 
	 * column index 2 = ability
	 * column index 3 = size
	 */
	
	private int[] attacks;
	
	
	//NEED SKILLS
	/*
	 * Just returns the String name of the skill.
	 * Will need to implement a 2d array for values
	 * of the skills
	 */
	public ArrayList<String> skills;
	public void getSkills(CharacterClass clas)
	{
		skills = clas.skills;
	}
	//NEED EXPERIANCE
	private int currentXP;
	private int xpTotalLvl;
	
	//NEED INIT
	public int getinit()
	{
		int dex = rawStats[ABILITY_DEXTERITY][ABILITY_TOTAL];
		return dex;
		
	//NEED MORE TO DETERMINE INITITIVE!!
		
		
	}
	
	
	//NEED DR-DAMAGE REDUCTION
	int damageReduction;
	//NEED SR-SPELL RESIST
	int spellResist;
	//NEED AP-ACTION POINTS
	int actionPoints;
	/************************************ Class Methods *************************************/
	public void initialize()
	{
		
	} /* end Initialize */
	
	/************************************ Class Getters *************************************/
	public int getAP(int attackType)
	{
		return(0);
	}
	
	/*
	 * Setting a race with a value in a raceContainer
	 */
	public void setRace(int raceValue)
	{
		this.race=raceValue;
		
	}
	public int getRace()
	{
		return(this.race);
	}
	
	/************************************ Stat Setters/Getters *************************************/
	/*SETTERS*/
	//need to add
	//fix me need more parameters for sets like add rolls = base, add inherence, enhance = 0, and misc = 0;
	int stat;
	int base; 
	int inher;
	int enhance;
	int misc;
	
	
	
	
	//ROLL FOR ABILITY METHOD
	/*
	 * This method can be used for deciding the initial values of your abilities
	 * 
	 */
	public int abilityRoll()
	{
		int returner= dice.roll(3, 4, 6);
		return returner;
	}
	
} /* end CharacterSheet method */
