package DD.GUI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class LobbyRoom extends BasicGameState
{
	
	private String mouse = "No input yet!";
	Image roomScreen;
	Sound button;
	
	
	
	public LobbyRoom(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
	{
		roomScreen = new Image("Images/Menus/menuscreen2.jpg");
		button = new Sound("Audio/dunSound.wav");
			
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
	{
		roomScreen.draw(110,50);
		//g.drawRect(100, 100, 200, 60);
		g.drawString("BACK", 122, 64);
		g.drawString(mouse, 100, 10);
		g.drawString("PLAY GAME", 1025, 64);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg,int delta)throws SlickException
	{
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		//back button
		if((posX > 114 && posX < 166) && (posY > 564 && posY < 588))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(0);
			}
		}
		
		//PLAY GAME BUTTON
		if((posX > 1020 && posX < 1112) && (posY > 564 && posY < 588))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(4);
			}
		}
		
		
		
		
		 
	      mouse = "Mouse position x: " + posX + " y: " + posY;
	      

		
	}
	
	public int getID()
	{
		return 2;
	}

}
	
	


