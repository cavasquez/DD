package DD.Character;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import DD.SlickTools.CharacterRenderComponent;

/*****************************************************************************************************
 * CombatStats will display the character name and HP over the character sprite on the map.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class CharacterStats extends CharacterRenderComponent
{
	/************************************ Class Constants *************************************/
	public static final float distanceAbove = 35;	/* The distance above the character that the info will be displayed */
	public static final float delta = 15;			/* The distance between health and name */
	
	/************************************ Class Methods *************************************/
	
	public CharacterStats()
	{
		super();
	} /* end CharacterStats constructor */

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr, float x, float y) 
	{
		/* Display character health and name */
		/* First render the name */
		gr.drawString(((DDCharacter)owner).getSheet().getName(),x,y - distanceAbove);
		
		/* Second, render the health */
		String health = Integer.toString(((DDCharacter)owner).getCurrentHP()) + "/" + Integer.toString(((DDCharacter)owner).getSheet().getHP());
		gr.drawString(health, x, y - distanceAbove + delta);
		
		/* Thirdly, check if dying or dead and print */
		if(((DDCharacter)owner).isDead()) gr.drawString("dead", x, y - distanceAbove + delta + delta);
		else if(((DDCharacter)owner).isDying()) gr.drawString("dying", x, y - distanceAbove + delta + delta);
		
	} /* end render method */

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		/*Do nothing */
	} /* end update method */

} /* End CharacterStats class */
