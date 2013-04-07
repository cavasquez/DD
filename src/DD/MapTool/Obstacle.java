package DD.MapTool;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import DD.SlickTools.*;

public class Obstacle extends Objects{
	public Obstacle(String name , DDImage image, int move, int light,Map map) throws SlickException{
		super(name, image, map, null, null); //TODO: needs position
		super.movePenalty = move;
		super.lightPenalty = light;
		super.priority = 2;
	}

	void checks() {
		
	}

	void action() {
		
	}
	//tooltip info?
	public String toString(){	
		return "Name: "+name+ ", movePenalty: "+ movePenalty+ ", lightPenalty: "+lightPenalty;
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
