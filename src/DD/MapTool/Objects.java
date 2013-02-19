package DD.MapTool;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import DD.SlickTools.*;

public class Objects extends ImageRenderComponent implements Serializable{//figure out comp.
	int movePenalty;
	int lightPenalty;
	String name;
	Entity owner;
	int priority;

	public Objects(String name, Image image, Map owner) {
		
		super(0, image);
		this.name = name;
		// TODO
		if(owner!=null)
			owner.addComponent(this);
	}

	/*
	 * Default constructor used for Serializable.
	 */
	public Objects() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void writeMe(){
		try{
			FileOutputStream fileOut = new FileOutputStream(name+".ser");
			ObjectOutputStream out =  new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		// TODO Auto-generated method stub
		
	}
	
}
