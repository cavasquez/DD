package DD.ActionBox;

import java.util.Random;

/*****************************************************************************************************
 * The Dice class will provide the basic dice rolling mechanics to the game.
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class Dice 
{
	/************************************ Class Constants*************************************/
	public final static int D3 = 3;
	public final static int D4 = 4;
	public final static int D6 = 6;
	public final static int D8 = 8;
	public final static int D10 = 10;
	public final static int D12 = 12;
	public final static int D20 = 20;
	public final static int D100 = 100;
	
	/************************************ Class Attributes *************************************/
	Random generator;
	int dieSize;
	
	/************************************ Class Methods*************************************/
	public Dice() 
	{
		dieSize = Integer.MAX_VALUE;
		generator = new Random();
	} /* end dieSize */
	
	public Dice(int dieSize) 
	{
		this.dieSize = dieSize;
		generator = new Random();
	} /* end Dice constructor */
	
	public int roll(int numOfRolls, int dieSize)
	{
		int returner = 0;
		
		for (int i = 0; i < numOfRolls; i++)
		{
			returner += generator.nextInt(dieSize) + 1;
		} /* end for loop */
		
		return(returner);
	} /* end roll method */
	
	public int roll(int numOfRolls)
	{
		int returner = 0;
		
		for (int i = 0; i < numOfRolls; i++)
		{
			returner += generator.nextInt(this.dieSize) + 1;
		} /* end for loop */
		
		return(returner);
	} /* end roll method */
	
	public int roll(int bestOf, int numOfRolls, int dieSize)
	{
		int returner = 0;
		int index = 0;
		int max = 0;
		int[] holding = new int[numOfRolls];
		for (int i = 0; i < numOfRolls; i++)
		{
			holding[i] = generator.nextInt(dieSize) + 1;
		} 
		for (int i = 0; i < bestOf; i++)
		{
			for (int j = 0; j < numOfRolls; j++) 
			{
			
				int temp = holding[j];
				if(temp > max)
				{
					index =j;
					max = temp;
				}
				
			}
			returner += max;
			holding[index] = 0;
		} 
		
		return returner;
	} 
	
} /* end Dice class */