package DD.MapTool;

import org.newdawn.slick.SlickException;

import DD.CombatSystem.TargetingSystem.Coordinate;

public class TestMainLinkedList {
public static void main(String[] args) {
	MapTool mt;
	try {
		mt = new MapTool();

	Floor flo1 = new Floor("floor1", null,0,0, 5, 5, mt.getMapAtLocation(0, 0));
	mt.getMapAtLocation(0, 0).placeObjects(0, 0, flo1);
	Coordinate cord = new Coordinate(0, 0);
	
	
	mt.selectedList.addSelectedList(cord);
	
	System.out.println(mt.selectedList.list.getFirst().x);
	System.out.println(mt.selectedList.list.getFirst().y);
	
	boolean getOut = false;
	for (int i = 0 ; i < mt.selectedList.list.size(); i++) {
		
		if(mt.selectedList.list.get(i).x ==0 && mt.selectedList.list.get(i).y ==0){
			System.out.println(mt.selectedList.list.remove(i));
			//getOut = true;
			break;
		}
	}
		
	//System.out.println(mt.selectedList.remove(cord));
	//System.out.println(mt.selectedList.getFirst().x);
	//System.out.println(mt.selectedList.getFirst().y);

	} catch (SlickException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
