package DD.ActionBox.CombatSystem.TargetingSystem;

import DD.MapTool.Map;

/*****************************************************************************************************
 * TargettingSystem will be used by Ability to place a TargetBlock on the Map object. The target blocks
 * will alert the player which blocks can be targeted. Once the target(s) are selected, the target
 * blocks will tell TargetingSystem they they are ready to be "cleaned up". 
 * 
 * TargetingSystem will then "clean up" the blocks and tell the Ability that called it who the target(s)
 * are (if any).
 * 
 * *TargetCount will tell TargetingSystem the type of targetCount needed by the Ability:
 * 	Single
 * 	Multiple
 * 	All
 * 
 * This could have been done with a number, but the introduction of an ALL field would have made it a 
 * but tricky. Of course, we could have used MAX INT as a number, but the enum seems much cleaner.
 * 
 * TargetShape will tell the TargetingSystem the "shape" that the TargetingSystem should be rendering
 * when placing TargetBlocks. The shapes are as follows:
 * 	Circle (the generic surrounding target placed around the origin (generally the player))
 * 	Cone
 * 
 * TargetSelection will tell the TargetingSystem whether or not the Characters that should be "acted" 
 * on are the selected targets or unselected targets.
 ******************************************************************************************************/

public class TargetingSystem 
{
	/************************************ Class Constants*************************************/
	private static int targetCount = 0;
	public static enum TargetCount
	{
		SINGLE (targetCount++),
		MULTIPLE (targetCount++),
		ALL (targetCount++);
		
		private final int index;
		
		TargetCount (int index)
		{
			this.index = index;
		} /* end TargetCount index */
		
		public int index()
		{
			return index;
		} /* end index for enum */
		
	} /* end TargetCount enum */
	
	public static enum TargetShape
	{
		CIRCLE,
		CONE;
		
	} /* end TargetShape enum */
	
	public static enum TargetSelection
	{
		SELECTED,
		UNSELECTED;
	} /* end Target enum */
	
	/************************************ Class Attributes *************************************/
	private static Map map = null;
	
	/************************************ Class Methods *************************************/
	public TargetingSystem()
	{
		
	} /* end TargetingSystem constructor */
	
	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
	public void setMap(Map map)
	{
		this.map = map;
	} /* end setMap method */
	
} /* end TargetingSystem class */
