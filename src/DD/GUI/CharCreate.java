package DD.GUI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;

import DD.Character.CharacterSheet.CharacterSheet;

import java.util.Scanner;

public class CharCreate extends BasicGameState {
	CharacterSheet sheet;
	UnicodeFont font;
	TextField playerName;
	TextField characterName;
	//clickable button for choosing race
	TextField languages;
	//clickable button for choosing size
	//clickable button for choosing gender
	TextField height;
	TextField weight;
	TextField age;
	TextField alignments;
	TextField deity;
	TextField background;
	TextField occupation;
	
	//clickable button to choose which class to pick
	
	
	
	//AFTER FILLING BASIC INFO WE WILL THEN NEED TO PASS TO A NEW STATE
	/*
	 * (String name,String player,int race,String languages, int size,
						  int gender,int height,int weight,int age,String alignments,
						  String deity,String background,String occupation)
	*/
	Scanner input = new Scanner(System.in);
	String create = "";
	
	public CharCreate(int x)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
	{
		playerName = new TextField(gc, font, 100, 230, 180, 25);
		
		
		
		font.loadGlyphs();
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
	{
		create = input.nextLine();
		g.drawString(create, 100, 100);
		g.setFont(font);
		
		
		playerName.render(gc, g);
	
	}
	
	public void update(GameContainer gc, StateBasedGame sbg,int delta)throws SlickException
	{
		font.loadGlyphs();
				
			
		
		//TextField tx = new TextField(gc, 14, 100,100, 10,10, mouseButtonDown(0));
		
	}

	@Override
	public int getID() {
		return 6;
	}
	
	
	//this is what the button should do then move to the next
	public void setCharacterSheet()
	{
		
		//fix with buttons
		//sheet.fillBasic(characterName.getText(), playerName.getText(), race, languages.getText(), size, gender, 
			//			height.getText(), weight.getText(), age.getText(), alignments.getText(), deity.getText(), 
				//		background.getText(), occupation.getText());
		
		sheet.fillAbilities();
		
		//get class option button value
		
		
		//sheet.fillRecorder(sheet.chooseClass(class button value here)/*This needs the class option from button */);
		
		//sheet.fillAttacksAndDefense(/* the class option*/);
		
		//after this we need to probably make an equipment purchase thing to buy armor to equip and weapons
	
	}
	
	
	/*
	
	public TextField (GUIContext gc, Font font,  int x, int y, int width, int height, ComponentListener listener)
	{
		
	}
	*/

	

}
