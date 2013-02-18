package DD.MapTool;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import DD.SlickTools.*;

public class Objects extends ImageRenderComponent {//figure out comp.
	int movePenalty;
	int lightPenalty;
	String name;
	Entity owner;
	int priority;
	//int id;
	//could pass in ID into constructor from the Component setID(...) ?
	//@brandon
	public Objects(String name, Image image, Map owner) {
		
		super(0, image);
		this.name = name;
		// TODO
		owner.addComponent(this);
	}

	
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		// TODO Auto-generated method stub
		
	}
	
}
