package DD.Character.CharacterSheet;

import DD.Character.CharacterSheet.Race.Elf;
import DD.Character.CharacterSheet.Race.Human;
import DD.Character.CharacterSheet.Race.Race;

public class ClassContainer {
	 
	
		public static final int TOTAL_CLASSES = 1;
		public static final int BARB = 0;
	
		
		CharacterClass[] clas = new CharacterClass[TOTAL_CLASSES];
		public ClassContainer(CharacterSheet sheet)
		{
			
			clas[BARB] = new Barbarian(sheet) ;

		}
		
		public CharacterClass getClass(int inputClass)
		{
			CharacterClass myClass = clas[inputClass];
			return myClass;
		}
		
		
		

		
	}


