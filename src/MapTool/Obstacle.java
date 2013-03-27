package MapTool;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

public class Obstacle extends Objects{
	
	//@brandon
	
	public Obstacle(String name , Image image, int move, int light,Map map){
		super(name, image, map);
		
		super.movePenalty = move;
		super.lightPenalty = light;
		
	
	}

	void checks() {
		
	}

	void action() {
		
	}
	//tooltip info?
	public String toString(){	
		return "Name: "+name+"\n"+
			   "movePenalty: "+ movePenalty+"\n"+
			   "lightPenalty: "+lightPenalty+"\n";
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		// TODO Auto-generated method stub
		
	}

}
