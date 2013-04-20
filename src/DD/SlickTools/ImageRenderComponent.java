package DD.SlickTools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/*****************************************************************************************************
 * @author Carlos Vasquez
 ******************************************************************************************************/

public abstract class ImageRenderComponent extends RenderComponent implements Serializable
{
	private static final long serialVersionUID = 7853838660291699011L;
	/************************************ Class Constants *************************************/
	public DDImage image;
	
	/************************************ Class Methods *************************************/
	public ImageRenderComponent(){
		super();
	}
	
	public ImageRenderComponent(int id)
	{
		super(id);
	}
	
	public ImageRenderComponent(int id, DDImage image)
	{
		super(id);
		this.image = image;
	} /* end ImageRenderComponent constructor */
	
	public void loadImage(){
		image.getImage();
	}
	
	public DDImage loadDDImage(String name) {
		DDImage e = null;
		try {
			FileInputStream fileIn = new FileInputStream("C:/Program Files (x86)/DD/test.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (DDImage) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();

		} catch (ClassNotFoundException c) {
			c.printStackTrace();

		}
		return e;
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
	{
		render(gc, sbg, gr, owner.getPosition());		
	} /* end render method */
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr, Vector2f pos)
	{
		float scale = owner.getScale();
		image.draw(pos.x, pos.y, scale);
	} /* end render method */
	
	public void setImage(DDImage image) {
		this.image = image;
	}
	
} /* end ImageRenderComponent class */
