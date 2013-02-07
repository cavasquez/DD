package MapTool;

import java.util.ArrayList;


//implements serializable ~
public class MapTool {
	World world;
	ArrayList<Objects> normList;
	ArrayList<Objects> tempList;
	
	public MapTool(){
		
	}
	
	/* TODO:updateNormList()  and tempList()
	 * 
	 * reads possible items via objects.ser files. add those items to an array that can be scanned each time.
	 * posts those items to the gui.
	 * clone/copy those items when they are selected to be placed.
	 * 
	 *  if a new items is placed.
	 *    add that item to the list of possible premade items.
	 *    
	 *  
	 */
	
	
	
	/*
	 * loads world then maps then objects on the maps using .ser files.
	 */
	public void loadWorld(){
		
	}
	
	public void addNormList(Objects o) {
		// TODO deserialize a file of objects 
		// DO: foreach (Objects:normList.update())
		normList.add(o);
	}
	public void addTempList(Objects o) {
		// TODO Auto-generated method stub
		tempList.add(o);
	}
	
	
	public static void main(String[] args) {
		MapTool mt = new MapTool();

	}

}
