package DD.MapTool;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;


public class Floor extends Objects{
	
	private static final long serialVersionUID = 4476115979010560425L;
	private float x, y;

	public Floor(){
		super();
		
	}
	
	public Floor(String name, Image image, float x, float y, int move, int light, Map map){
		super(name, image, map);
		super.movePenalty = move;
		super.lightPenalty = light;
		super.priority = 0;
		this.x = x;
		this.y = y;
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		Color myAlphaColor = new Color(1f, 1f, 1f, 0.5f);
		image.draw(x, y, myAlphaColor);
		//If opacity doesn't work then we could use another tile to represent clickable squares
		//this.image.setAlpha(0.8f);
		//this.image.draw(x, y);
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		
	}
	
	void checks() {
			
	}

	void action() {
		
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
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
