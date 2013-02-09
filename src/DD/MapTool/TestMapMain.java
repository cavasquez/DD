package DD.MapTool;

public class TestMapMain {
	public static void main(String[] args) {
		
		World world = new World();
		world.WorldName = "TESTME";
		
		for (int i = 0; i < world.world[0][0].mapSize; i++) {
			for (int j = 0; j < world.world[0][0].mapSize; j++) {
				if(i == 0 || j == 0 || i == 10 || j == 10 || i == 5 || j == 5){
					Obstacle obs = new Obstacle("1 " , null, 5, 5, world.world[0][0]);
					world.world[0][0].placeObjects(i, j, obs);
				}
				else{
						TempObjects temp = new TempObjects("2 ", 5,null,5,5,world.world[0][0]);
						world.world[0][0].placeTempObject(i, j, temp);
					}
				}
			}
		
		for (int i = 0; i < world.world[0][0].mapSize; i++) {
			for (int j = 0; j < world.world[0][0].mapSize; j++) {
				if(i>0&&i<5 && j>0&&j<5)
				world.world[0][0].removeTempObjects(i, j);
			}
		}
		Obstacle obs = new Obstacle("3 " , null, 5, 5, world.world[0][0]);
		world.world[0][0].massPlaceObjectsLine(0, 0, 0, 5, obs);
		
		world.world[0][0].updateComponentList();
		
		System.out.println(world.toString());
	}
}
