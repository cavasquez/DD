package DD.GUI;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class JoinLob extends BasicGameState{
	
	
	Image screen;
	private String mouse = "No input yet!";
	Image play;
	Image options;
	Image about;
	Sound button;
	Music dungeon;
	
	
	ArrayList<Lobby> lobbyList = new ArrayList<Lobby>();
	
	public JoinLob(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
	{
		
		screen = new Image("Images/Menus/DD1.jpg");
		
		
		play = new Image("Images/Menus/play button.png");
		//options = new Image("res/options button.png");
		about = new Image("Images/Menus/about button.png");

		button = new Sound("Audio/dunSound.wav");
		//dungeon = new Music("Audio/dunEffect1.wav");
		//dungeon.loop();
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
	{
		screen.draw(0,0);
		play.draw(100,100);
		//options.draw(100, 200);
		about.draw(100, 300);
		g.drawString(mouse, 100, 400);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg,int delta)throws SlickException
	{
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		//play now button
		if((posX > 100 && posX < 244) && (posY > 497 && posY < 549))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(0);
			}
		}
		//about
		if((posX > 100 && posX < 241) && (posY > 310 && posY < 349))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(2);
			}
		}
		
		
		 
	      mouse = "Mouse position x: " + posX + " y: " + posY;
	      

		
	}
	
	public int getID()
	{
		return 1;
	}

}
