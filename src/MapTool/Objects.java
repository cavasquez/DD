package MapTool;

import DD.Entity;
import DD.ImageRenderComponent;

public abstract class Objects extends ImageRenderComponent{//figure out comp.

	ImageRenderComponent image; //renderImageComp. 
	int movePenalty;
	int lightPenalty;
	String name;
	int id;
	
	public Objects(String name) {
		super(id);
		this.name = name;		
	}
	
	abstract void checks();

	abstract void action();	
			
}
