package DD.Character.CharacterSheet.Race;

import java.io.Serializable;

public class RaceContainer implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1389848317369327417L;
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
