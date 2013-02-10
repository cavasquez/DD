package DD.Network;

/*****************************************************************************************************
 * MessageInterpreter will be used by ServerSystem and ClientSystem to interpret every message. 
 * CombatInterpreter will have two methods: validate and interpret. 
 * 
 * The interpreter should have an equivalent ability for which it is interpreting. The interpreter will
 * be named I_AbilityName so as to provide an easy and obvious scheme.
 ******************************************************************************************************/

public interface NetworkInterpreter 
{
	/************************************ Class Methods *************************************/
	public void interpret(NetworkMessage message);

} /* end MessageInterpreter class */
