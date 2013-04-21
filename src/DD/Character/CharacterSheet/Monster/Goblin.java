package DD.Character.CharacterSheet.Monster;

import DD.ActionBox.Dice;
import DD.Character.DDCharacter;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.Character.Equipment.Weapon;
import DD.SlickTools.DDImage;

public class Goblin extends MonsterSheet
{	
	public Goblin()
	{
		super();
		this.rawStats[CharacterSheet.ABILITY_STRENGTH][CharacterSheet.ABILITY_TOTAL] = 11;
		this.rawStats[CharacterSheet.ABILITY_DEXTERITY][CharacterSheet.ABILITY_TOTAL] = 15;
		this.rawStats[CharacterSheet.ABILITY_CONSTITUTION][CharacterSheet.ABILITY_TOTAL] = 12;
		this.rawStats[CharacterSheet.ABILITY_INTELLIGENCE][CharacterSheet.ABILITY_TOTAL] = 10;
		this.rawStats[CharacterSheet.ABILITY_WISDOM][CharacterSheet.ABILITY_TOTAL] = 9;
		this.rawStats[CharacterSheet.ABILITY_CHARISMA][CharacterSheet.ABILITY_TOTAL] = 6;
		name = "Goblin";
		
		//this.attacks[CharacterSheet.ATTACK_MELEE][CharacterSheet.ATTACK_TOTAL]= 1;
		this.setMeleeTotal(1);
		
	
		this.setACTotal(16);
		this.setTouchTotal(13);
		this.setFlatfootArmor(2);
		this.setFlatfootShield(1);
		this.setFlatfootSize(1);
		this.setFlatfootTotal(14);
		
		this.setFortTotal(3);
		this.setRefTotal(2);
		this.setWillTotal(-1);
		
		//sheet has a hitpoints variable that holds the hp of the monster or person this sets it to 6 + rolls
		//a 1d10 die to determine the hp
		Dice dice = new Dice(Dice.DieSize.D10);
		this.hp = (6 + dice.roll(1));
		
		this.init = this.getInit() + 6;
		
		this.equipWeapon(new Weapon(15,"Cutlass",Dice.DieSize.D6,2,18,5,'M','S',"Note:",4),0);
		this.setImage(new DDImage("Images/Test/DungeonCrawl_ProjectUtumnoTileset.png", 98, 65, 33, 34));
		 this.XP = 135;
	}
	
//	public int getMelee()
//	{
//		return 1;
//	}
//	public int getCmb()
//	{
//		return 0;
//	}
//	public int getCmd()
//	{
//		return 12;
//	}
//	public int getFort()
//	{
//		return 3;
//	}
//	public int getRef()
//	{
//		return 2;
//	}
//	public int getWill()
//	{
//		return -1;
//	}
//	public int getAC()
//	{
//		return 16;
//	}
//	public int getTouch()
//	{
//		return 13;
//	}
//	public int getFlatfoot()
//	{
//		return 14;
//	}
//	public int getArmor()
//	{
//		return 2;
//	}
//	public int getShield()
//	{
//		return 1;
//	}
//	public int getSize()
//	{
//		return 1;
//	}
//	public int getInit()
//	{
//		return this.init;
//	}
//	
//	public Weapon getWeapon()
//	{
//		return this.getEquippedWeapon(0);
//	}
//	
//	public CharacterSheet getCharacterSheet() {
//		return sheet;
//	}
}
