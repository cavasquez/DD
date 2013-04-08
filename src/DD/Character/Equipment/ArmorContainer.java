package DD.Character.Equipment;

public class ArmorContainer {
	
	public static final int TOTAL_ARMOR= 1;
	public static final int PADDED = 0;
	
	Armor[] armor = new Armor[TOTAL_ARMOR];
	
	public ArmorContainer()
	{
		armor[PADDED] = new Padded(); 
	}
	
	public Armor getArmor(int arm)
	{
		return armor[arm];
	}

}
