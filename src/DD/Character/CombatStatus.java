package DD.Character;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import DD.SlickTools.CharacterRenderComponent;

/*****************************************************************************************************
 * CombatStatus will provide the character with the ability to communicate to players if an attack
 * missed or hit (and for how much). It will display over the character as a string for a couple 
 * seconds.
 * 
 * CombatStatus will also provide a way to produce a "status" without timeout
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class CombatStatus extends CharacterRenderComponent
{
	/************************************ Class Constants *************************************/
	private final int timeout = 25;	/* The amount of time the message will last */
	
	public static enum Status
	{
		DAMAGE ("", true);
		
		private String message;
		private boolean timesOut;
		Status (String message, boolean timesOut)
		{
			this.message = message;
			this.timesOut = timesOut;
		} /* end TargetCount index */
		
		public String message()
		{
			return message;
		} /* end message method */
		public boolean timesOut()
		{
			return timesOut;
		} /* end timesOut method */
		
	} /* end Action enum */
	
	/************************************ Class Attributes *************************************/
	private String message;
	private int counter;
	private boolean timesOut;
	
	/************************************ Class Methods *************************************/
	public CombatStatus(int id, String message)
	{/* Constructor that times out the message. Assume to be a damage Status */
		this(id, message, Status.DAMAGE.timesOut);
	} /* end CombatStatus constructor */
	
	public CombatStatus(int id, Status status)
	{
		this(id, status.message(), status.timesOut());
	} /* end overlaoded constructor */
	
	public CombatStatus(Status status)
	{
		this(0, status);
	} /* end overloaded constructor */
	
	private CombatStatus(int id, String message, boolean timesOut)
	{ 
		super(id);
		this.message = message;
		this.counter = 0;
		this.timesOut = timesOut;
	} /* end CombatStatus constructor */
	
	public CombatStatus(String message)
	{
		this(0, message);
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
		if(timesOut)
		{/* only increment counter and remove if the component times out */
			if(counter++ >= timeout ) this.remove();		
		} /* end if */
	} /* end update method */
	
	/****************************************************************************************
	 ************************************ Getter Methods ************************************
	 ****************************************************************************************/
	public String getMessage()
	{
		return message;
	} /* end getMessage method */

} /* end CombatStatus class */
