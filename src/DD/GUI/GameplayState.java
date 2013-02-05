package DD.GUI;
import org.newdawn.slick.Animation;
//import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.state.StateBasedGame;
 
public class GameplayState extends BasicGameState {
	private int stateID = 0;
	
	private float playerX=320;
	private float playerY=240;
	private TiledMap map;	
	private Animation player;
	//private int frame = 0;
	//private Image image
	
	public GameplayState(int stateID) {
		this.stateID = stateID;
	}
	
	public int getID() {
		return stateID;
	}
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		arg0.setVSync(true);
		SpriteSheet sheet = new SpriteSheet("Resources/karbonator.png",32,32);
		//SpriteSheet ogre = new SpriteSheet("Resources/DnD-OgreLeader.png", 130, 135);
		
		map = new TiledMap("Resources/tile.tmx");		
		player = new Animation();
		player.setAutoUpdate(true);	
		
		System.out.println("Horizontal: " + sheet.getHorizontalCount());
		System.out.println("Vertical: " + sheet.getVerticalCount());
		
		System.out.println("Map height: "+ map.getHeight());	//height is 30
		System.out.println("Map width: " + map.getWidth());		//width is 40
		//Each "tile" is 5x5 tiles
		
		
		
		player.addFrame(sheet.getSprite(0, 1), 150);
		
		/*
		for (int frame=0;frame<8;frame++) {
			player.addFrame(ogre.getSprite(frame,1), 150);
		}
		*/
		
		
	}
	
	public void update(GameContainer container, StateBasedGame sb, int delta) throws SlickException { 
		
		/*
		if (container.getInput().isKeyDown(Input.KEY_LEFT)) {playerX--;}
		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) {playerX++;}
		if (container.getInput().isKeyDown(Input.KEY_UP)) {playerY--;}
		if (container.getInput().isKeyDown(Input.KEY_DOWN)) {playerY++;}
		*/
		
		/* Want to make character move faster */
		
		if (container.getInput().isKeyDown(Input.KEY_LEFT)) {
			playerX = playerX - 2;
		}
		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) {playerX = playerX + 2;}
		if (container.getInput().isKeyDown(Input.KEY_UP)) {playerY = playerY - 2;}
		if (container.getInput().isKeyDown(Input.KEY_DOWN)) {playerY = playerY + 2;}
		
	}
	public void render(GameContainer container, StateBasedGame sb, Graphics g) throws SlickException {
		map.render(0, 0);
		g.drawAnimation(player, playerX, playerY);
	}
	/*
	public static void main(String[] argv) throws SlickException {
		AppGameContainer container = 
			new AppGameContainer(new Game(), 640, 480, false);
		container.start();
	}
	*/
}