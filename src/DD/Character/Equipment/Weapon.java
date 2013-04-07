package DD.Character.Equipment;

import java.io.Serializable;

import DD.ActionBox.Dice;
import DD.ActionBox.Dice.DieSize;


public class Weapon extends Equipment implements Serializable{

	private static final long serialVersionUID = 1971467098042856701L;
	int enchance;
	//int[] attackMod;
	Dice.DieSize damage;
	int critRange;
	int crit;
	int reach;
	char size;
	char type;// slashing or piercing
	String ammo_notes;
	int cost;
	public Weapon(int cost,String name, Dice.DieSize damage, int crit, int critRange, int reach,char size, char type ,String ammo_notes , int weight)
	{
		this.cost = cost;
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

//	
//	public int[] getAttackMod() {
//		// TODO Auto-generated method stub
//		return ;
//	}

	
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
		return ammo_notes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

}
