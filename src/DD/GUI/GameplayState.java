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

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
 
public class GameplayState extends BasicGameState {
 
	private int stateID = 0;
	public MapTool maptool = null;
    private DDCharacter player;
    private DDCharacter goblin;
    private CharacterObjects playerObj;
    private ActionBox actionBox;
    private GMToolsBox gmToolsBox = null;
    private CharacterSheet sheet = new CharacterSheet();
    private static Input mouse = new Input(650);
    private String mousePos;
    private DDImage weapon;
    private DDImage offhand;
    private DDImage armor;
    private DDImage shield;
    //private DDImage characterSheetButton;
    
 
    public GameplayState(int stateID)
    {
        this.stateID = stateID;
    }
 
    public int getID() {
    	return stateID;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sb) throws SlickException {
    	
        
    }
    
    @Override
    public void enter(GameContainer gc, StateBasedGame sb) throws SlickException {
    	
    	maptool = new MapTool();
    	//characterSheetButton = new DDImage("Images/ActionBox/CharacterSheet.png");
    	//BY DEFAULT, SET NETWORK AS SERVER
    	//TODO: THE ABOVE NEEDS TO BE CHANGED
    	System.out.println("system? " + Game.system);
    	Game.system.ns.setNetworkType(NetworkType.SERVER);
    	Game.system.setMap(maptool.getMapAtLocation(0, 0));
    	
    	//make character
        player = new DDCharacter(stateID++);  
        
        //make goblins
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
		
		sheet.addToEquipment(new Weapon(30, "Longsword", Dice.DieSize.D6, 2, 19, 5, 'M', 'S', "Note:", 4));
		sheet.addToEquipment(sheet.getArmorFromArmory(6));
		sheet.addToEquipment(sheet.getArmorFromArmory(9));	//shield
		sheet.equipWeapon(new Weapon(30, "Longsword", Dice.DieSize.D6, 2, 19, 5, 'M', 'S', "Note:", 4), 0);
		sheet.EquipArmor(sheet.getArmorFromArmory(6));
		sheet.EquipShield(sheet.getArmorFromArmory(9));		//equip shield
		
		System.out.println("gamplay" + sheet.EquippedWeapon.isEmpty());
		sheet.setImage(new DDImage("Images/Test/DungeonCrawl_ProjectUtumnoTileset.png", 2530, 1440, 33, 34 ));
		
		weapon = sheet.getImage().getSubImage(1090, 895, 33, 34).getScaledCopy(0.8f);
		offhand = sheet.getImage().getSubImage(675, 1505, 33, 34).getScaledCopy(0.8f);
		armor = sheet.getImage().getSubImage(990, 670, 33, 34).getScaledCopy(0.8f);
		shield = sheet.getImage().getSubImage(1410, 1410, 33, 34).getScaledCopy(0.8f);
		
		//set character sheets for player and goblin
        player.setCharacterSheet(sheet);
        goblin.setCharacterSheet(new Goblin());
        
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
        
        int playerx = 15;
        int playery = 6;
        int goblinx = 13;
        int gobliny = 6;

        playerObj = new CharacterObjects("Bob", player.getImage(), playerx, playery,  Game.system.cs.getMap(), player);
        CharacterObjects goblinObj = new CharacterObjects("Goblin", goblin.getImage(), goblinx, gobliny,  Game.system.cs.getMap(), goblin); 
        
        Game.system.cs.getMap().placeObjects(playerx, playery, playerObj);
        Game.system.cs.getMap().placeObjects(goblinx, gobliny, goblinObj);
        
        player.setCoordiante(new Coordinate(playerx, playery));
        goblin.setCoordiante(new Coordinate(goblinx, gobliny));
        
        Ability.setOwnerCharacter(player);
        
        player.resetCharacter();
        goblin.resetCharacter();
        
        player.startNewTurn();
        //System.out.println(Game.system.ts.getMap());	
    
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
		
		if(!player.getHasTurn()) {
			player.startNewTurn();
		}
		
		int posX = mouse.getMouseX();
		int posY = mouse.getMouseY();
		
		//Character Sheet button
//		if((posX > 1055 && posX < (1055+characterSheetButton.getWidth())) && (posY > 560 && posY < (560+characterSheetButton.getHeight())))
//		{
//			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
//			{
//				sb.enterState(8);
//			}
//		}
		
		//Back button
		if((posX > 1110 && posX < 1195) && (posY > 615 && posY < 630))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				sb.enterState(0);
			}
		}
		
		mousePos = "Mouse position: " + posX + " " + posY;
    }
 
    public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException
    {
    	//Render Map
    	Game.system.getMap().render(gc, sb, g);
 	
    	//Render Action Box
    	actionBox.render(gc, sb, g);
    	
    	//characterSheetButton.draw(1055, 560);
		
    	characterSheet(g);
    	weapon.draw(855, 220);
    	offhand.draw(855, 245);
    	armor.draw(855, 275);
    	shield.draw(855, 310);
		g.drawString("MAIN MENU",1110,615);
		g.drawString(mousePos, 900, 0);
		
    }
    
    public void characterSheet(Graphics g) {
    	int shift = 200;
    	g.drawString("Character Sheet:", 630, shift);
    	shift += 30;
    	
    	g.drawString(sheet.toString(), 630, shift);
    	g.drawString("Speed: " + sheet.getSpeed(), 630, 509);
    	
    	shift = 227;
    	g.drawString("Equipped Weapon: " + sheet.getEquippedWeapon(0).getName(), 880, shift);
    	shift += 27;
    	
    	if(sheet.EquippedWeapon.size() > 2) {
    		if(sheet.getEquippedWeapon(1) == null) {
        		g.drawString("No offhand weapon", 880, shift);
        	}
        	else {
        		g.drawString("Offhand Weapon: " + sheet.getEquippedWeapon(1).getName(), 880, shift);
        	}
        	shift += 27;
    	}
    	else {
    		g.drawString("No offhand weapon", 880, shift);
    	}
    	shift += 27;
    	
    	if(sheet.getEquippedArmor() == null) {
    		g.drawString("No armor equipped", 880, shift);
    	}
    	else {
    		g.drawString("Equipped Armor: " + sheet.getEquippedArmor().getName(), 880, shift);
        	shift += 27;
    	}
    	
    	if(sheet.getEquippedShield() == null) {
    		g.drawString("No shield equipped", 880, shift);
    	}
    	else {
    		g.drawString("Equipped Shield: " + sheet.getEquippedShield().getName(), 880, shift);
    	}
    	shift += 27;
    	
    	
    }
    
    public GMToolsBox getGMToolsBox()
    {
    	if (gmToolsBox == null)
			try {
				gmToolsBox = new GMToolsBox();
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	return gmToolsBox;
    } /* end getGMToolsBox */
}