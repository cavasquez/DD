package MapTool;

public class World {
	Map[][] world;
	String WorldName;
	
	public World(){
		world = new Map[5][5];
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world.length; j++) {
				world[i][j] = new Map();
			}
		}
	}
	
	public void LoadMap(int x, int y, Map mapIn){
		/*
		 * TODO:
		 * parse a text file to make a map
		 * use map default
		 */
		world[x][y] = mapIn;
	}
	
	public void removeMap(int x, int y){
		world[x][y] = null;
	}
	
	public void saveWorld(){
	/*
	 * TODO: print to a textFile.
	 * 		 			
	 * 
	 * toString maps to a file
	 * make text file with map names and positions
	 * 
	 */
	}
	
	/*
	 * TODO:	
	 * 		Parse a text file that contains 25 maps
	 * 		parse map files needed
	 *   	build world.
	 *   
	 *   WorldNameHere
	 *   0 0 map00.txt
	 *   0 1 map01.txt
	 *   0 2 map02.txt
	 *   0 3 map03.txt
	 *   0 4 map04.txt
	 *   1 0 map10.txt
	 *   1 1 map11.txt
	 *   1 2 map12.txt
	 *   . .	.
	 *   . .	.
	 *   . .	.
	 *   
	 *   call World default constructor
	 *   
	 *   for each map parced call
	 *   loadMap(int x, int y, MapFile map)
	 */
	public World loadWorld(){
		return null;	
	}
}
