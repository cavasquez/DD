package DD.Character.Abilities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import DD.ActionBox.ActionBox;
import DD.ActionBox.SubAction;
import DD.Character.Character;
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
 ******************************************************************************************************/

public abstract class Ability extends RenderComponent
{
	/************************************ Class Attributes *************************************/
	protected Character character;
	protected SubAction subAction;
	protected final int actionType;
	protected final String name;
	protected final String description;
	protected boolean activated;			/* flag that establishes if an ability has been clicked. */
	
	/************************************ Class Methods*************************************/
	public Ability(int id, int actionType, String name, String description)
	{
		super(id);
		this.actionType = actionType;
		this.name = name;
		this.description = description;
		activated = false;
	} /* end ability constructor */
	
	public void activate()
	{ /* Activate ability and add it to the appropriate Character variable. */
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
		/* TODO: add activate where necessarry */
		
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

	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
	public void setOwnerCharacter(Character character)
	{
		this.character = character;
	} /* end setOwnerCharacter method */
	
	public void setOwnerSubAction(SubAction subAction)
	{
		this.subAction = subAction;
	} /* end setOwnerCharacter method */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	public int getActionType()
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
	
} /* end Ability class */