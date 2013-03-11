package DD.Character.CharacterSheet;

import java.io.Serializable;
import java.util.ArrayList;

import DD.ActionBox.Dice;
import DD.SlickTools.Component;

public class Barbarian extends CharacterClass implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7891712802724053413L;
	//import class of skills
	private final int FIRSTATTACK = 0;
	private final int SECONDATTACK = 1;
	private final int THIRDATTACK =2;
	private final int FOURTHATTACK = 3;
	private final int FIFTHATTACK=4;
	
	int[][] list;
	
	//Barbarian Level 1
	/*
	 * HARD CODE VALUES
	 */
	
	
	
	public Barbarian(CharacterSheet sheet)
	{
		hp = 12;
		skills = 4 + sheet.getIntMod();
		
		className = "Barbarian";
		bab[FIRSTATTACK] = 1;
		bab[SECONDATTACK] = 0;
		bab[THIRDATTACK] = 0;
		bab[FOURTHATTACK] = 0;
		bab[FIFTHATTACK] = 0;
		fort = 2;
		ref= 0;
		will= 0;
		levels= 1;
		fortSave[0] = 2;
		fortSave[1] = 3;
		fortSave[2] = 3;
		fortSave[3] = 4;
		fortSave[4] = 4;
		fortSave[5] = 5;
		fortSave[6] = 5;
		fortSave[7] = 6;
		fortSave[8] = 6;
		fortSave[9] = 7;
		fortSave[10] = 7;
		fortSave[11] = 8;
		fortSave[12] = 8;
		fortSave[13] = 9;
		fortSave[14] = 9;
		fortSave[15] = 10;
		fortSave[16] = 10;
		fortSave[17] = 11;
		fortSave[18] = 11;
		fortSave[19] = 12;
		
		refSave[0] = 0;
		refSave[1] = 0;
		refSave[2] = 1;
		refSave[3] = 1;
		refSave[4] = 1;
		refSave[5] = 2;
		refSave[6] = 2;
		refSave[7] = 2;
		refSave[8] = 3;
		refSave[9] = 3;
		refSave[10] = 3;
		refSave[11] = 4;
		refSave[12] = 4;
		refSave[13] = 4;
		refSave[14] = 5;
		refSave[15] = 5;
		refSave[16] = 5;
		refSave[17] = 6;
		refSave[18] = 6;
		refSave[19] = 6;
		
		willSave[0] = 0;
		willSave[1] = 0;
		willSave[2] = 1;
		willSave[3] = 1;
		willSave[4] = 1;
		willSave[5] = 2;
		willSave[6] = 2;
		willSave[7] = 2;
		willSave[8] = 3;
		willSave[9] = 3;
		willSave[10] = 3;
		willSave[11] = 4;
		willSave[12] = 4;
		willSave[13] = 4;
		willSave[14] = 5;
		willSave[15] = 5;
		willSave[16] = 5;
		willSave[17] = 6;
		willSave[18] = 6;
		willSave[19] = 6;
		list=skillList.getList();//filled with 0s
	}
	
	public int[][] fillSkills()
	{
		
		
		list[skillList.ACROBATICS][skillList.TRAINED] = 3;
		list[skillList.CLIMB][skillList.TRAINED] = 3;
		list[skillList.CRAFT1][skillList.TRAINED] = 3;
		list[skillList.HANDLEANIMAL][skillList.TRAINED] = 3;
		list[skillList.INTIMIDATE][skillList.TRAINED] = 3;
		list[skillList.KNOWLEDGE1][skillList.TRAINED] = 3;
		list[skillList.PERCEPTION][skillList.TRAINED] = 3;
		list[skillList.RIDE][skillList.TRAINED] = 3;
		list[skillList.SURVIVAL][skillList.TRAINED] = 3;
		list[skillList.SWIM][skillList.TRAINED] = 3;
		return list;
	}
	
	@Override
	public int[] getBab() {
		// TODO Auto-generated method stub
		return bab;
	}

	@Override
	public int getFort() {
		// TODO Auto-generated method stub
		return fort;
	}

	@Override
	public int getRef() {
		// TODO Auto-generated method stub
		return ref;
	}

	@Override
	public int getWill() {
		// TODO Auto-generated method stub
		return will;
	}

	@Override
	public int getLevels() {
		// TODO Auto-generated method stub
		return levels;
	}

	@Override
	public int getSkills() {
		// TODO Auto-generated method stub
		return skills;
	}

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return className;
	}
	
	public int getHp()
	{
		return hp;
	}

	@Override
	//always pass in "this" sheet in CharacterSheet
	public void levelUp(CharacterSheet sheet) {
		Dice dice = new Dice();
		levels++;
		skills += 4 + sheet.getIntMod();
		hp += dice.roll(1,12);
		fort = fortSave[levels -1];
		ref = refSave[levels -1];
		will = willSave[levels -1];
		
		int value=0;
		
		if(levels <= 5 && levels > 0)
		{
			value = bab[FIRSTATTACK];
			value++;
			bab[FIRSTATTACK] = value;
		}
		else if(levels > 5 && levels <= 10  )
		{
			value = bab[FIRSTATTACK];
			value++;
			bab[FIRSTATTACK] = value;
			
			value = bab[SECONDATTACK];
			value++;
			bab[SECONDATTACK] = value;
		}
		else if(levels > 10 && levels <= 15  )
		{
			value = bab[FIRSTATTACK];
			value++;
			bab[FIRSTATTACK] = value;
			
			value = bab[SECONDATTACK];
			value++;
			bab[SECONDATTACK] = value;
			
			value = bab[THIRDATTACK];
			value++;
			bab[THIRDATTACK] = value;
		}
		else if(levels > 15 && levels <= 20  )
		{
			value = bab[FIRSTATTACK];
			value++;
			bab[FIRSTATTACK] = value;
			
			value = bab[SECONDATTACK];
			value++;
			bab[SECONDATTACK] = value;
			
			value = bab[THIRDATTACK];
			value++;
			bab[THIRDATTACK] = value;
			
			value = bab[FOURTHATTACK];
			value++;
			bab[FOURTHATTACK] = value;
		}
		else
		{
			//ERROR INVALID LEVEL
			if(levels  < 1)
			{
				levels = 1;
			}
			else
			{
				levels = 20;
			}
		}
		//could create a method in charactersheet that sets the value
		sheet.setFortBase(this);
	    //need attacks COME BACK HERE
	}

	
	
}
