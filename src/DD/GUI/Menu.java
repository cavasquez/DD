package DD.GUI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class Menu extends BasicGameState
{
	Image playNow;
	Image exitGame;
	Image screen;
	private String mouse = "No input yet!";
	Music dungeon;
	//Sound button;
	
	
	public Menu(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
	{
		screen = new Image("Images/Menus/menuscreen5.jpg");
		playNow = new Image("Images/Menus/MenuButtons.png");
		
		dungeon = new Music("Audio/Gauntlet.wav");
		dungeon.loop();
		
		//button = new Sound("res/button-9.wav");
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
	{
		
		screen.draw(0,0);
		playNow.draw(100,140);
		
		g.drawString(mouse, 100, 100);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg,int delta)throws SlickException
	{
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		//Join Lobby button
		if((posX > 130 && posX < 335) && (posY > 384 && posY < 450))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				//button.play();
				sbg.enterState(1);
			}
		}
		
		//Create Lobby
		if((posX > 130 && posX < 335) && (posY > 299 && posY < 365))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				//button.play();
				sbg.enterState(3);
			}
		}
		
		//play game
		if((posX > 130 && posX < 335) && (posY > 200 && posY < 280))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				//button.play();
				sbg.enterState(4);
			}
		}
		
		//exit button
		if((posX > 130 && posX < 335) && (posY > 118 && posY < 184))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				//button.play();
				System.exit(0);
			}
		}
		
		 
	      mouse = "Mouse position x: " + posX + " y: " + posY;
	      

		
		
	}
	public int getID()
	{
		return 0;
	}

}
