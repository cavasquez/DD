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
import DD.SlickTools.*;


//implements serializable ~
public class MapTool implements Serializable {
	private static final long serialVersionUID = 5788724726419120129L;
	public World world;
	ArrayList<Objects> normList;
	ArrayList<Objects> tempList;
	public SelectList selectedList;
	Map currentMap;
	
	public static final String ddPath = "world/";
	//TODO:fix
	//String gamePath = userPath+"/Documents/DD"

	public MapTool() throws SlickException {
		world = new World("world");
		currentMap = world.getMap(0, 0);
		normList = new ArrayList<Objects>();
		tempList = new ArrayList<Objects>();
		selectedList = new SelectList(currentMap);
	}

	public World getWorld() {
		return world;
	}

	/*
	 * loads world from a .ser and sets that world to the this.world
	 */
	public World loadWorld(String name, boolean loadChar) {
		World world = null;
		try {
			FileInputStream fileIn = new FileInputStream(ddPath + name
					+ "/" + name + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			world = (World) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();

		} catch (ClassNotFoundException c) {
			c.printStackTrace();

		}
//		this.world = e;
		System.out.println(world.getMap(0, 0).toString());
		System.out.println("size" + world.getWorldSize());
		System.out.println("ser " + world.getMap(0, 0).serMapHelper.size());
		for (int i = 0; i < world.getWorldSize(); i++) {
			for (int j = 0; j < world.getWorldSize(); j++) {
				if (world.getMap(i, j).serMapHelper.size() != 0) {
					for (SerMapCharHelper serHelper : world.getMap(i, j).serMapHelper) {
						System.out.println("im here" + i + j);
						DDCharacter temp = new DDCharacter(i * j);
						temp.setCharacterSheet(serHelper.cs);
						System.out.println("cs image " +serHelper.cs.getImage());
						CharacterObjects ddChar;
						try {
							System.out.println("*******************************************");
							ddChar = new CharacterObjects(temp
									.getCharacterSheet().getName(), serHelper.cs.getImage(),
									getMapAtLocation(i, j), temp);
							world.getMap(i, j).place(serHelper.coord.x,
									serHelper.coord.y, ddChar);
							
						} catch (SlickException e1) {

							e1.printStackTrace();
						}
					}
				}
			}
		}
		for (int i = 0; i < world.getWorldSize(); i++) {
			for (int j = 0; j < world.getWorldSize(); j++) {
				for (int k = 0; k < world.getMap(i, j).mapSize; k++) {
					for (int l = 0; l < world.getMap(i, j).mapSize; l++) {
						Objects[] list = new Objects[world.getMap(i, j).objectsStack[k][l].size()];
						System.arraycopy(world.getMap(i, j).objectsStack[k][l].toArray(),0, list, 0,world.getMap(i, j).objectsStack[k][l].size());
						for (int m = 0; m < list.length; m++) {
							try {
								if(list[m] instanceof Floor){
									if(list[m] instanceof Grass){
										Grass grass = new Grass("grass", k, l, 5, 5, world.getMap(i, j));
										world.getMap(i, j).place(k,l ,grass);
									}
									else{
										Floor flo = new Floor("floor",k,l,5,5,world.getMap(i, j));	
										world.getMap(i, j).place(k,l ,flo);
									}
								}
								if(list[m] instanceof Wall){
									Wall wall =  new Wall("wall", world.getMap(i, j));
									world.getMap(i, j).place(k, l, wall);
								}
								if(list[m] instanceof CharacterObjects)
								{
									if(loadChar)
									{
										DDCharacter ddc = ((CharacterObjects) list[m]).getDdchar();
										CharacterObjects co = new CharacterObjects(ddc.getSheet().getName(), ddc.getSheet().getImage(), world.getMap(i, j), ddc);
										world.getMap(i, j).place(k, l, co);
									}
									else
									{
										DDCharacter ddc = ((CharacterObjects) list[m]).getDdchar();
										world.getMap(i, j).serMapHelper.add(new SerMapCharHelper(new Coordinate(k,l), ddc.getSheet()));
									}
								}
							}
							catch (SlickException e1) {
									e1.printStackTrace();
							}
						}
					}
				}
			}
		}
		this.world = world;
		return world;
	}

	public Map getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(int x, int y) {
		currentMap = world.getMap(x, y);
	}

	public Map getMapAtLocation(int x, int y) {
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
