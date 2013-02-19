package DD.Character.CharacterSheet;

import java.util.ArrayList;

public abstract class CharacterClass {
	String className;
	int[] bab = new int[5];
	int[] fortSave = new int[20];
	int[] refSave  = new int[20];
	int[] willSave = new int[20];
	SkillsList skillList = new SkillsList();
	int skills; //number of skills allowed
	int fort;
	int ref;
	int will;
	int levels;
	int hp;
	public abstract String getClassName();
	public abstract int[] getBab();
	public abstract int getFort();
	public abstract int getRef();
	public abstract int getWill();
	public abstract int getLevels();
	public abstract int getSkills();
	public abstract int getHp();
	public abstract int[][] fillSkills();
	public abstract void levelUp(CharacterSheet sheet);
	
	
	
}
