package DD.GUI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class CreateLob extends BasicGameState
{
	
	Image screen;
	private String mouse = "No input yet!";
	Music dungeon;
	//Sound button;
	
	
	public CreateLob(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
	{
		screen = new Image("Images/Menus/lobby.jpg");
		
		dungeon = new Music("Audio/Gauntlet.wav");
		dungeon.loop();
		
		//button = new Sound("res/button-9.wav");
		
		
		
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
	{
		
		screen.draw(0,0);
		
		g.drawString(mouse, 100, 100);
		
		g.drawString("BACK",130,434);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg,int delta)throws SlickException
	{
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		//Back button
		if((posX > 125 && posX < 170) && (posY > 199 && posY < 219))
		{
			if(Mouse.isButtonDown(0))
			{
				//button.play();
				sbg.enterState(0);
			}
		}
		
		
		
		 
	      mouse = "Mouse position x: " + posX + " y: " + posY;
	      

		
		
	}
	public int getID()
	{
		return 3;
	}

}
