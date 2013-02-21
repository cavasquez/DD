package DD.ActionBox.CombatSystem.TargetingSystem;

import org.newdawn.slick.Image;

import DD.MapTool.Map;
import DD.MapTool.Objects;

/*****************************************************************************************************
 * TargetBlock will be a special block on the map reserved for use by the TargetSystem to find Targets
 * on the map. 
 * 
 * When clicked, TargetBlock should change color indicating it has been clicked and update its state
 * to reflect that it has been selected.
 * 
 * Upon placement, the TargetBlock should check for any Character's on the block and get their reference.
 ******************************************************************************************************/

public class TargetBlock extends Objects
{
	/************************************ Class Attributes *************************************/
	private Coordinate position = null;
	private static final String imagePath = "";
	private Character target = null;
	private boolean selected;
	
	/************************************ Class Methods *************************************/
	public TargetBlock(Map owner)
	{
		super("Target Block", new Image(imagePath), owner);
		super.movePenalty = 0;
		super.lightPenalty = 0;
		selected = false; 			/* start off as not selected */
	} /* end TargetBlock constructor */
	
	public void setTargetBlock(Coordinate coordinate)
	{
		this.position = coordinate;
		
		//TODO: TargetBlock will need to check the objects at this position for a Character.
		// If a character exists, store it as a target
	} /* end setTargetBlock method */
	
	public Character pickUp()
	{/* Pickup will return the character and essentially "clean up" */
		return(target);
	} /* end pickUp method */
	
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
	
	public Character getTarget()
	{
		return target;
	} /* end getTarget method */
	
} /* end TargetBlock class */
