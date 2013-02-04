package MapTool;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import DD.ImageRenderComponent;

public class Floor extends Objects{
	public Floor(String id, ImageRenderComponent image, int move, int light, String name){
		super(id);
		super.image =image;
		super.movePenalty = move;
		super.lightPenalty = light;
		super.name = name;
		addNormList(this);
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

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		// TODO Auto-generated method stub
		
	}
}