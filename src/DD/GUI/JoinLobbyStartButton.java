package DD.GUI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.GMToolsBox.AddCharacter;
import DD.SlickTools.RenderComponent;
import DD.System.DDSystem;

/*****************************************************************************************************
 * The JoinLobbyStartButton will be used in the JoinLob class and game state. It will take in the IP 
 * address from the text field, attempt to connect to the server, and then communicate the results.
 * Furthermore, it will progress the player to a GamePlayState game state. However, the gameplaystate
 * will need to wait on the server to start/load a world and players.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class JoinLobbyStartButton extends RenderComponent
{
	/************************************ Class Attributes *************************************/
	private TextField username;
	private TextField ip;
	private DDSystem system;
	private float length;
	private float width;
	private Vector2f position;
	private Input mouse = new Input(650);
	private BasicGameState bgs;
	private CharacterSheet sheet;
	
	/************************************ Class Methods *************************************/
	public JoinLobbyStartButton(DDSystem system, Vector2f position)
	{
		super();
		this.system = system;
		this.position = position;
		length = 20;
		width = 80;
	} /* end constructor */
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) 
	{
		gr.drawString("Connect", position.x, position.y);
		
	} /* end render method */

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		int posX = mouse.getMouseX();
		int posY = mouse.getMouseY();
		if((posX > (position.x - 10) && posX < (position.x + width)) && (posY < (position.y + length) && posY > (position.y - 5)))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				boolean connected = false;
				system.client();
				system.ns.setUsername(username.getText());
				connected = system.ns.setServerIP(ip.getText());
				
				if(connected)
				{
					system.ns.start();
					((JoinLob)bgs).killTextFields();
					System.out.println(system.ns.getNetID());
					
					/* assume sheet exists */
					if(sheet != null)
					{
						/* give the sheet to the server */
						int netID = Game.system.ns.getNetID();
						sheet.setNetID(netID);
						AddCharacter add = new AddCharacter(0, sheet);
						add.action();
						((GameplayState)sbg.getState(Game.gameplay)).setCharacterSheet(sheet);
						sbg.enterState(Game.gameplay);
					}
				} /* successfully connected */
				else
				{
					ip.setText("Invalid ip");
				} /* failed to connect. tell user */
				//sbg.enterState(0);
			} /* end mouse if */
			
		} /* end position if */
		
	} /* end update method */
	
	public void setBasicGameState(BasicGameState bgs)
	{
		this.bgs = bgs;
	} /* end setBasicGameState */
	
	public void setTextField(TextField username, TextField ip)
	{
		this.username = username;
		this.ip = ip;
	} /* end setTextField method */
	
	public void setCharacterSheet(CharacterSheet sheet)
	{
		this.sheet = sheet;
	} /* end setCharacterSheet method */

} /* end JoinLobbyStartButton class */
