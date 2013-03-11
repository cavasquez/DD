package DD.MapTool;

import org.newdawn.slick.Color; 
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Floor extends Objects{
	
	private static final long serialVersionUID = 4476115979010560425L;
	private String message = " ";


	public Floor(){
		super();
	}
	
	public Floor(String name, Image image, int x, int y, int move, int light, Map map) throws SlickException{
		super(name, image, map, x, y);
		super.movePenalty = move;
		super.lightPenalty = light;
		super.priority = 0;
	}
	
	
	
	public void setImage(Image image) {
		this.image = image;
	}
	void checks() {
			
	}

	void action() {
		
	}
	
	public int getX(){
		return position.x;
	}
	
	public int getY(){
		return position.y;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){	
		String t="";
		t += "Name: "+name+ ", movePenalty: "+ movePenalty+ ", lightPenalty: "+lightPenalty;
		return t; 
				
	}
	
	
	public static void main(String[] args) {
		
	}
	
	
}
