package DD;

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
	protected int id;
	protected Entity owner;
	
	public abstract void update(GameContainer gc, StateBasedGame sbg, int delta);
	
	/******************************************************************************
	 ******************************* Getter Methods *******************************
	 ******************************************************************************/
	
	public int getId()
	{
		return(id);
	} /* end getId method */
	
	public void setOwnerEntity(Entity owner)
	{
		this.owner = owner;
	} /* end setOwnerEntity method */
	
} /* end Component class */
