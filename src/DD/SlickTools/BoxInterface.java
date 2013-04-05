package DD.SlickTools;

import java.util.ArrayList;

import org.newdawn.slick.geom.Vector2f;

/*****************************************************************************************************
 * The BoxInterface class is an extension of Entity that focuses on representing a menu or other box in 
 * the interface. This could be the ChatBox, ActionBox, StatusBox, etc. The point being that the 
 * BoxInterface will need to retain a bit more information than a typical entity would (such as the size 
 * of the box, the location of the corners, etc). It might also need to hold more entities (such as menu 
 * selection choices) that a typical entity would not have to hold. 
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public abstract class BoxInterface extends Entity 
{
	/************************************ Class Constants *************************************/
	private float length;
	private float width;
	
	/************************************ Class Methods *************************************/
	public BoxInterface(int id, float length, float width) 
	{
		this(id, length, width, new Vector2f(0,0));
	} /* end MenuBox constructor */
	
	public BoxInterface(int id, float length, float width, Vector2f position)
	{
		super(id);
		this.length = length;
		this.width = width;
		this.position = position;
		components = new ArrayList<Component>();
	} /* end BoxInterface method */
	
	public ArrayList<Component> getComponentList() 
	{
		return components;
	} /* end getComponentList method */	
	
	/****************************************************************************************
	 ************************************ Getter Methods ************************************
	 ****************************************************************************************/
	public float getWidth()
	{
		return width;
	} /* end getWidth method */
	
	public float getLength()
	{
		return length;
	} /* end getLength method */

} /* end MenuBox method */
