package DD.GUI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import DD.GMToolsBox.GMToolsBox;
import DD.MapTool.*;

public class GMGameplayState extends BasicGameState {
	
	private int stateID = 0;
	private MapTool maptool;
	private GMToolsBox gmToolsBox;
	Input mouse = new Input(650);
	
	
	public GMGameplayState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sb) throws SlickException {
		maptool = new MapTool();
		gmToolsBox = new GMToolsBox(stateID, 300, 200);
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}

}
