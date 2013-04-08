package DD.MapTool;

import java.io.Serializable;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import DD.SlickTools.DDImage;

/*****************************************************************************************************
 * Wall will represent any "wall" that can be found in game. Although there may be many objects that 
 * have varying movement penalties, the point of making an explicit wall object is that there are 
 * certain feats or abilities that work specifically on walls. Furthermore, when it comes to targeting,
 * the movement penalty of an object is ignored (since it is only a movement penalty). What does make
 * a difference is if there is a wall.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class Wall extends Objects
{

	/************************************ Class Constants *************************************/
	private static final long serialVersionUID = -8729861511362126379L;
	private float x, y;
	
	/************************************ Class Attributes *************************************/
	
	/************************************ Class Methods *************************************/
	public Wall() {
		super();
		System.out.println("BLARG");
		image = new DDImage(1470, 577).getScaledCopy(0.9f);
	}
	
	public Wall(String name, DDImage image, int x, int y, Map map) throws SlickException
	{
		super(name, image, map, x, y);
		super.movePenalty = Integer.MAX_VALUE/2; 	/* Divide by 2 to avoid overflow when adding but maintain high value */
		super.lightPenalty = Integer.MAX_VALUE/2; 	/* Divide by 2 to avoid overflow when adding but maintain high value */
		super.priority = 9;
		this.x = x;
		this.y = y;
	} /* end constructor */
	
	public Wall(String name, Map map) throws SlickException
	{
		super(name, map);
		super.movePenalty = Integer.MAX_VALUE/2; 	/* Divide by 2 to avoid overflow when adding but maintain high value */
		super.lightPenalty = Integer.MAX_VALUE/2; 	/* Divide by 2 to avoid overflow when adding but maintain high value */
		super.priority = 9;
		this.x = 0;
		this.y = 0;
		
		image = new DDImage(1470, 577).getScaledCopy(0.9f);
	
	}
	
	/*
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		image.draw(x, y);
	}
	*/
} /* end Wall class */
