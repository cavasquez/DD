package DD.SlickTools;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/*****************************************************************************************************
 * The Entity class will represent objects in game. A monster, a menu, or whatever else will appear.
 * The key attributes for the entity class are it's ability to hold Components, it's render method, 
 * it's update method, and it's knowledge of it's place in the world. The idea for the Entity class
 * came from: http://slick.cokeandcode.com/wiki/doku.php?id=entity_tutorial. It appears to be basic
 * game design to utilize the idea of the Entity class.
 * 
 * @author Carlos Vasquez
 * @author GM-Michael VanWie
 ******************************************************************************************************/

public class Entity 
{
	/************************************ Class Attributes *************************************/
	protected int id;								/* entities ID */
	protected int componentId;						/* id used to keep track of component ID's (currently holds the NEXT component ID) */
	protected Queue<Integer> recycledIds;			/* ID's of objects that have given up their id (thus the ID can be used again */
	protected Queue<Component> trash;				/* temporary holding spot for components that will be deleted. This is to deal with the ConcurrentModificationException */
	protected Vector2f position;
	protected float scale;
	protected ArrayList<Component> components = null;
	
	/************************************ Class Methods *************************************/
	public Entity (int id)
	{
		this.id = id;
		trash = new LinkedList<Component>();
		components = new ArrayList<Component>();
		recycledIds = new LinkedList<Integer>();
	} /* end Entity constructor */
	
	/*
	 * used for Serializable.
	 */
	public Entity() {
		id=0;
		components = new ArrayList<Component>();
		recycledIds = new LinkedList<Integer>();
		// TODO Auto-generated constructor stub
	}

	public int addComponent(Component component)
	{ /* add a component to the components ArrayList and return it's id */
		Integer id = this.componentId;
		if((id = recycledIds.poll()) == null) id = this.componentId++; /* Take a recycled id. If none exists, take an ID from id and increment it */
		component.setOwnerEntity(this);
		component.setId(id);
		components.add(component);
		
		return(id);
	} /* end AddComponent method */
	
	public void removeComponent(int id)
	{ /* Remove the component with the provided ID from components and add it's id to the recycledIds ArrayList */
		boolean found = false;
		int index = 0;
		Component deleteMe = null;

		while (!found)
		{
			deleteMe = components.get(index++);
			
			if (deleteMe.getId() == id) 
			{ /* component found. remove it */
				found = true; 
				components.remove(index-1); // Recall that the index was incremented
				recycledIds.offer(id); /* TODO: implement later */
			} /* end if */
			
		} /* end while loop */
		
	} /* end AddComponent method */
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		updateComponents(gc, sbg, delta);
	} /* end update method */
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
	{
		renderComponents(gc, sbg, gr);
	} /* end render method */
	
	protected void updateComponents(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		for(int i = 0; i < components.size(); i++) components.get(i).update(gc, sbg, delta);
	} /* end updateComponents method */
	
	protected void renderComponents(GameContainer gc, StateBasedGame sbg, Graphics gr)
	{
		RenderComponent renderComponent = null;
		for(int i = 0; i < components.size(); i++)
		{
			if (RenderComponent.class.isInstance(components.get(i)))
			{
				renderComponent = (RenderComponent) components.get(i);
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
	
	public Component[] getComponentsArray()
	{
		return components.toArray(new Component[components.size()]);
	}
	
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
