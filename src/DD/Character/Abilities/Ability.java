package DD.Character.Abilities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import DD.ActionBox.SubAction;
import DD.ActionBox.CombatSystem.TargetingSystem.TargetingSystem;
import DD.Character.*;
import DD.CombatSystem.CombatSystem;
import DD.Message.CombatMessage;
import DD.Message.CombatValidationMessage;
import DD.Message.TargetSelectedMessage;
import DD.SlickTools.RenderComponent;

/*****************************************************************************************************
 * The Ability class will subclass the RenderComponent class. It will hold any possible player
 * abilities. This includes feats, spells, default actions, etc. The Ability class should be subclassed
 * to further specify the type of ability that is being utilized. For example, the movement types of
 * abilities will always interact with the Character whereas the Full-round actions might not. In this
 * case, the Movement subclass should provide an interface that affects the player. All abilities will 
 * have pointers to the Character so that they may obtain/modify character stats (ie, it might be 
 * pertinent to know the players speed, AC, etc.).
 * 
 * Note that there can be no more than one ability clicked at a time. This should be handled in the
 * activate method. The ability is set to a special variable in the Character when active so that the
 * appropriate render may be applied to the Character object. 
 * 
 * The renderActionBox will be used to render the ActionBox related components. It should be called by
 * the SubAction owning the Ability. This way, we can separate Character renders and ActionBox renders.
 * 
 * Abilities will work in Conjunction with CombatSystem and should be treated as the view to the 
 * appropriate Interpreter in CombatSystem. 
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public abstract class Ability extends RenderComponent
{
	/************************************ Class Attributes *************************************/
	protected static DDCharacter character;
	protected SubAction subAction;
	protected final CombatSystem.ActionTypes actionType;
	protected final String name;
	protected final String description;
	protected boolean activated;				/* flag that establishes if an ability has been clicked. */
	protected static boolean done;				/* flag to check if player is done with ability */
	protected static TargetingSystem ts = null;	/* to be used by abilities that need a target */
	protected static CombatSystem cs = null;	/* Used for combat */
	
	/************************************ Class Methods*************************************/
	public Ability(int id, CombatSystem.ActionTypes actionType, String name, String description)
	{
		super(id);
		this.actionType = actionType;
		this.name = name;
		this.description = description;
		this.activated = false;
		if (ts == null) ts = new TargetingSystem();
		if (cs == null) cs = new CombatSystem();
	} /* end ability constructor */
	
	public abstract void obtainTarget(TargetSelectedMessage tsm);
	/* obtainTarget will be called on by the TargetSystem signaling that a target(s) has
 	been chosen */
	
	protected abstract void action() throws SlickException;
	/* action will be the action performed by the ability */
	
	public void activate()
	{ /* Activate ability and add it to the appropriate Character variable. */
		/* When clicked on, the ability will perform this action */
		/* TODO: implement */
		activated = true;
	} /* end activate method */
	
	public void deactivate()
	{ /* Deactivate ability and remove it from the appropriate Character variable */
		/* TODO: implement */
		activated = false;
	} /* end activate method */
	
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) 
	{
		// TODO Auto-generated method stub
		/* TODO: add activate where necessary */
		
	} /* end update method */
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) 
	{
		// TODO Auto-generated method stub
		
		
	} /* end render method */
	
	public void renderActionBox(GameContainer gc, StateBasedGame sbg, Graphics gr) 
	{
		// TODO Auto-generated method stub
		
		
	} /* end render method */
	
	public void sendToInterpreter(CombatMessage cm)
	{
		/* Send the message to the proper interpreter and send it through the network */
		/* First, send to network */
		//TODO: send to network
		
		/* Second, sent to interpreter */
		CombatValidationMessage cvm = cs.process(cm);
		
		//TODO: Check for validity of cvm
		
		
		if(done)
		{
			//TODO: "Unclick" ie reset action box. By now, interpreter should have changed turn states
		} /* end if */
	} /* end sendToInterpreter method */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public CombatSystem.ActionTypes getActionType()
	{
		return(actionType);
	} /* end getAbilityType method */
	
	public String getName()
	{
		return(name);
	} /* end getName method */
	
	public String getDescription()
	{
		return(description);
	} /* end getDescription method */

	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
	public static void setOwnerCharacter(DDCharacter character)
	{
		Ability.character = character;
	} /* end setOwnerCharacter method */
	
	public void setOwnerSubAction(SubAction subAction)
	{
		this.subAction = subAction;
	} /* end setOwnerCharacter method */
	
	
} /* end Ability class */
