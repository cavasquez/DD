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
import DD.ActionBox.Dice;
import DD.Character.DDCharacter;
import DD.Character.Abilities.Ability;
import DD.Character.Abilities.DefaultAbilities.Move.Move;
import DD.Character.Abilities.DefaultAbilities.Standard.StandardAttack;
import DD.Character.CharacterSheet.CharacterClass;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.Character.Equipment.Weapon;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.TargetingSystem.Coordinate;
import DD.CombatSystem.TargetingSystem.TargetingSystem;
import DD.Message.ChooseTargetMessage;

// @author Carlos Vasquez

public class TestTargeting extends BasicGame
{
	public TestTargeting (String title) {
		super(title);

	}
 
    public TestTargeting()
    {
        super("Slick2DPath2Glory - SimpleGame");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
    	TargetingSystem ts = new TargetingSystem();
		CombatSystem cs = new CombatSystem();
		
		/*
		 * IMPORTANT: for this main to work, mapSize needs to be 21 or larger due to hardcodes massPlaceObjectsLine values.
		 * other wise you will get nullpointers.
		 */
		
		
		World world = new World("TESTME");
		TargetingSystem.setMap(world.world[0][0]);
	
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
		world.world[0][0].massPlaceObjectsLine(0, 1, 0, 20, wall);
		world.world[0][0].massPlaceObjectsLine(1, 10, 10, 10, wall);
		world.world[0][0].massPlaceObjectsLine(10, 9, 10, 1, wall);
		world.world[0][0].massPlaceObjectsLine(1, 20, 10, 20, wall);
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
				
		DDCharacter character = new DDCharacter(0);
		int x = 6;
		int y = 5;
		CharacterObjects charObj = new CharacterObjects("*****", null,0,0, world.world[0][0], character);
		world.world[0][0].placeObjects(x, y, charObj); /* place character */
		character.setCoordiante(new Coordinate(x,y));
		character.setCharacterID(0);
		
		ChooseTargetMessage ctm = new ChooseTargetMessage
				(
						TargetingSystem.TargetCount.SINGLE,
						TargetingSystem.TargetShape.MOVE,
						TargetingSystem.TargetSelection.SELECTED,
						false,
						character.getCoordinate(),
						5,
						null
				);
		ObjectsPriorityStack[][] stack = world.world[0][0].objectsStack;
		ts.chooseTarget(ctm);
	
		System.out.println(character.getMovedDiagonal());
		System.out.println(world.world[0][0].toString());
		
		/* Test the clearTargets() method */
		System.out.println("Testing clearTargets method");
		ts.clearTargets();
		System.out.println(world.world[0][0].toString());
		ts.clearTargets();
		System.out.println(world.world[0][0].toString());
		
		/*
		System.out.println("****************************************************");
		System.out.println("Place a Character and start moving it around the map");
		System.out.println("placeObjects(x, y, characterObjects)");
		System.out.println("removeObjects(x, y)           vvvvv");
		System.out.println("******************************     ******************");
		*/
		
		/*
		System.out.println("*********************");
		System.out.println("Movement down the map");
		System.out.println("*********************");
		*/
		/*
		for (int j = 0; j < 5; j++) {
				world.world[0][0].placeObjects(6, j, charObj);
				System.out.println(world.world[0][0].toString());
				world.world[0][0].removeObjects(6,j);
			}
		*/
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
			new AppGameContainer(new TestTargeting());
 
         //app.setDisplayMode(800, 600, false);
         app.start();
    }
    
    
}
