package DD.Character.CharacterSheet;

/*****************************************************************************************************
 * This structure represents the basic class information for a specific Character. The information includes
 * HP gained from a particular class, levels in a particular class, etc.
 ******************************************************************************************************/

public class ClassRecorder 
{
	String className;
	int bab; //base attack bonus
	int skills;
	int fort;
	int ref;
	int will;
	int levels;
	//default paladin class
	public ClassRecorder()
	{
		className = "paladin";
		bab= 0;
		skills = 0;
		fort =0;
		ref = 0;
		will =0;
		levels =0;
	}
	public ClassRecorder(String className,int bab,int skills,
			int fort,int ref,int will,int levels)
	{
		this.className = className;
		this.bab= bab;
		this.skills = skills;
		this.fort =fort;
		this.ref = ref;
		this.will = will;
	   	this.levels = levels;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getBab() {
		return bab;
	}
	public void setBab(int bab) {
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
