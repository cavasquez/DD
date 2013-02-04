package MapTool;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import DD.ImageRenderComponent;

public class ObjectsOfInterest extends Objects{
	String toolTipContent;
	
	public ObjectsOfInterest(String id, String toolTipContent, ImageRenderComponent image, int move, int light, String name){
		super(id);
		this.toolTipContent = toolTipContent;
		super.image =image;
		super.movePenalty = move;
		super.lightPenalty = light;
		super.name = name;
		MapTool.addNormList(this);
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
