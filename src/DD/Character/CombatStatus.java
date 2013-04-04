package DD.Character;

import java.util.ArrayList;
import java.util.Queue;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import DD.SlickTools.CharacterRenderComponent;
import DD.SlickTools.Component;

/*****************************************************************************************************
 * CombatStatus will provide the character with the ability to communicate to players if an attack
 * missed or hit (and for how much). It will display over the character as a string for a couple 
 * seconds.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class CombatStatus extends CharacterRenderComponent
{
	/************************************ Class Constants *************************************/
	private final int timeout = 25;	/* The amount of time the message will last */
	
	/************************************ Class Attributes *************************************/
	private String message;
	private int counter;
	
	/************************************ Class Methods *************************************/
	public CombatStatus(int id, String message)
	{
		super(id);
		this.message = message;
		this.counter = 0;
	} /* end CombatStatus constructor */
	
	public CombatStatus(String message)
	{
		super();
		this.message = message;
		this.counter = 0;
	} /* end CombatStatus constructor */
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr, float x, float y) 
	{
		gr.drawString(message,x,y);		
	} /* end render method */

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		if(counter++ >= timeout) this.remove();		
	} /* end update method */

} /* end CombatStatus class */
