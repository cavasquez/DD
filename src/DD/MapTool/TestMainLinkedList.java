package DD.MapTool;

import DD.CombatSystem.TargetingSystem.Coordinate;

public class TestMainLinkedList {
public static void main(String[] args) {
	MapTool mt = new MapTool();
	Floor flo1 = new Floor("floor1", null, 5, 5, mt.getMapAtLocation(0, 0));
	mt.getMapAtLocation(0, 0).placeObjects(0, 0, flo1);
	Coordinate cord = new Coordinate(0, 0);
	
	
	mt.selectedList.add(cord);
	
	System.out.println(mt.selectedList.getFirst().x);
	System.out.println(mt.selectedList.getFirst().y);
	
	boolean getOut = false;
	for (int i = 0 ; i < mt.selectedList.size(); i++) {
		
		if(mt.selectedList.get(i).x ==0 && mt.selectedList.get(i).y ==0){
			System.out.println(mt.selectedList.remove(i));
			//getOut = true;
			break;
		}
	}
		
	//System.out.println(mt.selectedList.remove(cord));
	//System.out.println(mt.selectedList.getFirst().x);
	//System.out.println(mt.selectedList.getFirst().y);
}
}
