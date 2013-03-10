
	package DD.MapTool;

	import org.newdawn.slick.AppGameContainer;
	import org.newdawn.slick.BasicGame;
	import org.newdawn.slick.GameContainer;
	import org.newdawn.slick.Graphics;
	import org.newdawn.slick.SlickException;
	import DD.CombatSystem.TargetingSystem.Coordinate;


	public class TestSelectedList extends BasicGame
	{
		public TestSelectedList(String title) {
			super(title);

		}
	 
	    public TestSelectedList()
	    {
	        super("Slick2DPath2Glory - SimpleGame");
	    }
	 
	    @Override
	    public void init(GameContainer gc) 
				throws SlickException {
	    	MapTool mt = new MapTool();
	    	
	    	for (int i = 0; i < mt.getMapAtLocation(0, 0).mapSize; i++) {
				for (int j = 0; j < mt.getMapAtLocation(0, 0).mapSize; j++) {
					if(i == j || i+j == 19){
						Coordinate coord = new Coordinate(i, j);
						mt.addSelectedList(coord);
					}
				}
			}		
	    	Obstacle wall = new Obstacle("t(*-*t)", null, 5, 5, mt.getMapAtLocation(0, 0));
			mt.placeSelectedList(wall, mt.getMapAtLocation(0, 0));
	    	System.out.println(mt.selectedToString());
	    	System.out.println(mt.getMapAtLocation(0, 0).toString());
	    	
	    	
	    	for (int i = 0; i < mt.getMapAtLocation(0, 0).mapSize; i++) {
				for (int j = 0; j < mt.getMapAtLocation(0, 0).mapSize; j++) {
					if(i == j || i+j == 19){
						mt.getMapAtLocation(0, 0).remove(i, j);//removing t(*-*t)
						Coordinate coord = new Coordinate(i, j);
						mt.removeSelectedList(coord); //clearing selectedList one by one
					}
				}
			}
			Floor flo1 = new Floor("~(*-*~)", null, 0, 0, 5, 5, mt.getMapAtLocation(0, 0));
			mt.placeSelectedList(flo1, mt.getMapAtLocation(0, 0)); //this should yield a map with all floors
	    	System.out.println(mt.selectedToString());
	    	System.out.println(mt.getMapAtLocation(0, 0).toString());
	    	
	    	mt.getMapAtLocation(0, 0).remove(0, 0);
	    	System.out.println(mt.getMapAtLocation(0, 0).toString());
	    	
	    	
	    }
	 
	    @Override
	    public void update(GameContainer gc, int delta) 
				throws SlickException     
	    {

	    }
	 
	    public void render(GameContainer gc, Graphics g) 
				throws SlickException 
	    {

	    }
	 
	    public static void main(String[] args) 
				throws SlickException
	    {
	    	AppGameContainer app = 
				new AppGameContainer(new TestSelectedList());
	    	
	         //app.setDisplayMode(800, 600, false);
	         app.start();
	    }   
	}

