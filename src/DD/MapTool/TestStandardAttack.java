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

public class TestStandardAttack extends BasicGame
{
	public TestStandardAttack(String title) {
		super(title);

	}
 
    public TestStandardAttack()
    {
        super("Slick2DPath2Glory - SimpleGame");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
    	TargetingSystem ts = new TargetingSystem();
		CombatSystem cs = new CombatSystem();
		ActionBox ab = new ActionBox(5, 0, 0);
		
		/*
		 * IMPORTANT: for this main to work, mapSize needs to be 21 or larger due to hardcodes massPlaceObjectsLine values.
		 * other wise you will get nullpointers.
		 */
		
		/* VERY FREAKING IMPORTANT STUFF */
		World world = new World("TESTME");
		TargetingSystem.setMap(world.world[0][0]);
		CombatSystem.setMap(world.world[0][0]);
	
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
		Wall wall = new Wall("walll", null, world.world[0][0]);
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
				
		int i = 0; // component and entity ids
		DDCharacter character = new DDCharacter(i++);
		DDCharacter enemy = new DDCharacter(i++);
		CharacterSheet sheet = new CharacterSheet();
		sheet.EquipWeapon(new Weapon("Longsword",Dice.DieSize.D6,2,19,5,'M','S',"Note:",4));
		
		/* ALSO VERY FREAKING IMPORTANT */
		character.setCharacterID(i++);
		enemy.setCharacterID(i++);
		CombatSystem.addCharacter(character);
		CombatSystem.addCharacter(enemy);
		character.setCharacterSheet(sheet);
		enemy.setCharacterSheet(sheet);
		
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
		
		ActionBox.setCharacter(character); // Character is performing actions
		
		
		
		int x = 6;
		int y = 5;
		int enemyx = x;
		int enemyy = y+1;
		CharacterObjects charObj = new CharacterObjects("*****", null, world.world[0][0], character);
		CharacterObjects enemyObj = new CharacterObjects("+++++", null, world.world[0][0], enemy);
		world.world[0][0].placeObjects(x, y, charObj); /* place character onto map */
		world.world[0][0].placeObjects(enemyx, enemyy, enemyObj); /* place enemy onto map */
		character.setCoordiante(new Coordinate(x,y));
		enemy.setCoordiante(new Coordinate(enemyx, enemyy));
		Ability.setOwnerCharacter(character); /* set the character who is performing the abilities. This should happen somewhere in ActionBox */
		character.resetCharacter();
		enemy.resetCharacter();
		character.startNewTurn();
		
//		Move move = new Move(i++);
//		move.activate();
		StandardAttack attack = new StandardAttack(i++);
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
//		ts.chooseTarget(ctm);
	
		System.out.println("Players have been set");
		System.out.println(world.world[0][0].toString());
		System.out.println("Attack: " + enemyx + ", " + enemyy);
		System.out.println("Character Position: " + character.getCoordinate().x + ", " + character.getCoordinate().y);
		System.out.println("Enemy Position: " + enemy.getCoordinate().x + ", " + enemy.getCoordinate().y);
		System.out.println("Enemy hp: " + enemy.getCurrentHP());
		System.out.println("Enemy AC: " + enemy.getAC());
		System.out.println("Character can attack??: " + character.getHasStandardAction());
		System.out.println("Weapon reach: " + character.getWeaponReach()[0]);

		attack.activate();
		
		System.out.println("Character activates attack, should see target slection");
		System.out.println(world.world[0][0].toString());
		System.out.println("Attack: " + enemyx + ", " + enemyy);
		System.out.println("Character Position: " + character.getCoordinate().x + ", " + character.getCoordinate().y);
		System.out.println("Enemy hp: " + enemy.getCurrentHP());
		System.out.println("Enemy AC: " + enemy.getAC());
		System.out.println("Enemy armor AC: " + enemy.getACArmor());
		System.out.println("Enemy Position: " + enemy.getCoordinate().x + ", " + enemy.getCoordinate().y);
		System.out.println("Character can attack??: " + character.getHasStandardAction());
		
		
		System.out.println("Character has selected enemy as an attack target");
		ObjectsPriorityStack stack = world.world[0][0].objectsStack[enemyx][enemyy]; // where the character is trying to attack
		System.out.println(ts.getTargetBlock(stack));
		ts.getTargetBlock(stack).select();
		
		System.out.println(world.world[0][0].toString());
		System.out.println("Attack: " + enemyx + ", " + enemyy);
		System.out.println("Character Position: " + character.getCoordinate().x + ", " + character.getCoordinate().y);
		System.out.println("Enemy Position: " + enemy.getCoordinate().x + ", " + enemy.getCoordinate().y);
		System.out.println("Enemy hp: " + enemy.getCurrentHP());
		System.out.println("Enemy AC: " + enemy.getAC());
		System.out.println("Character can attack?: " + character.getHasStandardAction());
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
			new AppGameContainer(new TestStandardAttack());
 
         //app.setDisplayMode(800, 600, false);
         app.start();
    }
    
    
}
