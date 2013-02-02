package MapTool;

import org.newdawn.slick.Image;

public class TempObjects extends Objects{

	int turnCount; //numPlayers*Duration, 
	public TempObjects(int turnCont, Image image, int move, int light, String name){
		this.turnCount = turnCont;
		super.image = image;
		super.movePenalty = move;
		super.lightPenalty = light;
		super.name = name;
		//super.addTempList(this);
	}
	void checks() {

	}

	void action() {
			
	}
	public String toString(){	
		return "Name: "+name+"\n"+
			   "TurnCount: "+turnCount+"\n"+
			   "movePenalty: "+ movePenalty+"\n"+
			   "lightPenalty: "+lightPenalty+"\n";
	}
}
