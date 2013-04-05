package DD.MapTool;

import java.io.Serializable;

import DD.Character.CharacterSheet.CharacterSheet;
import DD.CombatSystem.TargetingSystem.Coordinate;

public class SerMapCharHelper implements Serializable{
	private static final long serialVersionUID = 5809242053203716685L;
	Coordinate coord;
	CharacterSheet cs;
	
	public SerMapCharHelper(Coordinate coord, CharacterSheet cs){
		this.coord = coord;
		this.cs = cs;
	}
	
}
