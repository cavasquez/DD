package DD.Character.CharacterSheet;

import java.util.ArrayList;

/*****************************************************************************************************
 * This structure represents the basic class information for a specific Character. The information includes
 * HP gained from a particular class, levels in a particular class, etc.
 ******************************************************************************************************/

public class ClassRecorder 
{
	
	
	
	
	String className;
	int[] bab;
	int skills;
	int fort;
	int ref;
	int will;
	int levels;
	int hp;
	
	
	public ClassRecorder(CharacterClass clas)
	{
		hp = clas.getHp();
		className = clas.getClassName();
		bab = clas.getBab();
		fort = clas.getFort();
		ref = clas.getRef();
		will = clas.getWill();
		skills = clas.getSkills();
		levels = clas.getLevels();
		
	}
	
	
	
	public int[] getBab() {
		return bab;
	}
	public void setBab(int[] bab) {
		this.bab = bab;
	}
	public int getSkills() {
		return skills;
	}
	public void setSkills(int skills) {
		this.skills = skills;
	}
	public int getFort() {
		return fort;
	}
	public void setFort(int fort) {
		this.fort = fort;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getWill() {
		return will;
	}
	public void setWill(int will) {
		this.will = will;
	}
	public int getLevels() {
		return levels;
	}
	public void setLevels(int levels) {
		this.levels = levels;
	}
	
} /* end ClassRecorder class */
