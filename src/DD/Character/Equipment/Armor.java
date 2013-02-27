package DD.Character.Equipment;

public abstract class Armor extends Equipment{

	
	int enhance;
	int acBonus;
	int maxDex;
	int penalty;
	double spellFail; 
	char type;//light medium or heavy

	public Armor()
	{
		
	}
	abstract public int getEnhance();
	abstract public int getAcBonus();
	abstract public int getMaxDex();
	abstract public int getPenalty();
	abstract public double getSpellFail();
	abstract public char getType();
	
	
	
}
