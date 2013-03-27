package DD.CombatSystem.TargetingSystem;

import java.io.Serializable;

/*****************************************************************************************************
 * Coordinate will simply be an object that holds a pair of points (x,y)
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class Coordinate implements Serializable
{
	private static final long serialVersionUID = 6576095273459173876L;
	/************************************ Class Attributes *************************************/
	public final Integer x;
	public final Integer y;
	
	/************************************ Class Methods *************************************/
	public Coordinate(Integer x, Integer y)
	{
		this.x = x;
		this.y = y;
	} /* end Coordinate Constructor */
	
} /* end Coordinate class */
