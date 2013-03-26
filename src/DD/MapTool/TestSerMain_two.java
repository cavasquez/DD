package DD.MapTool;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;



public class TestSerMain_two extends BasicGame
{
	public TestSerMain_two(String title) {
		super(title);

	}
 
    public TestSerMain_two()
    {
        super("Slick2DPath2Glory - SimpleGame");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
    	String path = "C:/Users/Jin/Desktop/save/";
		/*
		 * IMPORTANT: to see this working edit the path above to a new folder you create on you Desktop.
		 * Run this main once to create the .ser files.
		 * Use ctrl+shift+c to bulk comment all of phase 1
		 * un bulk comment all of phase 2 using ctrl+shift+c
		 * run this main again.
		 * Notice that Norrath after running the second time Norrath is loaded and s.o.p to your console
		 * then the original world is loaded. 
		 */
		
		
		//make a map tool and a world.
		MapTool mt = new MapTool();
		
		//***************************************************
		//START PHASE1 - write the world
		//***************************************************
		mt.world.writeMe(path);
		Obstacle wall1 = new Obstacle("wall1", null, 5, 5, null);

		for (int i = 0; i < mt.world.worldSize; i++) {
			for (int j = 0; j < mt.world.worldSize; j++) {
				for (int j2 = 0; j2 < mt.getMapAtLocation(i, j).mapSize; j2++) {
					for (int k = 0; k < mt.getMapAtLocation(i, j).mapSize; k++) {
						mt.getMapAtLocation(i, j).placeObjects(j2, k, wall1);
						mt.getMapAtLocation(i, j).getObjectAtLocation(j2, k).setOwnerEntity(mt.getMapAtLocation(i, j));
					}
				}
			}
		}
		
		mt.world.setWorldName("Norrath");
		//path/Norrath/Norrath.ser
		mt.world.writeMe(path);
		//***************************************************
		//END PHASE1
		//***************************************************
		
		
		//***************************************************
		//START PHASE2 - read the world
		//***************************************************
//		mt.loadWorld("Norrath", path);
//		System.out.println("Norrath map0 and map24");
//		System.out.println(mt.getMapAtLocation(0, 0));
//		System.out.println(mt.getMapAtLocation(mt.world.worldSize-1, mt.world.worldSize-1));
//		
//		//load original world again
//		mt.loadWorld("world", path);
//		System.out.println("World map0 and map24");
//		System.out.println(mt.getMapAtLocation(0, 0));
//		System.out.println(mt.getMapAtLocation(mt.world.worldSize-1, mt.world.worldSize-1));
		//***************************************************
		//END PHASE2
		//***************************************************
		
		
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {

    }
 
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {

    }
 
    public static void main(String[] args) 
			throws SlickException
    {
    	AppGameContainer app = 
			new AppGameContainer(new TestSerMain_two());
 
         //app.setDisplayMode(800, 600, false);
         app.start();
    }
    
    
}
