package DD.GUI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import DD.Character.DDCharacter;
import DD.Character.CharacterSheet.Monster.Goblin;
import DD.GMToolsBox.GMToolsBox;
import DD.MapTool.*;
import DD.Network.NetworkSystem.NetworkType;
import DD.SlickTools.Component;
import DD.SlickTools.RenderComponent;

public class GMGameplayState extends BasicGameState {
	
	private int stateID = 0;
	private MapTool maptool;
	private GMToolsBox gmToolsBox;
	Input mouse = new Input(650);
	
	private DDCharacter goblin1, goblin2;
	
	
	public GMGameplayState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sb) throws SlickException {
		
		
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sb) throws SlickException {
		maptool = new MapTool();
		gmToolsBox = new GMToolsBox(stateID, 300, 200);
		
		Game.system.ns.setNetworkType(NetworkType.SERVER);
		Game.system.setMap(maptool.getMapAtLocation(0, 0));
		
		gmToolsBox.addCharacter(GMToolsBox.Holder.MOB, new Goblin());
		gmToolsBox.addCharacter(GMToolsBox.Holder.MOB, new Goblin());
		
//		DDCharacter goblin1 = new DDCharacter(stateID++);
//		DDCharacter goblin2 = new DDCharacter(stateID++);
//		goblin1.setCharacterSheet(new Goblin());
//		goblin2.setCharacterSheet(new Goblin());
//		goblin1.setCharacterID(stateID++);
//		goblin2.setCharacterID(stateID++);
	
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {
		Game.system.getMap().render(gc, sb, g);
		gmToolsBox.render(gc, sb, g);
		g.drawString("BACK",1130,615);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException {
		
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
    	
    	//Update GMToolsBox
    	gmToolsBox.update(gc, sb, delta);

		int posX = mouse.getMouseX();
		int posY = mouse.getMouseY();
		
		//Back button
		if((posX > 1130 && posX < 1170) && (posY > 615 && posY < 630))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				sb.enterState(0);
			}
		}
		
	}

	@Override
	public int getID() {
		return stateID;
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
