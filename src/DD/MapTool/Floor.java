package DD.MapTool;

import org.newdawn.slick.Image;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;


public class Floor extends Objects{
	/*
	 * Need the specific world not just some pointer to the Object world.
	 * 
	 * May have to pass the World ID or name to get the specific world
	 */
	public Floor(String name, Image image, int move, int light, Map map){
		super(name, image, map);
	
		super.movePenalty = move;
		super.lightPenalty = light;

	}
	
	void checks() {
			
	}

	void action() {
		
	}	
	
	public String toString(){	
		return "Name: "+name+"\n"+
			   "movePenalty: "+ movePenalty+"\n"+
			   "lightPenalty: "+lightPenalty+"\n";
	}

	
	public void render(){}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		// TODO Auto-generated method stub
		
	}
	
	
}
