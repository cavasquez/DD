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
		DDCharacter c1 = new DDCharacter(i++);
		DDCharacter c2 = new DDCharacter(i++);
		DDCharacter c3 = new DDCharacter(i++);
		DDCharacter c4 = new DDCharacter(i++);
		
		CharacterSheet sheet = new CharacterSheet();
		
		/* ALSO VERY FREAKING IMPORTANT */
		c1.setCharacterID(cid++);
		c2.setCharacterID(cid++);
		c3.setCharacterID(cid++);
		c4.setCharacterID(cid++);
		
		system.cs.addCharacter(c1);
		system.cs.addCharacter(c2);
		system.cs.addCharacter(c3);
		system.cs.addCharacter(c4);
		
		ab.addCharacter(c1.getCharacterID());
		ab.addCharacter(c2.getCharacterID());
		ab.addCharacter(c3.getCharacterID());
		ab.addCharacter(c4.getCharacterID());
		
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
		
		c1.setCharacterSheet(sheet);
		c2.setCharacterSheet(sheet);
		c3.setCharacterSheet(sheet);
		c4.setCharacterSheet(sheet);
		
		StartCombatPhase scp = new StartCombatPhase(i++);
		EndTurn end = new EndTurn(i++);
		
		int turn = 1;
		/* Start test */
		scp.activate();
		System.out.println("Starting CharacterID: " + ab.getCharacter().getCharacterID());
		System.out.println("Turn number " + turn++);
		System.out.println("ActionBox's current character: " + ab.getCharacter().getCharacterID());
		System.out.println( "11( " + c1.getCharacterID() + ") has turn?" + c1.getHasTurn());
		System.out.println( "c2( " + c2.getCharacterID() + ") has turn?" + c2.getHasTurn());
		System.out.println( "c3( " + c3.getCharacterID() + ") has turn?" + c3.getHasTurn());
		System.out.println( "c4( " + c4.getCharacterID() + ") has turn?" + c4.getHasTurn());
		System.out.println();
		
		/* Test turn switches: */
		end.activate();
		System.out.println("Test switching turns");
		System.out.println("Turn number " + turn++);
		System.out.println("ActionBox's current character: " + ab.getCharacter().getCharacterID());
		System.out.println( "11( " + c1.getCharacterID() + ") has turn?" + c1.getHasTurn());
		System.out.println( "c2( " + c2.getCharacterID() + ") has turn?" + c2.getHasTurn());
		System.out.println( "c3( " + c3.getCharacterID() + ") has turn?" + c3.getHasTurn());
		System.out.println( "c4( " + c4.getCharacterID() + ") has turn?" + c4.getHasTurn());
		System.out.println();
		
		end.activate();
		System.out.println("Test switching turns");
		System.out.println("Turn number " + turn++);
		System.out.println("ActionBox's current character: " + ab.getCharacter().getCharacterID());
		System.out.println( "11( " + c1.getCharacterID() + ") has turn?" + c1.getHasTurn());
		System.out.println( "c2( " + c2.getCharacterID() + ") has turn?" + c2.getHasTurn());
		System.out.println( "c3( " + c3.getCharacterID() + ") has turn?" + c3.getHasTurn());
		System.out.println( "c4( " + c4.getCharacterID() + ") has turn?" + c4.getHasTurn());
		System.out.println();
		
		end.activate();
		System.out.println("Test switching turns");
		System.out.println("Turn number " + turn++);
		System.out.println("ActionBox's current character: " + ab.getCharacter().getCharacterID());
		System.out.println( "11( " + c1.getCharacterID() + ") has turn?" + c1.getHasTurn());
		System.out.println( "c2( " + c2.getCharacterID() + ") has turn?" + c2.getHasTurn());
		System.out.println( "c3( " + c3.getCharacterID() + ") has turn?" + c3.getHasTurn());
		System.out.println( "c4( " + c4.getCharacterID() + ") has turn?" + c4.getHasTurn());
		System.out.println();
		
		end.activate();
		System.out.println("Test switching turns");
		System.out.println("Turn number " + turn++);
		System.out.println("ActionBox's current character: " + ab.getCharacter().getCharacterID());
		System.out.println( "11( " + c1.getCharacterID() + ") has turn?" + c1.getHasTurn());
		System.out.println( "c2( " + c2.getCharacterID() + ") has turn?" + c2.getHasTurn());
		System.out.println( "c3( " + c3.getCharacterID() + ") has turn?" + c3.getHasTurn());
		System.out.println( "c4( " + c4.getCharacterID() + ") has turn?" + c4.getHasTurn());
		System.out.println();
		
		end.activate();
		System.out.println("Test switching turns");
		System.out.println("Turn number " + turn++);
		System.out.println("ActionBox's current character: " + ab.getCharacter().getCharacterID());
		System.out.println( "11( " + c1.getCharacterID() + ") has turn?" + c1.getHasTurn());
		System.out.println( "c2( " + c2.getCharacterID() + ") has turn?" + c2.getHasTurn());
		System.out.println( "c3( " + c3.getCharacterID() + ") has turn?" + c3.getHasTurn());
		System.out.println( "c4( " + c4.getCharacterID() + ") has turn?" + c4.getHasTurn());
		System.out.println();
		
		end.activate();
		System.out.println("Test switching turns");
		System.out.println("Turn number " + turn++);
		System.out.println("ActionBox's current character: " + ab.getCharacter().getCharacterID());
		System.out.println( "11( " + c1.getCharacterID() + ") has turn?" + c1.getHasTurn());
		System.out.println( "c2( " + c2.getCharacterID() + ") has turn?" + c2.getHasTurn());
		System.out.println( "c3( " + c3.getCharacterID() + ") has turn?" + c3.getHasTurn());
		System.out.println( "c4( " + c4.getCharacterID() + ") has turn?" + c4.getHasTurn());
		System.out.println();
		
		end.activate();
		System.out.println("Test switching turns");
		System.out.println("Turn number " + turn++);
		System.out.println("ActionBox's current character: " + ab.getCharacter().getCharacterID());
		System.out.println( "11( " + c1.getCharacterID() + ") has turn?" + c1.getHasTurn());
		System.out.println( "c2( " + c2.getCharacterID() + ") has turn?" + c2.getHasTurn());
		System.out.println( "c3( " + c3.getCharacterID() + ") has turn?" + c3.getHasTurn());
		System.out.println( "c4( " + c4.getCharacterID() + ") has turn?" + c4.getHasTurn());
		System.out.println();
		
		end.activate();
		System.out.println("Test switching turns");
		System.out.println("Turn number " + turn++);
		System.out.println("ActionBox's current character: " + ab.getCharacter().getCharacterID());
		System.out.println( "11( " + c1.getCharacterID() + ") has turn?" + c1.getHasTurn());
		System.out.println( "c2( " + c2.getCharacterID() + ") has turn?" + c2.getHasTurn());
		System.out.println( "c3( " + c3.getCharacterID() + ") has turn?" + c3.getHasTurn());
		System.out.println( "c4( " + c4.getCharacterID() + ") has turn?" + c4.getHasTurn());
		System.out.println();
		
		end.activate();
		System.out.println("Test switching turns");
		System.out.println("Turn number " + turn++);
		System.out.println("ActionBox's current character: " + ab.getCharacter().getCharacterID());
		System.out.println( "11( " + c1.getCharacterID() + ") has turn?" + c1.getHasTurn());
		System.out.println( "c2( " + c2.getCharacterID() + ") has turn?" + c2.getHasTurn());
		System.out.println( "c3( " + c3.getCharacterID() + ") has turn?" + c3.getHasTurn());
		System.out.println( "c4( " + c4.getCharacterID() + ") has turn?" + c4.getHasTurn());
		System.out.println();
		
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
