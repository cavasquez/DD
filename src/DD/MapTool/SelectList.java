package DD.MapTool;

import java.util.LinkedList;

import org.newdawn.slick.SlickException;

import DD.Character.DDCharacter;
import DD.Character.CharacterSheet.Monster.Goblin;
import DD.CombatSystem.TargetingSystem.Coordinate;

	public class SelectList {
		LinkedList<Coordinate> list;
		Map owner;
	
	public SelectList(Map owner){
		 list = new LinkedList<Coordinate>();
		 this.owner = owner;
	}
		
	
	public Map getOwner(){
		return owner;
	}
	
	public void setOwner(Map map){
		owner = map;
	}
	
	public void massAddSelectedList(int x1, int y1, int x2, int y2) throws SlickException{
		//fix my coords.
		if(x1>x2){
			int t = x2;
			x2 = x1;
			x1 = t;
		}
		if(y1>y2){
			int t = y2;
			y2 = y1;
			y1 = t;
		}
		//check for clicking in the same spot.
		if(x1==x2 && y1==y2){
			Coordinate coord  = new Coordinate(x1, y1);
			addSelectedList(coord);
		}
		else{
			int x = x2-x1;
			System.out.println("x: "+x);
			int y = y2-y1;
			System.out.println("y: "+y);
			for (int i = 0; i <= x; i++) {
				for (int j = 0; j <= y; j++) {
					Coordinate coord = new Coordinate(i+x1, j+y1);
					addSelectedList(coord);
				}
			}
		}		
	}
	
	public void massRemoveSelectedList(int x1, int y1, int x2, int y2) throws SlickException{
		if(x1>x2){
			int t = x2;
			x2 = x1;
			x1 = t;
		}
		if(y1>y2){
			int t = y2;
			y2 = y1;
			y1 = t;
		}
		//check for clicking in the same spot.
		if(x1==x2 && y1==y2){
			Coordinate coord  = new Coordinate(x1, y1);
			removeSelectedList(coord);
		}
		else{
			int x = x2-x1;
			System.out.println("x: "+x);
			int y = y2-y1;
			System.out.println("y: "+y);
			for (int i = 0; i <= x; i++) {
				for (int j = 0; j <= y; j++) {
					Coordinate coord = new Coordinate(i+x1, j+y1);
					removeSelectedList(coord);
				}
			}
		}	
	}
	
	
	public void placeSelectedListOnMap(Objects obj) throws SlickException{
		for (Coordinate coord : list) {
			
			System.out.println("object place " + coord.x + " " + coord.y);
			
			if(obj instanceof Wall){
				Wall obj2 = new Wall("wall",owner); 
				owner.remove(coord.x, coord.y);
				owner.place(coord.x, coord.y, obj2);
				System.out.println(owner.toString());
				TargetBlock tarBlock = new TargetBlock(owner);
				owner.place(coord.x, coord.y , tarBlock);
			}
			else if(obj instanceof CharacterObjects){
				DDCharacter goblinChar = new DDCharacter(0);
    			goblinChar.setCharacterSheet(new Goblin());
    			goblinChar.setCharacterID(0);
    			goblinChar.resetCharacter();
    			CharacterObjects obj2 = new CharacterObjects("Goblin", goblinChar.getImage(), 0, 0, owner, goblinChar);
    			owner.remove(coord.x, coord.y);
    			owner.place(coord.x, coord.y, obj2);
    			System.out.println(owner.toString());
    			TargetBlock tarBlock = new TargetBlock(owner);
    			owner.place(coord.x, coord.y , tarBlock);
			}			
		
		}
		
	}	
	public void removeSelectedListOnMap(){
		for (Coordinate coord : list) {
			try {
				owner.remove(coord.x, coord.y);
				owner.remove(coord.x, coord.y);
				TargetBlock tarBlock = new TargetBlock(owner);
				owner.place(coord.x, coord.y, tarBlock);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//************************FOR BACK END ONLY***************************
	
	public void addSelectedList(Coordinate coord) throws SlickException{
		//if the coord is all ready in the list do nothing.
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).x == coord.x && list.get(i).y == coord.y){
				System.out.println("addSelectedList: duplicate coord "+coord.x +","+coord.y);
				return;
			}
		}
			TargetBlock tarBlock = new TargetBlock(owner);
			owner.place(coord.x,coord.y,tarBlock);
			list.add(coord);
	}
	

	
	public void removeSelectedList(Coordinate coord) throws SlickException{
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).x == coord.x && list.get(i).y == coord.y){
				System.out.println("removeSelectedList**");
				owner.remove(coord.x, coord.y);
				list.remove(i);
			}
		}
	}
	
	public void clearSelectedList() throws SlickException{
		for (int i = 0; i < owner.mapSize; i++) {
			for (int j = 0; j < owner.mapSize; j++) {
				if(owner.getObjectAtLocation(i, j) instanceof TargetBlock){
					owner.remove(i, j);
				}
			}
		}
		list.clear();
	}
	
	public String selectedToString(){
		String t = "";
		for (Coordinate coord : list) {
			t+= coord.x + ","+coord.y+"\n";
		}
		return t;
	}
}
