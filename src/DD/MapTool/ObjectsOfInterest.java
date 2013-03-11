package DD.MapTool;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import DD.SlickTools.*;


public class ObjectsOfInterest extends Objects{
	String toolTipContent;
	
	//@brandon
	public ObjectsOfInterest(String name, String toolTipContent, Image image, int move, int light, Map map) throws SlickException{
		super(name,image,map, null, null); //TODO: needs position
		this.toolTipContent = toolTipContent;
		
		super.movePenalty = move;
		super.lightPenalty = light;
		super.priority = 2;
	
	}
	/*
	 * TODO: i need more info to handle this.
	 */
	void checks() {
		//Check class is part of GameMechanics?
	}
	/*
	 * TODO: action(): display toolTipContent in tooltip window.
	 */
	void action() {
			
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
