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
		
		exitButton = new Image("Images/Menus/exitButton.png");
		
		button = new Sound("Audio/dunSound.wav");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
	{
		
		screen.draw(0,0);
		playNow.draw(100,50); // Image with the menu button options.
		
		g.drawString(mousePos, 100, 10);
		
		exitButton.draw(142, 580);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg,int delta)throws SlickException
	{
		int posX = mouse.getMouseX();
		int posY = mouse.getMouseY();
		
		//Join Lobby button
		if((posX > 130 && posX < 360) && (posY > 187 && posY < 254))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(1);
				
			}
		}
		
		//Create Lobby
		if((posX > 130 && posX < 360) && (posY > 295 && posY < 360))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(3);
			}
		}
		
		//START game
		if((posX > 130 && posX < 360) && (posY > 500 && posY < 566))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(4);
			}
		}
		
		//exit button
		if((posX > 146 && posX < 338) && (posY > 584 && posY < 642))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				System.exit(0);
			}
		}
		
		//map tool button
		if((posX > 130 && posX < 362) && (posY > 400 && posY < 464))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(5);
			}
		}
		
		//CREATE CHARACTER
		
		if((posX > 128 && posX < 359) && (posY > 75 && posY < 145 ))
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
