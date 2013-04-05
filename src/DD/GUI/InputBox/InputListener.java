package DD.GUI.InputBox;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import DD.SlickTools.ImageRenderComponent;

/*****************************************************************************************************
 * InputListener will listen for keyboard presses and working accordingly. It will modify the provided
 * InputBox based on the keyboard presses.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class InputListener extends ImageRenderComponent implements KeyListener
{
	/************************************ Class Attributes *************************************/
	Input keys = null;
	
	/************************************ Class Methods *************************************/
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		if(((InputBox)owner).focus == true)
		{
			
		}
		
	} /* end update method */
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
	{
		if (keys == null) keys = gc.getInput();
		render(gc, sbg, gr, owner.getPosition());		
		gr.getFont().getWidth(((InputBox)owner).input);
	} /* end render method */

	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int arg0, char arg1) {
		// TODO Auto-generated method stub
		System.out.println("You just pressed key " + arg0 + " char " +arg1);
	}

	@Override
	public void keyReleased(int arg0, char arg1) {
		// TODO Auto-generated method stub
		
	}
	
} /* end InputListener class */
