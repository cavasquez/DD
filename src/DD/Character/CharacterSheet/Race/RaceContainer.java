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
	Race[] race = new Race[TOTAL_RACES];
	public RaceContainer()
	{
		
		race[ELF] = new Elf() ;
		race[HUMAN] = new Human();
	}
	
	public Race getRace(int inputRace)
	{
		Race myRace = race[inputRace];
		return myRace;
	}
	
	
	

	
}
