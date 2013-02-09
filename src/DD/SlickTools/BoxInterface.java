package DD.SlickTools;

import org.newdawn.slick.geom.Vector2f;

/*****************************************************************************************************
 * The BoxInterface class is an extension of Entity that focuses on representing a menu or other box in 
 * the interface. This could be the ChatBox, ActionBox, StatusBox, etc. The point being that the 
 * BoxInterface will need to retain a bit more information than a typical entity would (such as the size 
 * of the box, the location of the corners, etc). It might also need to hold more entities (such as menu 
 * selection choices) that a typical entity would not have to hold. 
 ******************************************************************************************************/

public abstract class BoxInterface extends Entity 
{
	/************************************ Class Constants *************************************/
	float length;
	float width;
	
	/************************************ Class Methods *************************************/
	public BoxInterface(int id, float length, float width) 
	{
		super(id);
		this.length = length;
		this.width = width;
	} /* end MenuBox constructor */

} /* end MenuBox method */
