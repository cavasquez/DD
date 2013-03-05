package DD.Character.Equipment;

import java.util.ArrayList;

import DD.ActionBox.Dice;
import DD.ActionBox.Dice.DieSize;

public class Armory {

	public ArrayList<Armor> armor = new ArrayList<Armor>();
	public ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	public Armory()
	{
		/*
		name = "Padded";
		enhance = 0;
		acBonus = 1;
		maxDex = 8;
		penalty = 0;
		spellFail = .05; //5%  
		type = 'L';//light medium or heavy
		weight = 10;
		
		*/
		/*
		 *  this.name = name;
		 this.attackMod= attackMod; 
		 this.damage = damage; 
		 this.crit= crit;
		this.range=range;
		this.size=size; 
		this.type=type; 
		this.ammo_notes=ammo_notes;
		this.weight = weight;
		 */
		armor.add( new Armor("Padded",0,1,8,0,.05,'L',10 ));
		weapons.add(new Weapon("Longsword",Dice.DieSize.D6,2,19,5,'M','S',"Note:",4));
	}
			
		}
	

