package DD.SlickTools;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/*****************************************************************************************************
 * CharacterEntity will extend the Entity class and be used by DDCharacter to provide some modified 
 * entity related capabilities (specifically rendering capabilities).
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class CharacterEntity extends Entity
{
	/************************************ Class Methods *************************************/
	public CharacterEntity (int id)
	{
		super(id);
	} /* end CharacterEntity constructor */

	public CharacterEntity() 
	{
		super();
	} /* end CharacterEntity constructor */
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr, float x, float y)
	{
		renderComponents(gc, sbg, gr, x, y);
	} /* end render method */
	
	protected void renderComponents(GameContainer gc, StateBasedGame sbg, Graphics gr, float x, float y)
	{
		RenderComponent renderComponent = null;
		CharacterRenderComponent crc = null;
		for (Component component : components)
		{
			if (RenderComponent.class.isInstance(component))
			{
				renderComponent = (RenderComponent) component;
				renderComponent.render(gc, sbg, gr);
			} /* end if */
			else if(CharacterRenderComponent.class.isInstance(component))
			{
				crc = (CharacterRenderComponent) component;
				crc.render(gc, sbg, gr, x, y);
			} /* end else if */
			
		} /* end for loop */
		
	} /* end renderComponents method */
	
} /* end CharacterEntity class */
