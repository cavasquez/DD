package DD.MapTool;

import java.io.Console; 

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import slick.path2glory.SimpleGame.TestGame;
import DD.ActionBox.ActionBox;
import DD.Character.DDCharacter;
import DD.Character.Abilities.Ability;
import DD.Character.Abilities.DefaultAbilities.Move.Move;
import DD.Character.CharacterSheet.CharacterClass;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.TargetingSystem.Coordinate;
import DD.CombatSystem.TargetingSystem.TargetingSystem;
import DD.Message.ChooseTargetMessage;
import DD.System.DDSystem;

// @author Carlos Vasquez

public class TestMove extends BasicGame
{
	public TestMove(String title) {
		super(title);

	}
 
    public TestMove()
    {
        super("Slick2DPath2Glory - SimpleGame");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
    	DDSystem system = new DDSystem();
    	ActionBox ab = new ActionBox(0, 0, 0);
    	system.linkBoxes(ab, null);
		
		/*
		 * IMPORTANT: for this main to work, mapSize needs to be 21 or larger due to hardcodes massPlaceObjectsLine values.
		 * other wise you will get nullpointers.
		 */
		
		/* VERY FREAKING IMPORTANT STUFF */
		World world = new World("TESTME");
		TargetingSystem.setMap(world.world[0][0]);
		system.cs.setMap(world.world[0][0]);
	
		/*
		 * proof of concept movement backend
		 * the Map is implemented using a "priority stack"
		 */
		
		System.out.println("****************");
		System.out.println("World Map - map0");
		System.out.println("****************");
		//System.out.println(world.world[0][0].toString());
		
		/*
		System.out.println("****************************************************");
		System.out.println("MassPlaceObjectsLine() --place some walls on my map0");
		System.out.println("****************************************************");
		*/
		Wall wall = new Wall("walll", null,0,0, world.world[0][0]);
		world.world[0][0].massPlaceObjectsLine(0, 0, 10, 0, wall);
		world.world[0][0].massPlaceObjectsLine(0, 1, 0, 19, wall);
		world.world[0][0].massPlaceObjectsLine(1, 10, 10, 10, wall);
		world.world[0][0].massPlaceObjectsLine(10, 9, 10, 1, wall);
		world.world[0][0].massPlaceObjectsLine(1, 19, 10, 19, wall);
		world.world[0][0].massPlaceObjectsLine(10, 11, 10, 19, wall);

		/*
		System.out.println("********************************");
		System.out.println("Remove some walls to make a door  vvvv");
		System.out.println("********************************");
		*/
		world.world[0][0].removeObjects(6, 0);
		world.world[0][0].removeObjects(7, 0);
		world.world[0][0].removeObjects(6, 10);
		world.world[0][0].removeObjects(7, 10);
		//System.out.println(world.world[0][0].toString());
				
		int i = 0; // component and entity ids
		DDCharacter character = new DDCharacter(i++);
		CharacterSheet sheet = new CharacterSheet();
		
		/* ALSO VERY FREAKING IMPORTANT */
		character.setCharacterID(i++);
		system.cs.addCharacter(character);
		
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
		
		character.setCharacterSheet(sheet);
		ab.setCharacter(character);
		
		
		
		int x = 6;
		int y = 5;
		CharacterObjects charObj = new CharacterObjects("*****", null,0,0, world.world[0][0], character);
		world.world[0][0].placeObjects(x, y, charObj); /* place character */
		character.setCoordiante(new Coordinate(x,y));
		Ability.setOwnerCharacter(character); /* set the character who is performing the abilities. This should happen somewhere in ActionBox */
		character.startNewTurn();
		
		Move move = new Move(i++);
		move.activate();
//		
//		ChooseTargetMessage ctm = new ChooseTargetMessage
//				(
//						TargetingSystem.TargetCount.SINGLE,
//						TargetingSystem.TargetShape.MOVE,
//						TargetingSystem.TargetSelection.SELECTED,
//						false,
//						character.getCoordinate(),
//						5,
//						null
//				);
//		ObjectsPriorityStack[][] stack = world.world[0][0].objectsStack;
//		system.ts.chooseTarget(ctm);
	
		System.out.println(character.getMovedDiagonal());
		System.out.println(world.world[0][0].toString());
		
		/* Move down */
		int newx = x; //x being moved to
		int newy = y; //y being moved to
		newy -=1;
		ObjectsPriorityStack stack = world.world[0][0].objectsStack[newx][newy]; // where the character is trying to move
		((TargetBlock)stack.peek()).select();
		System.out.println(world.world[0][0].toString());
		System.out.println("Move to: " + newx + ", " + newy);
		System.out.println(character.getCoordinate().x + ", " + character.getCoordinate().y);
		System.out.println(character.getCurrentSpeed());
		System.out.println("has moved diagonal: " + character.getMovedDiagonal());
		
		newx -= 1;
		newy +=1;
		stack = world.world[0][0].objectsStack[newx][newy]; // where the character is trying to move
		((TargetBlock)stack.peek()).select();
		System.out.println(world.world[0][0].toString());
		System.out.println("Move to: " + newx + ", " + newy);
		System.out.println(character.getCoordinate().x + ", " + character.getCoordinate().y);
		System.out.println(character.getCurrentSpeed());
		System.out.println("has moved diagonal: " + character.getMovedDiagonal());
		
		newx -= 1;
		newy +=1;
		stack = world.world[0][0].objectsStack[newx][newy]; // where the character is trying to move
		((TargetBlock)stack.peek()).select();
		System.out.println(world.world[0][0].toString());
		System.out.println("Move to: " + newx + ", " + newy);
		System.out.println(character.getCoordinate().x + ", " + character.getCoordinate().y);
		System.out.println(character.getCurrentSpeed());
		System.out.println("has moved diagonal: " + character.getMovedDiagonal());
		
		newx -= 1;
		stack = world.world[0][0].objectsStack[newx][newy]; // where the character is trying to move
		((TargetBlock)stack.peek()).select();
		System.out.println(world.world[0][0].toString());
		System.out.println("Move to: " + newx + ", " + newy);
		System.out.println(character.getCoordinate().x + ", " + character.getCoordinate().y);
		System.out.println(character.getCurrentSpeed());
		System.out.println("has moved diagonal: " + character.getMovedDiagonal());
		
		newx -=1;
		stack = world.world[0][0].objectsStack[newx][newy]; // where the character is trying to move
		((TargetBlock)stack.peek()).select();
		System.out.println(world.world[0][0].toString());
		System.out.println("Move to: " + newx + ", " + newy);
		System.out.println(character.getCoordinate().x + ", " + character.getCoordinate().y);
		System.out.println(character.getCurrentSpeed());
		System.out.println("has moved diagonal: " + character.getMovedDiagonal());
		
		System.out.println("Character can move?: " + character.getHasMoveAction());
		
		/* Test termination of move before speed */
		character.startNewTurn();
		move = new Move(i++);
		System.out.println("New turn");
		System.out.println(character.getCurrentSpeed());
		System.out.println("has moved diagonal: " + character.getMovedDiagonal());
		
		System.out.println("Character can move?: " + character.getHasMoveAction());
		
		newx -=1;
		move.activate();
		stack = world.world[0][0].objectsStack[newx][newy]; // where the character is trying to move
		((TargetBlock)stack.peek()).select();
		System.out.println(world.world[0][0].toString());
		System.out.println("Move to: " + newx + ", " + newy);
		System.out.println(character.getCoordinate().x + ", " + character.getCoordinate().y);
		System.out.println(character.getCurrentSpeed());
		System.out.println("has moved diagonal: " + character.getMovedDiagonal());
		
		System.out.println("Character can move?: " + character.getHasMoveAction());
		
		move.done();
		stack = world.world[0][0].objectsStack[newx][newy]; // where the character is trying to move
		System.out.println(world.world[0][0].toString());
		System.out.println("Move to: " + newx + ", " + newy);
		System.out.println(character.getCoordinate().x + ", " + character.getCoordinate().y);
		System.out.println(character.getCurrentSpeed());
		System.out.println("has moved diagonal: " + character.getMovedDiagonal());
		
		System.out.println("Character can move?: " + character.getHasMoveAction());
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {

    }
 
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {

    }
 
    public static void main(String[] args) 
			throws SlickException
    {
    	AppGameContainer app = 
			new AppGameContainer(new TestMove());
 
         //app.setDisplayMode(800, 600, false);
         app.start();
    }
    
    
}
