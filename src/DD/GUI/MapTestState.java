package DD.GUI;

import DD.Character.*;
import DD.MapTool.*;
import DD.SlickTools.Component;
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
    Image floor = null;
    Image wall = null;
    Image spriteSheet = null;
    World world = null;
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
        //wall = new Image("data/land.jpg");
      
        world = new World("TestGUIMap");
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
 
    }
}