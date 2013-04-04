package DD.SlickTools;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/*****************************************************************************************************
 * CharacterRenderComponent will be owned by the DDCharacter class and provide DCharacters a way to
 * obtain additional functionality when rendering. An ImageRenderComponent is not enough because we
 * may need to provide coordinates.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public abstract class CharacterRenderComponent extends Component
{
	public CharacterRenderComponent() 
	{
		super();
	} /* end CharacterRenderComponent constructor */
	
	public CharacterRenderComponent (int id)
	{
		super(id);
	} /* end CharacterRenderComponent constructor */
	
	public abstract void render(GameContainer gc, StateBasedGame sbg, Graphics gr, float x, float y);

} /* end CharacterComponent method */
