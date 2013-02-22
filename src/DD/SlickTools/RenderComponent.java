package DD.SlickTools;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/*****************************************************************************************************
 * The RenderComponent class will extend the Component class and provide Components the option to render.
 * This should essentially give the Entity method a new render method to use. The idea for the 
 * RenderComponent class came from: http://slick.cokeandcode.com/wiki/doku.php?id=entity_tutorial.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public abstract class RenderComponent extends Component
{
	
	/************************************ Class Methods *************************************/
	public RenderComponent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RenderComponent (int id)
	{
		super(id);
	} /* end RenderComponent constructor */
	


	public abstract void render(GameContainer gc, StateBasedGame sbg, Graphics gr);
	
} /* end RenderComponent method */
