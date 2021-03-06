package DD.CombatSystem;

import java.util.ArrayList;
import DD.CombatSystem.Interpreter.*;
import DD.CombatSystem.Interpreter.Move.I_Move;
import DD.CombatSystem.Interpreter.Standard.*;
import DD.CombatSystem.Interpreter.System.I_AddCharacter;
import DD.CombatSystem.Interpreter.System.I_EndTurn;
import DD.CombatSystem.Interpreter.System.I_PlaceCharacter;
import DD.CombatSystem.Interpreter.System.I_RemoveCharacter;
import DD.CombatSystem.Interpreter.System.I_SetMap;
import DD.CombatSystem.Interpreter.System.I_StartCombatPhase;
import DD.MapTool.Map;
import DD.Message.CombatMessage;
import DD.Message.CombatValidationMessage;
import DD.Message.Message;
import DD.Network.NetworkSystem;
import DD.System.DDSystem;
import DD.Character.*;
import DD.Character.Abilities.Ability;
import DD.GMToolsBox.GMToolsBox;

/*****************************************************************************************************
 * CombatSystem will be the controller for DD combat. It will take messages from ActionBox (the player)
 * and the network. It will then validate these messages (if they originate from the player) and then
 * process the message and modify Character data and/or map data depending on the actions performed
 * by the player.
 * 
 * The message received will be a CombatMessage. The structure of the message will differ depending
 * on the action that needs to be performed. A list of constants will be provided so that the ActionBox
 * will know what messages it can send and what actions it can perform. The actions that are allowed 
 * will be heavily based on feats, abilities, and spells. The GameSystem is essentially where the
 * core of the GameLogic lies.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class CombatSystem
{
	/************************************ Class Constants *************************************/
	private static int I = 0;
	public static enum Action
	{
		STANDARD_ATTACK (I++, "Images/CombatSystem/StandardAttack.png"),
		MOVE(I++, "Images/CombatSystem/Move.png"),
		END_ACTION(I++, "Images/CombatSystem/EndAction.png"),
		START_COMBAT_PHASE(I++, "Images/CombatSystem/StartCombatPhase.png"),
		END_TURN(I++, "Images/CombatSystem/EndTurn.png"),
		PLACE_CHARACTER(I++, "Images/CombatSystem/PlaceCharacter.png"),
		REMOVE_CHARACTER(I++, "Images/CombatSystem/RemoveCharacter.png"),
		DYING(I++, null),
		SET_MAP(I++, null),
		ADD_CHARACTER(I++, null);
		
		public final int index;
		public final String image;
		public static final int NUM_OF_INTERPRETERS = I;
		
		Action (int index, String image)
		{
			this.index = index;
			this.image = image;
		} /* end TargetCount index */
		
	} /* end Action enum */
	
	public static enum ActionType
	{
		FREE,
		FULL_ROUND,
		FULL_ROUND_ENDSTART,
		IMMEDIATE,
		MOVE,
		STANDARD,
		SWIFT,
		SYSTEM;
	} /* end ActionTypes enum */
	
	/************************************ Class Attributes *************************************/
	private ArrayList<DDCharacter> characterList = null;	/* A list of all the Characters in game so they may be modified */
	private Map map;										/* The game map that may need to be modified. */
	private static CombatInterpreter[] system;				/* The core of CombatSystem */
	private int turn;										/* the current turn count */
	private ArrayList<Integer> order;						/* The order of  */
	private NetworkSystem ns;
	private GMToolsBox gmtb;								/* Strictly for server use */								
	
	/************************************ Class Methods *************************************/
	public CombatSystem()
	{	
		characterList = new ArrayList<DDCharacter>();
		order = new ArrayList<Integer>();
		
		/* Create the Interpreters */
		system = new CombatInterpreter[Action.NUM_OF_INTERPRETERS];
		
		/* First, we need to create the system */
		system[Action.STANDARD_ATTACK.index] = new I_StandardAttack();
		system[Action.MOVE.index] = new I_Move();
		system[Action.END_ACTION.index] = new I_EndAction();
		system[Action.START_COMBAT_PHASE.index] = new I_StartCombatPhase();
		system[Action.END_TURN.index] = new I_EndTurn();
		system[Action.PLACE_CHARACTER.index] = new I_PlaceCharacter();
		system[Action.REMOVE_CHARACTER.index] = new I_RemoveCharacter();
		system[Action.SET_MAP.index] = new I_SetMap();
		system[Action.ADD_CHARACTER.index] = new I_AddCharacter();
	} /* end CombatSystem constructor */
	
	public CombatValidationMessage process(CombatMessage cm)
	{ /* This will be the method called by ActionBox */
		CombatValidationMessage returner = null;
		returner = CombatSystem.validate(cm);
		if (returner.getValid()) 
		{
			/* TODO: Send to Server */
			/* We send the message to the network from here because
			 * the receiver should not be calling validated on the
			 * received message. Any sent messages are known to be
			 * valid. This way, it will automatically call interpret
			 * and will not infinitely re-send the same message by
			 * calling validate. Furthermore, we send the message
			 * before interpreting so we can somewhat parallelize
			 * interpret across the network (instead of waiting for
			 * interpret to happen and then sending the message,
			 * send the message and have most games process interpret
			 * at roughly the same time.*/
			CombatSystem.interpret(cm);
			
		} /* end if */
		
		return(returner);
	} /* end CombatValidationMessage method */
	
	private static CombatValidationMessage validate(CombatMessage cm)
	{/* validation will take in a combat message. It will identify the request of the
	 	CombatMessage and validate it. If it is invalid (missing data or invalid data),
	 	it will return an invalid CombatValidationMethod and the error message. If false,
	 	it will process the message and then send it to the network. */
		CombatValidationMessage returner = null;
		
		/* First, we check to make sure the message is of the correct type and the request is valid */
		if(cm.getMessageType() != Message.Type.COMBAT_MESSAGE)
		{/* The message is invalid */
			returner = new CombatValidationMessage(false, "Message is not a CombatMessage.");
		} /* end if */
		else if(cm.getRequest() == null)
		{/* The message is invalid */
			returner = new CombatValidationMessage(false, "Request is null.");
		} /* end if */
		else
		{
			returner = system[cm.getRequest().index].validate(cm);
		} /* end else */
		
		return (returner);
	} /* end the interpreter method */
	
	private static void interpret(CombatMessage cm)
	{
		system[cm.getRequest().index].interpret(cm);
	} /* end interpret method */
	
	public boolean characterExists(int characterID)
	{/* check to see if a character with the provided ID exists */
		boolean found = false;
		int index = 0;
		int size = characterList.size();
		while (!found && (index < size))
		{
			if ( ((characterList.get(index++)).getCharacterID() == characterID ) )
			{
				found = true;
			} /* end if */
		} /* end while loop */
		
		return(found);
	} /* end characterExists method */
	
	public void addCharacter(DDCharacter character)
	{
		characterList.add(character);
	} /* end addCharacter method */
	
	public void removeCharacter(DDCharacter character)
	{
		characterList.remove(character);
	} /* end removeCharacter */
	
	public void removeCharacter(int id)
	{
		removeCharacter(getCharacter(id));
	} /* end removeCharacter */
	
	public void addToOrder(int id, int place)
	{
		/* Add the new character to the provided place in the order */
		/* Check to make sure the id is not placed out of bounds */
		if(place > order.size()) order.add(id);
		else order.add(place, id);
	} /* end addToOrder method */
	
	public void addToOrder(int id)
	{
		/* Place the new character at the "end" of the list */
		order.add(0, id);
	} /* end addToOrder method */
	
	public void removeFromOder(Integer id)
	{
		order.remove(id);
	} /* end removeFromOrder */
	
	/****************************************************************************************
	 ************************************ Getter Methods ************************************
	 ****************************************************************************************/
	public DDCharacter getCharacter(int characterID)
	{/* return character with provided characterID */
		DDCharacter returner = null;
		int index = 0;
		
		while (returner == null)
		{
			if (characterList.get(index++).getCharacterID() == characterID )
			{
				returner = characterList.get(--index);
			} /* end if */
			
		} /* end while loop */
		
		return (returner);
	} /* end getCharacter method */
	
	public Map getMap()
	{
		return map;
	} /* end getMap method */
	
	public DDCharacter[] getCharacterList()
	{
		return characterList.toArray(new DDCharacter[characterList.size()]);
	} /* end getCharacterList method */
	
	public int getTurn()
	{
		return turn;
	} /* end getTurn method */
	
	public ArrayList<Integer> getOrder()
	{
		return order;
	} /* end getOrder method */
	
	public int getNetID()
	{
		/* returns network id */
		return ns.getNetID();
	} /* end getNetID method */
	
	public GMToolsBox getGMToolsBox()
	{
		return gmtb;
	} /* end getGMToolsBox */
	
	/****************************************************************************************
	 ************************************ Setter Methods ************************************
	 ****************************************************************************************/
	public void setMap(Map map)
	{
		this.map = map;
	} /* end setMap method */
	
	public void setTurn(int turn)
	{
		this.turn= turn;
	} /* end setTurn method*/
	
	public void setOrder(ArrayList<Integer> order)
	{
		this.order = order;
	} /* end setOrder method */
	
	public void setNetworkSystem(NetworkSystem ns)
	{
		this.ns = ns;
	} /* end setNetworkSystem method */
	
	public void setGMToolsBox(GMToolsBox gmtb)
	{
		this.gmtb = gmtb;
	} /* end setGMToolsBox */
	
} /* end CombatSystem class */
