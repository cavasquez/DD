package DD.MapTool;

public class TestSerMain {
	public static void main(String[] args) {
		/*
		 * IGNORE THIS CONTENT OF THIS MAIN IT'S NOT WORKING
		 * 
		 * IMPORTANT: to see this working edit this path to a new folder you create on you Decktop.
		 * Run this main once to create the .ser files.
		 * comment out the map.writeMe() below. the lines are marked.
		 */
		String path = "C:/Users/mike/Desktop/save/";
		
		//make a map tool and a world.
		MapTool mt = new MapTool();
		
		
		Obstacle wall1 = new Obstacle("wall1", null, 5, 5, null);

		

		for (int i = 0; i < mt.world.world[0][0].mapSize; i++) {
			for (int j = 0; j < mt.world.world[0][0].mapSize; j++) {
				mt.world.world[0][0].placeObjects(i, j, wall1);
				mt.world.world[0][0].getObjectAtLocation(i, j).setOwnerEntity(mt.world.world[0][0]);
			}
		}
		for (int k = 0; k < mt.world.worldSize; k++) {
			for (int k2 = 0; k2 < mt.world.worldSize; k2++) {
				mt.world.world[k][k2].writeMe(mt.world.world[k][k2].name, path);
			}
		}
	   
		
		
		/*
		
		///*use bulk comment on all lines between this line and when u see 
		Obstacle wall1 = new Obstacle("wall1", null, 5, 5, world.world[0][0]);
		wall1.writeMe("wall1.ser",path);		
		
		
		
		//changing a map to save for later.
		for (int i = 0; i < world.world[0][0].mapSize; i++) {
			for (int j = 0; j < world.world[0][0].mapSize; j++) {
				if(i == j && world.world[0][0].objectsStack.length!=1){
					world.world[0][0].placeObjects(i,j,wall1);
				}
				else if(i+j==9 && world.world[0][0].objectsStack.length!=1){
					world.world[0][0].placeObjects(i,j,wall1);
				}
			}
		}
		
		
		
		world.world[0][0] = world.readMap("map0.ser", path);
		System.out.println(world.world[0][0].toString());
		
		for (int i = 0; i < world.world[0][0].mapSize; i++) {
			for (int j = 0; j < world.world[0][0].mapSize; j++) {
				if(i == j && world.world[0][0].objectsStack.length!=1){
					world.world[0][0].removeObjects(i,j);
				}
				else if(i+j==9 && world.world[0][0].objectsStack.length!=1){
					world.world[0][0].removeObjects(i,j);
				}
					
			}
		}
		
		System.out.println(world.world[0][0].toString());
		Obstacle wall2 = world.readObstacle("wall1.ser",path);
		
		for (int i = 0; i < world.world[0][0].mapSize; i++) {
			for (int j = 0; j < world.world[0][0].mapSize; j++) {
				if(i == j && world.world[0][0].objectsStack.length!=1){
					world.world[0][0].placeObjects(i,j,wall2);
				}
				else if(i+j==9 && world.world[0][0].objectsStack.length!=1){
					world.world[0][0].placeObjects(i,j,wall2);
				}
					
			}
		}
	
		System.out.println(world.world[0][0].toString());
		*/		
	}
	
}