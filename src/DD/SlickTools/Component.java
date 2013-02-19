package DD.SlickTools;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/*****************************************************************************************************
 * The Component class will add functionality to the Entity class. It will add new interactions, as well
 * as new abilities to the Entity class. The Component class should provide new functionality to the
 * Entity class and provide an easy way to change how one interacts with the Entity. Once again, the
 * idea for the Component class came from: http://slick.cokeandcode.com/wiki/doku.php?id=entity_tutorial.
 ******************************************************************************************************/

public abstract class Component 
{
	/************************************ Class Constants *************************************/
	protected int id;
	protected Entity owner;
	
	/************************************ Class Methods *************************************/
	public Component(int id)
	{
		this.id = id;
	} /* end Component Constructor */
	
	public Component() {
		this.id = 0;
		this.owner =null;
		// TODO Auto-generated constructor stub
	}

	public abstract void update(GameContainer gc, StateBasedGame sbg, int delta);
	
	/******************************************************************************
	 ******************************* Setter Methods *******************************
	 ******************************************************************************/
	public void setOwnerEntity(Entity owner)
	{
		this.owner = owner;
	} /* end setOwnerEntity method */
	
	public void setId(int id)
	{
		this.id = id;
	} /* end setOwnerEntity method */
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	
	public int getId()
	{
		return(id);
	} /* end getId method */
	
	public Entity getOwner()
	{
		return(owner);
	} /* end getOwner method */
	
} /* end Component class */
