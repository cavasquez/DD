package MapTool;

import org.newdawn.slick.Image;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;


public class Floor extends Objects{
	/*
	 * Need the specific world not just some pointer to the Object world.
	 * 
	 * May have to pass the World ID or name to get the specific world
	 * 
	 */
	World world;
	//type means like snow or dirt or stone or anything else
	
	/*
	 * 
	 */
	//@brandon
	public Floor(String name, Image image, int move, int light){
		super(name, image);
		//super.image =image;
		super.movePenalty = move;
		super.lightPenalty = light;
		//super.name = name;
		
		//
		/*
		 * WHY DO WE NEED TO ADD 'THIS' FLOOR OBJECT TO THE ARRAYLIST FORM 'THIS'
		 * WHY NOT MAKE IT IN A METHOD IN A DIFFERENT CLASS AND BE ADDED TO THE ARRAYLIST 
		 * FROM THERE. MAPS NEED A FLOOR, MY NOT ADD 'THIS' FLOOR IN THE MAP CLASS 
		 */
		//this will run into a problem if this list has other things than floors
		//how will we be able to tell the difference between floors and other objects
		//in this array
		world.addNormList((Objects)this);
	}
	
	void checks() {
			
	}

	void action() {
		
	}	
	
	public String toString(){	
		return "Name: "+name+"\n"+
			   "movePenalty: "+ movePenalty+"\n"+
			   "lightPenalty: "+lightPenalty+"\n";
	}

	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		// TODO Auto-generated method stub
		
	}
	
	
}
