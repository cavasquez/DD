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
		
		g.drawString("CREATE LOBBY",540,222);
		
		g.drawString("BACK",540,434);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg,int delta)throws SlickException
	{
		int posX = Mouse.getX();
		int posY = Mouse.getY();

		//CREATE LOBBY
		
		if((posX > 540 && posX < 650) && (posY > 405 && posY < 430))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				
				sbg.enterState(2);
			}
		}
		
		
		
		//Back button
		if((posX > 540 && posX < 590) && (posY > 196 && posY < 222))
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
