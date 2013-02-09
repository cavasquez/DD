package MapTool;

import java.util.Stack;

public class testMapStack {
	public static void main(String[] args) {		
		
		Stack<Floor>[][] mapStack =  new Stack[10][10];
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10;j++) {
				mapStack[i][j] = new Stack<Floor>();
			}
		}
		
		Map map1 = new Map("map1");
		for (int i = 0; i < mapStack.length; i++) {
			for (int j = 0; j < mapStack.length; j++) {
				Floor foo = new Floor("1 ",null,5,5,map1);
				Floor foo1 = new Floor("2 ",null,5,5,map1);
				mapStack[i][j].push(foo);
				mapStack[i][j].push(foo1);
				
			}
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if(j ==9){
					mapStack[i][j].pop();
					System.out.println(mapStack[i][j].peek().name);
				}
				else{
					System.out.print(mapStack[i][j].peek().name);
				}
			}
		}
	}
}
