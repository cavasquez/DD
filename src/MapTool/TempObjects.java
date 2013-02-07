package MapTool;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import DD.ImageRenderComponent;

public class TempObjects extends Objects{

	int turnCount; //numPlayers*Duration,
	int id;
	
	
	//@brandon
	public TempObjects(String name, int turnCont, Image image, int move, int light, Map map){
		super(name,image,map);
		this.turnCount = turnCont;
		//super.image = image;
		super.movePenalty = move;
		super.lightPenalty = light;
		
		
		
	}
	void checks() {

	}

	void action() {
			
	}
	
	//method for testing.
	public String toString(){	
		return "Id: "+id+"\n"+
			   "TurnCount: "+turnCount+"\n"+
			   "movePenalty: "+ movePenalty+"\n"+
			   "lightPenalty: "+lightPenalty+"\n";
	}
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		// TODO Auto-generated method stub
		
	}
}
