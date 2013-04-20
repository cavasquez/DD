package DD.CombatSystem.Interpreter.System;

import org.newdawn.slick.SlickException;

import DD.Character.DDCharacter;
import DD.CombatSystem.Interpreter.CombatInterpreter;
import DD.CombatSystem.TargetingSystem.Coordinate;
import DD.MapTool.CharacterObjects;
import DD.MapTool.Floor;
import DD.MapTool.Grass;
import DD.MapTool.Map;
import DD.MapTool.Objects;
import DD.MapTool.SerMapCharHelper;
import DD.MapTool.Wall;
import DD.Message.CombatMessage;
import DD.Message.CombatValidationMessage;

/*****************************************************************************************************
 * The Interpreter for SetMap. This will reconfigure the CombatSystem and other related systems to
 * use the given map;
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class I_SetMap extends CombatInterpreter
{
	/************************************ Class Constants *************************************/
	private static int I = 0;
	public static final int MAP = I++;		/* Map */
	public static final int BODY_SIZE = I;
	
	/************************************ Class Methods *************************************/
	
	@Override
	public CombatValidationMessage validate(CombatMessage cm) 
	{
		// TODO Auto-generated method stub
		CombatValidationMessage returner = new CombatValidationMessage(true, null); // TODO: Check for validity
		return returner;
	} /* end validate method */
	
	@Override
	public void interpret(CombatMessage cm) 
	{
		Map map = (Map)cm.getBodyData()[MAP];
		//TODO: Fix this hacky ass bull. Need to understand serializaiton more

		for (int k = 0; k < map.mapSize; k++) {
			for (int l = 0; l < map.mapSize; l++) {
				Objects[] list = new Objects[map.objectsStack[k][l].size()];
				System.arraycopy(map.objectsStack[k][l].toArray(),0, list, 0,map.objectsStack[k][l].size());
				for (int m = 0; m < list.length; m++) {
					try {
						if(list[m] instanceof Floor){
							if(list[m] instanceof Grass){
								Grass grass = new Grass("grass", k, l, 5, 5, map);
								map.place(k,l ,grass);
							}
							else{
								Floor flo = new Floor("floor",k,l,5,5,map);	
								map.place(k,l ,flo);
							}
						}
						if(list[m] instanceof Wall){
							Wall wall =  new Wall("wall", map);
							map.place(k, l, wall);
						}
						if(list[m] instanceof CharacterObjects)
						{
//							if(loadChar)
//							{
//								DDCharacter ddc = ((CharacterObjects) list[m]).getDdchar();
//								CharacterObjects co = new CharacterObjects(ddc.getSheet().getName(), ddc.getSheet().getImage(), world.getMap(i, j), ddc);
//								world.getMap(i, j).place(k, l, co);
//							}
//							else
//							{
//								DDCharacter ddc = ((CharacterObjects) list[m]).getDdchar();
//								world.getMap(i, j).serMapHelper.add(new SerMapCharHelper(new Coordinate(k,l), ddc.getSheet()));
//							}
						}
					}
					catch (SlickException e1) {
							e1.printStackTrace();
					}
				}
			}
		}
		
		
		cs.setMap(map);
		ts.setMap(map);
		
	} /* end interpret method */

} /* end I_SetMap class */
