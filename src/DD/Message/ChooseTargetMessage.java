package DD.Message;

import DD.ActionBox.CombatSystem.TargetingSystem.Coordinate;
import DD.ActionBox.CombatSystem.TargetingSystem.TargetingSystem;


/*****************************************************************************************************
 * ChooseTargetMessage will be used by an Ability to tell the TargetingSystem that a target(s) needs to
 * be found. The message will instruct the TargetingSystem on the specifics of the target(s) required.
 ******************************************************************************************************/

public class ChooseTargetMessage extends Message
{
	/************************************ Class Constants *************************************/

	
	/************************************ Class Attributes *************************************/
	private TargetingSystem.TargetCount targetCount = null;
	private TargetingSystem.TargetShape targetShape = null;
	private TargetingSystem.TargetSelection targetSelection = null;
	
	private int numOfTargets;			/* This is used when there are multiple targets */
	private Coordinate origin;			/* Point of origin for the Target */
	private boolean self;				/* Refers to whether or not the ability allows the character to target himself */
	
	
	/************************************ Class Methods *************************************/
	public ChooseTargetMessage
	(
		TargetingSystem.TargetCount targetCount,
		TargetingSystem.TargetShape targetShape,
		TargetingSystem.TargetSelection targetSelection,
		boolean self,
		Coordinate origin
	) 
	{
		super(Message.CHOOSE_TARGET_MESSAGE);
		this.targetCount = targetCount;
		this.targetShape = targetShape;
		this.targetSelection = targetSelection;
		this.self = self;
		this.origin = origin;
		
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
	
	public int getNumOfTargets()
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
	
	/****************************************************************************************
	 ************************************ Setter Methods ************************************
	 ****************************************************************************************/
	public void setNumOfTargets(int numOfTargets)
	{
		this.numOfTargets = numOfTargets;
	} /* end setNumOfTargets method */

} /* end ChooseTargetMessage*/
