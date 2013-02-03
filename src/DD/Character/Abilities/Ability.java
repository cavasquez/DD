package DD.Character.Abilities;

import DD.ActionBox.ActionBox;
import DD.Character.Character;
import DD.SlickTools.RenderComponent;

/*****************************************************************************************************
 * The Ability class will subclass the RenderComponent class. It should contain all the possible player
 * abilities. This includes feats, spells, default actions, etc. The Ability class should be subclassed
 * to further specify the type of ability that is being utilized. For example, the movement types of
 * abilities will always interact with the Character whereas the Full-round actions might not. In this
 * case, the Movement subclass should provide an interface that affects the player. All abilities will 
 * have pointers to the Character so that they may obtain/modify character stats (ie, it might be 
 * pertinent to know the players speed, AC, etc.).
 * 
 * Note that there can be no more than one ability clicked at a time.
 ******************************************************************************************************/

public abstract class Ability extends RenderComponent
{
	/************************************ Class Attributes *************************************/
	private Character character;
	protected final int actionType;
	protected final String name;
	protected final String description;
	public boolean abilityClicked;		/* flag that establishes if an ability has been clicked. */
	private boolean renderCharacter;	/* flag to see if a render should be placed on the character */
	
	/************************************ Class Methods*************************************/
	public Ability(int id, int actionType, String name, String description)
	{
		super(id);
		this.actionType = actionType;
		this.name = name;
		this.description = description;
		abilityClicked = false;
	} /* end ability constructor */
	
	private void unclickSubActions()
	{ /* set abilityClicked to false for all subActions in ActionBox */ 
		((ActionBox) owner).unclickSubActions();
	} /* end unclickSubACtions method */
	
	
	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
	public void setOwnerCharacter(Character character)
	{
		this.character = character;
	} /* end  */
	
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
