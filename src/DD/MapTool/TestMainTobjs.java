package DD.MapTool;

import org.newdawn.slick.SlickException;

public class TestMainTobjs {
	public static void main(String[] args) throws SlickException {
		MapTool mt = new MapTool();
		System.out.println(mt.getMapAtLocation(0, 0));

		for (int i = 0; i < mt.getMapAtLocation(0, 0).mapSize; i++) {
			for (int j = 0; j < mt.getMapAtLocation(0, 0).mapSize; j++) {
				TempObjects temp1 = new TempObjects("temp1", 5, null, 5, 5, mt.getMapAtLocation(0, 0));
				mt.getMapAtLocation(0, 0).placeTempObject(i, j, temp1);
			}
		}
		
		
		System.out.println(mt.getMapAtLocation(0, 0));
		System.out.println(mt.getMapAtLocation(0, 0).numTempObjects);
		
		System.out.println(mt.getMapAtLocation(0, 0).tempObjects[0][0].toString());
		
		
		for (int i = 0; i < mt.getMapAtLocation(0, 0).mapSize; i++) {
			for (int j = 0; j < mt.getMapAtLocation(0, 0).mapSize; j++) {
				mt.getMapAtLocation(0, 0).remove(i, j);
			}
		}
			
		System.out.println(mt.getMapAtLocation(0, 0).toString());
		
		System.out.println(mt.getMapAtLocation(0, 0).numTempObjects);
	}
}
