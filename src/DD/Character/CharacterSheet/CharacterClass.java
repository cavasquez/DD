package DD.Character.CharacterSheet;

import java.util.ArrayList;

public abstract class CharacterClass {
	String className;
	int[] bab;
	int[] fortSave = new int[20];
	int[] refSave  = new int[20];
	int[] willSave = new int[20];
	int skills;
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
	public abstract void levelUp(CharacterSheet sheet);
	
	
	
}
