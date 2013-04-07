package DD.MapTool;

import org.newdawn.slick.Color; 
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import DD.SlickTools.DDImage;


public class Floor extends Objects{
	
	private static final long serialVersionUID = 4476115979010560425L;
	private String message = " ";

	public Floor() {
		
		super();
		System.out.println("fuck a duck");
		image = new DDImage(1185, 416).getScaledCopy(0.9f);
	}

	public Floor(String name, int x, int y, int move, int light, Map map) throws SlickException{
		this(name, new DDImage(1185, 416).getScaledCopy(0.9f), x, y,move, light, map);
		
	}
	
	
	public Floor(String name, DDImage image, int x, int y, int move, int light, Map map) throws SlickException{
		super(name, image, map, x, y);
		super.movePenalty = move;
		super.lightPenalty = light;
		super.priority = 0;
		
		
	}

	public void setImage(DDImage image) {
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
