package DD.GMToolsBox;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import DD.ActionBox.ActionBox;
import DD.Character.DDCharacter;
import DD.Character.Abilities.Ability;
import DD.Character.Abilities.DefaultAbilities.Move.Move;
import DD.Character.CharacterSheet.CharacterClass;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.CombatSystem.TargetingSystem.Coordinate;
import DD.MapTool.Wall;
import DD.MapTool.World;
import DD.Network.NetworkSystem;
import DD.System.DDSystem;

// @author Carlos Vasquez

public class TestRemoveAddCharacter extends BasicGame
{
	public TestRemoveAddCharacter(String title) {
		super(title);

	}
 
    public TestRemoveAddCharacter()
    {
        super("Slick2DPath2Glory - SimpleGame");
    }
 
    @Override
    public void init(GameContainer gc) throws SlickException {
    	int i = 0; // component and entity ids
    	DDSystem system = new DDSystem();
    	ActionBox ab = new ActionBox(i++, 0, 0);
    	GMToolsBox gmt = new GMToolsBox(i++,0,0);
    	system.linkBoxes(ab);
    	system.server();
		
		/*
		 * IMPORTANT: for this main to work, mapSize needs to be 21 or larger due to hardcodes massPlaceObjectsLine values.
		 * other wise you will get nullpointers.
		 */
		
		/* VERY FREAKING IMPORTANT STUFF */
		World world = new World("TESTME");
		
		/* Initialize sheets */
		int size = 10;
		CharacterSheet[] sheet = new CharacterSheet[size];
		for(int j = 0; j < size; j++) sheet[j] = new CharacterSheet();
		
		/* make sheets: */
		for (int j = 0; j < size; j++)
		{
			sheet[j].fillBasic("Tiffany" + j, 
					"Skanks-Worth", 
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
			sheet[j].fillAbilities();
			CharacterClass barb = sheet[j].chooseClass(0);	//this is barbarian
			sheet[j].fillRecorder(barb);
			sheet[j].fillAttacksAndDefense(barb);
			sheet[j].setNetID(NetworkSystem.GM_USER_ID);
		} /* end for loop */
		
		
		
		/* gm tools time */
		HolderTuple[] holder;
		Integer[] inPlay;
		PlaceCharacter[] place = new PlaceCharacter[size];
		String temp;
		for (int k = 0; k < size; k++)
		{
			System.out.println("Adding character " + sheet[k].getName() + ": ");
			place[k] = gmt.addCharacter(GMToolsBox.Holder.MOB, sheet[k]);
			
			System.out.println("Characters in mob holder:");
			holder = gmt.getHolder(GMToolsBox.Holder.MOB);
			for(int j = 0; j < holder.length; j++) System.out.println("Component ID: " + holder[j].id + " Name: " + holder[j].sheet.getName());
			
			System.out.println("Characters in player holder:");
			holder = gmt.getHolder(GMToolsBox.Holder.PLAYER);
			for(int j = 0; j < holder.length; j++) System.out.println("Component ID: " + holder[j].id + " Name: " + holder[j].sheet.getName());
			
			System.out.println("Characters in play:");
			inPlay = gmt.getCharactersInPlay();
			temp = "";
			for (int j = 0; j < inPlay.length; j++) temp += (inPlay[j] + ", " );
			System.out.println(temp);
		}
		
		System.out.println("Now testing removal: ");
		System.out.println("Removing character " + sheet[0].getName() + ": ");
		place[0].testDelete();
		System.out.println("Characters in mob holder:");
		holder = gmt.getHolder(GMToolsBox.Holder.MOB);
		for(int j = 0; j < holder.length; j++) System.out.println("Component ID: " + holder[j].id + " Name: " + holder[j].sheet.getName());
		
		System.out.println("Characters in player holder:");
		holder = gmt.getHolder(GMToolsBox.Holder.PLAYER);
		for(int j = 0; j < holder.length; j++) System.out.println("Component ID: " + holder[j].id + " Name: " + holder[j].sheet.getName());
		
		System.out.println("Characters in play:");
		inPlay = gmt.getCharactersInPlay();
		temp = "";
		for (int j = 0; j < inPlay.length; j++) temp += (inPlay[j] + ", " );
		System.out.println(temp);
		
		
		System.out.println("Removing character " + sheet[5].getName() + ": ");
		place[5].testDelete();
		System.out.println("Characters in mob holder:");
		holder = gmt.getHolder(GMToolsBox.Holder.MOB);
		for(int j = 0; j < holder.length; j++) System.out.println("Component ID: " + holder[j].id + " Name: " + holder[j].sheet.getName());
		
		System.out.println("Characters in player holder:");
		holder = gmt.getHolder(GMToolsBox.Holder.PLAYER);
		for(int j = 0; j < holder.length; j++) System.out.println("Component ID: " + holder[j].id + " Name: " + holder[j].sheet.getName());
		
		System.out.println("Characters in play:");
		inPlay = gmt.getCharactersInPlay();
		temp = "";
		for (int j = 0; j < inPlay.length; j++) temp += (inPlay[j] + ", " );
		System.out.println(temp);

		
		System.out.println("done");
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
			new AppGameContainer(new TestRemoveAddCharacter());
 
         //app.setDisplayMode(800, 600, false);
         app.start();
    }
    
    
}
