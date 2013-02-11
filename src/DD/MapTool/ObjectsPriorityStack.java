package DD.MapTool;

import java.util.PriorityQueue;


public class ObjectsPriorityStack {
	private  PriorityQueue<Objects> pQueue;
	public ObjectsPriorityStack() {
		OPSComparator opsc = new OPSComparator();
		pQueue = new PriorityQueue<Objects>(1,opsc);
	}
	
	public void pop(){
		pQueue.remove();
	}
	
	public Objects peek(){
		return pQueue.peek();
	}
	
	public void push(Objects obj){
		pQueue.add(obj);
	}
	public static void main(String[] args) {

		World world = new World();
		
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				Obstacle ob = new Obstacle("ob", null, 5, 5, world.world[0][0]);
				TempObjects te = new TempObjects("te",5,null,5,5,world.world[0][0]);
				
				world.world[0][0].objectsStack[i][j].push(ob);
				world.world[0][0].objectsStack[i][j].push(te);
								
			}
		}
		
		System.out.println(world.toString());
	}
}
