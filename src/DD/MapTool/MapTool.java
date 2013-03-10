package DD.MapTool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import org.newdawn.slick.SlickException;

import DD.CombatSystem.TargetingSystem.Coordinate;


//implements serializable ~
public class MapTool implements Serializable{
	private static final long serialVersionUID = 5788724726419120129L;
	World world;
	ArrayList<Objects> normList;
	ArrayList<Objects> tempList;
	LinkedList<Coordinate> selectedList;
	
	public MapTool() throws SlickException {
		world = new World("world");
		normList = new ArrayList<Objects>();
		tempList = new ArrayList<Objects>();
		selectedList = new LinkedList<Coordinate>();
	}
	

	public void addSelectedList(Coordinate coord){
		if(selectedList.contains(coord)){
		}
		else
			selectedList.add(coord);
	}
	
	public void placeSelectedList(Objects obj, Map map){
		for (Coordinate coord : selectedList) {
			map.place(coord.x, coord.y, obj);
		}
	}
	
	public void removeSelectedList(Coordinate coord){
		for (int i = 0; i < selectedList.size(); i++) {
			if(selectedList.get(i).x == coord.x && selectedList.get(i).y == coord.y){
				selectedList.remove(i);
			}
		}
	}
	
	public void clearSelectedList(){
		selectedList.clear();
	}
	
	public String selectedToString(){
		String t = "";
		for (Coordinate coord : selectedList) {
			t+= coord.x + ","+coord.y+"\n";
		}
		return t;
	}
	
	/*
	 * loads world from a .ser and sets that world to the this.world
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
