package DD.MapTool;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import DD.Character.*;

public class CharacterObjects extends Objects{

	private static final long serialVersionUID = -942204818115561142L;
	public DDCharacter ddchar;
	private float x, y;
	
	public CharacterObjects(String name, Image image, Map owner, DDCharacter ddchar) {
		super(name, image, owner);
		this.ddchar = ddchar;
		super.priority =10;
		x = 0;
		y = 0;
	}
	
	public CharacterObjects(String name, Image image, float x, float y, Map owner, DDCharacter ddchar) {
		super(name, image, owner);
		this.ddchar = ddchar;
		super.priority =10;
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return "DDcharToString:(~*-*)~\n";
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		image.draw(x, y);
	}
	
	public void update(GameContainer gc, StateBasedGame sb, Graphics gr) {
		
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
}
