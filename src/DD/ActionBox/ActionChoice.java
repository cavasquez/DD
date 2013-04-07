package DD.ActionBox;

import java.util.ArrayList; 

import org.newdawn.slick.Color; 
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.TrueTypeFont;
import org.lwjgl.input.Mouse;
import DD.Character.*;
import DD.Character.Abilities.Ability;
import DD.SlickTools.Component;
import DD.SlickTools.DDImage;
import DD.SlickTools.ImageRenderComponent;
import DD.SlickTools.RenderComponent;

/*****************************************************************************************************
 * The ActionChoice class will represent the many actions available to the player in the ActionBox class.
 * For example, the default Standard action should be represented in the ActionChoice method. It will be
 * subclass some variation of the RenderComponent classes (dependent on the GUI implementers). The 
 * ActionChoice class should be clicked on (or hotkeyed) to display the available actions the Player
 * can perform for the given action. This will be done by adding new components to the ActionBox method.
 * 
 * Each ActionChoice should be defined by an ActionType which is defined as a constant in the ActionBox
 * class. This will be important when determining what feats/abilities will be added as Ability object. 
 * We will get these from the Character class.
 * 
 * The actionType should also be used when determining where to render the ActionChoice in the ActionBox.  
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class ActionChoice extends ImageRenderComponent
{
	/************************************ Class Attributes *************************************/
	int numOfSubActions; 	/* Number of subActions available */
	int actionType;			/* Number that refers to the type of action this ActionChoice represents (standard, move, etc.) */
	String display;
	boolean actionPerformed;
	String message = " ";
	String mousePos = " ";
	float x, y;
	Ability ability;	//REMOVE IN SPRINT 3
	//boolean renderSubActions = false;
	Input mouse = new Input(650);
	//ArrayList<Component> subActions = new ArrayList<Component>();
	
	/************************************ Class Methods *************************************/
	/* Remember, ID is the same as ActionType */
	public ActionChoice(int id, int actionType, String display, DDImage image, float x, float y)
	{
		super(id, image);
		this.actionType = actionType;
		this.display = display;
		actionPerformed = false;
		this.x = x;
		this.y = y;
		this.ability = null;
		
	} /* end ActionChoice constructor */
	
	public ActionChoice(int id, int actionType, String display, DDImage image, float x, float y, Ability ability )
	{
		super(id, image);
		this.actionType = actionType;
		this.display = display;
		actionPerformed = false;
		this.x = x;
		this.y = y;
		this.ability = ability;
		
	} /* end ActionChoice constructor */
	
	/*
	public void addSubAction(int id, Image image, Ability ability) {
		subActions.add(new SubActionButtons(id, image, ability, x, y+this.image.getHeight()));
	}
	*/

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
	{
		image.draw(x, y);
		gr.drawString(message, x, 250 + y);
		gr.drawString(mousePos, 900, 0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{ 	 
		/* get mouse coordinates */
		int mouseX = mouse.getMouseX();
		int mouseY = mouse.getMouseY();
		
		if( (mouseX >= x && mouseX <= x + image.getWidth() ) &&
			(mouseY >= y && mouseY <= y + image.getHeight() ) )
		{
			/* You are inside the button */
			
			//if(mouse.isMousePressed(Input.MOUSE_LEFT_BUTTON)) 
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				System.out.println("You are clicking " + display);
				//message = "You are clicking " + display;
				if(actionType == 1000) {
					ability.done();
				}
				else {
					if(ability != null) {
						ability.activate();
					}
				}
				
				
				
			}
		}
		mousePos = "Mouse Position x: " + mouseX + " y: " + mouseY;
	}
	
	protected void performAction()
	{ /* Logic that should take place when action is clicked on.
	 	performAction needs to get all the appropriate abilities from
	 	the Character and make them available in ActionBox by creating
	 	adding the ability to the SubAction array list in ActionBox.*/
		Ability abilities[] = DDCharacter.getAbilities(this.actionType);
		
		for (Ability ability : abilities)
		{
			((ActionBox)owner).addSubAction(ability);
		} /* end for loop */
		
		/*TODO: Need to add the subActions to ActionBox's subActions */
	} /* end performAction method */
	
} /* end ActionChoice class */
