package DD.GUI;
 
import java.util.Iterator; 

import DD.Character.*; 
import DD.MapTool.*;
import DD.SlickTools.Component;
import DD.SlickTools.ImageRenderComponent;
import DD.SlickTools.RenderComponent;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
 
public class MapTestState extends BasicGameState {
 
	private int stateID = 0;
	private Image floor = null;
	private Image wall = null;
	private Image playerImage = null;
	private Image spriteSheet = null;
	private World world = null;
    private DDCharacter player;
    private CharacterObjects playerObj;
    private String mousePos = " ";
    Input mouse = new Input(650);
    //private float x, y;
    
    
 
    public MapTestState(int stateID)
    {
        this.stateID = stateID;
    }
 
    public int getID() {
    	return stateID;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sb) throws SlickException {
    	spriteSheet = new Image("Images/Test/DungeonCrawl_ProjectUtumnoTileset.png");
    	// get the floor image
        floor = spriteSheet.getSubImage(1185, 416, 33, 34);
        playerImage = spriteSheet.getSubImage(2530, 1440, 33, 34);
        player = new DDCharacter(stateID);      
        //wall = new Image("data/land.jpg");
      
        world = new World("TestGUIMap");
        
        playerObj = new CharacterObjects("Bob", playerImage, 210, 25, world.getMap(0, 0), player); 
        //playerObj.setPosition(6*30, 6*30);
        world.getMap(0, 0).placeObjects(6, 6, playerObj);
        //playerObj.setPosition(6*30, 6*30);
        
    }
 
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
    {
    	int mouseX = mouse.getMouseX();
    	int mouseY = mouse.getMouseY();
    	
    	RenderComponent renderComponent = null;
    	for(int i = 0; i < world.getMap(0, 0).mapSize; i++) {
    		for(int j = 0; j < world.getMap(0, 0).mapSize; j++) {
    			Objects[] list = new Objects[world.getMap(0, 0).objectsStack[i][j].size()];
    			System.arraycopy(world.getMap(0, 0).objectsStack[i][j].toArray(), 0, list, 0, world.getMap(0, 0).objectsStack[i][j].size());
    			for(int k = 0; k < list.length; k++) {
    				Component component = (Component)list[k];
    				if (RenderComponent.class.isInstance(component))
    				{
    					renderComponent = (RenderComponent) component;
    					renderComponent.update(gc, sb, delta);
    				}
    			}
    		}
    	}
		
    	mousePos = "Mouse Position x; " + mouseX + " y: " + mouseY;
		
    }
 
    public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException
    {
    	RenderComponent renderComponent = null;
    	
    
    	/*
    	
    	for(int i = 0; i < world.getMap(0, 0).mapSize; i++) {
    		for(int j = 0; j < world.getMap(0, 0).mapSize; j++) {
    			Iterator iterator = world.getMap(0, 0).objectsStack[i][j].getIterator();
    			while(iterator.hasNext()) {	
    				Component component = (Component)iterator.next();
    				if (RenderComponent.class.isInstance(component))
    				{
    					renderComponent = (RenderComponent) component;
    					renderComponent.render(gc, sb, g);
    				}
    			}
    		}
    	}
    	*/
    	for(int i = 0; i < world.getMap(0, 0).mapSize; i++) {
    		for(int j = 0; j < world.getMap(0, 0).mapSize; j++) {
    			Objects[] list = new Objects[world.getMap(0, 0).objectsStack[i][j].size()];
    			System.arraycopy(world.getMap(0, 0).objectsStack[i][j].toArray(), 0, list, 0, world.getMap(0, 0).objectsStack[i][j].size());
    			for(int k = 0; k < list.length; k++) {
    				Component component = (Component)list[k];
    				if (RenderComponent.class.isInstance(component))
    				{
    					renderComponent = (RenderComponent) component;
    					renderComponent.render(gc, sb, g);
    				}
    			}
    		}
    	}
    	
    	//mouse position
    	g.drawString(mousePos, 900, 0);
 
    }
}