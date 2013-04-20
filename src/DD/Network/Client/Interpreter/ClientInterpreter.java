package DD.Network.Client.Interpreter;

import DD.Message.NetworkMessage;
import DD.Network.NetworkInterpreter;
import DD.Network.Client.ClientSystem;

/*****************************************************************************************************
 * ClientInterpreter will be used by ClientSystem to interpret every message. 
 * 
 * The interpreter should have an equivalent ability for which it is interpreting. The interpreter will
 * be named I_MessageName so as to provide an easy and obvious scheme to follow.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public abstract class ClientInterpreter extends NetworkInterpreter
{
	/************************************ Class Attributes *************************************/
	protected static ClientSystem system;
	
	/************************************ Class Methods *************************************/
	public abstract void interpret(NetworkMessage message);
	
	public static void setClientSystem(ClientSystem system)
	{
		ClientInterpreter.system = system;
	} /* end setClientSystem method */

} /* end ClientInterpreter class */
