package DD.Message;

import DD.ActionBox.CombatSystem.TargetingSystem.Coordinate;
import DD.Character.DDCharacter;

/*****************************************************************************************************
 * TargetSelectedMessage will be used by the TargetSystem to tell an Ability that its target(s) have
 * been selected and provide it with the list of targets.
 ******************************************************************************************************/

public class TargetSelectedMessage extends Message
{
	/************************************ Class Attributes *************************************/
	private DDCharacter[] targets;
	private Coordinate position;
	
	/************************************ Class Methods *************************************/
	public TargetSelectedMessage(DDCharacter[] targets, Coordinate position)
	{
		super(Message.TARGET_SELECTED_MESSAGE);
		this.targets = targets;
		this.position = position;
	} /* end TargetSelectedMessage constructor */
	
	/****************************************************************************************
	 ************************************ Getter Methods ************************************
	 ****************************************************************************************/
	public DDCharacter[] getTargets()
	{
		return targets;
	} /* end getTargets method */
	
	public Coordinate getPosition()
	{
		return position;
	} /* end getPosition method */
	
} /* end TargetSelectedMessage class */