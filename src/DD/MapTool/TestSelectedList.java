
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
	    	
	    	System.out.println("massAddSelectedMap");
	    	mt.selectedList.massAddSelectedList(0, 0, 20, 20);	    	
	    	System.out.println(mt.currentMap.toString());
	    	
	    	System.out.println("removeSelectedOnMap");
	    	mt.selectedList.removeSelectedListOnMap();
         	System.out.println(mt.currentMap.toString());
	    	
	    	
         	mt.selectedList.massRemoveSelectedList(1, 1, 19, 19);
         	System.out.println(mt.currentMap.toString());
	    	
			Floor flo1 = new Floor("~(*-*~)", null, 0, 0, 5, 5, mt.getMapAtLocation(0, 0));
			mt.selectedList.placeSelectedListOnMap(flo1); //this should yield a map with all floors
			System.out.println(mt.currentMap.toString());
			mt.selectedList.clearSelectedList();
			System.out.println(mt.currentMap.toString());

			mt.selectedList.massAddSelectedList(0, 0, 20, 20);
	    	System.out.println(mt.currentMap.toString());
         	mt.selectedList.massRemoveSelectedList(1, 1, 19, 19);
         	System.out.println(mt.currentMap.toString());
         	mt.selectedList.removeSelectedListOnMap();
         	System.out.println(mt.currentMap.toString());
    		mt.selectedList.clearSelectedList();
			System.out.println(mt.currentMap.toString());
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

