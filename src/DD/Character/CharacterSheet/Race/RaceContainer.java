package DD.Character.CharacterSheet.Race;

public class RaceContainer 
{
	public static final int TOTAL_RACES = 2;
	public static final int ELF = 0;
	public static final int HUMAN = 1;
	/*
	 * humna
	 * elf
	 */
	public RaceContainer()
	{
		Race[] race = new Race[TOTAL_RACES];
		race[ELF] = new Race();
		race[HUMAN] = new Race();
	}
	
	
	

	
}
