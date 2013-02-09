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
					if(i==1 && j==1){
						TempObjects temp = new TempObjects("tempTestRemove", 5,null,5,5,world.world[0][0]);
						world.world[0][0].placeObjects(i, j, temp);
					}
					else{
						TempObjects temp = new TempObjects("2 ", 5,null,5,5,world.world[0][0]);
						world.world[0][0].placeObjects(i, j, temp);
					}
				}
			}
		}
		
		
		
		for (int i = 0; i < world.world[0][0].mapSize; i++) {
			for (int j = 0; j < world.world[0][0].mapSize; j++) {
				if(i>0&&i<5 && j>0&&j<5)
				world.world[0][0].removeTempObjects(i, j, world.world[0][0].getTempAtLocation(i, j));
			}
		}
		
		
				
		
		world.world[0][0].updateComponentList();
		
		System.out.println(world.world[0][0].toString());
		System.out.println(world.world[0][0].getComponents().toString());
		
		
		
		System.out.println(world.toString());
	}
}
