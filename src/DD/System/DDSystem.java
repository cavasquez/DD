package DD.System;

import DD.ActionBox.ActionBox;
import DD.Character.Abilities.Ability;
import DD.Chat.ChatBox;
import DD.Chat.ChatSystem;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.Interpreter.CombatInterpreter;
import DD.CombatSystem.TargetingSystem.TargetingSystem;
import DD.Network.NetworkSystem;

/*****************************************************************************************************
 * System will be used as a class that contains every other System in the game (ie CombatSystem, ChatSystem,
 * etc.) and will be subclassed by them so they can access each other.
 * 
 * It will also allow for ease of use when constructing the game by having all the systems created and
 * managed in one place.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class DDSystem 
{
	/************************************ Class Constants *************************************/
	
	/************************************ Class Attributes *************************************/
	public static final CombatSystem cs = new CombatSystem();
	public static final TargetingSystem ts = new TargetingSystem();
	public static final NetworkSystem ns = new NetworkSystem();
	public static final ChatSystem chat = new ChatSystem();
	
	private static boolean initialized = false;	/* flag used to check if system has been initialized yet  */
	
	/************************************ Class Methods *************************************/
	public DDSystem()
	{
		/* Check to see if any of the necessary parts have had their systems set. 
		 * If not, set them */
		if (!initialized)
		{
			initialized = true;
			
			/* Provide the CombatInterpreter the necessary systems */
			CombatInterpreter.setCombatSystem(cs);
			CombatInterpreter.setTargetingSystem(ts);
			
			/* Provide the CombatInterpreter the necessary systems */
			Ability.setCombatSystem(cs);
			Ability.setTargetingSystem(ts);
		} /* end if */
	} /* end System constructor */
	
	public void linkBoxes(ActionBox ab, ChatBox cb)
	{
		/* linkBoxes will pass on the Games Boxes to the appropriate objects. */
		CombatInterpreter.setActionBox(ab);
	} /* end linkBoxes method */
	
	/****************************************************************************************
	 ************************************ Getter Methods ************************************
	 ****************************************************************************************/
	
	
	/****************************************************************************************
	 ************************************ Setter Methods ************************************
	 ****************************************************************************************/
} /* end System class */
