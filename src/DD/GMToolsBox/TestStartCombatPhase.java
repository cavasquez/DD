package DD.GMToolsBox;

import java.util.Set;
import java.util.TreeSet;


public class TestStartCombatPhase 
{
	public static void main(String[] args) 
	{
		int i = 0;
		BinarySearchTree head = new BinarySearchTree(i++, 50, 2);
		head.add(i++, 53, 2);
		head.add(i++, 51, 1);
		head.add(i++, 10, 2);
		head.add(i++, 13, 2);
		head.add(i++, 11, 2);
		head.add(i++, 9, 2);
		head.add(i++, 60, 2);
		head.add(i++, 30, 2);
		head.add(i++, 1, 2);
		head.add(i++, 2, 2);
		head.add(i++, 3, 2);
		
		Integer[] test = head.toArray();
		System.out.println("Testing BinarySearchTree: ");
		for (int j = 0; j < i; j++ )
		{
			System.out.println(test[j]);
		} /* end for loop */
		
		
		/* Testing set */
		System.out.println("Testing set:");
		Set<Integer> test1 = new TreeSet<Integer>();
		
		int id1 = 5;
		int id2 = 6;
		int id3 = 10;
		test1.add(id1);
		test1.add(id2);
		test1.add(id3);
		
		int id4 = 5;
		test1.remove(id4);
		System.out.println(test1.toString());
		
	} /* end main */
	
} /* end testCombatPhase class */
