package DD.GUI;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import DD.System.DDSystem;
public class CreateLob extends BasicGameState
{
	
	Image screen;
	private String mouse = "No input yet!";
	Music dungeon;
	Sound button;
	ArrayList<Lobby> lobby = new ArrayList<Lobby>();
	
	
	
	public CreateLob(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
	{
		screen = new Image("Images/Menus/lobby.jpg");
		
		
		//dungeon = new Music("Audio/dunEffect1.wav");
		//dungeon.loop();
		
		button = new Sound("Audio/dunSound.wav");
		
		
		
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
	{
		
		screen.draw(0,0);
		
		g.drawString(mouse, 100, 100);
		
		
		
		g.drawString("LOAD MAP",540,330);
		
		g.drawString("CHOOSE WORLD",540,364);
		
		g.drawString("START SERVER",540,404);
		
		g.drawString("START GAME",540,440);
		
		g.drawString("BACK",540,480);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg,int delta)throws SlickException
	{
		int posX = Mouse.getX();
		int posY = Mouse.getY();

		//LOAD MAP
		
		if((posX > 536 && posX < 616) && (posY > 303 && posY < 320))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				
				
			}
		}
		
		//CHOOSE WORLD
		
		if((posX > 538 && posX < 650) && (posY > 270 && posY < 282))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
						
				
			}
		}
				
				
				
		//START SERVER
				
		if((posX > 540 && posX < 650) && (posY > 228 && posY < 246))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				Game.system.server();
						
				
			}
		}
				
				
		//START GAME
				
		if((posX > 540 && posX < 630) && (posY > 190 && posY < 211))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
						
				
			}
		}
		
		
		
		//Back button
		if((posX > 537 && posX < 580) && (posY > 151 && posY < 170))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
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
