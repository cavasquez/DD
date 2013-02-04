package MapTool;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import DD.ImageRenderComponent;

public class TempObjects extends Objects{

	int turnCount; //numPlayers*Duration,
	int id;
	MapTool owner;
	public TempObjects(String id, int turnCont, ImageRenderComponent image, int move, int light, MapTool owner){
		super(id);
		this.turnCount = turnCont;
		super.image = image;
		super.movePenalty = move;
		super.lightPenalty = light;
		this.owner  = owner;
		owner.addTempList(this);
	}
	void checks() {

	}

	void action() {
			
	}
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
