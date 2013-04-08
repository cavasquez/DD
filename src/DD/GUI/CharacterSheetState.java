package DD.GUI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import DD.Character.DDCharacter;
import DD.Character.CharacterSheet.CharacterSheet;

public class CharacterSheetState extends BasicGameState {
	
	private int stateID = 0;
	private static Input mouse = new Input(650);
	
	public CharacterSheetState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sb) throws SlickException {
		// TODO Auto-generated method stub
		DDCharacter[] characters = Game.system.cs.getCharacterList();
		CharacterSheet sheet;
		for(int i = 0; i < characters.length; i++)
		{
			if(characters[i].getSheet().getNetID() == Game.system.ns.getNetID()) sheet = characters[i].getSheet();
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) throws SlickException {
		gr.drawString("Character Sheet", 0, 0);
		gr.drawString("BACK",1130,615);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException {
		int posX = mouse.getMouseX();
		int posY = mouse.getMouseY();
		
		//Back button
		if((posX > 1130 && posX < 1170) && (posY > 615 && posY < 630))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				sb.enterState(4);
			}
		}
	}

	@Override
	public int getID() {
		return stateID;
	}
	
}
