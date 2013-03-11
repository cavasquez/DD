package DD.Character.CharacterSheet;

import java.io.Serializable;

public class SkillsList implements Serializable{
	private static final long serialVersionUID = 7876338012984496061L;
	public final int ACROBATICS = 0;
	public final int APPRAISE = 1;
	public final int BLUFF = 2;
	public final int CLIMB = 3;
	public final int CRAFT1 = 4;
	public final int CRAFT2 = 5;
	public final int DIPLOMACY = 6;
	public final int DISABLEDEVICE = 7;
	public final int DISGUISE = 8;
	public final int ESCAPEARTIST = 9;
	public final int FLY = 10;
	public final int HANDLEANIMAL = 11;
	public final int HEAL = 12;
	public final int INTIMIDATE = 13;
	public final int KNOWLEDGE1 = 14; //nature
	public final int KNOWLEDGE2 = 15;//nobility
	public final int KNOWLEDGE3 = 16;//religion
	public final int KNOWLEDGE4 = 17;//
	public final int KNOWLEDGE5 = 18;
	public final int KNOWLEDGE6 = 19;
	public final int LINGUISTICS = 20;
	public final int PERCEPTION = 21;
	public final int PERFORM = 22;
	public final int PROFESSION = 23;
	public final int RIDE = 24;
	public final int SENSEMOTIVE = 25;
	public final int SLEIGHTOFHAND = 26;
	public final int SPELLCRAFT = 27;
	public final int STEALTH = 28;
	public final int SURVIVAL = 29;
	public final int SWIM = 30;
	public final int USEMAGICDEVICE = 31;
	
	public final int TOTAL_SKILL_ROWS = 32;
	public final int TOTAL_SKILL_COL = 4;
	
	public final int TOTAL = 0;
	public final int RANKS = 1;
	public final int ABILITY = 2;
	public final int TRAINED = 3;
	
	int[][] skills = new int[TOTAL_SKILL_ROWS][TOTAL_SKILL_COL];
	
	public SkillsList()
	{
		for (int i = 0; i < TOTAL_SKILL_ROWS; i++) {
			for(int j = 0; j< TOTAL_SKILL_COL; j++)
			{
				skills[i][j] = 0;
			}
		}
	}
	public SkillsList(CharacterClass clas)
	{
		skills = clas.fillSkills();
	}
	
	public int[][] getList()
	{
		return skills;
	}
	
	
}
