package DD.CombatSystem.Interpreter;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

/*****************************************************************************************************
 * This BinarySearchTree will be used by I_StartCombatPhase to help determine the ordering of players.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class BinarySearchTree 
{
	/************************************ Class Attributes *************************************/
	private BinarySearchTree leftChild = null;
	private BinarySearchTree rightChild = null;
	private BinarySearchTree parent = null;
	
	private int characterID;
	private int initiative;
	private int dex;
	
	private static Integer count = null;
	
	/************************************ Class Methods *************************************/
	public BinarySearchTree(int characterID, int initiative, int dex) 
	{
		this.characterID = characterID;
		this.initiative = initiative;
		this.dex = dex;
		
		if (count == null) count = 0;
		else count++;
	} /* end BinarySearchTree Constructor */
	
	public BinarySearchTree(int characterID, int initiative, int dex, BinarySearchTree parent) 
	{
		this.characterID = characterID;
		this.initiative = initiative;
		this.dex = dex;
		this.parent = parent;

		if (count == null) count = 0;
		else count++;
	} /* end BinarySearchTree Constructor */
	
	public void add(int characterID, int initiative, int dex)
	{
		boolean left = false;
		
		/* Find correct branch */
		if ((initiative + dex) == (this.initiative + this.dex))
		{
			/* If the total initiative is the same, order is determined based on dex */
			if (dex == this.dex)
			{
				
			} /* same dex as well, order by characterID */
			else if (dex < this.dex) left = true;
		} /* end if */
		else if ((initiative + dex) < (this.initiative + this.dex)) left = true;
	
		/* place into correct branch */
		if(left == true)
		{
			/* place into left branch */
			if (leftChild == null) leftChild = new BinarySearchTree(characterID, initiative, dex, this);
			else leftChild.add(characterID, initiative, dex);
		} /* end if */
		else
		{
			/* place into right branch */
			if (rightChild == null) rightChild = new BinarySearchTree(characterID, initiative, dex, this);
			else rightChild.add(characterID, initiative, dex);
		} /* end else */
	} /* end add method */
	
} /* end BinarySearchTree method */
