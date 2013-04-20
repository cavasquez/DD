package DD.System;

import DD.ActionBox.ActionBox;
import DD.Character.Abilities.Ability;
import DD.Chat.ChatBox;
import DD.Chat.ChatSystem;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.Interpreter.CombatInterpreter;
import DD.CombatSystem.TargetingSystem.TargetingSystem;
import DD.MapTool.Map;
import DD.Network.MessageQueue;
import DD.Network.Network;
import DD.Network.NetworkInterpreter;
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
	/************************************ Class Attributes *************************************/
	public final CombatSystem cs;
	public final TargetingSystem ts;
	public final NetworkSystem ns;
	public final ChatSystem chat;
	
	/************************************ Class Methods *************************************/
	public DDSystem()
	{	
		cs = new CombatSystem();
		ts = new TargetingSystem();
		ns = new NetworkSystem();
		chat = new ChatSystem();
		/* Provide the CombatSystem the necessary systems */
		cs.setNetworkSystem(ns);
		
		/* Provide the CombatInterpreter the necessary systems */
		CombatInterpreter.setCombatSystem(cs);
		CombatInterpreter.setTargetingSystem(ts);
		
		/* Provide the Abilities the necessary systems */
		Ability.setCombatSystem(cs);
		Ability.setTargetingSystem(ts);
		Ability.setNetworkSystem(ns);
		
		/* Provide the NetworkSystem the necessary items */
		MessageQueue.getInstance().setNetworkSystem(ns);
		NetworkInterpreter.setCombatSystem(cs);
		NetworkInterpreter.setChatSystem(chat);
		
	} /* end System constructor */
	
	public void linkBoxes(ActionBox ab, ChatBox cb)
	{
		/* linkBoxes will pass on the Games Boxes to the appropriate objects. */
		linkBoxes(ab);
		linkBoxes(cb);
		
	} /* end linkBoxes method */
	
	public void linkBoxes(ActionBox ab)
	{
		/* linkBoxes will pass on the Games Boxes to the appropriate objects. */
		CombatInterpreter.setActionBox(ab);
	} /* end linkBoxes method */
	
	public void linkBoxes(ChatBox cb)
	{
		/* linkBoxes will pass on the Games Boxes to the appropriate objects. */
		
	} /* end linkBoxes method */
	
	public void client()
	{
		ns.setNetworkType(NetworkSystem.NetworkType.CLIENT);
	} /* end client() */
	
	public void server()
	{
		ns.setNetworkType(NetworkSystem.NetworkType.SERVER);
		System.out.println("DDSystem" + ns.getNetwork() + " " + Ability.getNetworkSystem().getNetwork());
	} /* end client() */
	
	public boolean isGM()
	{
		boolean returner = false;
		if(Network.GM_USER_ID == ns.getNetID()) returner = true;
		return returner;
	} /* end isGM method */
	
	/****************************************************************************************
	 ************************************ Getter Methods ************************************
	 ****************************************************************************************/
	public Map getMap()
	{
		return cs.getMap();
	} /* end getMap method */
	
	/****************************************************************************************
	 ************************************ Setter Methods ************************************
	 ****************************************************************************************/
	public void setMap(Map map)
	{
		cs.setMap(map);
		ts.setMap(map);
	} /* end setMap method */
	
} /* end System class */
