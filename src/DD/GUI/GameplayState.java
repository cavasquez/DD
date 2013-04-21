package DD.GUI;

import DD.ActionBox.ActionBox;
import DD.Character.*; 
import DD.Character.CharacterSheet.CharacterSheet;
import DD.GMToolsBox.GMToolsBox;
import DD.MapTool.*;
import DD.SlickTools.DDImage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
 
public class GameplayState extends BasicGameState {
 
	private int stateID = 0;
	public MapTool maptool = null;
    private ActionBox actionBox;
    private CharacterSheet sheet;
    private static Input mouse = new Input(650);
    private String mousePos;
    private DDImage weapon;
    private DDImage offhand;
    private DDImage armor;
    private DDImage shield;
    
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
    	
        //Create ActionBox
        actionBox = new ActionBox(stateID, 300, 200);
        actionBox.setSheet(sheet);
        Game.system.linkBoxes(actionBox, null);
    
    }
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException
    {
    	
    	//Update Map
//    	RenderComponent renderComponent = null;
//    	for(int i = 0; i < maptool.getMapAtLocation(0, 0).mapSize; i++) {
//    		for(int j = 0; j < maptool.getMapAtLocation(0, 0).mapSize; j++) {
//    			Objects[] list = new Objects[maptool.getMapAtLocation(0, 0).objectsStack[i][j].size()];
//    			System.arraycopy(maptool.getMapAtLocation(0, 0).objectsStack[i][j].toArray(), 0, list, 0, maptool.getMapAtLocation(0, 0).objectsStack[i][j].size());
//    			for(int k = 0; k < list.length; k++) {
//    				Component component = (Component)list[k];
//    				if (RenderComponent.class.isInstance(component))
//    				{
//    					
//    					renderComponent = (RenderComponent) component;
//    					renderComponent.update(gc, sb, delta);
//    				}
//    			}
//    		}
//    	}
    	if(Game.system.cs.getMap() != null) Game.system.cs.getMap().update(gc, sb, delta);
    	
    	
		
    	//Update Action Box
    	/* go through ArrayList of Components to call their update methods */
		actionBox.update(gc, sb, delta);
		

		int posX = mouse.getMouseX();
		int posY = mouse.getMouseY();
		
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
    	if(Game.system.getMap() != null) Game.system.getMap().render(gc, sb, g);
    	else g.drawString("Waiting on server", 300, 600);
    	
 	
    	//Render Action Box
    	actionBox.render(gc, sb, g);
		
    	if(actionBox.getSheet() != null) characterSheet(g);
    	if(weapon != null) weapon.draw(855, 220);
    	if(offhand != null) offhand.draw(855, 245);
    	if(armor != null) armor.draw(855, 275);
    	if(shield != null) shield.draw(855, 310);
		g.drawString("MAIN MENU",1110,615);
		g.drawString(mousePos, 900, 0);
		
    }
    
    public void characterSheet(Graphics g) {
    	int shift = 200;
    	
    	if(weapon == null) weapon = sheet.getImage().getSubImage(1090, 895, 33, 34).getScaledCopy(0.8f);
 		if(offhand == null) offhand = sheet.getImage().getSubImage(675, 1505, 33, 34).getScaledCopy(0.8f);
 		if(armor == null) armor = sheet.getImage().getSubImage(990, 670, 33, 34).getScaledCopy(0.8f);
 		if(shield == null) shield = sheet.getImage().getSubImage(1410, 1410, 33, 34).getScaledCopy(0.8f);
    	
    	g.drawString("Character actionBox.getSheet():", 630, shift);
    	shift += 30;
    	
    	g.drawString(actionBox.getSheet().toString(), 630, shift);
    	g.drawString("Speed: " + actionBox.getSheet().getSpeed(), 630, 509);
    	
    	shift = 227;
    	g.drawString("Equipped Weapon: " + actionBox.getSheet().getEquippedWeapon(0).getName(), 880, shift);
    	shift += 27;
    	
    	if(actionBox.getSheet().EquippedWeapon.size() > 2) {
    		if(actionBox.getSheet().getEquippedWeapon(1) == null) {
        		g.drawString("No offhand weapon", 880, shift);
        	}
        	else {
        		g.drawString("Offhand Weapon: " + actionBox.getSheet().getEquippedWeapon(1).getName(), 880, shift);
        	}
        	shift += 27;
    	}
    	else {
    		g.drawString("No offhand weapon", 880, shift);
    	}
    	shift += 27;
    	
    	if(actionBox.getSheet().getEquippedArmor() == null) {
    		g.drawString("No armor equipped", 880, shift);
    	}
    	else {
    		g.drawString("Equipped Armor: " + actionBox.getSheet().getEquippedArmor().getName(), 880, shift);
        	shift += 27;
    	}
    	
    	if(actionBox.getSheet().getEquippedShield() == null) {
    		g.drawString("No shield equipped", 880, shift);
    	}
    	else {
    		g.drawString("Equipped Shield: " + actionBox.getSheet().getEquippedShield().getName(), 880, shift);
    	}
    	shift += 27;
    	
    	
    }
    
    public void setCharacterSheet(CharacterSheet sheet)
    {
    	this.sheet = sheet;
    }
}