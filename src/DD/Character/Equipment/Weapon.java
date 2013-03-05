package DD.Character.Equipment;

import DD.ActionBox.Dice;
import DD.ActionBox.Dice.DieSize;


public class Weapon extends Equipment{

	
	int enchance;
	//int[] attackMod;
	Dice.DieSize damage;
	int critRange;
	int crit;
	int reach;
	char size;
	char type;// slashing or piercing
	String ammo_notes;
	
	public Weapon(String name, Dice.DieSize damage, int crit, int critRange, int reach,char size, char type ,String ammo_notes , int weight)
	{
		 this.name = name;
		// this.attackMod= attackMod; 
		 this.damage = damage; 
		 this.crit= crit;
		 this.critRange = critRange;
		this.reach=reach;
		this.size=size; 
		this.type=type; 
		this.ammo_notes=ammo_notes;
		this.weight = weight;
	}
	
	//string, dice, int,int char,char, string, int
	
	public int getEnhance() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int[] getAttackMod() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public DieSize getDamage() {
		// TODO Auto-generated method stub
		return damage;
	}
	
	public int getCritRange()
	{
		return critRange;
	} /* end getCritRange method */

		public int getCrit() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getReach() {
		// TODO Auto-generated method stub
		return reach;
	}

	
	public char getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public char getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getAmmo_Notes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
