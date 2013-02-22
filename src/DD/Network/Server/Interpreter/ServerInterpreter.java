package DD.Network.Server.Interpreter;

import DD.Message.NetworkMessage;
import DD.Network.Server.ServerSystem;

/*****************************************************************************************************
 * ServerInterpreter will be used by ServerSystem to interpret every NetworkMessage. 
 * 
 * The interpreter will be named I_MessageName so as to provide an easy and obvious scheme.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public abstract class ServerInterpreter 
{
	/************************************ Class Attributes *************************************/
	protected static ServerSystem system;
	
	/************************************ Class Methods *************************************/
	public void interpret(int listenerID, NetworkMessage message) {}
	public void setServerSystem(ServerSystem system) {}

} /* end ServerInterpreter class */
