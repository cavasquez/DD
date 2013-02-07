package DD.Character.CharacterSheet;

/*****************************************************************************************************
 * This class holds the characters most important information. It contains all the information that
 * concerns a character. The Character Sheet itself is modeled after the Pathfinder character. Thus,
 * there might be some differing elements from other D&D games.
 ******************************************************************************************************/

public class CharacterSheet 
{
	/************************************ Class Constants *************************************/
	/* Ability Array locations */
	public static final int ABILITY_STRENGTH = 0;
	public static final int ABILITY_DEXTERITY = 1;
	public static final int ABILITY_CONSTITUTION = 2;
	public static final int ABILITY_INTELLIGENCE = 3;
	public static final int ABILITY_WISDOM = 4;
	public static final int ABILITY_CHARISMA = 5;
	
	public static final int ABILITY_TOTAL = 0;
	public static final int ABILITY_MODIFIER = 1;
	public static final int ABILITY_BASE = 2;
	public static final int ABILITY_ENHANCE = 4;
	public static final int ABILITY_MISC = 5;
	
	public static final int ABILITY_COUNT = 5;		/* The number of abilities possessed by a character */
	public static final int ABILITY_FACTORS = 5;		/* The number of factors that affect the ability score */
	
	
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
	private int[][] abilityScore;
	
	private int[][] classRecorder;
	
	
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
	
	/*FLATFOO
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
	 * row index 0 = fort
	 * row index 1 = reflex
	 * row index 2 = willpower
	 * 
	 * Y:
	 * column index 0 = total
	 * column index 1 ...class base.. 
	 * column index 2 = ability
	 * column index 3 = enhance
	 */
	private int[] savingthrows;
	
	
	
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
	
	//NEED EXPERIANCE
	private int currentXP;
	private int xpTotalLvl;
	
	//NEED INIT
	public void init()
	{
		
	}
	//NEED DR-DAMAGE REDUCTION
	
	//NEED SR-SPELL RESIST
	
	//NEED AP-ACTION POINTS
	
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
	
} /* end CharacterSheet method */
