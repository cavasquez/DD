package DD.ActionBox;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.TrueTypeFont;
import org.lwjgl.input.Mouse;
import DD.Character.*;
import DD.Character.Abilities.Ability;
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
 ******************************************************************************************************/

public class ActionChoice extends ImageRenderComponent
{
	/************************************ Class Attributes *************************************/
	int numOfSubActions; 	/* Number of subActions available */
	int actionType;			/* Number that refers to the type of action this ActionChoice represents (standard, move, etc.) */
	String display;
	boolean actionPerformed;
	float x, y;
	
	/************************************ Class Methods *************************************/
	/* Remember, ID is the same as ActionType */
	public ActionChoice(int id, int actionType, String display, Image image, float x, float y)
	{
		super(id, image);
		this.actionType = actionType;
		this.display = display;
		actionPerformed = false;
		this.x = x;
		this.y = y;
		
	} /* end ActionChoice constructor */

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
	{/* TODO: generate */
		image.draw(x, y);
		/*
		 This is Full Round Actionwith x and y of 500.0 80.0
		This is Swift Actionwith x and y of 500.0 120.0
		This is Immediate Actionwith x and y of 500.0 160.0
		This is Free Actionwith x and y of 500.0 200.0
		This is Standard Actionwith x and y of 500.0 0.0
		This is Move Actionwith x and y of 500.0 40.0
		 */
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) 
	{ 
		/* get mouse coordinates */
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
		
		//Font font = new Font("Verdana", Font.BOLD, 20);
		//TrueTypeFont ttf = new TrueTypeFont(font, true); 
		//Color color = new Color(1,1,1);
		
		if( (mouseX >= x && mouseX <= x + image.getWidth() ) &&
			(mouseY >= 560 - y && mouseY <= 560 - y + image.getHeight() ) )
		{
			/* You are inside the button */
			if(Mouse.isButtonDown(0))
			{
				System.out.println("You are clicking " + display);
				//ttf.drawString(100, 100, "Hello", color);
			}
		}
			 
		
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
