package DD.Message;

import DD.CombatSystem.TargetingSystem.Coordinate;
import DD.CombatSystem.TargetingSystem.TargetingSystem;
import DD.Character.Abilities.Ability;

/*****************************************************************************************************
 * ChooseTargetMessage will be used by an Ability to tell the TargetingSystem that a target(s) needs to
 * be found. The message will instruct the TargetingSystem on the specifics of the target(s) required.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class ChooseTargetMessage extends Message
{
	/************************************ Class Attributes *************************************/
	private TargetingSystem.TargetCount targetCount = null;
	private TargetingSystem.TargetShape targetShape = null;
	private TargetingSystem.TargetSelection targetSelection = null;
	private Integer numOfTargets = null;	/* This is used when there are multiple targets */
	private Coordinate origin;				/* Point of origin for the Target */
	private int length;						/* the radius of the circle or length of the cone or speed of the character */
	private boolean self;					/* Refers to whether or not the ability allows the character to target himself */
	private Ability caller;					/* ability calling the TargetingSystem to select a target */
	
	/************************************ Class Methods *************************************/
	public ChooseTargetMessage
	(
		TargetingSystem.TargetCount targetCount,
		TargetingSystem.TargetShape targetShape,
		TargetingSystem.TargetSelection targetSelection,
		boolean self,
		Coordinate origin,
		int length,
		Ability caller
	) 
	{
		super(Message.CHOOSE_TARGET_MESSAGE);
		this.targetCount = targetCount;
		this.targetShape = targetShape;
		this.targetSelection = targetSelection;
		this.self = self;
		this.origin = origin;
		this.length = length;
		this.caller = caller;
		
	} /* end ChooseTargetMessage constructor */

	/****************************************************************************************
	 ************************************ Getter Methods ************************************
	 ****************************************************************************************/
	public TargetingSystem.TargetCount getTargetCount()
	{
		return this.targetCount;
	} /* end getTargetCount method */
	
	public TargetingSystem.TargetShape getTargetShape()
	{
		return this.targetShape;
	} /* end getTargetShape method */
	
	public TargetingSystem.TargetSelection getTargetSelection()
	{
		return this.targetSelection;
	} /* end getTargetShape method */
	
	public Integer getNumOfTargets()
	{
		return numOfTargets;
	} /* end getNumOfTargets method */
	
	public boolean getSelf()
	{
		return self;
	} /* end getSelf method */
	
	public Coordinate getOrigin()
	{
		return origin;
	} /* end getOrigin method */
	
	public int getLength()
	{
		return length;
	} /* end getLength method */
	
	public Ability getCaller()
	{
		return caller;
	} /* end getCaller method */
	
	/****************************************************************************************
	 ************************************ Setter Methods ************************************
	 ****************************************************************************************/
	public void setNumOfTargets(int numOfTargets)
	{
		this.numOfTargets = numOfTargets;
	} /* end setNumOfTargets method */

} /* end ChooseTargetMessage*/
