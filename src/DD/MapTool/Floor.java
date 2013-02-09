package DD.MapTool;
import org.newdawn.slick.Image;


public class Floor extends Objects{

	public Floor(String name, Image image, int move, int light, Map map){
		super(name, image, map);
		super.movePenalty = move;
		super.lightPenalty = light;
	}
	
	void checks() {
			
	}

	void action() {
		
	}	
	
	public String toString(){	
		return "Name: "+name+ ", movePenalty: "+ movePenalty+ ", lightPenalty: "+lightPenalty+"\n";
	}
	
	
	public static void main(String[] args) {
		
	}
	
	
}
