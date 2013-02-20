package DD.MapTool;

public class TestSerMain {
	public static void main(String[] args) {
		
		World world = new World();		
		
		world.world[0][0] = world.readMap("map0.ser");
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
				
	}
}