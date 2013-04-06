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
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import DD.Character.DDCharacter;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.TargetingSystem.Coordinate;
import DD.SlickTools.*;


public class Map extends Entity implements Serializable{
	private static final long serialVersionUID = -2402013046237396326L;
	public ObjectsPriorityStack[][] objectsStack ; //ops<Objects>
	TempObjects[][]  tempObjects;
	int numTempObjects;
	transient Image defImage = null;
	transient Image spriteSheet = null;
	transient Image floorSprite = null;
	transient Image floorImage = null;
	boolean hasTempObjects;
	String name;
	public final int mapSize = 20;
	protected ArrayList<SerMapCharHelper> serMapHelper = new ArrayList<SerMapCharHelper>();

	public Map() {
		super();
	}
	
	public Map(String name) throws SlickException {
		super(0);
		this.name = name;
		objectsStack =  new ObjectsPriorityStack[mapSize][mapSize];
		tempObjects = new TempObjects[mapSize][mapSize];
		super.components = new ArrayList<Component>();
		
		spriteSheet = new Image("Images/Test/DungeonCrawl_ProjectUtumnoTileset.png");
    	// get the floor image
        floorSprite = spriteSheet.getSubImage(1185, 416, 33, 34);
        //scale the floor image
        floorImage = floorSprite.getScaledCopy(0.9f);
		
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {				              
				Floor floor = new Floor("floor", floorImage, i, j, 5 , 5, this);				
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
	
//	public void writeMeHelper(){		
//		
//		Stack<Objects> stackHelper = new Stack<Objects>();
//		int[][] stackSize = new int[mapSize][mapSize];
//		for (int i = 0; i < mapSize; i++) {
//			for (int j = 0; j < mapSize; j++) {
//				stackSize[i][j] = objectsStack[i][j].getPQueue().size();
//			}
//		}
//		
//		for (int i = 0; i < mapSize; i++) {
//			for (int j = 0; j < mapSize; j++) {
//				for (int z = 0; z < objectsStack[i][j].getPQueue().size(); z++) {
//					stackHelper.push(objectsStack[i][j].pop());
//				}
//			}
//		}
//		
//		for (int i = 0; i < mapSize; i++) {
//			for (int j = 0; j < mapSize; j++) {
//				for (int z = 0; z < stackSize[i][j]; z++) {
//					stackHelper.peek().setImage(null);
//					place(i,j,stackHelper.pop());
//				}
//			}
//		}
//	}
	
	public void serMapHelperMethod() throws SlickException{
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if(objectsStack[i][j].peek() instanceof CharacterObjects){
					CharacterObjects workDamnYou;
					workDamnYou = new CharacterObjects();
						workDamnYou = (CharacterObjects) objectsStack[i][j].peek();
					serMapHelper.add(new SerMapCharHelper(new Coordinate(i, j), workDamnYou.ddchar.getCharacterSheet()));
					try {
						remove(i,j);
					} catch (SlickException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void writeMe(String name, String path){
		try{
			//for each objects in map. make it's image null
		
			FileOutputStream fileOut = new FileOutputStream(path+name+".ser");
			ObjectOutputStream out =  new ObjectOutputStream(fileOut);
			
			try {
				serMapHelperMethod();
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.writeObject(this);
			out.close();
			fileOut.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public Objects[] getContent(int x, int y){
		return objectsStack[x][y].toArray();
	}
	
	public String contentToString(int x, int y){
		String t = "";
		Objects[] foo = objectsStack[x][y].toArray();
		for (int i = 0; i < foo.length; i++) {
			t+= foo[i].toString();
		}
		return t;
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
			//placeObjectHelper(x, y,temp);
			objectsStack[x][y].push(temp);
			updateComponentList();
		}
	}

	
	public void remove(int x, int y) throws SlickException{
		if(objectsStack[x][y].peek() instanceof TempObjects){
			removeTempObjects(x, y);
		}
		else{
			removeObjects(x, y);
		}
	}
	
	public void place(int x, int y, Objects obj){
		if(obj instanceof TempObjects){
			System.out.println("map.place IM HERE");
			placeTempObject(x, y, (TempObjects) obj);
		}
		else{
			placeObjects(x, y, obj);
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
		super.components.add(obj);
		obj.setPosition(x, y);
		placeObjectHelper(x, y, obj);
		objectsStack[x][y].push(obj);
		updateComponentList();		
	}
	
	public void placeObjectHelper(int x, int y, Objects obj){
		//check if there is an objects with the same priority. if so remove it.
		Objects[] t = objectsStack[x][y].toArray(); //generates a sorted array based on the given priority queue
		for (int i = 0; i < t.length; i++) {
			if(t[i].getPriority() == obj.getPriority()){
				t[i] = null;
			}
		}
		objectsStack[x][y].getPQueue().clear();
		for (int i = 0; i < t.length; i++) {
			if(t[i]!=null)
				objectsStack[x][y].push(t[i]);
		}
	}
	
	
	/*
	 * resetMap() returns currentMap to Default values
	 * 		clears priority queues and new floor object
	 * 		resets all tempObjects and any variable likewise.
	 * 
	 */
	public void resetMap() throws SlickException{
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				objectsStack[i][j].getPQueue().clear();
				//Floor floor = new  Floor("floor", null, 5, 5, this);
				Floor floor = new Floor("floor", floorImage, (i + (floorImage.getHeight() * i)), (j + (floorImage.getWidth() * j)), 5 , 5, this);
				placeObjects(i, j, floor);
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
	
	/*  massPlaceObjectsLine(int x1, int y1, int x2, int y2, Objects obj)
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

	public DDCharacter loadCharacter(String path, String charName){
		DDCharacter e = null;
	      try
	      {
	         FileInputStream fileIn =
	                          new FileInputStream(path+"/"+charName+".ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (DDCharacter) in.readObject();
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

//	public void removeObjects(int x, int y) {
//		objectsStack[x][y].pop();
//		updateComponentList();
//	}

	
	public void removeObjects(int x, int y) throws SlickException {
		if(objectsStack[x][y].peek() instanceof Floor){
			int xCoord = ((Floor) objectsStack[x][y].peek()).getX();
			int yCoord = ((Floor) objectsStack[x][y].peek()).getY();
			objectsStack[x][y].pop();
			if(objectsStack[x][y].getPQueue().isEmpty()){
				Floor floor = new Floor("t(*-*t)", floorImage, (xCoord + (floorImage.getHeight() * xCoord)), (yCoord + (floorImage.getWidth() * yCoord)), 5 , 5, this);
				place(x,y,floor);
			}
		}
		else{
			objectsStack[x][y].pop();
		}
		updateComponentList();
	}
	
	/************************************ Slick Methods *************************************/
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
	{
		//super.render(gc, sbg, gr);
		
		RenderComponent renderComponent = null;
		ArrayList<CharacterObjects> characters = new ArrayList<CharacterObjects>();
    	
    	for(int i = 0; i < mapSize; i++) {
    		for(int j = 0; j < mapSize; j++) {
    			Objects[] list = new Objects[objectsStack[i][j].size()];
    			System.arraycopy(objectsStack[i][j].toArray(), 0, list, 0, objectsStack[i][j].size());
    			for(int k = list.length; k > 0; k--) {
    				Component component = (Component)list[k-1];
    				if (RenderComponent.class.isInstance(component))
    				{
    					if(CharacterObjects.class.isInstance(component)) characters.add((CharacterObjects) component);
    					else
    					{
    						renderComponent = (RenderComponent) component;
        					renderComponent.render(gc, sbg, gr);
    					}
    				}
    			}
    		}
    	}
    	/* Render characters last */
    	for(int i = 0; i < characters.size(); i++)
		{
    		characters.get(i).render(gc, sbg, gr);
		}
	} /* end render method */
	
	
	public ArrayList<Component> getComponents() {
		// TODO Auto-generated method stub
		return super.components;
	}
	
}
