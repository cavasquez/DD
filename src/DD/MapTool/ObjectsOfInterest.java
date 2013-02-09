package DD.MapTool;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import DD.ImageRenderComponent;


public class ObjectsOfInterest extends Objects{
	String toolTipContent;
	
	//@brandon
	public ObjectsOfInterest(String name, String toolTipContent, Image image, int move, int light, Map map){
		super(name,image,map);
		this.toolTipContent = toolTipContent;
		
		super.movePenalty = move;
		super.lightPenalty = light;
	
	
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
}
