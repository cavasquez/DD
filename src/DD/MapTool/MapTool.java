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

import DD.Character.DDCharacter;
import DD.CombatSystem.TargetingSystem.Coordinate;


//implements serializable ~
public class MapTool implements Serializable{
	private static final long serialVersionUID = 5788724726419120129L;
	World world;
	ArrayList<Objects> normList;
	ArrayList<Objects> tempList;
	SelectList selectedList;
	Map currentMap;
	String userPath =  System.getProperties().getProperty("user.home");
	
	String ddPath = "C:/Program Files (x86)/DD/";
	//TODO:fix
	//String gamePath = userPath+"/Documents/DD"
	
	public MapTool() throws SlickException {
		world = new World("world");
		currentMap = world.getMap(0, 0);
		normList = new ArrayList<Objects>();
		tempList = new ArrayList<Objects>();
		selectedList = new SelectList(currentMap);  
	}
		
	
	public World getWorld(){
		return world;
	}
	/*
	 * loads world from a .ser and sets that world to the this.world
	 */
	public void loadWorld(String name){
		World e = null;
	      try
	      {
	         FileInputStream fileIn =
	                          new FileInputStream(ddPath+"/"+name+"/"+name+".ser");
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
	      
	      for (int i = 0; i < world.worldSize; i++) {
			for (int j = 0; j < world.worldSize; j++) {
				if(world.getMap(i,j).serMapHelper.size()!=0){
					for (SerMapCharHelper serHelper : world.getMap(i, j).serMapHelper) {
						System.out.println("im here"+ i + j);
						DDCharacter temp = new DDCharacter(i*j);
						temp.setCharacterSheet(serHelper.cs);
						 CharacterObjects ddChar;
						try {
							ddChar = new CharacterObjects(temp.getCharacterSheet().getName(), null, getMapAtLocation(i, j), temp);
							 world.getMap(i, j).place(serHelper.coord.x, serHelper.coord.y, ddChar);
						} catch (SlickException e1) {
						
							e1.printStackTrace();
						} 
					}
				}
			}
		} 
	 }
	
	public Map getCurrentMap() {
		return currentMap;
	}
	
	public void setCurrentMap(int x, int y){
		currentMap = world.getMap(x, y);
	}
	
	public Map getMapAtLocation(int x,int y){
		return world.world[x][y];
	}
	
	public SelectList getSelectedList() {
		return selectedList;
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
