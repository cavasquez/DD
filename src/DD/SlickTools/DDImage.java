package DD.SlickTools;

import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import DD.Character.DDCharacter;
import DD.CombatSystem.Interpreter.CombatInterpreter;
import DD.MapTool.Map;
import DD.Network.NetworkSystem;

/*****************************************************************************************************
 * DDImage will contain all the necessary information for a DD object to create a slick image;
 * 
 * @author Carlos Vasquez
 ******************************************************************************************************/

public class DDImage implements Serializable
{
	/************************************ Class Constants *************************************/
	private static final long serialVersionUID = -7224331772028830679L;
	
	/************************************ Class Attributes *************************************/
	private String spriteSheetPath;
	private int x;
	private int y;
	private int width;
	private int height;
	
	/************************************ Class Methods *************************************/
	public DDImage(String spriteSheetPath, int x, int y, int width, int height)
	{
		this.spriteSheetPath = spriteSheetPath;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	} /* end DDImage constructor */
	
	public Image makeImage()
	{
		Image returner = null;
		Image spriteSheet = null;
		try 
		{
			spriteSheet = new Image(spriteSheetPath);
			returner = spriteSheet.getSubImage(x, y, width, height);
		} /* end try */
		catch (SlickException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /* end catch */
		
		return returner;
	} /* end makeImage method */
	
} /* end DDImage class */
