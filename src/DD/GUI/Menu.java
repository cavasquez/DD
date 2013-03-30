package DD.GUI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class Menu extends BasicGameState
{
	Image playNow;
	Image exitGame;
	Image screen;
	Image mapToolButton;
	private String mousePos = "No input yet!";
	Music dungeon;
	Sound button;
	Input mouse = new Input(650);
	
	
	public Menu(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
	{
		dungeon = new Music("Audio/Gauntlet.wav");
		dungeon.loop();
		screen = new Image("Images/Menus/menuscreen5.jpg");
		playNow = new Image("Images/Menus/MenuButtons.png");
		mapToolButton = new Image("Images/Menus/MapTool.png");
		
		button = new Sound("Audio/dunSound.wav");
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
	{
		
		screen.draw(0,0);
		playNow.draw(100,140);
		mapToolButton.draw(130, 550);
		g.drawString(mousePos, 100, 100);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg,int delta)throws SlickException
	{
		int posX = mouse.getMouseX();
		int posY = mouse.getMouseY();
		
		//Join Lobby button
		if((posX > 130 && posX < 335) && (posY > 200 && posY < 266))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(1);
				
			}
		}
		
		//Create Lobby
		if((posX > 130 && posX < 335) && (posY > 285 && posY < 351))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(3);
			}
		}
		
		//play game
		if((posX > 130 && posX < 335) && (posY > 370 && posY < 450))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(4);
			}
		}
		
		//exit button
		if((posX > 130 && posX < 335) && (posY > 466 && posY < 532))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				System.exit(0);
			}
		}
		
		//map tool button
		if((posX > 130 && posX < 335) && (posY > 550 && posY < 550 + mapToolButton.getHeight()))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(5);
			}
		}
		 
	      mousePos = "Mouse position x: " + posX + " y: " + posY;
	      

		
		
	}
	public int getID()
	{
		return 0;
	}

}
