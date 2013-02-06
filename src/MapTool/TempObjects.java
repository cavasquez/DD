package MapTool;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import DD.ImageRenderComponent;

public class TempObjects extends Objects{

	int turnCount; //numPlayers*Duration,
	int id;
	MapTool owner;
	World world;
	//@brandon
	public TempObjects(String name, int turnCont, Image image, int move, int light, MapTool owner){
		super(name,image);
		this.turnCount = turnCont;
		//super.image = image;
		super.movePenalty = move;
		super.lightPenalty = light;
		this.owner  = owner;
		//DO YOU WANT THIS METHOD STILL? OR USE THE ONE FROM WORLD? ...i need it here for in the place method.
		//GOING TO USE THE ONE FROM WORLD, IT WAS FROM being cast from MapTool
		//MADE A METHOD IN WORLD TO HOLD JUST TEMP ITEMS
		world.addTempList(this);
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
