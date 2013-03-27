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
import DD.System.DDSystem;

// @author Carlos Vasquez

public class TestDDCharacter extends BasicGame
{
	public TestDDCharacter(String title) {
		super(title);

	}
 
    public TestDDCharacter()
    {
        super("Slick2DPath2Glory - SimpleGame");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
    	DDSystem system = new DDSystem();
		ActionBox ab = new ActionBox(5, 0, 0);
		
		/*
		 * IMPORTANT: for this main to work, mapSize needs to be 21 or larger due to hardcodes massPlaceObjectsLine values.
		 * other wise you will get nullpointers.
		 */
		
		/* VERY FREAKING IMPORTANT STUFF */
		World world = new World("TESTME");
		system.setMap(world.world[0][0]);
	
		/*
		 * proof of concept movement backend
		 * the Map is implemented using a "priority stack"
		 */
				
		int i = 0; // component and entity ids
		int test = 0;
		DDCharacter character = new DDCharacter(1);
		CharacterSheet sheet = new CharacterSheet();
		sheet.equipWeapon(new Weapon(15, "Longsword",Dice.DieSize.D6,2,19,5,'M','S',"Note:",4), 0);
		
		/* ALSO VERY FREAKING IMPORTANT */
		character.setCharacterID(1);
		system.cs.addCharacter(character);
		character.setCharacterSheet(sheet);
		
		System.out.println("character address: " + character);
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
			new AppGameContainer(new TestDDCharacter());
 
         //app.setDisplayMode(800, 600, false);
         app.start();
    }
    
    
}
