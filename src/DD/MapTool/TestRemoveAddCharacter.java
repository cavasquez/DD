package DD.MapTool;

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
import DD.GMToolsBox.GMToolsBox;
import DD.GMToolsBox.HolderTuple;
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
		system.setMap(world.world[0][0]);
		
		Wall wall = new Wall("walll", null,0,0, world.world[0][0]);
		world.world[0][0].massPlaceObjectsLine(0, 0, 10, 0, wall);
		world.world[0][0].massPlaceObjectsLine(0, 1, 0, 19, wall);
		world.world[0][0].massPlaceObjectsLine(1, 10, 10, 10, wall);
		world.world[0][0].massPlaceObjectsLine(10, 9, 10, 1, wall);
		world.world[0][0].massPlaceObjectsLine(1, 19, 10, 19, wall);
		world.world[0][0].massPlaceObjectsLine(10, 11, 10, 19, wall);

		world.world[0][0].removeObjects(6, 0);
		world.world[0][0].removeObjects(7, 0);
		world.world[0][0].removeObjects(6, 10);
		world.world[0][0].removeObjects(7, 10);
		
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
		String temp;
		for (int k = 0; k < size; k++)
		{
			System.out.println("Adding character " + sheet[k].getName() + ": ");
			gmt.addCharacter(GMToolsBox.Holder.MOB, sheet[k]);
			
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
		
		
		System.out.println("done");
//		character.setCharacterSheet(sheet);
//		ab.setCharacter(character);
		
		
//		
//		int x = 6;
//		int y = 5;
//		CharacterObjects charObj = new CharacterObjects("*****", null,0,0, world.world[0][0], character);
//		world.world[0][0].placeObjects(x, y, charObj); /* place character */
//		character.setCoordiante(new Coordinate(x,y));
//		Ability.setOwnerCharacter(character); /* set the character who is performing the abilities. This should happen somewhere in ActionBox */
//		character.startNewTurn();
//		
//		Move move = new Move(i++);
//		move.activate();
////		
////		ChooseTargetMessage ctm = new ChooseTargetMessage
////				(
////						TargetingSystem.TargetCount.SINGLE,
////						TargetingSystem.TargetShape.MOVE,
////						TargetingSystem.TargetSelection.SELECTED,
////						false,
////						character.getCoordinate(),
////						5,
////						null
////				);
////		ObjectsPriorityStack[][] stack = world.world[0][0].objectsStack;
////		system.ts.chooseTarget(ctm);
//	
//		System.out.println(character.getMovedDiagonal());
//		System.out.println(world.world[0][0].toString());
//		
//		/* Move down */
//		int newx = x; //x being moved to
//		int newy = y; //y being moved to
//		newy -=1;
//		ObjectsPriorityStack stack = world.world[0][0].objectsStack[newx][newy]; // where the character is trying to move
//		((TargetBlock)stack.peek()).select();
//		System.out.println(world.world[0][0].toString());
//		System.out.println("Move to: " + newx + ", " + newy);
//		System.out.println(character.getCoordinate().x + ", " + character.getCoordinate().y);
//		System.out.println(character.getCurrentSpeed());
//		System.out.println("has moved diagonal: " + character.getMovedDiagonal());
//		
//		newx -= 1;
//		newy +=1;
//		stack = world.world[0][0].objectsStack[newx][newy]; // where the character is trying to move
//		((TargetBlock)stack.peek()).select();
//		System.out.println(world.world[0][0].toString());
//		System.out.println("Move to: " + newx + ", " + newy);
//		System.out.println(character.getCoordinate().x + ", " + character.getCoordinate().y);
//		System.out.println(character.getCurrentSpeed());
//		System.out.println("has moved diagonal: " + character.getMovedDiagonal());
//		
//		newx -= 1;
//		newy +=1;
//		stack = world.world[0][0].objectsStack[newx][newy]; // where the character is trying to move
//		((TargetBlock)stack.peek()).select();
//		System.out.println(world.world[0][0].toString());
//		System.out.println("Move to: " + newx + ", " + newy);
//		System.out.println(character.getCoordinate().x + ", " + character.getCoordinate().y);
//		System.out.println(character.getCurrentSpeed());
//		System.out.println("has moved diagonal: " + character.getMovedDiagonal());
//		
//		newx -= 1;
//		stack = world.world[0][0].objectsStack[newx][newy]; // where the character is trying to move
//		((TargetBlock)stack.peek()).select();
//		System.out.println(world.world[0][0].toString());
//		System.out.println("Move to: " + newx + ", " + newy);
//		System.out.println(character.getCoordinate().x + ", " + character.getCoordinate().y);
//		System.out.println(character.getCurrentSpeed());
//		System.out.println("has moved diagonal: " + character.getMovedDiagonal());
//		
//		newx -=1;
//		stack = world.world[0][0].objectsStack[newx][newy]; // where the character is trying to move
//		((TargetBlock)stack.peek()).select();
//		System.out.println(world.world[0][0].toString());
//		System.out.println("Move to: " + newx + ", " + newy);
//		System.out.println(character.getCoordinate().x + ", " + character.getCoordinate().y);
//		System.out.println(character.getCurrentSpeed());
//		System.out.println("has moved diagonal: " + character.getMovedDiagonal());
//		
//		System.out.println("Character can move?: " + character.getHasMoveAction());
//		
//		/* Test termination of move before speed */
//		character.startNewTurn();
//		move = new Move(i++);
//		System.out.println("New turn");
//		System.out.println(character.getCurrentSpeed());
//		System.out.println("has moved diagonal: " + character.getMovedDiagonal());
//		
//		System.out.println("Character can move?: " + character.getHasMoveAction());
//		
//		newx -=1;
//		move.activate();
//		stack = world.world[0][0].objectsStack[newx][newy]; // where the character is trying to move
//		((TargetBlock)stack.peek()).select();
//		System.out.println(world.world[0][0].toString());
//		System.out.println("Move to: " + newx + ", " + newy);
//		System.out.println(character.getCoordinate().x + ", " + character.getCoordinate().y);
//		System.out.println(character.getCurrentSpeed());
//		System.out.println("has moved diagonal: " + character.getMovedDiagonal());
//		
//		System.out.println("Character can move?: " + character.getHasMoveAction());
//		
//		move.done();
//		stack = world.world[0][0].objectsStack[newx][newy]; // where the character is trying to move
//		System.out.println(world.world[0][0].toString());
//		System.out.println("Move to: " + newx + ", " + newy);
//		System.out.println(character.getCoordinate().x + ", " + character.getCoordinate().y);
//		System.out.println(character.getCurrentSpeed());
//		System.out.println("has moved diagonal: " + character.getMovedDiagonal());
//		
//		System.out.println("Character can move?: " + character.getHasMoveAction());
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
