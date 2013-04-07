package DD.GUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import DD.MapTool.Map;
import DD.MapTool.MapTool;
import DD.MapTool.World;
import DD.System.DDSystem;
public class CreateLob extends BasicGameState
{
	
	Image screen;
	private String mouse = "No input yet!";
	Music dungeon;
	Sound button;
	World world;
	Map map;
	private ArrayList<String> worldList;
	
	
	public CreateLob(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
	{
		screen = new Image("Images/Menus/lobby.jpg");
		worldList = new ArrayList<String>();
		
		
		//dungeon = new Music("Audio/dunEffect1.wav");
		//dungeon.loop();
		
		button = new Sound("Audio/dunSound.wav");
		
		ArrayList<String> worldChoices = new ArrayList<String>();
		File file = new File(MapTool.ddPath + "World/");
//		DD/DefaultWorld/
		if (file.exists())
		{
			float delta = 10;
			File[] list = file.listFiles();
			for(int i = 0; i < list.length; i++)
			{
				if(list[i].isDirectory() && !list.toString().matches("Characters"))
				{
					worldList.add(list[i].getName());
				} /* end if */
			} /* end loop */
		} /* end if */
		
		
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
	{
		
		
		screen.draw(0,0);
		int x = 100;
		int y = 200;
		int delta = 20;
		g.drawString("World List: ",x , y);
		for(int i = 0; i < worldList.size(); i++)
		{
			g.drawString(worldList.get(i),x ,y+=delta);
		} /* end loop */
		
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
				Game.system.ns.stop();
						
				
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
