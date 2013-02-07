package MapTool;

public class TestMapMain {
	public static void main(String[] args) {
		
		World world = new World();
		MapTool maptool= new MapTool();
		world.WorldName = "TESTME";
		//@brandon
		//Need a test image
	
		 
		for (int i = 0; i < world.world[0][0].mapSize; i++) {
			
			for (int j = 0; j < 50; j++) {
				if(j==49)
				{
					System.out.println(world.world[0][0].objects[i][j].name + " ");
				}
				else
				{
					System.out.print(world.world[0][0].objects[i][j].name + " ");
				}
			}
			
		}
		 
		
		
	}

}
