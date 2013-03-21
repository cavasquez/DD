package DD.System;

import DD.Chat.ChatSystem;
import DD.CombatSystem.CombatSystem;
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
	
	/************************************ Class Methods *************************************/
	public DDSystem()
	{

	} /* end System constructor */
	
	/****************************************************************************************
	 ************************************ Getter Methods ************************************
	 ****************************************************************************************/
	
	
	/****************************************************************************************
	 ************************************ Setter Methods ************************************
	 ****************************************************************************************/
} /* end System class */
