package DD.Character.CharacterSheet;

import java.util.ArrayList;

import DD.SlickTools.Component;
import MapTool.Objects;

public class Barbarian extends CharacterClass {
	
	public ArrayList<String> skills;
	
	//Barbarian Level 1
	public Barbarian()
	{
		skills = new ArrayList<String>();
		fillBarbSkills();
		className = "Barbarian";
		bab = 1;
		fort = 2;
		ref= 0;
		will= 0;
		levels= 1;
		
	}
	
	public void fillBarbSkills()
	{
		/*
		 * Barbarians have specific skills
		 * always plus 2 to trianed
		 * 
		 */
		skills.add("Acrobatics");
		skills.add("Climb");
		skills.add("Craft");
		skills.add("Handle Animal");
		skills.add("Inimidate");
		skills.add("Knowledge(nature)");
		skills.add("Perception");
		skills.add("Ride");
		skills.add("Survival");
		skills.add("Swim");
		
		
	}
	
	@Override
	public int getBab() {
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
	public ArrayList<String> getSkills() {
		// TODO Auto-generated method stub
		return skills;
	}
	
	
}
