package DD.MapTool;

public class TestMainTobjs {
	public static void main(String[] args) {
		MapTool mt = new MapTool();
		System.out.println(mt.getMapAtLocation(0, 0));
		Obstacle wall1 = new Obstacle("wall1", null, 5, 5, mt.getMapAtLocation(0, 0));
		mt.getMapAtLocation(0, 0).placeObjects(0, 0, wall1);
		System.out.println(mt.getMapAtLocation(0, 0));
		TempObjects temp1 = new TempObjects("temp1", 5, null, 5, 5, mt.getMapAtLocation(0, 0));
		mt.getMapAtLocation(0, 0).placeTempObject(0, 0, temp1);
		System.out.println(mt.getMapAtLocation(0, 0));
	}
}
