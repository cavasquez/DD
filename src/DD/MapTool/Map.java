package DD.MapTool;
/*
 * @author GM-Michael VanWie
 */

import java.util.ArrayList; 
import java.util.Stack;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import DD.SlickTools.*;

public class Map extends Entity{
	Stack<Objects>[][] objectsStack ;
	TempObjects[][] tempObjects;
	int numTempObjects;
	Image defImage;
	boolean hasTempObjects;
	String name;
	final int mapSize = 11;

	public Map(String name){
		
		super(0, null);
		this.name = name;
		objectsStack =  new Stack[mapSize][mapSize];
		tempObjects = new TempObjects[mapSize][mapSize];
		super.components = new ArrayList<Component>();
		
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				//needs image                    VVV
				Floor floor = new Floor("floor",null,5,5,this);
				
				addComponent(floor);
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
						System.out.println("removing object");
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
	
	public void updateComponentList(){
		components.clear();
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				components.add(objectsStack[i][j].peek());
			}
		}
	}
	
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
			 * 		 reasoning: cant have 2 temp objects in the same location.
			 */	
		}
		else{
			tempObjects[x][y] = temp;
			numTempObjects++;
			setHasTempObjects(true);
			objectsStack[x][y].push(temp);
			updateComponentList();
		}
	}

	public void removeTempObjects(int x, int y) {
		tempObjects[x][y] = null;
		numTempObjects--;
		objectsStack[x][y].pop();
		updateComponentList();
		if(numTempObjects==0)
			setHasTempObjects(false);
	}
	
	//places single object on x,y
	public void placeObjects(int x,int y,Objects obj) {
		super.components.add(obj);
		objectsStack[x][y].push(obj);
		updateComponentList();
	}
	
	/*
	 * massPlaceObjectsLine(int x1, int y1, int x2, int y2, Objects obj)
	 * takes in 2 locations x1,y1 x2,y2 places a deep copy of the
	 *  object passed in from point to point(inclusive)
	 *  
	 *  because of stack implementation the object that is all ready 
	 *  there will be 2nd on the stack.
	 */  
	
	public void massPlaceObjectsLine(int x1, int y1, int x2, int y2, Objects obj){
		if(x1==x2){
			if(y1 > y2){
				int t = y2;
				y2 = y1;
				y1 = t;
			}
			for (int i = 0; i < y2-y1+1; i++) {
				placeObjects(x1,y1+i,obj);
			}
		}
	}
	 
	
	
	//needed for gui
	public String getName() {
		return name;
	}

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

	/*
	 * debuging toString
	 * only used in testMapMain.
	 */
	public String toString(){
		String t ="";
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if(j == mapSize-1){
					t += objectsStack[j][i].peek().name +"\n";
				}
				else{
					t += objectsStack[j][i].peek().name;
				}
			}
		}
		return t;
	}
	
}
