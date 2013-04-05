package DD.Character;

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
import DD.MapTool.CharacterObjects;
import DD.MapTool.MapTool;
import DD.Message.ChooseTargetMessage;

// @author Carlos Vasquez

public class TestSerChar extends BasicGame
{
	public TestSerChar(String title) {
		super(title);

	}
 
    public TestSerChar()
    {
        super("Slick2DPath2Glory - SimpleGame");
    }
 
    @Override
    public void init(GameContainer gc) throws SlickException {
    	
    	//phase 1
//    	DDCharacter mike = new DDCharacter(0);
//    	CharacterSheet cs = new CharacterSheet();
//    	mike.setCharacterSheet(cs);
//    	mike.getCharacterSheet().fillBasic("Max", 
//    			"Mike", 
//    			0, 
//    			"Elvish, Common", 
//    			0, 
//    			1, 
//    			5, 
//    			150, 
//    			200, 
//    			"Chaotic Neutral", 
//    			"Apple", 
//    			"Noble", 
//    			"Archer");
//    	
//    	mike.getCharacterSheet().fillAbilities();
//    	CharacterClass cc = mike.getCharacterSheet().chooseClass(0);
//    	mike.getCharacterSheet().fillRecorder(cc);
//    	mike.getCharacterSheet().fillAttacksAndDefense(cc);
//    	mike.writeMe("C:/Users/mike/Desktop/save1/");
//    	
    	MapTool mt = new MapTool();
    	
//    	mt.setCurrentMap(0, 0);
//    	
//    	DDCharacter mike = mt.getCurrentMap().loadCharacter("C:/Users/mike/Desktop/save1/Characters/", "Max");
//    	CharacterObjects co = new CharacterObjects(mike.getCharacterSheet().getName(), null, 0, 0, null, mike);
// 	
//    	mt.getCurrentMap().place(0, 0,co);
//    	System.out.println(mt.getCurrentMap().toString());
    	
//    	
    	
    	
//    	mt.getWorld().writeMe("C:/Users/mike/Desktop/save1/");
    	
    	mt.loadWorld("world", "C:/Users/mike/Desktop/save1/");
    	System.out.println(mt.getCurrentMap().toString());
    	
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
			new AppGameContainer(new TestSerChar());
 
         //app.setDisplayMode(800, 600, false);
         app.start();
    }
    
    
}
