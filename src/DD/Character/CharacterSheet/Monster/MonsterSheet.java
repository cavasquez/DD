package DD.Character.CharacterSheet.Monster;

import DD.Character.CharacterSheet.CharacterSheet;
import DD.Network.Network;

/*****************************************************************************************************
 * The Character class will act as an interface to CharacterSheet as well as play the role of the Model
 * in the Model View Controller scheme for DD combat (although in reality, it is more of a Model Controller
 * mix). It will provide the CombatSystem any necessary information and represent the Character being 
 * played by the player. It will hold some extra information such as turn, current speed, etc. that are 
 * needed to keep up the game.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class MonsterSheet extends CharacterSheet
{
	private static final long serialVersionUID = 3517780188723784758L;
	protected int hp;
	protected int XP;
	
	public MonsterSheet()
	{
		super();
	}
	
	public int getXP()
	{
		return XP;
	}
	
	@Override 
	public int getHP()
	{
		return hp;
	} /* end getHP method */
	
} /* end MonsterSheet class */
