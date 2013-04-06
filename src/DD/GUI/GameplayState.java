package DD.GUI;
 
import java.util.ArrayList;
import java.util.Iterator;  

import DD.ActionBox.ActionBox;
import DD.ActionBox.Dice;
import DD.Character.*; 
import DD.Character.Abilities.Ability;
import DD.Character.CharacterSheet.CharacterClass;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.Character.CharacterSheet.Monster.Goblin;
import DD.Character.Equipment.Weapon;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.TargetingSystem.Coordinate;
import DD.CombatSystem.TargetingSystem.TargetingSystem;
import DD.GMToolsBox.GMToolsBox;
import DD.MapTool.*;
import DD.Network.NetworkSystem.NetworkType;
import DD.SlickTools.Component;
import DD.SlickTools.DDImage;
import DD.SlickTools.ImageRenderComponent;
import DD.SlickTools.RenderComponent;
import DD.System.DDSystem;

import org.lwjgl.input.Mouse;
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
//	private Image playerImage = null;
//	private Image spriteSheet = null;
	private World world = null;
	private MapTool maptool = null;
    private DDCharacter player;
    private DDCharacter goblin;
    private CharacterObjects playerObj;
    private ActionBox actionBox;
    private GMToolsBox gmToolsBox;
    private CharacterSheet sheet = new CharacterSheet();
    private String charToString = " ";
    private String goblinHP = " ";
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
    	
    	//BY DEFAULT, SET NETWORK AS SERVER
    	//TODO: THE ABOVE NEEDS TO BE CHANGED
    	System.out.println("system? " + Game.system);
    	Game.system.ns.setNetworkType(NetworkType.SERVER);
//    	maptool.getMapAtLocation(0, 0).setPosition(position);
    	Game.system.setMap(maptool.getMapAtLocation(0, 0));
//    	spriteSheet = new Image("Images/Test/DungeonCrawl_ProjectUtumnoTileset.png");
        //floor = spriteSheet.getSubImage(1185, 416, 33, 34);
//        playerImage = spriteSheet.getSubImage(2530, 1440, 33, 34);
//        Image goblinImage = spriteSheet.getSubImage(98, 65, 33, 34);
    	
        player = new DDCharacter(stateID++);  
        //make goblins
//        goblin1 = new DDCharacter(stateID++);
        goblin = new DDCharacter(stateID++);
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
		sheet.equipWeapon(new Weapon(30, "Longsword", Dice.DieSize.D6, 2, 19, 5, 'M', 'S', "Note:", 4), 0);
		sheet.setImage(new DDImage("Images/Test/DungeonCrawl_ProjectUtumnoTileset.png", 2530, 1440, 33, 34 ));
        player.setCharacterSheet(sheet);
        goblin.setCharacterSheet(new Goblin());
//        goblin1.setCharacterSheet(goblin.getCharacterSheet());
        
        player.setCharacterID(stateID++);
        goblin.setCharacterID(stateID++);
       
        Game.system.cs.addCharacter(player);
        Game.system.cs.addCharacter(goblin);
      
        
        //Create ActionBox
        actionBox = new ActionBox(stateID, 300, 200);
        //Create GMToolsBox for GM
        gmToolsBox = new GMToolsBox(stateID, 300, 200);
        //Fill in ActionBox with action choices
        actionBox.addActionChoices();
        Game.system.linkBoxes(actionBox, null);
        //set ActionBox's character
        actionBox.setCharacter(player);
       
        
        //wall = spriteSheet.getSubImage(1280, 574, 33, 34);
        //scaledWall = wall.getScaledCopy(0.9f);
       // Obstacle renderWall = new Obstacle("wall", scaledWall, 5, 5, maptool.getMapAtLocation(0, 0));
       // System.out.println(renderWall.toString());
        //world = new World("TestGUIMap");
        
        int playerx = 15;
        int playery = 6;
        int goblinx = 13;
        int gobliny = 6;
        //playerObj = new CharacterObjects("Bob", playerImage, 210, 25, world.getMap(0, 0), player); 
        playerObj = new CharacterObjects("Bob", player.getImage(), playerx, playery, maptool.getMapAtLocation(0, 0), player);
        CharacterObjects goblinObj = new CharacterObjects("Goblin", goblin.getImage(), goblinx, gobliny, maptool.getMapAtLocation(0, 0), goblin); 
        
        maptool.getMapAtLocation(0, 0).placeObjects(playerx, playery, playerObj);
        maptool.getMapAtLocation(0, 0).placeObjects(goblinx, gobliny, goblinObj);
        
        player.setCoordiante(new Coordinate(playerx, playery));
        goblin.setCoordiante(new Coordinate(goblinx, gobliny));
        
       // maptool.getMapAtLocation(0, 0).massPlaceObjectsLine(10, 11, 10, 19, renderWall);
        Ability.setOwnerCharacter(player);
        
        player.resetCharacter();
        goblin.resetCharacter();
        
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
		actionBox.update(gc, sb, delta);

		
		//Character sheet
		//charToString = "CHARACTER SHEET: \n" + player.getCharacterSheet().toString();
		//goblinHP = "Goblin HP: " + goblin1.getMonHP();
		
		if(!player.getHasTurn()) {
			player.startNewTurn();
		}
		
		int posX = mouse.getMouseX();
		int posY = mouse.getMouseY();
		
		if((posX > 1130 && posX < 1170) && (posY > 615 && posY < 630))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				sb.enterState(0);
			}
		}
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
    	maptool.getMapAtLocation(0, 0).render(gc, sb, g);
//    	RenderComponent renderComponent = null;
//    	ArrayList<CharacterObjects> characters = new ArrayList<CharacterObjects>();
//    	
//    	for(int i = 0; i < maptool.getMapAtLocation(0, 0).mapSize; i++) {
//    		for(int j = 0; j < maptool.getMapAtLocation(0, 0).mapSize; j++) {
//    			Objects[] list = new Objects[maptool.getMapAtLocation(0, 0).objectsStack[i][j].size()];
//    			System.arraycopy(maptool.getMapAtLocation(0, 0).objectsStack[i][j].toArray(), 0, list, 0, maptool.getMapAtLocation(0, 0).objectsStack[i][j].size());
//    			for(int k = list.length; k > 0; k--) {
//    				Component component = (Component)list[k-1];
//    				if (RenderComponent.class.isInstance(component))
//    				{
//    					if (RenderComponent.class.isInstance(component))
//        				{
//        					if(CharacterObjects.class.isInstance(component)) characters.add((CharacterObjects) component);
//        					else
//        					{
//        						renderComponent = (RenderComponent) component;
//            					renderComponent.render(gc, sb, g);
//        					}
//        				}
//    				}
//    			}
//    		}
//    	}
    	
    	
    	//Render Action Box
    	actionBox.render(gc, sb, g);
 
		//g.drawString(charToString, 950, 30);
		//g.drawString(goblinHP, 950, 400);
		
		g.drawString("BACK",1130,615);
		
    }
}