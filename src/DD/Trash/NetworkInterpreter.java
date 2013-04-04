package DD.Trash;

import DD.Message.NetworkMessage;
import DD.Network.NetworkSystem;

/*****************************************************************************************************
 * NetworkInterpreter will be used by ClientSystem and ServerSystem to interpret every message. 
 * 
 * The interpreter should have an equivalent ability for which it is interpreting. The interpreter will
 * be named I_MessageName so as to provide an easy and obvious scheme to follow.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public abstract class NetworkInterpreter 
{
	/************************************ Class Attributes *************************************/
	protected static NetworkSystem network;
	
	/************************************ Class Methods *************************************/
	public abstract void interpret(NetworkMessage message);
	
	public static void setNetworkSystem(NetworkSystem network)
	{
		NetworkInterpreter.network = network;
	} /* end setServerSystem */
} /* end NetworkInterpreter class */
