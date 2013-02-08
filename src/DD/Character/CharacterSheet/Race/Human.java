package DD.Character.CharacterSheet.Race;

public class Human extends Race {
	
	
	/*
	 * something annoying about humans is 
	 * you get the choice of placing 2 points
	 * in any 1 stat
	 */
	int choice;
	/*int str;
	int dex;
	int intel;
	int con;
	int wis;
	int cha;*/
	public Human()
	{
		baseSpeed = 30;
		size = 0;
		choice = stat(choice);
		if(choice == 0)
		{
			str = 2;
		}
		else if(choice == 1)
		{
			dex = 2;
		}
		else if(choice == 2)
		{
			intel = 2;
		}
		else if(choice == 3)
		{
			con = 2;
		}
		else if(choice == 4)
		{
			wis = 2;
		}
		else if(choice == 5)
		{
			cha = 2;
		}
	}
	
	/*
	 * 0 = str
	 * 1=  dex
	 * 2= con
	 * 3= int
	 * 4 = wis
	 * 5 = cha
	 */
	public int stat(int stat)
	{
		int choice=0;//default str
		if(stat == 0)
		{
			choice = 0;
		}
		else if(stat == 1)
		{
			choice = 1;
		}
		else if(stat == 2)
		{
			choice = 2;
		}
		else if(stat == 3)
		{
			choice = 3;
		}
		else if(stat == 4)
		{
			choice = 4;
		}
		else if(stat == 5)
		{
			choice = 5;
		}
		
		return choice;
	}

	@Override
	public int getStr() {
		return str;
	}

	@Override
	public int getDex() {
		
		return dex;
	}

	@Override
	public int getIntel() {
		
		return intel;
	}

	@Override
	public int getCon() {
	
		return con;
	}

	@Override
	public int getWis() {
		
		return wis;
	}

	@Override
	public int getcha() {
		
		return cha;
	}
	
}
