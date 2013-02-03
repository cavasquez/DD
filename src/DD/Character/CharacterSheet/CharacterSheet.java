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
	private int[][] abilityScore;
	private int totalHP;
	
	private int[][] classRecorder;
	
	
	/* Ability Score influences */
	
	
	/************************************ Class Methods *************************************/
	public void initialize()
	{
		
	} /* end Initialize */
	
	/************************************ Class Getters *************************************/
	public int getAP(int attackType)
	{
		return(0);
	}
	
} /* end CharacterSheet method */
