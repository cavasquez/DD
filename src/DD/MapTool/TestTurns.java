package DD.MapTool;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import DD.ActionBox.ActionBox;
import DD.Character.DDCharacter;
import DD.Character.Abilities.Ability;
import DD.Character.Abilities.EndTurn;
import DD.Character.Abilities.StartCombatPhase;
import DD.Character.Abilities.DefaultAbilities.Move.Move;
import DD.Character.CharacterSheet.CharacterClass;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.TargetingSystem.Coordinate;
import DD.CombatSystem.TargetingSystem.TargetingSystem;
import DD.System.DDSystem;

// @author Carlos Vasquez

public class TestTurns extends BasicGame
{
	public TestTurns(String title) {
		super(title);

	}
 
    public TestTurns()
    {
        super("Slick2DPath2Glory - SimpleGame");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
    	DDSystem system = new DDSystem();
    	ActionBox ab = new ActionBox(0, 0, 0);
    	system.linkBoxes(ab, null);
		
				
		int i = 0; // component and entity ids
		int cid = 0; // character ids
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
		
		StartCombatPhase scp = new StartCombatPhase(i++);
		EndTurn end = new EndTurn(i++);
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
			new AppGameContainer(new TestTurns());
 
         //app.setDisplayMode(800, 600, false);
         app.start();
    }
    
    
}
