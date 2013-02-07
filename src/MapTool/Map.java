package MapTool;
/*
 * @author GM-Michael VanWie
 */

import org.newdawn.slick.Image;

import DD.Entity;

public class Map extends Entity{
	
	//protected String id;
	Objects[][] objects;
	TempObjects[][] tempObjects;
	int numTempObjects;
	Image defImage;
	boolean hasTempObjects;
	String name;
	final int mapSize = 50;
	
	
	
	
	public Map(String name){
		
		
		super(0);
		this.name = name;
		objects = new Objects[mapSize][mapSize];
		tempObjects = new TempObjects[mapSize][mapSize];
		for (int i = 0; i < objects.length; i++) {
			for (int j = 0; j < objects.length; j++) {
				objects[i][j] = new Floor("floor"+j*(i+5), null , 5, 5,this);
			}
		}
		for (int i = 0; i < tempObjects.length; i++) {
			for (int j = 0; j < tempObjects.length; j++) {
				tempObjects[i][j]= null;
			}
		}
				
		numTempObjects=0;
		defImage = null;
		hasTempObjects = false;
	}
	/*
	 * after each player turn, decrement each temp object turn count.
	 * 	if turn count == 0 after decremented, remove that temp item.
	 */
	public void tempObjectsUpdate(){
		for (int i = 0; i < tempObjects.length; i++) {
			for (int j = 0; j < tempObjects.length; j++) {
				if(tempObjects[i][j]!=null){
					tempObjects[i][j].turnCount--;
					if(tempObjects[i][j].turnCount == 0){
						numTempObjects--;
						removeTempObjects(i,j);
					}
				}
			}
		}
	}
	

	
	public Objects getObjectAtLocation(int x, int y){
		return objects[x][y];
	}
	
	public TempObjects getTempAtLocation(int x, int y){
		return tempObjects[x][y];
	}

	public void placeTempObject(int x,int y,TempObjects temp) {
		this.tempObjects[x][y] = temp;
		numTempObjects++;
		//map.addComp(temp);
		setHasTempObjects(true);
	}
	
	public void removeTempObjects(int x, int y) {
		tempObjects[x][y] = null;		
	}
			
	public void placeObjects(int x,int y,Objects obj) {
		this.objects[x][y] = obj;
	}

	
	//needed for gui
	public String getName() {
		return name;
	}

	//GM should be able to name the map at will.
	public void setName(String name) {
		this.name = name;
	}

	public Image getDefImage() {
		return defImage;
	}

	public void setDefImage(Image defImage) {
		this.defImage = defImage;
	}

	public boolean isHasTempObjects() {
		return hasTempObjects;
	}

	public void setHasTempObjects(boolean hasTempObjects) {
		this.hasTempObjects = hasTempObjects;
	}

	public void setObjects(Objects[][] objects) {
		this.objects = objects;
	}
	public String toString(){
		String t ="";
		for (int i = 0; i < objects.length; i++) {
			for (int j = 0; j < objects.length; j++) {
				if(j == objects.length-1)
				t += objects[i][j].name;
			
			else
				t += objects[i][j].name;
			}
		}
		return t;
	}
	
}
