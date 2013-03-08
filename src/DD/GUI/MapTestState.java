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
    float x = 400;
    float y = 300;
    float scale = 1;
 
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
        
        playerObj = new CharacterObjects("Bob", playerImage, 100, 200, world.getMap(0, 0), player); 
        world.getMap(0, 0).placeObjects(6, 6, playerObj);
        
    }
 
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
    {
    	/* go through ArrayList of Components to call their update methods */
		RenderComponent renderComponent = null;
		for (Component component : world.getMap(0, 0).getComponents())
		{
			if (RenderComponent.class.isInstance(component))
			{
				renderComponent = (RenderComponent) component;
				renderComponent.update(gc, sb, delta);
			}
			
		}
		
		
    }
    
    int counter = 0;
 
    public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException
    {
    	RenderComponent renderComponent = null;
 
		for (Component component : world.getMap(0, 0).getComponents())
		{
			if (RenderComponent.class.isInstance(component))
			{
				renderComponent = (RenderComponent) component;
				renderComponent.render(gc, sb, g);
			}
			
		}
	
    
    	
    	
    	for(int i = 0; i < world.getMap(0, 0).mapSize; i++) {
    		for(int j = 0; j < world.getMap(0, 0).mapSize; j++) {
    			//while(world.getMap(0, 0).objectsStack[i][j].size() > 0) {
    			Iterator iterator = world.getMap(0, 0).objectsStack[i][j].getIterator();
    			while(iterator.hasNext()) {	
    				System.out.println("Does this print " + counter);
    				counter++;
    				Component component = (Component)iterator.next();
    				if (RenderComponent.class.isInstance(component))
    				{
    				
    					renderComponent = (RenderComponent) component;
    					renderComponent.render(gc, sb, g);
    				}
    			}
    			
    			//}
    		
    		}
    	}
    	
 
    }
}