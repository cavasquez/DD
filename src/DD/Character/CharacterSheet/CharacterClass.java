package DD.Character.CharacterSheet;

import java.util.ArrayList;

public abstract class CharacterClass {
	String className;
	int bab;
	ArrayList<String> skills;
	int fort;
	int ref;
	int will;
	int levels;
	
	public abstract int getBab();
	public abstract int getFort();
	public abstract int getRef();
	public abstract int getWill();
	public abstract int getLevels();
	public abstract ArrayList<String> getSkills();
	
}
