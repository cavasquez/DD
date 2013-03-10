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
		armor.add( new Armor("Leather",0,2,6,0,.1,'L',15 ));
		armor.add( new Armor("Chain Shirt",0,4,4,-2,.2,'L',25 ));
		armor.add( new Armor("Scale mail",0,5,3,-4,.25,'M',30 ));
		armor.add( new Armor("Chainmail",0,6,2,-5,.3,'M',40 ));
		armor.add( new Armor("Breastplate",0,6,3,-4,.25,'M',30 ));
		armor.add( new Armor("Banded mail",0,7,1,-7,.4,'H',45 ));
		armor.add( new Armor("Half-plate",0,8,0,-7,.4,'H',50 ));
		armor.add( new Armor("Full plate",0,9,1,-6,.35,'H',50 ));
		armor.add( new Armor("Heavy Steel Shield",0,2,0,-2,.15,'S',15 ));
		armor.add( new Armor("Buckler",0,1,0,-1,.05,'S',5 ));
		armor.add( new Armor("Light Steel Shield",0,1,0,-2,.05,'S',7 ));
		
		
		
		weapons.add(new Weapon("Longsword",Dice.DieSize.D8,2,19,5,'M','S',"Note:",4));
		weapons.add(new Weapon("BattleAxe",Dice.DieSize.D8,3,0,5,'M','S',"Note:",6));
		weapons.add(new Weapon("Cutlass",Dice.DieSize.D6,2,18,5,'M','S',"Note:",4));
		weapons.add(new Weapon("Klar",Dice.DieSize.D6,2,0,5,'M','S',"Note:",6));
		weapons.add(new Weapon("Heavy Pick",Dice.DieSize.D6,4,0,5,'M','P',"Note:",6));
		weapons.add(new Weapon("Rapier",Dice.DieSize.D6,2,18,5,'M','S',"Note:",1));
		weapons.add(new Weapon("Warhammer",Dice.DieSize.D8,3,19,5,'M','B',"Note:",5));
	
	}
			
		}
	

