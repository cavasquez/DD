package DD.MapTool;

public class TestMainPriority {
	public static void main(String[] args) {
		MapTool mt = new MapTool();
		Map map = mt.getMapAtLocation(0, 0);
		
		
		Floor floor1 = new Floor("floor1", null, 5, 5, mt.getMapAtLocation(0, 0));
		Obstacle wall1 = new Obstacle("wall1", null, 5, 5, mt.getMapAtLocation(0, 0));
		Obstacle wall2 = new Obstacle("wall2", null, 5, 5, mt.getMapAtLocation(0, 0));
		Obstacle wall3 = new Obstacle("wall3", null, 5, 5, mt.getMapAtLocation(0, 0));
		Obstacle wall4 = new Obstacle("wall4", null, 5, 5, mt.getMapAtLocation(0, 0));
		Obstacle wall5 = new Obstacle("wall5", null, 5, 5, mt.getMapAtLocation(0, 0));
		TempObjects temp1 = new TempObjects("temp1", 5, null, 5, 5, mt.getMapAtLocation(0, 0));
		TempObjects temp2 = new TempObjects("temp2", 5, null, 5, 5, mt.getMapAtLocation(0, 0));
		TempObjects temp3 = new TempObjects("temp3", 5, null, 5, 5, mt.getMapAtLocation(0, 0));
		TempObjects temp4 = new TempObjects("temp4", 5, null, 5, 5, mt.getMapAtLocation(0, 0));
		TempObjects temp5 = new TempObjects("temp5", 5, null, 5, 5, mt.getMapAtLocation(0, 0));
		

		//adding various objects/tempObjects to the stack. priority kicking in.
		
		System.out.println(map.getObjectAtLocation(0, 0).toString());
		map.placeObjects(0, 0, floor1); //removes default floor
		System.out.println(map.getObjectAtLocation(0, 0).toString());
		map.placeObjects(0, 0, wall1); //place wall1 on top of stack.
		System.out.println(map.getObjectAtLocation(0, 0).toString());
		map.placeObjects(0, 0, wall2); //removes wall1
		System.out.println(map.getObjectAtLocation(0, 0).toString());
		map.placeObjects(0, 0, temp1); //place temp on top of stack.
		System.out.println(map.getObjectAtLocation(0, 0).toString());
		map.placeObjects(0, 0, wall3); //removes wall2 temp1 is top
		
		//bulk comment from stars to stars to see another test.
		//****************************************************
		Objects[] objTemp = map.objectsStack[0][0].toArray();
		System.out.println("Content of the Stack at 0,0");
		for (int i = 0; i < objTemp.length; i++) {
			System.out.println(objTemp[i].toString());
		}
		
		for (int i = 0; i < objTemp.length; i++) {
			if(objTemp[i] instanceof Obstacle){
				System.out.println("WALL!!!!");
			}
			else if(objTemp[i] instanceof TempObjects){
				System.out.println("TEMP!!!!");
			}
		}
		
		
		//****************************************************
		
//		System.out.println(map.getObjectAtLocation(0, 0).toString());
//		map.placeObjects(0, 0, wall4); //removes wall3 temp1 is top
//		System.out.println(map.getObjectAtLocation(0, 0).toString());
//		map.removeObjects(0, 0); //removes temp1 wall4 is back
//		System.out.println(map.getObjectAtLocation(0, 0).toString());
//		map.removeObjects(0, 0); //removes wall4 floor1 is back
//		System.out.println(map.getObjectAtLocation(0, 0).toString());		
	}
}
