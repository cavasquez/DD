package DD.MapTool;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import DD.SlickTools.*;

public class World implements Serializable{
	private static final long serialVersionUID = 2853246324745729078L;
	Map[][] world;
	String worldName;
	int worldSize = 5;
	//normList is an Array of all Possible Objects?
	ArrayList<Objects> normList;
	ArrayList<Objects> tempList;//holds temp items like from spells
	
//	String userPath =  System.getProperties().getProperty("user.home");
	String path = "C:/Program Files (x86)/DD/";
		
	public World(String name) throws SlickException {
		this.worldName = name;
		normList = new ArrayList<Objects>();
		tempList = new ArrayList<Objects>();
		world = new Map[worldSize][worldSize];
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world.length; j++) {
				world[i][j] = new Map("map"+(j+i*worldSize)); 
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
	
	public void writeMe(){
		System.out.println("1");
		File theDir = new File(path +worldName);
		System.out.println("2");
		// if it doesn't exist, make a directory with the name of the world
		if (!theDir.exists())
		{
			System.out.println("3");
			theDir.mkdir();  
			System.out.println("dir? " + theDir.toString());
			System.out.println("4");
		}
		System.out.println("5");
//		for (int k = 0; k <worldSize; k++) {
//			for (int k2 = 0; k2 <worldSize; k2++) {
//				world[k][k2].writeMe(world[k][k2].name, path+"/"+worldName+"/");
//			}
//		}
		
		
		try{
			FileOutputStream fileOut = new FileOutputStream(path+"/"+worldName+"/"+worldName+".ser");
			ObjectOutputStream out =  new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	public Floor readFloor(String name){
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
	
	public Obstacle readObstacle(String name){
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
	
	
	public Map readMap(String name){
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
