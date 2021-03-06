package DD.GMToolsBox;
 
import java.util.ArrayList;  
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import DD.Character.CharacterSheet.CharacterSheet;
import DD.CombatSystem.TargetingSystem.Coordinate;
import DD.GUI.Game;
import DD.MapTool.MapTool;
import DD.SlickTools.BoxInterface;
import DD.SlickTools.DDImage;
import DD.SlickTools.ImageRenderComponent;
import DD.SlickTools.RenderComponent;

/*****************************************************************************************************
 * The GMToolsBox class will hold the GM abilities. It will provide the GM with many of his necessary
 * tools such as the ability to start combat or place characters on the board.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class GMToolsBox extends BoxInterface 
{
	/************************************ Class Constants *************************************/
	private static int I= 0;
	public static enum Action
	{
		START_COMBAT_PHASE(I++),
		PLACE_CHARACTER(I++),
		REMOVE_CHARACTER(I++);
		
		public final int index;
		public static final int NUM_OF_ACTIONS = I;
		
		Action (int index)
		{
			this.index = index;
		} /* end TargetCount index */
		
		public int index()
		{
			return index;
		} /* end index for enum */
		
	} /* end Action enum */
	
	public static enum Holder
	{
		/* The enum that will correlate with the holder ArrayList */
		MOB(I++),
		PLAYER(I++);
		
		public final int index;
		public static final int NUM_OF_HOLDERS = I;
		
		Holder (int index)
		{
			this.index = index;
		} /* end TargetCount index */
		
		public int index()
		{
			return index;
		} /* end index for enum */
		
	} /* end Action enum */

	/************************************ Class Attributes *************************************/
	private int characterID; 							/* Unique ID's for each character */
	private Queue<Integer> recycledCIDs;				/* ID's of objects that have given up their id (thus the ID can be used again */
	private ArrayList<ArrayList<HolderTuple>> holder;	/* The holder array will hold either mob characters or player characters NOT on the field*/
	private Set<Integer> charactersInPlay;				/* A set containing the ID's of all the characters in play. It should mirror the one in the CombatSystem */
	private MapTool maptool;							
	private int shift;
	private static Input mouse = new Input(650);
	private DDImage clearSelection;
	
	/************************************ Button Images *************************************/
	private Image startCombatPhaseButton= null;
	
	public GMToolsBox(int id, float length, float width) throws SlickException
	{
		super(id, length, width);
		recycledCIDs = new LinkedList<Integer>();
		charactersInPlay = new TreeSet<Integer>();
		holder = new ArrayList<ArrayList<HolderTuple>>();
		holder.add(Holder.MOB.index, new ArrayList<HolderTuple>());
		holder.add(Holder.PLAYER.index, new ArrayList<HolderTuple>());
		
		clearSelection = new DDImage("Images/MapTool/ClearSelection.png");
		startCombatPhaseButton= new Image("Images/GMTools/StartCombatPhase.png"); 
		//placeCharacter = new Image("Images/GMTools/PlaceCharacter.png");
		//removeCharacter = new Image("Images/GMTools/RemoveCharacter.png");
		
		shift = startCombatPhaseButton.getHeight();
		Vector2f boxPosition = new Vector2f(660f, 10f);
		this.setPosition(boxPosition);
		
		/* To begin with, the basic ActionChoices need to be available. */
		this.addComponent(new StartCombatPhase(this.id));
		this.addComponent(new RemoveCharacter(this.id, this));
		
	} /* end GMToolsBox constructor */
	
	public GMToolsBox() throws SlickException
	{
		this(0, 0, 0);
	} /* end overloaded constructor */
	
	public PlaceCharacter addCharacter(Holder type, CharacterSheet sheet)
	{
		PlaceCharacter returner;
		/* Add PlaceCharacter object. */
		this.addComponent(returner = new PlaceCharacter(this.id, sheet, this, type));
		
		/* Add a character to the holder.  */
		holder.get(type.index).add(new HolderTuple(sheet, returner.getId()));
		System.out.println("GMToolsBox " + sheet.getName() + " placed into GMToolsBox");
		return returner;
		
	} /* end addCharacter method */
	
	public PlaceCharacter addCharacter(Holder type, CharacterSheet sheet, Coordinate position, boolean reset)
	{
		PlaceCharacter returner = addCharacter(type, sheet);
		try 
		{
			returner.place(position, reset);
		} /* end try */ 
		catch 
		(SlickException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /* end catch */
		
		return returner;
		
	} /* end addCharacter method */
	
	public void removeCharacter(Holder type, int id)
	{
		/* remove character from holder. Note that the id represents the component id */
		int index = -1;
		
		for(int i = 0; i < holder.get(type.index).size(); i++)
		{
			if(holder.get(type.index).get(i).id == id) index = i;
		} /* end for loop */

		if (index != -1)  holder.get(type.index).remove(index);

	} /* end removeCharacter method */
	
	public void clearHolder(Holder type)
	{
		/* remove all PlaceCharacter components */
		for(HolderTuple remove : holder.get(type.index)) removeComponent(remove.id);
		
		/* remove all characters from the holder */		
		holder.get(type.index).clear();
		
	} /* end clearHolder */
	
	public Integer getNewCharacterID()
	{
		Integer id = this.characterID;
		if((id = recycledCIDs.poll()) == null) id = this.characterID++; /* Take a recycled id. If none exists, take an ID from id and increment it */

		charactersInPlay.add(id);
		return id;
	} /* end addCharacterToPlay method */
	
	public void removeCharacterID(Integer id)
	{
		charactersInPlay.remove(id);
		recycledCIDs.offer(id);
		
	} /* end removeCharacter method */
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) 
	{
		int posX = mouse.getMouseX();
    	int posY = mouse.getMouseY();
    	try {
			super.update(gc, sbg, delta);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	//clear selection button
    	if((posX > 950 && posX < 950 + clearSelection.getWidth()) && (posY > 120 && posY < (120 + clearSelection.getHeight()))) {
    		//if you click on the button
    		if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON)) {
    			ClearSelection clear = new ClearSelection(0);
    			try {
					clear.action();
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
		
	} /* end update method */
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) 
	{
		//render start combat phase and remove character buttons
		ImageRenderComponent renderComponent = null;
		int delta = 50;
		for(int i = 0; i < components.size(); i++)
		{
			if (ImageRenderComponent.class.isInstance(components.get(i)))
			{
				renderComponent = (ImageRenderComponent) components.get(i);
				if(components.get(i) instanceof PlaceCharacter)
				{
					Vector2f pos = new Vector2f(950, 110 + delta );
					delta += 50;
					renderComponent.render(gc, sbg, gr, pos);
				} /* end if */
				else
				{
					renderComponent.render(gc, sbg, gr);
				} /* end else */
				
			} /* end if */
		} /* end for loop */
		
		clearSelection.draw(950, 120);
		
	} /* end render method */
	
	/****************************************************************************************
	 ************************************ Getter Methods ************************************
	 ****************************************************************************************/
	public HolderTuple[] getHolder(Holder type)
	{
		return holder.get(type.index).toArray(new HolderTuple[holder.get(type.index).size()]);
	} /* end getHolder method */
	
	public int getCharacterID()
	{
		return characterID;
	} /* end getCharacterID */
	
	public Integer[] getCharactersInPlay()
	{
		return charactersInPlay.toArray(new Integer[charactersInPlay.size()]);
	} /* end getCharactersInPlay method */
	
	public MapTool getMapTool()
	{
		return maptool;
	} /* end getMapTool method */
	
	/****************************************************************************************
	 ************************************ Setter Methods ************************************
	 ****************************************************************************************/
	public void setMapTool(MapTool maptool)
	{
		this.maptool = maptool;
	} /* end setMapTool method */
	
	public boolean setMap()
	{
		/* sets the current map. if maptool is null, return false */
		boolean returner = false;
		if(maptool != null)
		{
			SetMap setmap = new SetMap(this.id, maptool.getCurrentMap());
			try 
			{
				setmap.action();
				returner = true;
			} /* end try */ 
			catch (SlickException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} /* end catch */

		} /* end if */
		return returner;
	} /* end setMap method */
	
} /* end GMToolsBox method */
