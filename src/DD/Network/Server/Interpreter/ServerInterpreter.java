package DD.Network.Server.Interpreter;

import DD.Network.Message.NetworkMessage;
import DD.Network.Server.ServerSystem;

/*****************************************************************************************************
 * ServerInterpreter will be used by ServerSystem to interpret every message. 
 * 
 * The interpreter should have an equivalent ability for which it is interpreting. The interpreter will
 * be named I_AbilityName so as to provide an easy and obvious scheme.
 ******************************************************************************************************/

public abstract class ServerInterpreter 
{
	/************************************ Class Attributes *************************************/
	protected ServerSystem system;
	
	/************************************ Class Methods *************************************/
	public void interpret(int listenerID, NetworkMessage message) {}
	public void setServerSystem(ServerSystem system) {}

} /* end MessageInterpreter class */
