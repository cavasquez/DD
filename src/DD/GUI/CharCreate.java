package DD.GUI;

import java.util.ArrayList;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.TextField;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import java.awt.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


import DD.Character.CharacterSheet.CharacterSheet;




public class CharCreate extends BasicGameState {
	CharacterSheet sheet;
	UnicodeFont font;
	private String mouse = "No input yet!";
	Input inputMouse = new Input(650);
	TextField playerName;
	//TextField characterName;
	//clickable button for choosing race
//	TextField languages;
//	//clickable button for choosing size
//	//clickable button for choosing gender
//	TextField height;
//	TextField weight;
//	TextField age;
//	TextField alignments;
//	TextField deity;
//	TextField background;
//	TextField occupation;
	JoinLobbyStartButton jlsb;
	
	private Image bg_image = null;
	
	//clickable button to choose which class to pick
	
	
	
	//AFTER FILLING BASIC INFO WE WILL THEN NEED TO PASS TO A NEW STATE
	/*
	 * (String name,String player,int race,String languages, int size,
						  int gender,int height,int weight,int age,String alignments,
						  String deity,String background,String occupation)
	*/


	
	public CharCreate(int x)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
	{
		
		
		bg_image = new Image ("Images/Menus/menuscreen4.jpg");
		font = getNewFont("Arial" , 16);
		
		playerName = new TextField(gc, font, 100, 230, 180, 25);
		playerName.setText("username");
		
		
		
		
		font.loadGlyphs();
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
	{
		bg_image.draw(0,0);
		g.drawString(mouse, 100, 400);
		g.drawString("inputMouse x " + inputMouse.getMouseX() + " y " + inputMouse.getMouseY(), 100, 420);
		g.drawString("BACK", 190, 552);
		g.setFont(font);
		
		
		playerName.render(gc, g);
	
	}
	
	public void update(GameContainer gc, StateBasedGame sbg,int delta)throws SlickException
	{
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		font.loadGlyphs();
				
		 mouse = "Mouse position x: " + posX + " y: " + posY;
		
		//TextField tx = new TextField(gc, 14, 100,100, 10,10, mouseButtonDown(0));
		
	}
	
	public UnicodeFont getNewFont(String fontName , int fontSize)
    {
        font = new UnicodeFont(new Font(fontName , Font.PLAIN , fontSize));
//        font.addGlyphs("@");
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        return (font);
    }

	@Override
	public int getID() {
		return 6;
	}
	
	
	//this is what the button should do then move to the next
	public void setCharacterSheet(TextField username)
	{
		
		//fix with buttons
		//sheet.fillBasic(characterName.getText(), playerName.getText(), race, languages.getText(), size, gender, 
			//			height.getText(), weight.getText(), age.getText(), alignments.getText(), deity.getText(), 
				//		background.getText(), occupation.getText());
		
		//sheet.fillAbilities();
		
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
