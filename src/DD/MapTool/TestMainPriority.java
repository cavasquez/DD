package DD.MapTool;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;



public class TestMainPriority extends BasicGame
{
	public TestMainPriority(String title) {
		super(title);

	}
 
    public TestMainPriority()
    {
        super("Slick2DPath2Glory - SimpleGame");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
    	MapTool mt = new MapTool();
		
		CharacterObjects char1 = new CharacterObjects("(~*-*)~", null, 0, 0, mt.getMapAtLocation(4, 4), null);
		
		System.out.println("ObjectStackContent@ 0,0: expected output:\nfloor.toString()");
		System.out.println(mt.getMapAtLocation(4, 4).contentToString(0, 0));		
		System.out.println("The current map");
		System.out.println(mt.getMapAtLocation(4, 4).toString());
		
		Floor floor1 = new Floor("floor1", null,0,0, 5, 5, mt.getMapAtLocation(0, 0));
		System.out.println("place floor1 at 0,0");
		mt.getMapAtLocation(4, 4).place(0, 0, floor1);
		
		System.out.println("place (~*-*)~ at 0,0");
		mt.getMapAtLocation(4, 4).place(0, 0, char1);
		System.out.println("ObjectStackContent@ 0,0: expected output: \ncharacter.toString() \nfloor1.toString()");
		System.out.println(mt.getMapAtLocation(4, 4).contentToString(0, 0));		
		System.out.println("The current map");
		System.out.println(mt.getMapAtLocation(4, 4).toString());
		
		System.out.println("remove (~*-*)~ from 0,0");
		mt.getMapAtLocation(4, 4).remove(0, 0);
		System.out.println("ObjectStackContent@ 0,0: expected output:\nfloor1.toString()");
		System.out.println(mt.getMapAtLocation(4, 4).contentToString(0, 0));		
		System.out.println("The current map");
		System.out.println(mt.getMapAtLocation(4, 4).toString());
				
		System.out.println("place (~*-*)~ at 19,19");
		mt.getMapAtLocation(4, 4).place(19, 19, char1);
		System.out.println("ObjectStackContent@ 19,19: expected output: \ncharacter.toString() \nfloor.toString()");
		System.out.println(mt.getMapAtLocation(4, 4).contentToString(19, 19));		
		System.out.println("The current map");
		System.out.println(mt.getMapAtLocation(4, 4).toString());
		
		System.out.println("remove (~*-*)~ from 19,19");
		mt.getMapAtLocation(4, 4).remove(19,19);
		System.out.println("ObjectStackContent@ 19,19: expected output:\nfloor.toString()");
		System.out.println(mt.getMapAtLocation(4, 4).contentToString(19,19));		
		System.out.println("The current map");
		System.out.println(mt.getMapAtLocation(4, 4).toString());
		
		
//		
//		Floor floor1 = new Floor("floor1", null,0,0, 5, 5, mt.getMapAtLocation(0, 0));
//		Obstacle wall1 = new Obstacle("wall1", null, 5, 5, mt.getMapAtLocation(0, 0));
//		Obstacle wall2 = new Obstacle("wall2", null, 5, 5, mt.getMapAtLocation(0, 0));
//		Obstacle wall3 = new Obstacle("wall3", null, 5, 5, mt.getMapAtLocation(0, 0));
//		Obstacle wall4 = new Obstacle("wall4", null, 5, 5, mt.getMapAtLocation(0, 0));
//		TempObjects temp1 = new TempObjects("temp1", 5, null, 5, 5, mt.getMapAtLocation(0, 0));
//
//		//adding various objects/tempObjects to the stack. priority kicking in.
//		
//		System.out.println(mt.getMapAtLocation(0, 0).getObjectAtLocation(0, 0).toString());
//		mt.getMapAtLocation(0, 0).place(0, 0, floor1); //removes default floor
//		System.out.println(mt.getMapAtLocation(0, 0).getObjectAtLocation(0, 0).toString());
//		mt.getMapAtLocation(0, 0).place(0, 0, wall1); //place wall1 on top of stack.
//		System.out.println(mt.getMapAtLocation(0, 0).getObjectAtLocation(0, 0).toString());
//		mt.getMapAtLocation(0, 0).place(0, 0, wall2); //removes wall1
//		System.out.println(mt.getMapAtLocation(0, 0).getObjectAtLocation(0, 0).toString());
//		mt.getMapAtLocation(0, 0).place(0, 0, temp1); //place temp on top of stack.
//		System.out.println(mt.getMapAtLocation(0, 0).getObjectAtLocation(0, 0).toString());
//		mt.getMapAtLocation(0, 0).place(0, 0, wall3); //removes wall2 temp1 is top
//		
//		//bulk comment from stars to stars to see another test.
//		//****************************************************
//		Objects[] objTemp = mt.getMapAtLocation(0, 0).getContent(0, 0);
//		System.out.println("Content of the Stack at 0,0");
//		for (int i = 0; i < objTemp.length; i++) {
//			System.out.println(objTemp[i].toString());
//		}
//		
//		for (int i = 0; i < objTemp.length; i++) {
//			if(objTemp[i] instanceof Obstacle){
//				System.out.println("WALL!!!!");
//			}
//			else if(objTemp[i] instanceof TempObjects){
//				System.out.println("TEMP!!!!");
//			}
//		}
//		
//		
//		//****************************************************
//		
////		System.out.println(mt.getMapAtLocation(0, 0).getObjectAtLocation(0, 0).toString());
////		mt.getMapAtLocation(0, 0).placeObjects(0, 0, wall4); //removes wall3 temp1 is top
////		System.out.println(mt.getMapAtLocation(0, 0).getObjectAtLocation(0, 0).toString());
////		mt.getMapAtLocation(0, 0).removeObjects(0, 0); //removes temp1 wall4 is back
////		System.out.println(mt.getMapAtLocation(0, 0).getObjectAtLocation(0, 0).toString());
////		mt.getMapAtLocation(0, 0).removeObjects(0, 0); //removes wall4 floor1 is back
////		System.out.println(mt.getMapAtLocation(0, 0).getObjectAtLocation(0, 0).toString());		
	}
		
    
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {

    }
 
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {

    }
 
    public static void main(String[] args) 
			throws SlickException
    {
    	AppGameContainer app = 
			new AppGameContainer(new TestMainPriority());
 
         //app.setDisplayMode(800, 600, false);
         app.start();
    }
    
    
}
