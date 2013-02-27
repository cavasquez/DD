package DD.MapTool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import DD.ActionBox.CombatSystem.TargetingSystem.Coordinate;


//implements serializable ~
public class MapTool implements Serializable{
	private static final long serialVersionUID = 5788724726419120129L;
	World world;
	ArrayList<Objects> normList;
	ArrayList<Objects> tempList;
	LinkedList<Coordinate> selectedList;
	
	public MapTool(){
		world = new World("world");
		normList = new ArrayList<Objects>();
		tempList = new ArrayList<Objects>();
		selectedList = new LinkedList<Coordinate>();
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
	public void loadWorld(String name, String path){
		World e = null;
	      try
	      {
	         FileInputStream fileIn =
	                          new FileInputStream(path+"/"+name+"/"+name+".ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (World) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         
	      }catch(ClassNotFoundException c)
	      {
	         c.printStackTrace();
	         
	      }
	      this.world = e;
	 }
	
	public Map getMapAtLocation(int x,int y){
		return world.world[x][y];
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
}
