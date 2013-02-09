package DD.MapTool;



public class TestMapMain {
	public static void main(String[] args) {
		
		World world = new World();
		world.WorldName = "TESTME";

		for (int i = 0; i < world.worldSize; i++) {
			for (int j = 0; j < world.worldSize; j++) {
				if(j == world.worldSize-1)
				{
					System.out.println(world.world[i][j].name+"\t");
				}
				else
				{
					System.out.print(world.world[i][j].name+"\t");
				}
			}
		}	
		
		for (int i = 0; i < world.world[0][0].mapSize; i++) {
			for (int j = 0; j < world.world[0][0].mapSize; j++) {
				if(i == 0 || j == 0 || i == 9 || j == 9 || i == 4 || j == 4){
					Obstacle obs = new Obstacle("walll", null, 5, 5, world.world[0][0]);
					world.world[0][0].placeObjects(i, j, obs);
				}
			}
		}
		System.out.println(world.toString());
	}
}
