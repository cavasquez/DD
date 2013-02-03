package DD.MapTool;

import java.util.ArrayList;

import org.newdawn.slick.Image;

public abstract class Objects {
	ArrayList<Objects> normList;
	ArrayList<TempObjects> tempList;
	Image image;
	int movePenalty;
	int lightPenalty;
	String name;
	
	
	abstract void checks();
	public ArrayList<Objects> getObjectsList(){
		return normList;
	}
	abstract void action();	
	
	
	/*
	 * returns a list of all normal Objects that can be created.
	 */
	
	public void addNormList(Objects obj){
		normList.add(obj);
	}
	
	/*
	 * returns a list of all temp objects that can be created.
	 */
	public void addTempList(TempObjects temp){
		tempList.add(temp);
	}	
}
