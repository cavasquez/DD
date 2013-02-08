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
	/* Ability Array locations */
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
	
	
	public static final int SAVE_FORT = 0;
	public static final int SAVE_REF = 1;
	public static final int SAVE_WILL = 2;
	
	public static final int SAVE_TOTAL = 0;
	public static final int SAVE_CLASSBASE = 1;
	public static final int SAVE_ABILITY = 2;
	public static final int SAVE_ENHANCE = 3;
	
	
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
	
	/********* Basic Stats *********/
	
	
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
	
	ClassRecorder classRecorder;
	
	
	private int hitpoints;//work on this later
	
	
	
	/* Ability Score influences */
	
	/*	Attacks And Defense*/
	/*AC
	 * index 0 = total
	 * index 1 = armor
	 * index 2 = shield
	 * index 3 = dex
	 * index 4 = size
	 * index 5 = dodge
	 * index 6 = natural
	 * index 7 = deflect
	 */
	private int[] ac; //has a -10
	
	/*TOUCH
	 * (SHOULD THIS BE THE SAME AS HITPOINTS WITH NULL SPACES?)
	 * index 0 = total
	 * index 1 = dex
	 * index 2 = size
	 * index 3 = dodge
	 * INDEX 4 = DEFLECT
	 */
	private int[] touch; //has a -10
	
	/*FLATFOOT
	 * index 0 = total
	 * index 1 = armor
	 * index 2 = shield
	 * index 4 = size
	 * index 6 = natural
	 * index 7 = deflect
	 */
	private int[] flatfoot; //has a -10
	
	
	
	
	
	//SavingThrows
	//does not have notes or modifiers section
	
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
	private int[][] savingthrows;
	
	
	
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
	public int getStr()
	{
		stat =rawStats[ABILITY_STRENGTH][ABILITY_TOTAL];
		return stat;
	}
	public int getDex()
	{
		stat =rawStats[ABILITY_DEXTERITY][ABILITY_TOTAL];
		return stat;
	}
	public int getCon()
	{
		stat =rawStats[ABILITY_CONSTITUTION][ABILITY_TOTAL];
		return stat;
	}
	public int getIntel()
	{
		stat =rawStats[ABILITY_INTELLIGENCE][ABILITY_TOTAL];
		return stat;
	}
	public int getWis()
	{
		stat =rawStats[ABILITY_WISDOM][ABILITY_TOTAL];
		return stat;
	}
	public int getCha()
	{
		stat =rawStats[ABILITY_CHARISMA][ABILITY_TOTAL];
		return stat;
	}
	
	/************************************ Saving Throws Setters/Getters *************************************/
	/*Setters*/
	//FIX ME don't have sets for columns yet just totals
	
	/*
	
	public static final int SAVE_FORT = 0;
	public static final int SAVE_REF = 1;
	public static final int SAVE_WILL = 2;
	
	public static final int SAVE_TOTAL = 0;
	public static final int SAVE_CLASSBASE = 1;
	public static final int SAVE_ABILITY = 2;
	public static final int SAVE_ENHANCE = 3;
	 */
	
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
