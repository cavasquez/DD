package DD.MapTool;

public class TestMapMain {
	public static void main(String[] args) {
		World world = new World();
		world.WorldName = "TESTME";
		TempObjects to = new TempObjects(10, null, 10, 10, "tempTestObject");
				
		world.world[0][0].placeTempObject(0,0,to);
				
		System.out.println("World Map:	0,0\n"+
						   "Map Location:  	0,0\n"+
						   world.world[0][0].getTempAtLocation(0, 0).toString());
	}
}
