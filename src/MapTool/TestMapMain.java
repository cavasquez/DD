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
		
		int t=0;
		
		for (int i = 0; i < world.getMap(t, t).mapSize ; i++) {
			for (int j = 0; j < world.getMap(t, t).mapSize; j++) {
				if(j == world.getMap(t, t).mapSize -1)
				{
					System.out.println(world.getMap(t, t).objects[i][j].name);
				}
				else
				{
					System.out.print(world.getMap(t, t).objects[i][j].name);
				}
			}
		}
		
		//world.world[t][t].addComponent(component);
	}
}
