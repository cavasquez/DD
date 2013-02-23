package DD.MapTool;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;
import DD.SlickTools.*;

public class TempObjects extends Objects{

	int turnCount; //numPlayers*Duration,
	int id;
	
	public TempObjects(String name, int turnCont, Image image, int move, int light, Map map){
		super(name,image,map);
		this.turnCount = turnCont;
		super.movePenalty = move;
		super.lightPenalty = light;
		super.priority = 4; 
	}
	void checks() {

	}

	void action() {
			
	}
	
	//method for testing.
	public String toString(){	
		return "Name: "+name+ ", TurnCount: "+turnCount + ", movePenalty:" + movePenalty+ ", lightPenalty: "+lightPenalty;

	}
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) {
		// TODO Auto-generated method stub
		
	}
}
