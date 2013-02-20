package DD.MapTool;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import DD.SlickTools.*;

public class World implements Serializable{
	private static final long serialVersionUID = 2853246324745729078L;
	Map[][] world;
	String worldName;
	int worldSize = 5;
	//normList is an Array of all Possible Objects?
	ArrayList<Objects> normList;
	ArrayList<Objects> tempList;//holds temp items like from spells
	
	public World(String name){
		this.worldName = name;
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
	
	/*
	 * returns the map specified by input coordinates.
	 */
	public Map getMap(int x, int y){
		return world[x][y];
	}
	
	public void setWorldName(String name){
		this.worldName = name;
	}
	
	public void loadMap(int x, int y, Map mapIn){
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
	
	public void writeMe(String path){
		try{
			FileOutputStream fileOut = new FileOutputStream(path+worldName+".ser");
			ObjectOutputStream out =  new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	public Floor readFloor(String name, String path){
		Floor e = null;
	      try
	      {
	         FileInputStream fileIn =
	                          new FileInputStream(path+name);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (Floor) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         
	      }catch(ClassNotFoundException c)
	      {
	         c.printStackTrace();
	         
	      }
	      return e;
	 }
	
	public Obstacle readObstacle(String name, String path){
		Obstacle e = null;
	      try
	      {
	         FileInputStream fileIn =
	                          new FileInputStream(path+name);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (Obstacle) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         
	      }catch(ClassNotFoundException c)
	      {
	         c.printStackTrace();
	         
	      }
	      return e;
	 }
	
	
	public Map readMap(String name, String path){
		Map e = null;
	      try
	      {
	         FileInputStream fileIn =
	                          new FileInputStream(path+name);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (Map) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         
	      }catch(ClassNotFoundException c)
	      {
	         c.printStackTrace();
	         
	      }
	      return e;
	 }
	
	public String toString(){
		String t = worldName+"\n";
		for (int i = 0; i < worldSize; i++) {
			for (int j = 0; j < worldSize; j++) {
				if(j == worldSize-1)
				{
					t += world[i][j].name+"\n";
				}
				else
				{
					t += world[i][j].name+"\t";
				}
			}
		}
		
		t += "\n";
		
		for (int i = 0; i < worldSize; i++) {
			for (int j = 0; j < worldSize; j++) {
				t += "***********************\n";
				t += "\t"+world[i][j].name+"\n";
				t += "***********************\n";
				t += world[i][j].toString();
			}
		}
		return t;
	}
	
}
