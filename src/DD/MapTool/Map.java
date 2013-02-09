package DD.MapTool;
/*
 * @author GM-Michael VanWie
 */

import java.util.ArrayList;
import java.util.Stack;

import org.newdawn.slick.Image;

import DD.Component;
import DD.Entity;

public class Map extends Entity{
	Stack<Objects>[][] objectsStack ;
	TempObjects[][] tempObjects;
	int numTempObjects;
	Image defImage;
	boolean hasTempObjects;
	String name;
	final int mapSize = 10;

	public Map(String name){
		
		super(0);
		this.name = name;
		objectsStack =  new Stack[mapSize][mapSize];
		tempObjects = new TempObjects[mapSize][mapSize];
		super.components = new ArrayList<Component>();
		
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				
				Floor floor = new Floor("floor",null,5,5,this);
				super.addComponent(floor);
				objectsStack[i][j] =  new Stack();
				objectsStack[i][j].push(floor);
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
						removeTempObjects(i,j);
					}
				}
			}
		}
	}
	
	
	public Objects getObjectAtLocation(int x, int y){
		return objectsStack[x][y].peek();
	}
	
	public TempObjects getTempAtLocation(int x, int y){
		return tempObjects[x][y];
	}

	/*
	 * assigns the tempObjects passed in to the specified location.
	 */
	public void placeTempObject(int x,int y,TempObjects temp) {
		if(tempObjects[x][y]!=null){
			/*
			 * TODO: tell the user that they must remove the temp 
			 * 		 object in the location they selected before they
			 * 		 can place a new temp object.
			 * 
			 * 		 ??ask the user if they want to remove. if yes then
			 * 		 call removeTempObjects(x,y), then call place(x,y,temp)
			 * 
			 * 		reasoning: cant have 2 temp objects in the same location.
			 * 
			 * 		ill implement the non gui part...
			 */
			
				//GUI message: Would you like to replace the temp object currently
				//			   in that location?
				//if yes DO:
				removeTempObjects(x, y);
				placeObjects(x, y, temp);
				//else ask them if they want to place that object in a diferent location?
				//if yes
				//placeObjects(NewUserInputX, NewUserInputY, obj);	
		}
		else{
			tempObjects[x][y] = temp;
			numTempObjects++;
			super.components.add(temp);
			setHasTempObjects(true);
		}
	}
	
	public void removeTempObjects(int x, int y) {
		tempObjects[x][y] = null;
		numTempObjects--;
		if(numTempObjects==0)
			setHasTempObjects(false);
	}
			
	public void placeObjects(int x,int y,Objects obj) {
		super.components.add(obj);
		objectsStack[x][y].push(obj);
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

	public String toString(){
		String t ="";
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if(j == mapSize-1){
					t += objectsStack[i][j].peek().name +"\n";
				}
				else{
					t += objectsStack[i][j].peek().name;
				}
			}
		}
		return t;
	}
	
}
