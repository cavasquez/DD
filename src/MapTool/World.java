package MapTool;
import java.io.Serializable;
import java.util.ArrayList;

import DD.Entity;

public class World extends Entity implements Serializable{
	private static final long serialVersionUID = 1L;
	Map[][] world;
	String WorldName;
	int worldSize = 5;
	//normList is an Array of all Possible Objects?
	ArrayList<Objects> normList;
	ArrayList<Objects> tempList;//holds temp items like from spells
	
	public World(){
		
		
		super(0);
		normList = new ArrayList<Objects>();
		tempList = new ArrayList<Objects>();
		world = new Map[worldSize][worldSize];
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world.length; j++) {
				world[i][j] = new Map("map"+(j+i*worldSize)); 
				/*
				 * 
				 * files will be saved as id.ser  ie "map0.ser"
				 * Lables map0-24
				 * map0 map1 map2 map3 map4 
				 * map5 map6 map7 map8 map9 
				 * 	  .	   .	. 	.	  .
				 *    .
				 *    .
				 */
			}
		}
	}
	
	public void LoadMap(int x, int y, Map mapIn){
		/*
		 * TODO:
		 * parse a text file to make a map
		 * use map default
		 */
		world[x][y] = mapIn;
	}
	
	public void removeMap(int x, int y){
		world[x][y] = null;
	}
	
	public void saveWorld(){

	}
	
	public World loadWorld(){
		return null;	
	}

	public void addNormList(Objects o) {
		// TODO Auto-generated method stub
		normList.add(o);
	}
	public void addTempList(Objects o) {
		// TODO Auto-generated method stub
		tempList.add(o);
	}
}
