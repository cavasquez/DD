package DD.MapTool;

import java.io.Serializable;

import org.newdawn.slick.Image;

/*****************************************************************************************************
 * Wall will represent any "wall" that can be found in game. Although there may be many objects that 
 * have varying movement penalties, the point of making an explicit wall object is that there are 
 * certain feats or abilities that work specifically on walls. Furthermore, when it comes to targeting,
 * the movement penalty of an object is ignored (since it is only a movement penalty). What does make
 * a difference is if there is a wall.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class Wall extends Objects implements Serializable
{

	/************************************ Class Constants *************************************/
	private static final long serialVersionUID = 1L;
	
	/************************************ Class Attributes *************************************/
	
	/************************************ Class Methods *************************************/
	public Wall() {super();}
	
	public Wall(String name, Image image, Map map)
	{
		super(name, image, map);
		super.movePenalty = Integer.MAX_VALUE/2; 	/* Divide by 2 to avoid overflow when adding but maintain high value */
		super.lightPenalty = Integer.MAX_VALUE/2; 	/* Divide by 2 to avoid overflow when adding but maintain high value */
		super.priority = 10;
	} /* end constructor */
} /* end Wall class */
