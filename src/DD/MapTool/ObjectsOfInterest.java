package DD.MapTool;

import org.newdawn.slick.Image;

public class ObjectsOfInterest extends Objects{
	String toolTipContent;
	
	public ObjectsOfInterest(String toolTipContent, Image image, int move, int light, String name){
		this.toolTipContent = toolTipContent;
		super.image =image;
		super.movePenalty = move;
		super.lightPenalty = light;
		super.name = name;
		super.addNormList(this);
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
}
