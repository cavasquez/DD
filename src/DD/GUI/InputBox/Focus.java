package DD.GUI.InputBox;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import DD.SlickTools.BoxInterface;
import DD.SlickTools.Component;

/*****************************************************************************************************
 * Focus will check to see if the input box has focus.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class Focus extends Component
{
	/************************************ Class Methods *************************************/
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
		{
			if((posX > owner.getPosition().x && posX < (owner.getPosition().x + ((BoxInterface)owner).getWidth())) && 
					(posY > owner.getPosition().y && posX < (owner.getPosition().y - ((BoxInterface)owner).getLength())))
			{
				((InputBox)owner).focus = true;
				System.out.println("InputBox has focus");
			} /* end if */
			else 
			{
				((InputBox)owner).focus = false;
				System.out.println("InputBox does not focus");
			} /* end else */
			
		} /* end mouse button if */
		
	} /* end update method */

} /* end Focus class */
