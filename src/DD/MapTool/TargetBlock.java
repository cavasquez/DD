package DD.MapTool;

import java.util.Iterator;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import DD.Character.DDCharacter;
import DD.CombatSystem.TargetingSystem.Coordinate;
import DD.CombatSystem.TargetingSystem.TargetingSystem;

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
	private static TargetingSystem system = null;
	private static TargetingSystem.TargetCount targetCount = null;	/* Used by targetBlock to check if it will call TargetingSystems targetSelected method */
	private static TargetingSystem.TargetSelection targetSelection = null;
	private static Integer numOfTargets = null;
	private ObjectsPriorityStack stack;
	private Image spriteSheet = null;
	
	
	/************************************ Class Methods *************************************/
	public TargetBlock(String name, Image image, Map owner)
	{
		super(name, image, owner);
		movePenalty = 0;
		lightPenalty = 0;
		this.target = null;
		selected = false; 		/* start off as not selected */
		//priority = 9;			/* set to highest? priority */
		priority = 11;
		
		if (system == null) system = new TargetingSystem();
	} /* end TargetBlock constructor */
	
	public TargetBlock(Map owner) throws SlickException
	{
		super("|---|", null, owner); /* TODO: remove this after testing */
		//super("Target Block", new Image(imagePath), owner);
		spriteSheet = new Image("Images/Test/DungeonCrawl_ProjectUtumnoTileset.png");
		Image targetBlock = spriteSheet.getSubImage(1473, 513, 33, 34);
		super.setImage(targetBlock);
		movePenalty = 0;
		lightPenalty = 0;
		this.target = null;
		selected = false; 		/* start off as not selected */
		//priority = 9;			/* set to highest? priority */
		priority = 11;
		
		if (system == null) system = new TargetingSystem();
		
	} /* end TargetBlock constructor */
	
	public void setTargetBlock(Coordinate coordinate, ObjectsPriorityStack stack) throws SlickException
	{
		this.position = coordinate;
		this.stack = stack;
		
		//TODO: TargetBlock will need to check the objects at this position for a Character.
		// If a character exists, store it as a target.
		if (targetCount == TargetingSystem.TargetCount.ALL) this.select(); /* We want to select this as a target */
		target = system.getCharacter(stack); /* get the target */
		stack.push(this);
		stack.setHasTargetBlock(true);
		
	} /* end setTargetBlock method */
	
	public void cleanUp()
	{/* cleanUp will remove the block from the map */
		stack.remove(this);
		stack.setHasTargetBlock(false);
	} /* end pickUp method */
	
	public void select() throws SlickException
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
	} /* end setNumOfTargets method */
	
	public static void setTargetSelection(TargetingSystem.TargetSelection targetSelection)
	{
		TargetBlock.targetSelection = targetSelection;
	} /* end setTargetSelection method */
	
} /* end TargetBlock class */
