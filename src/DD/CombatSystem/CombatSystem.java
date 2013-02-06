package DD.CombatSystem;

import java.util.ArrayList;

import com.sun.xml.internal.stream.Entity;

import DD.CombatSystem.Interpreter.CombatInterpreter;
import DD.CombatSystem.Interpreter.Standard.I_StandardAttack;
import DD.MapTool.Map;
import DD.Network.Message;

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
 ******************************************************************************************************/

public class CombatSystem 
{
	/************************************ Class Constants *************************************/
	private static final int NUM_OF_INTERPRETERS = 1;
	
	public static final int STANDARD_ATTACK = 0;
	
	/************************************ Class Attributes *************************************/
	static private ArrayList<Character> characterList;	/* A list of all the Characters in game so they may be modified */
	static private Map map;								/* The game map that may need to be modified. */
	static private CombatInterpreter[] system;			/* The core of CombatSystem */					
	
	/************************************ Class Methods *************************************/
	public CombatSystem()
	{
		system = new CombatInterpreter[NUM_OF_INTERPRETERS];
		
		/* First, we need to create the system */
		system[STANDARD_ATTACK] = new I_StandardAttack();
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
			 * before interpreting so we can somehwat parallelize
			 * interpret across the network (instead of waiting for
			 * interpret to happen and then sending the message,
			 * send the message and have most games process interpret
			 * at roughly the same time.*/
			this.interpret(cm);
			
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
		if(cm.getMessageType() != Message.COMBAT_VALIDATION_MESSAGE)
		{/* The message is invalid */
			returner = new CombatValidationMessage(false, "Message is not a CombatValidationMessage.");
		} /* end if */
		else if ( cm.getRequest() < 0 || cm.getRequest() > NUM_OF_INTERPRETERS)
		{ /* The request does not exist and thus is invalid. */
			returner = new CombatValidationMessage(false, "Request does not exist");
		} /* end else */
		else
		{
			returner = system[cm.getRequest()].validate(cm);
		} /* end else */
		
		return (returner);
	} /* end the interpreter method */
	
	private static void interpret(CombatMessage cm)
	{
		system[cm.getRequest()].interpret(cm);
	} /* end interpret method */
	
	public boolean characterExists(int characterID)
	{/* check to see if a character with the provided ID exists */
		boolean found = false;
		int index = 0;
		int size = characterList.size();
		while (!found && (index < size))
		{
			if ( ((characterList.get(index++)).getId() == characterID ) )
			{
				found = true;
			} /* end if */
		} /* end while loop */
		
		return(found);
	} /* end characterExists method */
	
	/****************************************************************************************
	 ************************************ Getter Methods ************************************
	 ****************************************************************************************/
	public Character getCharacter(int characterID)
	{/* return character with provided characterID */
		Character returner = null;
		int index = 0;
		while (returner == null)
		{
			if (characterList.get(index++).getId() == characterID )
			{
				returner = characterList.get(--index);
			} /* end if */
			
		} /* end while loop */
		
		return (returner);
	} /* end getCharacter method */
	
} /* end CombatSystem class */