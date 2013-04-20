package DD.ActionBox;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import DD.Character.Abilities.Ability;
import DD.SlickTools.*;

public class SubActionButtons extends ImageRenderComponent {
	
	private SubAction subaction;
	float x, y;
	Input mouse = new Input(650);
	
	public SubActionButtons(int id, Image image, Ability ability, float x, float y) {
		super(id, image);
		subaction = new SubAction(id, ability);
		this.x = x;
		this.y = y;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		// TODO Auto-generated method stub
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
				/*
				try {
					subaction.click();
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				System.out.println("Clicking subaction\n");
			}
		}
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) {
		image.draw(x, y);
	}

}
