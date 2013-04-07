package DD.GMToolsBox;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import DD.ActionBox.Dice;
import DD.Character.DDCharacter;
import DD.Character.Abilities.Ability;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.CombatSystem.Action;
import DD.CombatSystem.CombatSystem.ActionType;
import DD.Message.CombatMessage;
import DD.SlickTools.DDImage;

/*****************************************************************************************************
 * StartCombatPhase is a special ability that should only be provided to the GM(ie the server).
 * It will allow the GM to begin a new CombatPhase.
 * 
 * The Body will consist of the CharacterID's in order
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class StartCombatPhase extends Ability 
{
	private static Input mouse = new Input(650);
	private DDImage cancel;

	public StartCombatPhase(int id) 
	{
		super(id, CombatSystem.ActionType.SYSTEM, CombatSystem.Action.START_COMBAT_PHASE, "Start Combat Phase", "Game will roll for initiative and order players accordingly.");
		this.image = new DDImage("Images/GMTools/StartCombatPhase.png");
		cancel = new DDImage("Images/GMTools/Cancel.png");
	} /* end StartCombatPhase constructor */

	@Override
	protected void action() throws SlickException 
	{
		DDCharacter[] list = cs.getCharacterList();
		Dice dice = new Dice(20);
		
		if (list.length > 0)
		{
			/* Create the head of the tree */
			BinarySearchTree head = new BinarySearchTree(list[0].getCharacterID(), dice.roll(1), list[0].getCharacterSheet().rawStats[CharacterSheet.ABILITY_DEXTERITY][CharacterSheet.ABILITY_MODIFIER]);
			
			/* Now, create the tree/order */
			for (int i = 1; i < list.length; i++)
			{
				head.add(list[i].getCharacterID(), dice.roll(1), list[i].getCharacterSheet().rawStats[CharacterSheet.ABILITY_DEXTERITY][CharacterSheet.ABILITY_MODIFIER]);
			} /* end for loop */
			
			/* Make the combat message. The body will consist of the CharacterID's in the correct order. */
			CombatMessage cm = new CombatMessage
				(
					null,
					null, 
					actionType,
					action,
					head.toArray()
				);
			/* Since StartCombatPhase is a "special" ability, it will not go through sendToInterpreter
			 * as usual. Nor will it call done() */
			// sendToInterpreter(cm); 
			cs.process(cm);
			
		} /* end if */
		
	} /* end action method */
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) 
	{
		int posX = mouse.getMouseX();
    	int posY = mouse.getMouseY();
		
		//Start Combat Phase Button
		if((posX > 950 && posX < 950 + this.image.getWidth()) && (posY > 80 && posY < (80 + this.image.getHeight()))) {
    		//if you click on the button
    		if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON)) {
    			try {
					action();
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			System.out.println("Start Combat Phase");
    		}
    	}
		
	} /* end update method */
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) 
	{
		this.image.draw(950, 80);
		
	} /* end render method */

} /* end StartCombatPhase method */
