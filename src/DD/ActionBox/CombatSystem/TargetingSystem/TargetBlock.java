package DD.ActionBox.CombatSystem.TargetingSystem;

import java.util.Iterator;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import DD.Character.DDCharacter;
import DD.MapTool.CharacterObjects;
import DD.MapTool.Map;
import DD.MapTool.Objects;
import DD.MapTool.ObjectsPriorityStack;
import DD.MapTool.Wall;

/*****************************************************************************************************
 * TargetBlock will be a special block on the map reserved for use by the TargetSystem to find Targets
 * on the map. 
 * 
 * When clicked, TargetBlock should change color indicating it has been clicked and update its state
 * to reflect that it has been selected.
 * 
 * Upon placement, the TargetBlock should check for any Character's on the block and get their reference.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class TargetBlock extends Objects
{
	/************************************ Class Constants *************************************/
	private static final long serialVersionUID = 1L;
	
	/************************************ Class Attributes *************************************/
	private Coordinate position = null;
	private static final String imagePath = "";
	private DDCharacter target = null;
	private boolean selected;
	private static TargetingSystem.TargetCount targetCount = null;	/* Used by targetBlock to check if it will call TargetingSystems targetSelected method */
	private static Integer numOfTargets = null;
	
	/************************************ Class Methods 
	 * @throws SlickException *************************************/
	public TargetBlock(Map owner) throws SlickException
	{
		super("Target Block", new Image(imagePath), owner);
		super.movePenalty = 0;
		super.lightPenalty = 0;
		this.target = null;
		selected = false; 			/* start off as not selected */
	} /* end TargetBlock constructor */
	
	public void setTargetBlock(Coordinate coordinate)
	{
		this.position = coordinate;
		
		/* Look for a Character and set it as target if it exists */
		ObjectsPriorityStack stack = ((Map)(this.owner)).objectsStack[position.x][position.y];
		Iterator<Objects> obj = stack.getIterator();
		boolean found = false;
		Objects tempTarget = null;
		
		while (found == false && obj.hasNext())
		{
			if(CharacterObjects.class.isInstance(tempTarget = obj.next()))
				{/* Character found in the stack, target it */
					target = ((CharacterObjects)(tempTarget)).ddchar;
					found = true;
				} /* end if */
		} /* end while loop */
		
		// If a character exists, store it as a target.
		if (targetCount == TargetingSystem.TargetCount.ALL) this.select(); /* We want to select this as a target */
	} /* end setTargetBlock method */
	
	public void cleanUp()
	{/* cleanUp will remove the block from the map */
		/* The targetBlock should be located on the top of the ObjectStack of the map */
		((Map)(this.owner)).objectsStack[position.x][position.y].pop();
	} /* end pickUp method */
	
	public void select()
	{
		/* This method should be called by update when TargetBlock is clicked */
		selected = true;
		
		switch(targetCount)
		{
			case SINGLE:
				/* There is only one selected target. Thus, it is this one. Tell
				 * TargetingSystem to collect the target. */
				TargetingSystem.targetSelected();
				break;
				
			case MULTIPLE:
				/* There are multiple targets. Decrement the numOfTargets and
				 * if we hit 0, tell TargetingSystem to collect the targets */
				if (--numOfTargets <= 0) TargetingSystem.targetSelected();
				break;
				
			case ALL:
				/* If this is the case, it is up to the player to choose when to get targets.
				 * Thus, we do nothing since the target has already been selected */
				break;
		} /* end switch */
		
	} /* end select method */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public Coordinate getPosition()
	{
		return position;
	} /* end getPosition method */
	
	public boolean getSelected()
	{
		return selected;
	} /* end getSelected method */
	
	public DDCharacter getTarget()
	{
		return target;
	} /* end getTarget method */
	
	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
	public static void setTargetCount(TargetingSystem.TargetCount targetCount)
	{
		TargetBlock.targetCount = targetCount;
	} /* end setTargetCount method */
	
	public static void setNumOfTargets(Integer numOfTargets)
	{
		TargetBlock.numOfTargets = numOfTargets;
	} /* end setTargetCount method */
	
} /* end TargetBlock class */
