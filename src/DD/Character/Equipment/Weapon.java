package DD.Character.Equipment;


public abstract class Weapon extends Equipment{

	
	int enchance;
	int[] attackMod;
	int damage;
	int crit;
	int range;
	char size;
	char type;// slashing or piercing
	String ammo_notes;
	
	abstract public int getEnhance();
	abstract public int[] getAttackMod();
	abstract public int getDamage();
	abstract public int getCrit();
	abstract public int getRange();
	abstract public char getSize();
	abstract public char getType();
	abstract public String  getAmmo_Notes();
}
