package slick.path2glory.SimpleGame;
 
import java.io.Console;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
 
public class TestGame extends BasicGame{
	public TestGame(String title) {
		super(title);

	}

	Image land = null;
	Image plane = null;
	float x = 400;
	float y = 300;
	float scale = 1.0f;
	Console console = System.console();
 
    public TestGame()
    {
        super("Slick2DPath2Glory - SimpleGame");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
    	/* This method initializes the game */
    	land = new Image("Images/fetch.jpg");
    	plane = new Image("Images/plane.png");
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
    	/* This method establishes what the user input is and 
    	 * what is to be done with the user input. It also
    	 * updates all game related data. This is
    	 * where game logic should go. */
    	Input input = gc.getInput();
    	
    	 if(input.isKeyDown(Input.KEY_A))
         {
             plane.rotate(-0.2f * delta);
         }
  
         if(input.isKeyDown(Input.KEY_D))
         {
             plane.rotate(0.2f * delta);
         }
  
         if(input.isKeyDown(Input.KEY_W))
         {
             float hip = 0.4f * delta;
  
             float rotation = plane.getRotation();
  
             x+= hip * Math.sin(Math.toRadians(rotation));
             y-= hip * Math.cos(Math.toRadians(rotation));
         }
         
         if(input.isKeyDown(Input.KEY_S))
         {
             float hip = 0.4f * delta;
  
             float rotation = plane.getRotation();
  
             x-= hip * Math.sin(Math.toRadians(rotation));
             y+= hip * Math.cos(Math.toRadians(rotation));
         }
  
         if(input.isKeyDown(Input.KEY_2))
         {
             scale += (scale >= 5.0f) ? 0 : 0.1f;
             plane.setCenterOfRotation(plane.getWidth()/2.0f*scale, plane.getHeight()/2.0f*scale);
         }
         if(input.isKeyDown(Input.KEY_1))
         {
             scale -= (scale <= 1.0f) ? 0 : 0.1f;
             plane.setCenterOfRotation(plane.getWidth()/2.0f*scale, plane.getHeight()/2.0f*scale);
         }
    }
 
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
    	/* This method "updates" the GUI with information based on 
    	 * update() */
    	land.draw(0,0);
    	plane.draw(x, y, scale);
    }
 
    public static void main(String[] args) 
			throws SlickException
    {
         AppGameContainer app = 
			new AppGameContainer(new TestGame());
 
         app.setDisplayMode(800, 600, false);
         app.start();
    }
}