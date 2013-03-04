package DD.MapTool;

import org.newdawn.slick.Image;


public class Floor extends Objects{
	
	private static final long serialVersionUID = 4476115979010560425L;

	public Floor(){
		super();
		
	}
	
	public Floor(String name, Image image, int move, int light, Map map){
		super(name, image, map);
		super.movePenalty = move;
		super.lightPenalty = light;
		super.priority = 0;
	}
	
	void checks() {
			
	}

	void action() {
		
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){	
		String t="";
		t += "Name: "+name+ ", movePenalty: "+ movePenalty+ ", lightPenalty: "+lightPenalty;
		return t; 
				
	}
	
	
	public static void main(String[] args) {
		
	}
	
	
}
