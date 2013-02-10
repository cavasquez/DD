package DD.MapTool;
import org.newdawn.slick.Image;

import DD.Character.*;

public class CharacterObjects extends Objects{
	DDCharacter ddchar;
	
	public CharacterObjects(String name, Image image, Map owner, DDCharacter ddchar) {
		super(name, image, owner);
		this.ddchar = ddchar;
		super.priority =10;
	}
	
}
