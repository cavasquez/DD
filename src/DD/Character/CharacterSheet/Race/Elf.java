package DD.Character.CharacterSheet.Race;

import java.io.Serializable;

public class Elf extends Race implements Serializable{

	private static final long serialVersionUID = 2871670282082301008L;
	//starting stats
	int str;
	int dex;
	int intel;
	int con;
	int wis;
	int cha;
	
	public Elf()
	{
		raceName="Elf";
		naturalArmor =0;
		baseSpeed = 30;
		size = 0;
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
	
	@Override
	public int getSize() {
		
		return size;
	}

	@Override
	public int getNaturalArmor() {
		// TODO Auto-generated method stub
		return naturalArmor;
	}

	@Override
	public String getRaceName() {
		// TODO Auto-generated method stub
		return raceName;
	}
}
