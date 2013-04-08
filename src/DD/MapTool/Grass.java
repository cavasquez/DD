package DD.MapTool;

import org.newdawn.slick.Color; 
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import DD.SlickTools.DDImage;


public class Grass extends Floor{

	private static final long serialVersionUID = 2196087082873521545L;
	private String message = " ";

	public Grass() {
		
		super();
		image = new DDImage(0, 477).getScaledCopy(0.9f);
		priority =0;
	}

	public Grass(String name, Map map) throws SlickException
	{
		super(name,0,0,5,5,map);		
		image = new DDImage(0, 477).getScaledCopy(0.9f);
		priority =0;
	
	}
	
	
	public Grass(String name, int x, int y, int move, int light, Map map) throws SlickException{
		this(name, new DDImage(0, 477).getScaledCopy(0.9f), x, y,move, light, map);
	}
	
	
	public Grass(String name, DDImage image, int x, int y, int move, int light, Map map) throws SlickException{
		super(name, image, x, y,move,light,map);
		movePenalty = move;
		lightPenalty = light;
		priority = 0;
		

	}

	public Grass(String name, DDImage image) throws SlickException {
		super(name,image,0,0,5,5,null);
		priority = 0;
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
