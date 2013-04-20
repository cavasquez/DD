package DD.GUI;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.*;

import DD.ActionBox.ActionBox;
import DD.Character.Abilities.Ability;
import DD.GMToolsBox.GMToolsBox;
import DD.MapTool.Map;
import DD.MapTool.MapTool;
import DD.MapTool.SerMapCharHelper;
import DD.MapTool.World;
import DD.System.DDSystem;
public class CreateLob extends BasicGameState
{
	
	Image screen;
	private String mouse = "No input yet!";
	private TextField loadMapText = null;
	UnicodeFont font = null;
	Music dungeon;
	Sound button;
//	UnicodeFont font;
//	TextField worldField;
//	TextField mapX;
//	TextField mapY;
	private ArrayList<String> worldList;
	private ArrayList<String> userList;
	public MapTool maptool;
	
	
	public CreateLob(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
	{
//		font = getNewFont("Arial" , 16);
		screen = new Image("Images/Menus/lobby.jpg");
		worldList = new ArrayList<String>();
		maptool = new MapTool();
		userList = new ArrayList<String>();
		
		font  = new UnicodeFont(new Font("Arial" , Font.PLAIN , 16));
		font.getEffects().add(new ColorEffect(java.awt.Color.white));
		font.loadGlyphs();
		
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
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		loadMapText = new TextField(gc, font, 640, 360, 180, 25);
//		worldField = new TextField(gc, font, 700, 320, 180, 25);
//		worldField.setText("World");
//		mapX = new TextField(gc, font, 700, 350, 180, 25);
//		mapX.setText("Map X Position (0-4)");
//		mapY = new TextField(gc, font,700, 380, 180, 25);
//		mapY.setText("Map Y Position (0-4)");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
	{
		
		
		screen.draw(0,0);
		int x = 100;
		int y = 200;
		int delta = 20;
		g.drawString("World List: ",x , y);
		if(loadMapText!=null) (loadMapText).render(gc,g);
		for(int i = 0; i < worldList.size(); i++)
		{
			g.drawString(worldList.get(i),x ,y+=delta);
		} /* end loop */
//		if(worldField != null) worldField.render(gc, g);
//		if(mapX != null) mapX.render(gc, g);
//		if(mapY!= null) mapY.render(gc, g);
//		g.setFont(font);
		
		g.drawString(mouse, 100, 100);
		
		
		g.drawString("LOAD WORLD",540,330);
		
		if (maptool.world != null)g.drawString("CHOOSE MAP",540,364);
		
		g.drawString("START SERVER",540,404);
		
		g.drawString("START GAME",540,440);
		
		g.drawString("BACK",540,480);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg,int delta)throws SlickException
	{
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		font.loadGlyphs();
		
		//LOAD World
		
		if((posX > 536 && posX < 616) && (posY > 303 && posY < 320))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				System.out.println("Load World");
				button.play();
				maptool.world = maptool.loadWorld("world", false);
				maptool.setCurrentMap(0, 0);
			}
		}
		
		//LOAD Map
		
		if((posX > 536 && posX < 616) && (posY > 270 && posY < 285) && maptool.world != null)
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON) )
			{
				System.out.println("Load Map");
				button.play();
				
				for (int i = 0; i < maptool.world.getWorldSize(); i++) {
					for (int j = 0; j < maptool.world.getWorldSize(); j++) {
						System.out.println("iteration j:"+ j +" input: "+loadMapText.getText().trim()+ " mapname: "+ maptool.world.getMap(i, j).getName());
						if((loadMapText.getText().trim()).equals(maptool.world.getMap(i, j).getName())){
					
							maptool.setCurrentMap(i, j);
							maptool.selectedList.clearSelectedList();
							maptool.selectedList.setOwner(maptool.getCurrentMap());
						}
					}
				}
			}
		}
				
				
		//START SERVER
				
		if((posX > 540 && posX < 650) && (posY > 228 && posY < 246))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				System.out.println("Start Server");
				button.play();
				Game.system.server();
				Game.system.ns.setUsername("GM Bitches");
				try 
				{
					Game.system.ns.setServerIP(InetAddress.getLocalHost().getHostAddress());
					((GMGameplayState)sbg.getState(Game.gmGameplay)).getGMToolsBox(); /* gets the GMToolsBox made */ 
				} /* end try */
				catch (UnknownHostException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} /* end catch */
				Game.system.ns.start();
			}
		}
				
				
		//START GAME
				
		if((posX > 540 && posX < 630) && (posY > 190 && posY < 211))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				System.out.println("Start Game");
				button.play();
				Game.system.ns.stop();
				/* Firstly, get ActionBox from GMGameplayState and link it */
				ActionBox ab = new ActionBox(((GMGameplayState)sbg.getState(Game.gmGameplay)).stateID, 300, 200);;
				((GMGameplayState)sbg.getState(Game.gmGameplay)).actionBox = ab;
				Game.system.linkBoxes(ab);
				
				GMToolsBox gmt;
				gmt = ((GMGameplayState)sbg.getState(Game.gmGameplay)).getGMToolsBox();
				/* Firstly, give the map to all the characters */
				gmt.setMapTool(maptool);
				gmt.setMap();
				
				/* Secondly, place characters onto the map */
				
				ArrayList<SerMapCharHelper> preMadeCharacters = new ArrayList<SerMapCharHelper>();
				preMadeCharacters = maptool.getCurrentMap().serMapHelper;
				
				for(SerMapCharHelper character : preMadeCharacters)
				{
					/* assume all SerMapCharHelper are mob */
					gmt.addCharacter(GMToolsBox.Holder.MOB, character.cs, character.coord, true);
				} /* end for loop */
				sbg.enterState(Game.gmGameplay);
				killTextFields();
			}
			
		}
		
		
		
		//Back button
		if((posX > 537 && posX < 580) && (posY > 151 && posY < 170))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(0);
				killTextFields();
			}
		}
		
		
		
		
		 
	      mouse = "Mouse position x: " + posX + " y: " + posY;
	      

		
		
	}
	public int getID()
	{
		return 3;
	}

	public UnicodeFont getNewFont(String fontName , int fontSize)
    {
        font = new UnicodeFont(new Font(fontName , Font.PLAIN , fontSize));
        font.addGlyphs("@");
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        return (font);
    }
	public void killTextFields()
	{
		/* put username and passible onto an inaccessible part of the gui */
		loadMapText.setLocation(Game.Xsize + 100, Game.Ysize - 100);
//		worldField.setLocation(Game.Xsize + 100, Game.Ysize - 100);
//		mapX.setLocation(Game.Xsize + 100, Game.Ysize - 100);
//		mapY.setLocation(Game.Xsize + 100, Game.Ysize - 100);
	} /* end killTextFields method */
}
