package DD.MapTool;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import DD.CombatSystem.TargetingSystem.Coordinate;
import DD.SlickTools.*;

public class Objects extends ImageRenderComponent implements Serializable{//figure out comp.
	private static final long serialVersionUID = 7499478093741949923L;
	protected int movePenalty;
	protected int lightPenalty;
	protected Coordinate position = null;
	String name;
	int priority;
	static protected transient Image spriteSheet = null;
	static protected Map owner = null;
	protected static Input mouse = new Input(650);
	

	public Objects(String name, DDImage image, Map owner, Integer x, Integer y) throws SlickException {
		
		super(0, image);
		if (spriteSheet == null) spriteSheet = new Image("Images/Test/DungeonCrawl_ProjectUtumnoTileset.png");
		this.name = name;
		this.position = new Coordinate(x,y);
		// TODO
		Objects.owner = owner;
		if(owner!=null) owner.addComponent(this);
	}
	
	public Objects(String name, Map owner) throws SlickException
	{
		super(0);
		if (spriteSheet == null) spriteSheet = new Image("Images/Test/DungeonCrawl_ProjectUtumnoTileset.png");
		this.name = name;
		this.position = new Coordinate(0,0);
		// TODO
		Objects.owner = owner;
		if(owner!=null) owner.addComponent(this);
	}
	
	public void select() throws SlickException {};
	
	public void makeDDImage(){
		image.makeImage();
	}
	
	public Image getImage(){
		return spriteSheet;
	}
	
	public void setImage(Image image){
		spriteSheet = image;
	}	
	
	public int getMovePenalty(){
		return movePenalty;
	}
	public void setMovePenalty(int mp){
		this.movePenalty = mp;
	}
	public int getlightPenalty(){
		return lightPenalty;
	}
	public void setlightPenalty(int lp){
		this.lightPenalty = lp;
	}
	public int getPriority(){
		return priority;
	}
		
	/*
	 * Default constructor used for Serializable.
	 */
	public Objects() {
		super();
		// TODO Auto-generated constructor stub
	}

	//set owner is in component.
	
	public void writeMe(String InName,String path){
		try{
			FileOutputStream fileOut = new FileOutputStream(path+InName+".ser");
			ObjectOutputStream out =  new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		int mouseX = mouse.getMouseX();
		int mouseY = mouse.getMouseY();
		
		float x = position.x;
		float y = position.y + 1;
		
//		System.out.println("x: " + position.x);
//		System.out.println("y: " + position.y);
		if(image !=null){
			if( (mouseX >= x*30.85 && mouseX <= x*30.85 + image.getWidth() ) &&
					(mouseY >= y*30 && mouseY <= y*30 + image.getHeight() ) )
			{
				/* You are inside the button */
				
				//if(mouse.isMousePressed(Input.MOUSE_LEFT_BUTTON)) 
				if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
				{
					String message = "You are clicking " + position.x + ", " + position.y;
					try {
						select();
						//System.out.println(message);
					} catch (SlickException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		Color myAlphaColor = new Color(1f, 1f, 1f, 1f);
		float x = position.x;
		float y = position.y;
		
		if(!Wall.class.isInstance(this) && 
				!Floor.class.isInstance(this) && !TargetBlock.class.isInstance(this))
		{
			float xCorrection = 30.85f;
			float yCorrection = 30;
			if(image != null)image.draw(x*xCorrection, (y+1)*yCorrection);
			
		}
		else {
			//System.out.println("carlos' mother");
			if(image != null)image.draw((x + (image.getHeight() * x)), ((y+40) + (image.getWidth() * y)));	//y offset by 40 to shift down a little
			//System.out.println("chris' mother");
		}
		//if(CharacterObjects.class.isInstance(this)) System.out.println("x: " + (x + (image.getHeight() * x)) + ", y: " + (y + (image.getHeight() * y)));
		//gr.drawString(message, 110, 10);
		//If opacity doesn't work then we could use another tile to represent clickable squares
		//this.image.setAlpha(0.8f);
		//this.image.draw(x, y);
	}
	
	public void setPosition(int x, int y)
	{
		position = new Coordinate(x,y);
	}
}
