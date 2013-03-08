 package DD.MapTool;

import org.newdawn.slick.SlickException;

import DD.Character.DDCharacter;

public class TestMapMain {
	public static void main(String[] args) throws SlickException {
		
		/*
		 * IMPORTANT: for this main to work, mapSize needs to be 21 or larger due to hardcodes massPlaceObjectsLine values.
		 * other wise you will get nullpointers.
		 */
		
		
		World world = new World("TESTME");
	
		/*
		 * proof of concept movement backend
		 * the Map is implemented using a "priority stack"
		 */
		
		System.out.println("****************");
		System.out.println("World Map - map0");
		System.out.println("****************");
		System.out.println(world.world[0][0].toString());
		
		System.out.println("****************************************************");
		System.out.println("MassPlaceObjectsLine() --place some walls on my map0");
		System.out.println("****************************************************");
		Obstacle wall = new Obstacle("walll", null, 5, 5, world.world[0][0]);
		world.world[0][0].massPlaceObjectsLine(0, 0, 10, 0, wall);
		world.world[0][0].massPlaceObjectsLine(0, 1, 0, 20, wall);
		world.world[0][0].massPlaceObjectsLine(1, 10, 10, 10, wall);
		world.world[0][0].massPlaceObjectsLine(10, 9, 10, 1, wall);
		world.world[0][0].massPlaceObjectsLine(1, 20, 10, 20, wall);
		world.world[0][0].massPlaceObjectsLine(10, 11, 10, 19, wall);
		System.out.println(world.world[0][0].toString());
		
		System.out.println("********************************");
		System.out.println("Remove some walls to make a door  vvvv");
		System.out.println("********************************");
		world.world[0][0].removeObjects(6, 0);
		world.world[0][0].removeObjects(7, 0);
		world.world[0][0].removeObjects(6, 10);
		world.world[0][0].removeObjects(7, 10);
		System.out.println(world.world[0][0].toString());
				
		DDCharacter ddchar = new DDCharacter(0);
		CharacterObjects charObj = new CharacterObjects("*****", null,0,0, world.world[0][0], ddchar);
		System.out.println("****************************************************");
		System.out.println("Place a Character and start moving it around the map");
		System.out.println("placeObjects(x, y, characterObjects)");
		System.out.println("removeObjects(x, y)           vvvvv");
		System.out.println("******************************     ******************");
		
		
		System.out.println("*********************");
		System.out.println("Movement down the map");
		System.out.println("*********************");
		for (int j = 0; j < 5; j++) {
				world.world[0][0].placeObjects(6, j, charObj);
				System.out.println(world.world[0][0].toString());
				world.world[0][0].removeObjects(6,j);
			}
		
	}
}
