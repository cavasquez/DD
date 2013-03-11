package DD.GUI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class About extends BasicGameState
{
	
	private String mouse = "No input yet!";
	Image about;
	Sound button;
	
	
	
	public About(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
	{
		about = new Image("Images/Menus/protoAbout.jpg");
		button = new Sound("Audio/dunSound.wav");
			
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
	{
		about.draw(110,50);
		//g.drawRect(100, 100, 200, 60);
		g.drawString("BACK", 122, 633);
		g.drawString(mouse, 100, 10);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg,int delta)throws SlickException
	{
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		//back button
		if((posX > 120 && posX < 163) && (posY > 2 && posY < 15))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(1);
			}
		}
		
		
		
		 
	      mouse = "Mouse position x: " + posX + " y: " + posY;
	      

		
	}
	
	public int getID()
	{
		return 2;
	}

}
	
	


