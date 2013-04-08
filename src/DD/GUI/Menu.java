package DD.GUI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
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
	private TextField username;
	
	private Image exitButton = null;
	
	
	public Menu(int state)
	{
		
	}
	
	
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
	{
		dungeon = new Music("Audio/Gauntlet.wav");
		dungeon.loop();
		screen = new Image("Images/Menus/menuscreen5.jpg");
		playNow = new Image("Images/Menus/MenuButtons.png"); // Contains the menu button options
		
		
		
		button = new Sound("Audio/dunSound.wav");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
	{
		
		screen.draw(0,0);
		playNow.draw(100,100); // Image with the menu button options.
		
		g.drawString(mousePos, 100, 10);
		
				
	}
	
	public void update(GameContainer gc, StateBasedGame sbg,int delta)throws SlickException
	{
		int posX = mouse.getMouseX();
		int posY = mouse.getMouseY();
		
		//Join Lobby button
		if((posX > 136 && posX < 334) && (posY > 227 && posY < 297))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(1);
				
			}
		}
		
		//Create Lobby
		if((posX > 136 && posX < 334) && (posY > 333 && posY < 402))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(3);
			}
		}
		
		
		
		//exit button
		if((posX > 136 && posX < 334) && (posY > 539 && posY < 606))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				System.exit(0);
			}
		}
		
		//map tool button
		if((posX > 136 && posX < 334) && (posY > 434 && posY < 503))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(5);
			}
		}
		
		//CREATE CHARACTER
		
		if((posX > 136 && posX < 334) && (posY > 123 && posY < 193 ))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(6);
			}
		}
		 
	      mousePos = "Mouse position x: " + posX + " y: " + posY;
	      

		
		
	}
	public int getID()
	{
		return 0;
	}

}
