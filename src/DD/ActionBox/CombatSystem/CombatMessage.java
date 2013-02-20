package DD.ActionBox.CombatSystem;

import DD.Network.Message;

/*****************************************************************************************************
 * CombatMessage will provide a way for the View to communicate with CombatSystem. While CombatSystem
 * itself will be an interface for any combat related actions, it will all be done through CombatMessage.
 * CombatMessage will be passed to the CombatSystem's interpret method and handled accordingly.
 * 
 * One of the biggest reasons for having a CombatMessage are to send them through the network so they 
 * can update Character information on all client and server machines.
 ******************************************************************************************************/

public class CombatMessage extends Message
{
	/************************************ Class Attributes *************************************/
	
	/************************************ Class Methods *************************************/
	public CombatMessage()
	{
		this.messageType = Message.COMBAT_MESSAGE;
	} /* end Constructor */
}/* end CombatMessage class */
