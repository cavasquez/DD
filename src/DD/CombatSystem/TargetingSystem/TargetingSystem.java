package DD.CombatSystem.TargetingSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import org.newdawn.slick.SlickException;
import DD.Character.DDCharacter;
import DD.Character.Abilities.TargetAbility;
import DD.MapTool.CharacterObjects;
import DD.MapTool.Map;
import DD.MapTool.Objects;
import DD.MapTool.ObjectsPriorityStack;
import DD.MapTool.TargetBlock;
import DD.MapTool.Wall;
import DD.Message.ChooseTargetMessage;
import DD.Message.TargetSelectedMessage;
import DD.System.DDSystem;

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
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class TargetingSystem extends DDSystem
{
	/************************************ Class Constants*************************************/
	public static final int blockSize = 5; /* distance of block (5 feet) */
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
	public static Map map = null;						/* Map on which we are placing TargetBlocks */
	private static TargetAbility caller = null;				/* keeps track of Ability that called TargetSystem */
	private static TargetSelection selection = null;	/* keeps track of type of TargetSelection so targetSelected know which Characters to return */
	private static Queue<TargetBlock> blocks = null;	/* this stack will be used to keep track of placed blocks */
	private static boolean self;						/* determine whether or not target can choose self */
	private static Coordinate origin = null;			/* keep track of the origins position */
	
	/************************************ Class Methods *************************************/
	public TargetingSystem()
	{
		blocks = new LinkedList<TargetBlock>();
	} /* end TargetingSystem constructor */
	
	
	public void chooseTarget(ChooseTargetMessage ctm) throws SlickException
	{
		/* chooseTarget should be called by an Ability to alert TargetingSystem that
	 	* a target is needed. It is here where TargetingSystem will interpret the 
	 	* ChooseTargetMessage and place the TargetBlocks onto the map accordingly 
	 	*/
		
		caller = ctm.getCaller(); /* set the Caller */
		TargetingSystem.selection = ctm.getTargetSelection();
		TargetingSystem.self = ctm.getSelf();
		TargetingSystem.origin = ctm.getOrigin();
		placeTargetBlocks(ctm);
		
	} /* end chooseTarget method */
	
	public void clearTargets()
	{
		/* Get rid of target blocks */
		TargetBlock block;
		while (blocks.peek() != null)
		{
			block = blocks.remove();
			block.cleanUp();
		} /* end while loop */
	} /* end clearTargets method */
	
	public static void targetSelected() throws SlickException
	{
		/* TargetSelected should be called by an Ability or a TargetBlock (depending
		 * on the situation). This method will provide the caller with a list of 
		 * targets found 
		 */
		ArrayList<DDCharacter> blockTargets = new ArrayList<DDCharacter>();
		Coordinate movePosition = null;
		TargetBlock block;
		
		while (blocks.peek() != null)
		{
			block = blocks.remove();
			/* Get all Character targets that are not null */
			if ( 
					((selection == TargetSelection.SELECTED && block.getSelected() == true)) || ((selection == TargetSelection.UNSELECTED && block.getSelected() == false))) 
			{
				if (block.getTarget() != null) blockTargets.add(block.getTarget());
				movePosition = block.getPosition(); /* This should work because there is only one target in a move */
			} /* end if */
			block.cleanUp();
		} /* end while loop */
		
		DDCharacter[] targets = null;
		if (blockTargets.size() > 0) targets = blockTargets.toArray(new DDCharacter[blockTargets.size()]);
		
		TargetSelectedMessage tsm = new TargetSelectedMessage(targets, movePosition);
		caller.obtainTarget(tsm); /* here, we tell the ability the target */
		
	} /* end targetSelected method */
	
	private void placeTargetBlocks(ChooseTargetMessage ctm) throws SlickException
	{
		/*
		 * placeTargetBlocks will take int the TargetShape and decide
		 * which method to use when placing the targetBlocks 
		 */
		switch(ctm.getTargetCount())
		{
			case SINGLE:
				TargetBlock.setNumOfTargets(1);
				TargetBlock.setTargetCount(TargetCount.SINGLE);
				break;
				
			case MULTIPLE:
				TargetBlock.setNumOfTargets(ctm.getNumOfTargets());
				TargetBlock.setTargetCount(TargetCount.MULTIPLE);
				break;
				
			case ALL:
				TargetBlock.setNumOfTargets(null);
				TargetBlock.setTargetCount(TargetCount.ALL);
				break;
		} /* end switch case */
		
		/* This should be performed last because it begins the actual placement */
		switch(ctm.getTargetShape())
		{
			case CIRCLE:
				placeCircle(ctm.getOrigin(), ctm.getLength());
				break;
				
			case CONE:
				placeCone(ctm.getOrigin(), ctm.getLength());
				break;
				
			case MOVE:
				placeMove(ctm.getOrigin(), ctm.getLength());
				break;
		
		} /* end switch case */
		
	} /* end placeTargetBlocks method */
	
	/******************************************************************************
	 ************************ PlaceCircle Related Methods ************************
	 ******************************************************************************/
	
	private void placeCircle(Coordinate origin, int distance) throws SlickException
	{
		/* Place a circular shaped area of TargetBlocks centered at the origin.
		 * Exclude any blocks with walls, outside of the boundary, or set at the origin
		 * (unless the character is allowed to select self)
		 */
		Coordinate position = null;
		boolean originDealtWith = false;
		Queue<CircleStackElement> stack = new LinkedList<CircleStackElement>();
		stack.add(new CircleStackElement(origin, distance));
		
		boolean placeBlock = false;
		boolean lookForMoreBlocks = false;
		int diagonalPenalty = 2;		/* We double the speed when a diagonal is taken after it the first diagonal is taken */
		
		Coordinate top;
		Coordinate bottom;
		Coordinate left;
		Coordinate right;
		
		Coordinate topLeft;
		Coordinate bottomLeft;
		Coordinate topRight;
		Coordinate bottomRight;
		
		while(stack.peek() != null)
		{
			CircleStackElement next = stack.poll();
			position = next.getPosition();
			distance = next.getDistance();
			
			placeBlock = false;
			lookForMoreBlocks = false;
			diagonalPenalty = 2;		/* We double the speed when a diagonal is taken after it the first diagonal is taken */
			
			top = new Coordinate(position.x, position.y + 1);
			bottom = new Coordinate(position.x, position.y - 1);
			left = new Coordinate(position.x - 1, position.y);
			right = new Coordinate(position.x + 1, position.y);
			
			topLeft = new Coordinate(position.x - 1, position.y + 1);
			bottomLeft = new Coordinate(position.x - 1, position.y - 1);
			topRight = new Coordinate(position.x + 1, position.y + 1);
			bottomRight = new Coordinate(position.x + 1, position.y - 1);
			
			/* First, make sure we are in bounds. Thus must happen first so we do not get array outofbounds errors*/
			if (positionExists(position))
			{
				/* Second, check to see if a TargetBlock exists at the position. If it does not,
				 * then place it there and continue. If one does exist, then stop and do not
				 * place another one. If there is a TargetBlock, it will always be at the top
				 * due to it's priority */
				if (!positionHasTargetBlock(position))
				{
					/* Second, check if we are at the origin and check if it can be selected.
					 * If it can't we do not place a block in the spot. If we do, (or this is  */
					if ( position.x == origin.x && position.y == origin.y ) 
					{
						if (!originDealtWith)
						{
							/* Since the origin has not been dealt with, we should branch 
							 * from the origin to look for more blocks that could be potential targets. */
							lookForMoreBlocks = true;
							diagonalPenalty = 1;		/* We have not had the chance to take a diagonal yet, no penalty */
							originDealtWith = true;
							if (self == true) placeBlock = true; /* since this is a self target, we can place a block at this position */
						} /* end if */
					} /* end if */
					
					/* Lastly, check to see that we are within the radius (0 is still valid) and check to see that
					 * there are no walls */
					else if (distance >= 0	&& !wallExists(position))
					{
						placeBlock = true;
						if (distance > 0 )lookForMoreBlocks = true;
					} /* end else if */
				} /* end if */
			} /* end if */
			
			if (placeBlock)
			{
				placeTargetBlock(position);
			} /* finish placing blocks */
			
			if (lookForMoreBlocks)
			{
				/* Recursion for the win! */
				/* Deal with the sides */
				/* Search for the next block one to the right */
				stack.add(new CircleStackElement(right, distance - blockSize));
				/* Search for the next block one block to the left */
				stack.add(new CircleStackElement(left, distance - blockSize));
				/* Search for the next block one block up */
				stack.add(new CircleStackElement(top, distance - blockSize));
				/* Search for the next block one block down */
				stack.add(new CircleStackElement(bottom, distance - blockSize));
				
				/* Deal with the diagonals */
				/* Search for the next block to the top right*/
				stack.add(new CircleStackElement(topRight, distance - (blockSize * diagonalPenalty)));
				/* Search for the next block to the top left */
				stack.add(new CircleStackElement(topLeft, distance - (blockSize * diagonalPenalty)));
				/* Search for the next block to the bottom right*/
				stack.add(new CircleStackElement(bottomRight, distance - (blockSize * diagonalPenalty)));
				/* Search for the next block to the bottom left */
				stack.add(new CircleStackElement(bottomLeft, distance - (blockSize * diagonalPenalty)));
			} /*  end if*/
			
		} /* end while loop */
		
	} /* end placeCircle method */
	
	/******************************************************************************
	 ************************- PlaceCone Related Methods *************************
	 ******************************************************************************/
	private void placeCone(Coordinate position, Integer targetCount)
	{
		/* Place a cone shaped area of TargetBlocks centered at he origin.
		 * Exclude blocks with walls. 
		 * This method is public because the dynamic nature of a cone type
		 * selection requires for the player to be able to change the target.
		 */
	} /* end placeCone method */
	
	/******************************************************************************
	 ************************* PlaceMove Related Methods *************************
	 ******************************************************************************/
	private void placeMove(Coordinate origin, int speed) throws SlickException
	{
		/* Similar to placing a circle. However, the radius of the circle is 5 feet
		 * and the block should differ slightly and return a coordinate as opposed 
		 * a character. Furthermore, we care about the movePenalty of the Objects */
		System.out.println(origin.x + ", " + origin.y);
		boolean diagonal = getCharacter(origin).getMovedDiagonal();

		Coordinate movePosition = null;
		/* First, place the non-diagonal blocks */
		/* place the targetBlock to the right of the origin */
		if (speed - getMovePenalty(movePosition = new Coordinate(origin.x + 1, origin.y)) >=0) placeTargetBlock(movePosition);
		/* place the targetBlock to the left of the origin */
		if (speed - getMovePenalty(movePosition = new Coordinate(origin.x  - 1, origin.y)) >=0) placeTargetBlock(movePosition);
		/* place the targetBlock to the top of the origin */
		if (speed - getMovePenalty(movePosition = new Coordinate(origin.x, origin.y + 1)) >=0) placeTargetBlock(movePosition);
		/* place the targetBlock to the bottom of the origin */
		if (speed - getMovePenalty(movePosition = new Coordinate(origin.x, origin.y - 1)) >=0) placeTargetBlock(movePosition);
		
		/* Now we place the diagonals */
		/* place the targetBlock to the top right of the origin */
		if (speed - getMovePenalty(movePosition = new Coordinate(origin.x + 1, origin.y + 1), diagonal) >=0) placeTargetBlock(movePosition);
		/* place the targetBlock to the top left of the origin */
		if (speed - getMovePenalty(movePosition = new Coordinate(origin.x - 1, origin.y + 1), diagonal) >=0) placeTargetBlock(movePosition);
		/* place the targetBlock to the bottom right of the origin */
		if (speed - getMovePenalty(movePosition = new Coordinate(origin.x + 1, origin.y - 1), diagonal) >=0) placeTargetBlock(movePosition);
		/* place the targetBlock to the bottom left of the origin */
		if (speed - getMovePenalty(movePosition = new Coordinate(origin.x - 1, origin.y - 1), diagonal) >=0) placeTargetBlock(movePosition);
		
	} /* end placeMove method */
	
	/************************************ Methods used by class *************************************/	
	private void placeTargetBlock(Coordinate position) throws SlickException
	{
		/* Make sure blocks do not go out of bounds */
		if 
		(
			( position.x < map.mapSize && position.x >= 0 ) && 	/* check x bounds */
			( position.y < map.mapSize && position.y >= 0 )		/* check y bounds */
		)
		{
			TargetBlock block = new TargetBlock(map);
			block.setTargetBlock(position, map.objectsStack[position.x][position.y]);
			blocks.add(block);
		} /* end if */
	} /* end placeTargetBlock method */
	
	private boolean wallExists( Coordinate position )
	{/* Check if there is a wall at the position */
		boolean returner = false;
		Iterator<Objects> obj = map.objectsStack[position.x][position.y].getIterator();
		
		while (returner == false && obj.hasNext())
		{
			if(Wall.class.isInstance(obj.next())) returner = true;
		} /* end while loop */
		
		return returner;
	} /* end wallExists method */
	
	private boolean positionExists(Coordinate position)
	{
		boolean returner = false;
		if
		(
			( position.x < map.mapSize && position.x >= 0 ) && 	/* check x bounds */
			( position.y < map.mapSize && position.y >= 0 )		/* check y bounds */
		)
		{
			returner = true;
		}
		return returner;
	} /* end positionExists method */
	
	private boolean positionHasTargetBlock(Coordinate position)
	{
		return map.objectsStack[position.x][position.y].hasTargetBlock();
	} /* end hasTargetBlock method */
	
	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
	public static void setMap(Map map)
	{
		TargetingSystem.map = map;
	} /* end setMap method */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public DDCharacter getCharacter(Coordinate position)
	{
		return (getCharacter(map.objectsStack[position.x][position.y]));
	} /* end getCharacter method */
	
	public DDCharacter getCharacter(ObjectsPriorityStack stack)
	{
		DDCharacter returner = null;
		
		/* Check if a character exists. If one does, add it to target */
		Iterator<Objects> obj = stack.getIterator();
		boolean characterFound = false;
		Objects potentialCharacter = null;
		
		while (characterFound == false && obj.hasNext())
		{
			if(CharacterObjects.class.isInstance(potentialCharacter = obj.next()))
			{
				returner = ((CharacterObjects) potentialCharacter).ddchar; 
				characterFound = true;
			} /* end if */
		} /* end while loop */
		
		return returner;

	} /* end getCharacter method */
	
	public TargetBlock getTargetBlock(ObjectsPriorityStack stack)
	{
		TargetBlock returner = null;
		
		/* Check if a character exists. If one does, add it to target */
		Iterator<Objects> obj = stack.getIterator();
		boolean targetBlockFound = false;
		Objects potentialTargetBlock= null;
		
		while (targetBlockFound == false && obj.hasNext())
		{
			if(TargetBlock.class.isInstance(potentialTargetBlock = obj.next()))
			{
				returner = ((TargetBlock) potentialTargetBlock); 
				targetBlockFound = true;
			} /* end if */
		} /* end while loop */
		
		return returner;

	} /* end getCharacter method */
	
	private int getMovePenalty(Coordinate position, boolean diagonal)
	{
		int returner = getMovePenalty(position);
		if (diagonal) returner = returner*2;
		return returner;
	} /* end getMovePenalty method */
	
	public int getMovePenalty(Coordinate position)
	{
		/* Note that when checking for movement speed, we assume that the top Objects 
		 * in the stack has the most significant movement penalty */
		//System.out.println("");
		return map.objectsStack[position.x][position.y].peek().getMovePenalty();
	} /* end getMovePenalty method */
	
} /* end TargetingSystem class */
