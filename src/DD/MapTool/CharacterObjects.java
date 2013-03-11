package DD.MapTool;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import DD.Character.*;
import DD.CombatSystem.TargetingSystem.Coordinate;

public class CharacterObjects extends Objects{
	public DDCharacter ddchar;
	
	public CharacterObjects(String name, Image image, Map owner, DDCharacter ddchar) throws SlickException {
		super(name, image, owner, 0, 0);
		this.ddchar = ddchar;
		super.priority =10;

	}
	
	public CharacterObjects(String name, Image image, int x, int y, Map owner, DDCharacter ddchar) throws SlickException {
		super(name, image, owner, x, y);
		this.ddchar = ddchar;
		super.priority =10;
	}
	
	public String toString(){
		return "DDcharToString:(~*-*)~\n";
	}
	
	public void update(GameContainer gc, StateBasedGame sb, Graphics gr) {
	
	}
	
	public void setPosition(int x, int y) {
		this.position = new Coordinate(x,y);
	}
}
