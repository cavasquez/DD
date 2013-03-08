package DD.MapTool;

import org.newdawn.slick.SlickException;

public class TestSerMain {
	public static void main(String[] args) throws SlickException {
		String path = "C:/Users/mike/Desktop/save/";
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
		//START PHASE1
		//***************************************************
//		mt.world.writeMe(path);
//		Obstacle wall1 = new Obstacle("wall1", null, 5, 5, null);
//
//		for (int i = 0; i < mt.world.worldSize; i++) {
//			for (int j = 0; j < mt.world.worldSize; j++) {
//				for (int j2 = 0; j2 < mt.getMapAtLocation(i, j).mapSize; j2++) {
//					for (int k = 0; k < mt.getMapAtLocation(i, j).mapSize; k++) {
//						mt.getMapAtLocation(i, j).placeObjects(j2, k, wall1);
//						mt.getMapAtLocation(i, j).getObjectAtLocation(j2, k).setOwnerEntity(mt.getMapAtLocation(i, j));
//					}
//				}
//			}
//		}
//		
//		mt.world.setWorldName("Norrath");
//		//path/Norrath/Norrath.ser
//		mt.world.writeMe(path);
		//***************************************************
		//END PHASE1
		//***************************************************
		
		
		//***************************************************
		//START PHASE2
		//***************************************************
		mt.loadWorld("Norrath", path);
		System.out.println("Norrath map0 and map24");
		System.out.println(mt.getMapAtLocation(0, 0));
		System.out.println(mt.getMapAtLocation(mt.world.worldSize-1, mt.world.worldSize-1));
		
		//load original world again
		mt.loadWorld("world", path);
		System.out.println("World map0 and map24");
		System.out.println(mt.getMapAtLocation(0, 0));
		System.out.println(mt.getMapAtLocation(mt.world.worldSize-1, mt.world.worldSize-1));
		//***************************************************
		//END PHASE2
		//***************************************************
		
		
	}
	
}