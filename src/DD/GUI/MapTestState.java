package DD.GUI;

import DD.Character.CharacterSheet.*; 
import DD.Character.CharacterSheet.Race.*;
import DD.ActionBox.ActionBox;
import DD.Character.*;
import DD.SlickTools.Component;
import DD.SlickTools.RenderComponent;
import org.newdawn.slick.Animation; 
import org.newdawn.slick.geom.Vector2f;
//import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.state.StateBasedGame;


 
public class MapTestState extends BasicGameState {
	private int stateID = 0;
	
	private CharacterSheet sheet = new CharacterSheet();
	
	private String charToString = " ";
	private float playerX=230;
	private float playerY=445;
	private TiledMap map;	
	private Animation player;
	private ActionBox actionBox;
	private DDCharacter warrior;
	private Image enemy = null, enemy2 = null, enemy3 = null;
	//private int frame = 0;
	//private Image image
	
	public MapTestState(int stateID) {
		this.stateID = stateID;
	}
	
	public int getID() {
		return stateID;
	}
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
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
		
		
		arg0.setVSync(true);
		//SpriteSheet sheet = new SpriteSheet("Images/Test/karbonator.png",32,32);
		//SpriteSheet ogre = new SpriteSheet("Resources/DnD-OgreLeader.png", 130, 135);
		//Vector2f actionBoxPosition = new Vector2f(600f, 200f);
		//Vector2f characterPosition = new Vector2f(0f, 0f);
		warrior = new DDCharacter(stateID);
		warrior.setCharacterSheet(sheet);
		actionBox = new ActionBox(stateID, 300, 200);
		actionBox.setCharacter(warrior);
		
		Image characters = new Image("Images/Test/DungeonCrawl_ProjectUtumnoTileset.png");
		//System.out.println("Image height: " + characters.getHeight());
		//System.out.println("Image width: " + characters.getWidth());
		
		/*2 up from bottom, 16 across */
		//Image warrior = characters.getSubImage(2530, 1440, 33, 34);
		//Target block image
		Image warrior = characters.getSubImage(1473, 513, 33, 34);
	
		
		
		/* 16 across, 7 down */
		enemy = characters.getSubImage(480, 194, 33, 34);
		enemy2 = characters.getSubImage(480, 194, 33, 34);
		enemy3 = characters.getSubImage(480, 194, 33, 34);
		
		map = new TiledMap("Images/Test/dungeon.tmx");		
		player = new Animation();
		player.setAutoUpdate(true);
		
		//System.out.println("Map height: "+ map.getHeight());	//height is 30
		//System.out.println("Map width: " + map.getWidth());		//width is 40
		
		player.addFrame(warrior, 150);
		
	}
	
	public void update(GameContainer container, StateBasedGame sb, int delta) throws SlickException { 
		/* Want to make character move faster */
		if (container.getInput().isKeyDown(Input.KEY_LEFT)) {
			playerX = playerX - 2;
		}
		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) {
			playerX = playerX + 2;
		}
		if (container.getInput().isKeyDown(Input.KEY_UP)) {
			playerY = playerY - 2;
		}
		if (container.getInput().isKeyDown(Input.KEY_DOWN)) {
			playerY = playerY + 2;
		}
		
		/* go through ArrayList of Components to call their update methods */
		RenderComponent renderComponent = null;
		for (Component component : actionBox.getComponentList())
		{
			if (RenderComponent.class.isInstance(component))
			{
				renderComponent = (RenderComponent) component;
				renderComponent.update(container, sb, delta);
			}
			
		}
		
		charToString = "CHARACTER SHEET: \n" + warrior.getCharacterSheet().toString();
		
	}
	public void render(GameContainer container, StateBasedGame sb, Graphics g) throws SlickException {
		/* render map */
		g.drawString(charToString, 800, 30);
		map.render(0, 0);
		/* render enemies */
		enemy.draw(200, 100);
		enemy2.draw(160, 100);
		enemy3.draw(240, 100);
		
		/* go through ArrayList of Components to call their render methods */
		RenderComponent renderComponent = null;
		for (Component component : actionBox.getComponentList())
		{
			if (RenderComponent.class.isInstance(component))
			{
				renderComponent = (RenderComponent) component;
				renderComponent.render(container, sb, g);
			}
			
		}
		
		g.drawAnimation(player, playerX, playerY);
	}
}