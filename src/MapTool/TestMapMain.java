package MapTool;



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
		
		for (int i = 0; i < world.worldSize; i++) {
			for (int j = 0; j < world.worldSize; j++) {
				System.out.println("***********************");
				System.out.println("\t"+world.world[i][j].name);
				System.out.println("***********************");
				System.out.println(world.world[i][j].toString());
			}
		}	
	}
}
