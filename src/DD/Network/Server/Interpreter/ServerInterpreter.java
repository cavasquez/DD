package DD.Network.Server.Interpreter;

import DD.Network.NetworkMessage;

/*****************************************************************************************************
 * ServerInterpreter will be used by ServerSystem to interpret every message. 
 * 
 * The interpreter should have an equivalent ability for which it is interpreting. The interpreter will
 * be named I_AbilityName so as to provide an easy and obvious scheme.
 ******************************************************************************************************/

public interface ServerInterpreter 
{
	/************************************ Class Methods *************************************/
	public void interpret(int serverID, NetworkMessage message);

} /* end MessageInterpreter class */
