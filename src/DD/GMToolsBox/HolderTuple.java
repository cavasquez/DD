package DD.GMToolsBox;

import DD.Character.CharacterSheet.CharacterSheet;

/*****************************************************************************************************
 * HolderTuple will simply hold the CharacterSheet and corresponding component ID for the PlaceCharacter
 * component that is holding the CharacterSheet
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class HolderTuple 
{
	/************************************ Class Attributes *************************************/
	public final CharacterSheet sheet;
	public final int id;
	
	/************************************ Class Methods *************************************/
	public HolderTuple(CharacterSheet sheet, int id)
	{
		this.sheet = sheet;
		this.id = id;
	} /* end HolderTuple constructor */
	
} /* end HolderTuple method */
