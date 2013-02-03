package DD.MapTool;

import org.newdawn.slick.Image;

public class Obstacle extends Objects{
	public Obstacle(Image image, int move, int light, String name){
		super.image =image;
		super.movePenalty = move;
		super.lightPenalty = light;
		super.name = name;
		super.addNormList(this);
	}

	void checks() {
		
	}

	void action() {
		
	}
	//tooltip info?
	public String toString(){	
		return "Name: "+name+"\n"+
			   "movePenalty: "+ movePenalty+"\n"+
			   "lightPenalty: "+lightPenalty+"\n";
	}

}
