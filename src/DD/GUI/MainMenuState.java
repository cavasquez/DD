package DD.GUI;
import org.newdawn.slick.state.BasicGameState;  
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.state.StateBasedGame;
 
public class MainMenuState extends BasicGameState {
	int stateID = 0;
	
	private Image background = null;
	private Image startOption = null;
	private Image exitOption = null;
	private float startOptionScale = 1.0f;
	private float exitOptionScale = 1.0f;
	private float scaleStep = 0.0001f;
	
	public MainMenuState(int stateID) {
		this.stateID = stateID;
	}
	
	public int getID() {
		return stateID;
	}
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		background = new Image("Images/Test/titleBackground.png");
		Image menuOptions = new Image("Images/Test/options.png");
		
		startOption = menuOptions.getSubImage(0, 0, 377, 71);
		exitOption = menuOptions.getSubImage(0, 71, 377, 71);
		//title = new Image("Resources/PathfinderTitle.png");
		
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException{ 
		/* Make buttons do stuff */
		
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
		
		boolean insideStart = false;
		boolean insideExit = false;
		
		if( ( mouseX >= 400 && mouseX <= 400 + startOption.getWidth() ) &&
		   ( mouseY >= 175 && mouseY <= 175 + startOption.getHeight() ) ) 
		{	
				insideStart = true;
		}
		else if( ( mouseX >= 625 && mouseX <= 625 + exitOption.getWidth() ) &&
				( mouseY >= 100 && mouseY <= 100 + exitOption.getHeight() ) )
		{
			insideExit = true;
		}
		
		/* Make buttons enlarge a bit when mouse is over them */
		if(insideStart) {
			if(startOptionScale < 1.05f)
				startOptionScale += scaleStep * delta;
			
			/* Enter GamePlayState if the mouse is clicked */
			if(Mouse.isButtonDown(0))
				sb.enterState(Game.GAMEPLAYSTATE);
		}
		else {
			/* Make buttons original size if mouse is not over them */
			if(startOptionScale > 1.0f)
				startOptionScale -= scaleStep * delta;
		}
		
		if(insideExit) {
			if(exitOptionScale < 1.05f)
				exitOptionScale += scaleStep * delta;
			
			if(Mouse.isButtonDown(0))
			{
				System.exit(0);
			}
		}
		else {
			if(exitOptionScale > 1.0f)
				exitOptionScale -= scaleStep * delta;
		}
		
		
	}
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)  {
		background.draw(0, 0, 1.3f);
		startOption.draw(400, 350, startOptionScale);
		exitOption.draw(400, 425, exitOptionScale);
	}
}