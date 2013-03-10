package DD.Character.CharacterSheet.Race;

import java.io.Serializable;

public abstract class  Race  implements Serializable
{

	private static final long serialVersionUID = 3551099833072892648L;
	public String raceName;
	public int naturalArmor;
	public int str;
	public int dex;
	public int intel;
	public int con;
	public int wis;
	public int cha;
	public int baseSpeed;
	public int size; // 1 small . 0 med. -1 large
	
	public abstract int getStr();
	public abstract int getDex();
	public abstract int getIntel();
	public abstract int getCon();
	public abstract int getWis();
	public abstract int getcha();
	public abstract int getSize();
	public abstract int getNaturalArmor();
	public abstract String getRaceName();
}
