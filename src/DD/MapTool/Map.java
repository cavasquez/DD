package DD.MapTool;
/*
 * @author GM-Michael VanWie
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList; 
import java.util.Stack;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import DD.SlickTools.*;


public class Map extends Entity implements Serializable{
	private static final long serialVersionUID = -2402013046237396326L;
	ObjectsPriorityStack[][] objectsStack ; //ops<Objects>
	TempObjects[][]  tempObjects;
	int numTempObjects;
	Image defImage;
	boolean hasTempObjects;
	String name;
	final int mapSize = 21;

	public Map(){
		super();
		
	}
	
	public Map(String name){
		super(0);
		this.name = name;
		objectsStack =  new ObjectsPriorityStack[mapSize][mapSize];
		tempObjects = new TempObjects[mapSize][mapSize];
		super.components = new ArrayList<Component>();
		
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				//needs image                    VVV
				Floor floor = new Floor("floor",null,5,5,this);
				
				addComponent(floor);
				objectsStack[i][j] =  new ObjectsPriorityStack();
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
	public void writeMe(String name, String path){
		try{
			FileOutputStream fileOut = new FileOutputStream(path+name+".ser");
			ObjectOutputStream out =  new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	public CharacterObjects getDDCharacter(int x, int y){
		if(objectsStack[x][y].peek() instanceof CharacterObjects){
			return (CharacterObjects) objectsStack[x][y].peek();
		}
		else {
			return null;
		}
	}
	
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
		if(numTempObjects==0){
			setHasTempObjects(false);
		}
	}
	
	//places single object on x,y
	public void placeObjects(int x,int y,Objects obj) {
		//System.out.println("map.placeObjects()");
		super.components.add(obj);
		objectsStack[x][y].push(obj);
		updateComponentList();
	}
	
	public void resetMap(){
		//TODO: resets all arrays to default objects.
	}
	
	/*  TODO: i need to finish this for any type of input.
	 *  massPlaceObjectsLine(int x1, int y1, int x2, int y2, Objects obj)
	 *  takes in 2 locations x1,y1 x2,y2 places a deep copy of the
	 *  object passed in from point to point(inclusive)
	 *  
	 *  because of stack implementation the object that is all ready 
	 *  there will be 2nd on the stack.
	 *  
	 *  
	 *  first click = 1
	 *  second click = 2
	 *  filler = 3
	 *  example:
	 *  click 1 = point(0,0)
	 *  click 2 = point(6,0)
	 * 	1333332
	 * 	0000000
	 *	0000000
	 * 	0000000
	 * 	
	 * 	
	 * 
	 */  
	
	public void massPlaceObjectsLine(int x1, int y1, int x2, int y2, Objects obj){
		/* if  
		 * 	
		 *  0000000
		 *  0000000
		 *	1333200
		 * 	0000000
		 *
		 * OR
		 * 	
		 *  0000000
		 *  0000000
		 *	2333100
		 * 	0000000
		 */
			
		if(y1==y2){
			if(x1 > x2){
					int t = x2;
					x2 = x1;
					x1 = t;
				}
			for (int i = 0; i < x2-x1+1; i++) {
				placeObjects(x1+i,y1,obj);
			}
		}
		
		/* if  
		 * 	
		 *  0100000
		 *  0300000
		 *	0300000
		 * 	0200000
		 *
		 *  OR
		 *  
		 *	0200000
		 *  0300000
		 *	0300000
		 * 	0100000
		 */
		
		else if(x1==x2){
			if(y1 > y2){
					int t = y2;
					y2 = y1;
					y1 = t;
				}
			for (int i = 0; i < y2-y1+1; i++) {
				placeObjects(x1,y1+i,obj);
			}
		}
		
		/* if  			OR
		 * 	
		 *  1000000      0001000
		 *  0300000      0030000
		 *	0030000      0300000
		 * 	0002000      2000000
		 *
		 * OR  			OR
		 * 	
		 *  2000000      0002000
		 *  0300000      0030000
		 *	0030000      0300000
		 * 	0001000      1000000
		 */
		
		else if(x2-x1 == y2-y1){ //slope == 1 or -1
			/* if  
			 * 	
			 *  0000000
			 *  0000000
			 *	0000000
			 * 	0000000
			 *
			 *  OR
			 *  
			 *	0000000
			 *  0000000
			 *	0000000
			 * 	0000000
			 */
			
			if(x1>x2 && y1>y2){
				int timesPlaced = x1-x2;
				System.out.println("slop1");
				for (int i = 0; i < timesPlaced; i++) {
					
				}
			}
			else{
				System.out.println("slop2");
			}
		}
				
		updateComponentList();
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

	public void removeObjects(int x, int y) {
		objectsStack[x][y].pop();
		updateComponentList();
	}

	public Object getComponents() {
		// TODO Auto-generated method stub
		return super.components;
	}
	
}
