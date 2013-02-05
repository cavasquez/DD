package MapTool;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import DD.ImageRenderComponent;

public class Obstacle extends Objects{
	
	//@brandon
	World world;
	public Obstacle(String name , Image image, int move, int light){
		super(name, image);
		//super.image =image;
		super.movePenalty = move;
		super.lightPenalty = light;
		//super.name = name;
		world.addNormList(this);
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
