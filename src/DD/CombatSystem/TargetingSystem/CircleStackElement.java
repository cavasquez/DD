package DD.CombatSystem.TargetingSystem;

/*****************************************************************************************************
 * CircleStackElement will be used exclusively by the placeCircle method in TargetingSystem. It will
 * contain a position and an integer that correlates to the distance moved during placement.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class CircleStackElement 
{
	/************************************ Class Attributes *************************************/
	private final Coordinate position;
	private final int distance;
	
	/************************************ Class Methods*************************************/
	public CircleStackElement(Coordinate position, int distance)
	{
		this.position = position;
		this.distance = distance;
	} /* end CircleStackElement constructor */
	
	public Coordinate getPosition()
	{
		return position;
	} /* end getPosition method */
	
	public int getDistance()
	{
		return distance;
	} /* end getDistance method */
	
} /* end CircleStackElement class */
