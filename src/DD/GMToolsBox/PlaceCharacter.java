package DD.GMToolsBox;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import DD.Character.Abilities.TargetAbility;
import DD.Character.CharacterSheet.CharacterSheet;
import DD.CombatSystem.CombatSystem;
import DD.CombatSystem.Interpreter.Standard.I_StandardAttack;
import DD.CombatSystem.Interpreter.System.I_PlaceCharacter;
import DD.CombatSystem.TargetingSystem.Coordinate;
import DD.CombatSystem.TargetingSystem.TargetingSystem;
import DD.GUI.Game;
import DD.Message.ChooseTargetMessage;
import DD.Message.CombatMessage;
import DD.Message.TargetSelectedMessage;
import DD.SlickTools.DDImage;

/*****************************************************************************************************
 * PlaceCharacter will five the GM the ability to place the held character as well as remove the character
 * from play.Furthermore, if the character is a main character, it should serialize the character data.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class PlaceCharacter extends TargetAbility
{
	/************************************ Class Attributes *************************************/
	private CharacterSheet sheet;	/* characters data */
	private GMToolsBox gmt;			/* Give PlaceCharacter access tot he GMToolBox */
	private GMToolsBox.Holder type;
	private DDImage cancel;
	private Vector2f pos;
	private static Input mouse = new Input(650);
	
	/************************************ Class Methods *************************************/
	public PlaceCharacter(int id, CharacterSheet sheet, GMToolsBox gmt, GMToolsBox.Holder type) 
	{
		super(id, CombatSystem.ActionType.SYSTEM, CombatSystem.Action.PLACE_CHARACTER, sheet.getName(), "place");
		this.sheet = sheet;
		this.gmt = gmt;
		this.type = type;
		this.pos = new Vector2f(Game.Xsize, Game.Ysize);
		this.image = new DDImage("Images/GMTools/PlaceCharacter.png");
		cancel = new DDImage("Images/GMTools/Cancel.png");
	} /* end PlaceCharacter class */

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
	{
		int posX = mouse.getMouseX();
    	int posY = mouse.getMouseY();
    	
    	if((posX > pos.x && posX < pos.x + this.image.getWidth()) && (posY > pos.y && posY < (pos.y + this.image.getHeight()))) {
    		//if you click on the button
    		if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON)) {
    			try {
					place();
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			System.out.println("Remove Character");
    		}
    	}
		
		//Cancel Button
		if((posX > ((pos.x+this.image.getWidth()) + 5)  && posX < ((pos.x+this.image.getWidth()) + 5) + cancel.getWidth()) && (posY > pos.y && posY < (pos.y + cancel.getHeight()))) {
    		//if you click on the button
    		if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON)) {
    			delete();
    		}
    	}
		
	} /* end update method */
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr, Vector2f pos) 
	{
		this.pos = pos;
		this.image.draw(pos.x, pos.y);
		cancel.draw((pos.x+this.image.getWidth()) + 5, pos.y);
		gr.drawString(sheet.getName(), pos.x+5, pos.y+10);
		
	} /* end render method */
	
	@Override
	protected void action() throws SlickException 
	{
		/* do nothing */
	} /* end action method */
	
	private void place() throws SlickException
	{
		ChooseTargetMessage tcm = new ChooseTargetMessage
				(
					TargetingSystem.TargetCount.SINGLE,
					TargetingSystem.TargetShape.ALL,
					TargetingSystem.TargetSelection.SELECTED,
					false,
					null,
					0,
					this
				);
		ts.chooseTarget(tcm);
	} /* end place method */
	
	protected void place(Coordinate position) throws SlickException
	{
		Integer[] body = new Integer[I_PlaceCharacter.BODY_SIZE];
		body[I_PlaceCharacter.CHARACTER_ID] = gmt.getNewCharacterID();
		body[I_PlaceCharacter.POS_X] = position.x;
		body[I_PlaceCharacter.POS_Y] = position.y;
		CombatMessage cm = new CombatMessage
				(
					null,
					null,
					CombatSystem.ActionType.SYSTEM,
					CombatSystem.Action.PLACE_CHARACTER,
					sheet,
					body
				);
		sendToInterpreter(cm);
		System.out.println("removing");;
		/* remove the character from the holder */
		removeCharacter();
		
		done(null);
	} /* end place method */

	private void delete()
	{
		removeCharacter();
		//TODO: save sheet? give to corresponding player to save? tell corresponding player to save?
	} /* end delete method */
	
	private void removeCharacter()
	{
		/* remove character from holder */
		gmt.removeCharacter(type, this.id);
		
		/* remove PlaceCharacter object from the list in GMToolsBox */
		gmt.removeComponent(this.id);
	} /* end remove method */
	
	@Override
	public void obtainTarget(TargetSelectedMessage tsm) throws SlickException 
	{
		System.out.println("obtain target");
		if (tsm.getTargets() == null)
		{
			System.out.println("obtain target: no character found");
			/* We can only place a character on a nonempty square */
			Integer[] body = new Integer[I_PlaceCharacter.BODY_SIZE];
			body[I_PlaceCharacter.CHARACTER_ID] = gmt.getNewCharacterID();
			body[I_PlaceCharacter.POS_X] = tsm.getPosition().x;
			body[I_PlaceCharacter.POS_Y] = tsm.getPosition().y;
			CombatMessage cm = new CombatMessage
					(
						null,
						null,
						CombatSystem.ActionType.SYSTEM,
						CombatSystem.Action.PLACE_CHARACTER,
						sheet,
						body
					);
			sendToInterpreter(cm);
			
			/* remove the character from the holder */
			removeCharacter();
			
			done(null);
		} /* end if */
		
	} /* end obtainTarget method */
	
	public void testDelete() throws SlickException
	{
		delete();
	}
	public void testPlace() throws SlickException
	{
		place();
	}

} /* end PlaceCharacter class */
