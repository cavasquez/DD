package DD.Character.Equipment;

import java.io.Serializable;

public class Armor extends Equipment implements Serializable{
	private static final long serialVersionUID = 5345532757085254659L;
	
	int enhance;
	int acBonus;
	int maxDex;
	int penalty;
	double spellFail; 
	char type;//light medium or heavy
	
	public Armor(String name, int enhance, int acBonus, int maxDex,int penalty,double spellFail, char type, int weight)
	{
		 this.name = name;
		 this.enhance = enhance; 
		 this.acBonus = acBonus; 
		 this.maxDex= maxDex;
		this.penalty=penalty;
		this.spellFail=spellFail; 
		this.type=type; 
		this.weight=weight;
		
	}

	public int getEnhance() {
		// TODO Auto-generated method stub
		return enhance;
	}


	public int getAcBonus() {
		// TODO Auto-generated method stub
		return acBonus;
	}

	
	public int getMaxDex() {
		// TODO Auto-generated method stub
		return maxDex;
	}

	
	public int getPenalty() {
		// TODO Auto-generated method stub
		return penalty;
	}

	
	public double getSpellFail() {
		// TODO Auto-generated method stub
		return spellFail;
	}

	
	public char getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return weight;
	}
	
}
