package DD.ActionBox.CombatSystem.TargetingSystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import DD.Character.DDCharacter;
import DD.Character.Abilities.Ability;
import DD.MapTool.Map;
import DD.Message.ChooseTargetMessage;
import DD.Message.TargetSelectedMessage;

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
 * on are the selected targets or unselected targets or whether or not we are performing a move.
 * 	SELECTED
 * 	UNSELECTED
 * 	MOVE
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
		CONE,
		MOVE;	/* A circle, but the placement differs slightly */
		
	} /* end TargetShape enum */
	
	public static enum TargetSelection
	{
		SELECTED,
		UNSELECTED;
	} /* end Target enum */
	
	/************************************ Class Attributes *************************************/
	private static Map map = null;						/* Map on which we are placing TargetBlocks */
	private static Ability caller = null;				/* keeps track of Ability that called TargetSystem */
	private static TargetSelection selection = null;	/* keeps track of type of TargetSelection so targetSelected know which Characters to return */
	private static TargetShape shape = null;			/* We need to know the shape during clean up */
	private static Queue<TargetBlock> blocks = null;	/* this stack will be used to keep track of placed blocks */
	
	/************************************ Class Methods *************************************/
	public TargetingSystem()
	{
		blocks = new LinkedList<TargetBlock>();
	} /* end TargetingSystem constructor */
	
	
	public void chooseTarget(ChooseTargetMessage ctm)
	{
		/* chooseTarget should be called by an Ability to alert TargetingSystem that
	 	* a target is needed. It is here where TargetingSystem will interpret the 
	 	* ChooseTargetMessage and place the TargetBlocks onto the map accordingly 
	 	*/
		
		caller = ctm.getCaller(); /* set the Caller */
		selection = ctm.getTargetSelection();
		TargetBlock.setTargetCount(ctm.getTargetCount());
		TargetingSystem.selection = ctm.getTargetSelection();
		TargetingSystem.shape = ctm.getTargetShape();
		placeTargetBlocks(ctm);
		
	} /* end chooseTarget method */
	
	public static void targetSelected()
	{
		/* TargetSelected should be called by an Ability or a TargetBlock (depending
		 * on the situation). This method will provide the caller with a list of 
		 * targets found 
		 */
		ArrayList<DDCharacter> blockTargets = new ArrayList<DDCharacter>();
		Coordinate movePosition = null;
		TargetBlock block;
		while (blocks.peek() == null)
		{
			/* Get all Character targets that are not null */
			if ((block = blocks.remove()).getTarget() != null) blockTargets.add(block.getTarget());
			movePosition = block.getPosition(); /* This should work because there is only one target in a move */
			block.cleanUp();
		} /* end while loop */
		
		DDCharacter[] targets = null;
		if (blockTargets.size() > 0) targets = blockTargets.toArray(targets);
		
		TargetSelectedMessage tsm = new TargetSelectedMessage(targets, movePosition);
		caller.obtainTarget(tsm); /* here, we tell the ability the target */
		
	} /* end targetSelected method */
	
	private void placeTargetBlocks(ChooseTargetMessage ctm)
	{
		/*
		 * placeTargetBlocks will take int the TargetShape and decide
		 * which method to use when placing the targetBlocks 
		 */
		switch(ctm.getTargetShape())
		{
			case CIRCLE:
				placeCircle(ctm.getOrigin(), ctm.getLength(), ctm.getNumOfTargets());
				break;
				
			case CONE:
				placeCone(ctm.getOrigin(), ctm.getLength(), ctm.getNumOfTargets());
				break;
				
			case MOVE:
				placeMove(ctm.getOrigin(), ctm.getLength(), ctm.getNumOfTargets());
				break;
		
		} /* end switch case */
		
	} /* end placeTargetBlocks method */
	
	private void placeCircle(Coordinate origin, int radius, Integer targetCount)
	{
		/* Place a circular shaped area of TargetBlocks centered at the origin.
		 * Exclude any blocks with walls.
		 */
		
	} /* end placeCircle method */
	
	public void placeCone(Coordinate origin, int length, Integer targetCount)
	{
		/* Place a cone shaped area of TargetBlocks centered at he origin.
		 * Exclude blocks with walls. 
		 * This method is public because the dynamic nature of a cone type
		 * selection requires for the player to be able to change the target.
		 */
	} /* end placeCone method */
	
	private void placeMove(Coordinate origin, int speed, Integer targetCount)
	{
		/* Similar to placing a circle. However, the radius of the circle is 5 feet
		 * and the block should differ slightly and return a coordinate as opposed 
		 * a character. */
	} /* end placeMove method */
	
	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
	public static void setMap(Map map)
	{
		TargetingSystem.map = map;
	} /* end setMap method */
	
} /* end TargetingSystem class */
