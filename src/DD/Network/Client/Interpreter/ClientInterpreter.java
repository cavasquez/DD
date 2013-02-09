package DD.Network.Client.Interpreter;

import DD.Network.Client.ClientSystem;
import DD.Network.Message.NetworkMessage;

/*****************************************************************************************************
 * ServerInterpreter will be used by ClientSystem to interpret every message. 
 * 
 * The interpreter should have an equivalent ability for which it is interpreting. The interpreter will
 * be named I_MessageName so as to provide an easy and obvious scheme to follow.
 ******************************************************************************************************/

public abstract class ClientInterpreter 
{
	/************************************ Class Attributes *************************************/
	protected static ClientSystem system;
	
	/************************************ Class Methods *************************************/
	public void interpret(NetworkMessage message) {}
	public void setClientSystem(ClientSystem system) {}

} /* end ClientInterpreter class */
