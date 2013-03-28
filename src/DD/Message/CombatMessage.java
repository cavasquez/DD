package DD.Message;

import DD.Character.CharacterSheet.CharacterSheet;
import DD.CombatSystem.CombatSystem;

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
	private static final long serialVersionUID = -5536130004546109256L;
	/************************************ Class Attributes *************************************/
	private Integer source;					/* The source of the request */
	private Integer target[];				/* The target, if any, of the request */
	private CombatSystem.ActionType action;	/* Type of action being performed */
	private CombatSystem.Action request;	/* The requested action, ability, or spell to be performed */
	private CharacterSheet characterData;	/* Character Data explicitly used by GMTools */
	private Integer[] body;					/* An array that will contain well defined data */
	
	/************************************ Class Methods *************************************/
	public CombatMessage
	(
			Integer source,		/* This should be the ID of the source DDCharacter */
			Integer target[], 	/* This should be the ID of the target DDCharacter */
			CombatSystem.ActionType action,
			CombatSystem.Action request,
			Integer[] body
	)
	{
		super(Message.COMBAT_MESSAGE);
		this.source = source;
		this.target = target;
		this.action = action;
		this.request = request;
		this.body = body;
		this.characterData = null;
	} /* end Constructor */
	
	public CombatMessage
	(
			Integer source,		/* This should be the ID of the source DDCharacter */
			Integer target[], 	/* This should be the ID of the target DDCharacter */
			CombatSystem.ActionType action,
			CombatSystem.Action request,
			CharacterSheet characterData,
			Integer[] body
	)
	{
		this(source, target, action, request, body);
		this.characterData = characterData;
	} /* end Constructor */

	/****************************************************************************************
	 ************************************ Getter Methods ************************************
	 ****************************************************************************************/
	public Integer getSource() 
	{
		return source;
	} /* end getSource method */
	
	public Integer[] getTarget() 
	{
		return target;
	} /* end getTarget method */
	
	public CombatSystem.ActionType getAction() 
	{
		return action;
	} /* end getAction method */

	public CombatSystem.Action getRequest() 
	{
		return request;
	} /* end getRequest method */
	
	public CharacterSheet getCharacterData()
	{
		return characterData;
	} /* end getCharacterData method */

	public Integer[] getBody() 
	{
		return body;
	} /* end getBody method */
	
}/* end CombatMessage class */
