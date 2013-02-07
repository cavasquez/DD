package DD.Character.CharacterSheet.Race;

public class Elf extends Race{

	//starting stats
	int str;
	int dex;
	int intel;
	int con;
	int wis;
	int cha;
	public Elf()
	{
		baseSpeed = 30;
		str =0;
		dex = 2;
		intel =2;
		con =-2;
		wis =0;
		cha =0;
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
