package DD.MapTool;

import org.newdawn.slick.Image;

import DD.Entity;
import DD.ImageRenderComponent;

public abstract class Objects extends ImageRenderComponent{//figure out comp.
	int movePenalty;
	int lightPenalty;
	String name;
	Entity owner;
	//int id;
	//could pass in ID into constructor from the Component setID(...) ?
	//@brandon
	public Objects(String name, Image image, Map owner) {
		
		super(0, image);
		this.name = name;
		// TODO
		owner.addComponent(this);
	}

	abstract void checks();

	abstract void action();	
			
}
