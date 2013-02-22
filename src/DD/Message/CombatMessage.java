package DD.Message;


/*****************************************************************************************************
 * CombatMessage will provide a way for the View to communicate with CombatSystem. While CombatSystem
 * itself will be an interface for any combat related actions, it will all be done through CombatMessage.
 * CombatMessage will be passed to the CombatSystem's interpret method and handled accordingly.
 * 
 * One of the biggest reasons for having a CombatMessage are to send them through the network so they 
 * can update Character information on all client and server machines. 
 * 
 * The body will contain data that will be defined by the request. This data will be specified via
 * constants that will be structured as REQUEST_PLACEMENT/DATA_NAME.
 * 
 * Attributes are private because they should not be changed.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class CombatMessage extends Message
{
	/************************************ Class Attributes *************************************/
	private Character source;	/* The source of the request */
	private Character target[];	/* The target, if any, of the request */
	private int request;		/* The requested action, ability, or spell to be performed */
	private int[] body;			/* An array that will contain well defined data */
	
	/************************************ Class Methods *************************************/
	public CombatMessage
	(
			Character source,
			Character target[],
			int request,
			int[] body
	)
	{
		super(Message.COMBAT_MESSAGE);
		this.source = source;
		this.target = target;
		this.request = request;
		this.body = body;
	} /* end Constructor */

	/****************************************************************************************
	 ************************************ Getter Methods ************************************
	 ****************************************************************************************/
	public Character getSource() 
	{
		return source;
	} /* end getSource method */
	
	public Character[] getTarget() 
	{
		return target;
	} /* end getTarget method */

	public int getRequest() 
	{
		return request;
	} /* end getRequest method */

	public int[] getBody() 
	{
		return body;
	} /* end getBody method */
	
}/* end CombatMessage class */
