package DD.GUI;
 
import java.util.Iterator;  

import DD.ActionBox.ActionBox;
import DD.Character.*; 
import DD.Character.Abilities.Ability;
import DD.Character.CharacterSheet.CharacterClass;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.TargetingSystem.Coordinate;
import DD.CombatSystem.TargetingSystem.TargetingSystem;
import DD.MapTool.*;
import DD.SlickTools.Component;
import DD.SlickTools.ImageRenderComponent;
import DD.SlickTools.RenderComponent;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
 
public class GameplayState extends BasicGameState {
 
	private int stateID = 0;
	private Image floor = null;
	private Image wall = null;
	private Image scaledWall = null;
	private Image playerImage = null;
	private Image spriteSheet = null;
	private World world = null;
	private MapTool maptool = null;
    private DDCharacter player;
    private CharacterObjects playerObj;
    private ActionBox actionBox;
    private CharacterSheet sheet = new CharacterSheet();
    private String charToString = " ";
    //private String mousePos = " ";
    Input mouse = new Input(650);
    //private float x, y;
    
    
 
    public GameplayState(int stateID)
    {
        this.stateID = stateID;
    }
 
    public int getID() {
    	return stateID;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sb) throws SlickException {
    	maptool = new MapTool();
    	
//    	maptool.getMapAtLocation(0, 0).setPosition(position);
    	TargetingSystem ts = new TargetingSystem();
    	CombatSystem cs = new CombatSystem();
    	
    	TargetingSystem.setMap(maptool.getMapAtLocation(0, 0));
    	CombatSystem.setMap(maptool.getMapAtLocation(0, 0));
    	
    	spriteSheet = new Image("Images/Test/DungeonCrawl_ProjectUtumnoTileset.png");
        //floor = spriteSheet.getSubImage(1185, 416, 33, 34);
        playerImage = spriteSheet.getSubImage(2530, 1440, 33, 34);
        player = new DDCharacter(stateID++);  
        /* character creation process */
		sheet.fillBasic("Max", 
			"Bob", 
			0, 
			"Elvish, Common", 
			0, 
			1, 
			5, 
			150, 
			200, 
			"Chaotic Neutral", 
			"Apple", 
			"Noble", 
			"Archer");
		
		sheet.fillAbilities();
		CharacterClass barb = sheet.chooseClass(0);	//this is barbarian
		sheet.fillRecorder(barb);
		sheet.fillAttacksAndDefense(barb);
        player.setCharacterSheet(sheet);
        
        player.setCharacterID(stateID++);
        CombatSystem.addCharacter(player);
        
        actionBox = new ActionBox(stateID, 300, 200);
        ActionBox.setCharacter(player);
       
        
        wall = spriteSheet.getSubImage(1280, 574, 33, 34);
        scaledWall = wall.getScaledCopy(0.9f);
       // Obstacle renderWall = new Obstacle("wall", scaledWall, 5, 5, maptool.getMapAtLocation(0, 0));
       // System.out.println(renderWall.toString());
        //world = new World("TestGUIMap");
        
        int x = 15;
        int y = 6;
        //playerObj = new CharacterObjects("Bob", playerImage, 210, 25, world.getMap(0, 0), player); 
        playerObj = new CharacterObjects("Bob", playerImage,x,y, maptool.getMapAtLocation(0, 0), player); 
        maptool.getMapAtLocation(0, 0).placeObjects(x, y, playerObj);
        player.setCoordiante(new Coordinate(x,y));
       // maptool.getMapAtLocation(0, 0).massPlaceObjectsLine(10, 11, 10, 19, renderWall);
        Ability.setOwnerCharacter(player);
        player.startNewTurn();
        System.out.println("GS:" + player);
        
        
    }
 
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
    {
    	
    	//Update Map
    	RenderComponent renderComponent = null;
    	for(int i = 0; i < maptool.getMapAtLocation(0, 0).mapSize; i++) {
    		for(int j = 0; j < maptool.getMapAtLocation(0, 0).mapSize; j++) {
    			Objects[] list = new Objects[maptool.getMapAtLocation(0, 0).objectsStack[i][j].size()];
    			System.arraycopy(maptool.getMapAtLocation(0, 0).objectsStack[i][j].toArray(), 0, list, 0, maptool.getMapAtLocation(0, 0).objectsStack[i][j].size());
    			for(int k = 0; k < list.length; k++) {
    				Component component = (Component)list[k];
    				if (RenderComponent.class.isInstance(component))
    				{
    					
    					renderComponent = (RenderComponent) component;
    					renderComponent.update(gc, sb, delta);
    				}
    			}
    		}
    	}
    	
    	
		
    	//Update Action Box
    	/* go through ArrayList of Components to call their update methods */
		renderComponent = null;
		for (Component component : actionBox.getComponentList())
		{
			if (RenderComponent.class.isInstance(component))
			{
				renderComponent = (RenderComponent) component;
				renderComponent.update(gc, sb, delta);
			}
			
		}
		
		//Character sheet
		charToString = "CHARACTER SHEET: \n" + player.getCharacterSheet().toString();
    }
 
    public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException
    {
    	/*
    	
    	for(int i = 0; i < world.getMap(0, 0).mapSize; i++) {
    		for(int j = 0; j < world.getMap(0, 0).mapSize; j++) {
    			Iterator iterator = world.getMap(0, 0).objectsStack[i][j].getIterator();
    			while(iterator.hasNext()) {	
    				Component component = (Component)iterator.next();
    				if (RenderComponent.class.isInstance(component))
    				{
    					renderComponent = (RenderComponent) component;
    					renderComponent.render(gc, sb, g);
    				}
    			}
    		}
    	}
    	*/
    	
    	//Render Map
    	RenderComponent renderComponent = null;
    	
    	for(int i = 0; i < maptool.getMapAtLocation(0, 0).mapSize; i++) {
    		for(int j = 0; j < maptool.getMapAtLocation(0, 0).mapSize; j++) {
    			Objects[] list = new Objects[maptool.getMapAtLocation(0, 0).objectsStack[i][j].size()];
    			System.arraycopy(maptool.getMapAtLocation(0, 0).objectsStack[i][j].toArray(), 0, list, 0, maptool.getMapAtLocation(0, 0).objectsStack[i][j].size());
    			for(int k = list.length; k > 0; k--) {
    				Component component = (Component)list[k-1];
    				if (RenderComponent.class.isInstance(component))
    				{
    					renderComponent = (RenderComponent) component;
    					renderComponent.render(gc, sb, g);
    				}
    			}
    		}
    	}
    	
    	
    	//Render Action Box
    	renderComponent = null;
		for (Component component : actionBox.getComponentList())
		{
			if (RenderComponent.class.isInstance(component))
			{
				renderComponent = (RenderComponent) component;
				renderComponent.render(gc, sb, g);
			}
			
		}
 
		g.drawString(charToString, 950, 30);
    }
}