package DD.SlickTools;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/*****************************************************************************************************
 * The Entity class will represent objects in game. A monster, a menu, or whatever else will appear.
 * The key attributes for the entity class are it's ability to hold Components, it's render method, 
 * it's update method, and it's knowledge of it's place in the world. The idea for the Entity class
 * came from: http://slick.cokeandcode.com/wiki/doku.php?id=entity_tutorial. It appears to be basic
 * game design to utilize the idea of the Entity class.
 ******************************************************************************************************/

public class Entity 
{
	/************************************ Class Constants *************************************/
	protected int id;
	protected Vector2f position;
	protected float scale;
	ArrayList<Component> components = null;
	
	/************************************ Class Methods *************************************/
	public Entity (int id)
	{
		this.id = id;
	} /* end Entity constructor */
	
	public void addComponent(Component component)
	{
		component.setOwnerEntity(this);
		components.add(component);
	} /* end AddComponent method */
	
	public void update (GameContainer gc, StateBasedGame sbg, int delta)
	{
		updateComponents(gc, sbg, delta);
	} /* end update method */
	
	public void render (GameContainer gc, StateBasedGame sbg, Graphics gr)
	{
		
		renderComponents(gc, sbg, gr);
	} /* end render method */
	
	protected void updateComponents(GameContainer gc, StateBasedGame sbg, int delta)
	{
		for (Component component : components)
		{
			component.update(gc, sbg, delta);
		} /* end for loop */
	} /* end updateComponents method */
	
	protected void renderComponents(GameContainer gc, StateBasedGame sbg, Graphics gr)
	{
		RenderComponent renderComponent = null;
		for (Component component : components)
		{
			if (RenderComponent.class.isInstance(component))
			{
				renderComponent = (RenderComponent) component;
				renderComponent.render(gc, sbg, gr);
			} /* end if */
			
		} /* end for loop */
		
	} /* end renderComponents method */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	
	public Component getComponent(int id)
	{
		Component returner = null;
		
		return(returner);
	} /* end getComponent method */
	
	public Vector2f getPosition()
	{
		return (position);
	} /* end getPosition method */
	
	public float getScale()
	{
		return (scale);
	} /* end getScale method */
	
	public int getId()
	{
		return(id);
	} /* end getId method */
	
	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
	public void setPosition(Vector2f position)
	{
		this.position = position;
	} /* end setPosition method */
	
	public void setScale(float scale)
	{
		this.scale = scale;
	} /* end setScale method */
	
} /* End Entity class */
