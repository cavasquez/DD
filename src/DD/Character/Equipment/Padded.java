package DD.Character.Equipment;


public class Padded extends Armor {

	public Padded()
	{
		super();
		name = "Padded";
		enhance = 0;
		acBonus = 1;
		maxDex = 8;
		penalty = 0;
		spellFail = .05; //5%  
		type = 'L';//light medium or heavy
		weight = 10;
	}
	

	@Override
	public int getEnhance() {
		// TODO Auto-generated method stub
		return enhance;
	}

	@Override
	public int getAcBonus() {
		// TODO Auto-generated method stub
		return acBonus;
	}

	@Override
	public int getMaxDex() {
		// TODO Auto-generated method stub
		return maxDex;
	}

	@Override
	public int getPenalty() {
		// TODO Auto-generated method stub
		return penalty;
	}

	@Override
	public double getSpellFail() {
		// TODO Auto-generated method stub
		return spellFail;
	}

	@Override
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
