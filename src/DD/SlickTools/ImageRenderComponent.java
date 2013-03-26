package DD.SlickTools;

import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/*****************************************************************************************************
 * @author Carlos Vasquez
 ******************************************************************************************************/

public abstract class ImageRenderComponent extends RenderComponent
{
	/************************************ Class Constants *************************************/
	protected Image image;
	
	/************************************ Class Methods *************************************/
	public ImageRenderComponent(){
		super();
	}
	
	public ImageRenderComponent(int id)
	{
		super(id);
	}
	
	public ImageRenderComponent(int id, Image image)
	{
		super(id);
		this.image = image;
	} /* end ImageRenderComponent constructor */
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
	{
		Vector2f pos = owner.getPosition();
		float scale = owner.getScale();
		image.draw(pos.x, pos.y, scale);
		
	} /* end render method */
	
	public void setImage(Image image) {
		this.image = image;
	}
	
} /* end ImageRenderComponent class */
