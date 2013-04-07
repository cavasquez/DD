package DD.GUI;

import java.util.ArrayList;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.TextField;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import java.awt.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import DD.Character.CharacterSheet.CharacterSheet;

public class CharCreate extends BasicGameState
{
	
	CharacterSheet sheet;
	Image screen;
	private String mouse = "No input yet!";
	//Image play;
	Sound button;
	//Music dungeon;
	UnicodeFont font2;
	TextField textbox;
	ArrayList<Lobby> lobbyList = new ArrayList<Lobby>();
	TextField text2;
	TextField text1;
	TextField text3;
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
	JoinLobbyStartButton jlsb;
	Input inputMouse = new Input(650);
	int race;
	int char_class;
	int gender;
	int size;
	
	
	int state = 0;
	
	public CharCreate(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
	{
		screen = new Image("Images/Menus/menuscreen4.jpg");
		
		
		//play = new Image("Images/Menus/play button.png");
		//options = new Image("res/options button.png");
		//font2 = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD,8),false);
		font2 = getNewFont("Arial" , 16);
		button = new Sound("Audio/dunSound.wav");
		//dungeon = new Music("Audio/dunEffect1.wav");
		//dungeon.loop();
//		textbox = new TextField(gc, font2, 100, 266, 250 , 50);
		
//		jlsb = new JoinLobbyStartButton(username, ip, Game.system, new Vector2f(190, 600));
//		textbox.addListener(new KeyListener());
		
		font2.loadGlyphs();
		
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		playerName = new TextField(gc, font2, 100, 100, 180, 25);
		characterName = new TextField(gc, font2, 100, 130, 180, 25);
		languages = new TextField(gc, font2, 100, 160, 180, 25);
		height = new TextField(gc, font2, 100, 190, 180, 25);
		weight = new TextField(gc, font2, 100, 220, 180, 25);
		age = new TextField(gc, font2, 100, 250, 180, 25);
		alignments = new TextField(gc, font2, 100, 280, 180, 25);
		deity = new TextField(gc, font2, 100, 310, 180, 25);
		background = new TextField(gc, font2, 100, 340, 180, 25);
		occupation = new TextField(gc, font2, 100, 370, 180, 25);
		
		
		playerName.setText("Your Name");
		characterName.setText("Character Name");
		languages.setText("Languages you Speak");
		height.setText("How Tall are you");
		weight.setText("How much do you weigh?");
		age.setText("How Old are you");
		alignments.setText("What Alignments");
		deity.setText("What Deity");
		background.setText("Your Background");
		occupation.setText("Your Occupation");
		
		
		
		
	}
	
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
	{
		screen.draw(0,0);
		//play.draw(100,100);
		//options.draw(100, 200);
		//about.draw(100, 300);
		g.drawString(mouse, 100, 400);
		g.drawString("inputMouse x " + inputMouse.getMouseX() + " y " + inputMouse.getMouseY(), 100, 420);
//		g.drawString("LobbyIP: 123.43.345", 82, 266);
//		g.drawString("LOGIN", 190, 604);
		
//		jlsb.render(gc, sbg, g);
		g.setFont(font2);
		
		
		
			g.drawString("BACK", 150, 552);
		
		if(state == 0)
			g.drawString("CONTINUE", 135, 522);
		
		if(state == 1)
		{
			g.drawString("CHOOSE RACE", 853, 506);
			g.drawString("PREVIOUS", 867, 546);
			g.drawString("HUMAN", 782, 448);
			g.drawString("ELF", 1005, 448);
			
			
		}
		
		
		if(state == 2)
		{
			g.drawString("CHOOSE CLASS", 853, 506);
			g.drawString("PREVIOUS", 867, 546);
			g.drawString("BARBARIAN", 782, 448);
			//g.drawString("SOME OTHER POSSIBLE CLASS", 1005, 448);
			
			
		}
		
		if(state == 3)
		{
			g.drawString("CHOOSE GENDER", 853, 506);
			g.drawString("PREVIOUS", 867, 546);
			g.drawString("MALE", 782, 448);
			g.drawString("FEMALE", 1005, 448);
			
			
		}
		
		if(state == 4)
		{
			g.drawString("CHOOSE SIZE", 853, 506);
			g.drawString("PREVIOUS", 867, 546);
			g.drawString("SMALL", 782, 448);
			g.drawString("MEDIUM", 894, 448);
			g.drawString("LARGE", 1005, 448);
			
			
		}
		
		if(state == 5)
		{
			g.drawString("DONE", 887, 506);
			g.drawString("PREVIOUS", 867, 546);
		}
		
		
		
		
//		textbox.render(gc, g);
		if(playerName != null) playerName.render(gc, g);
		if(characterName != null) characterName.render(gc, g);
		if(languages != null) languages.render(gc, g);
		if(height != null) height.render(gc, g);
		if(weight != null) weight.render(gc, g);
		if(age != null) age.render(gc, g);
		if(alignments != null) alignments.render(gc, g);
		if(deity != null) deity.render(gc, g);
		if(background != null) background.render(gc, g);
		if(occupation != null) occupation.render(gc, g);
		
		g.setFont(font2);
//		textbox.setCursorVisible(true);
		

		
		
		
	}
	
	
	public void update(GameContainer gc, StateBasedGame sbg,int delta)throws SlickException
	{
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		font2.loadGlyphs();
		//BACK button
//		jlsb.update(gc, sbg, delta);
		if((posX > 146 && posX < 194) && (posY > 80 && posY < 100))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(0);
				/* kill the overlap */
				characterName.setLocation(2000, 2000);
				playerName.setLocation(2000,2000);
				languages.setLocation(2000,2000);
				height.setLocation(2000,2000);
				weight.setLocation(2000,2000);
				age.setLocation(2000,2000);
				alignments.setLocation(2000,2000);
				deity.setLocation(2000,2000);
				background.setLocation(2000,2000);
				occupation.setLocation(2000,2000);

			}
		}
		
		//IF STATE = 0 THEN GO TO STATE 1
		if(state == 0)
			if((posX > 132 && posX < 219) && (posY > 110 && posY < 132))
			{
				if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
				{
					button.play();
					state++;
				}
			
			}
		
		
		/**************RACE HUMAN OR ELF*****************/
		//IF STATE = 1 AND IF CLICK HUMAN OR ELF THEN GO TO STATE 2 
		//or 
		//CLICK PREVIOUS AND GO BACK TO STATE 0
		if(state == 1)
		{
			
			/*********HUMAN HUMAN HUMAN HUMAN ***************/
			if((posX > 780 && posX < 842) && (posY > 180 && posY < 205))
			{
				if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
				{
					button.play();
					race = 1;
					state++;
				}
			
			}
			
			/*************ELF  ELF  ELF ************************/
			else if((posX > 1002 && posX < 1036) && (posY > 185 && posY < 205))
			{
				if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
				{
					button.play();
					race = 0;
					state++;
				}
			
			}
			
			/*********************PREVIOUS ******************/
			else if((posX > 867 && posX < 952) && (posY > 85 && posY < 105))
			{
				if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
				{
					button.play();
					state--;
				}
				
			}
			
			
			
		}
		
		
		/**************CHOOSE CLASS*****************/
		//IF STATE = 2 AND IF CLICK CLASS GO TO STATE 3 
		//or 
		//CLICK PREVIOUS AND GO BACK TO STATE 1
		if(state == 2)
		{
			
			/*********CHOOSE BARBARIAN ***************/
			if((posX > 780 && posX < 874) && (posY > 180 && posY < 205))
			{
				if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
				{
					button.play();
					char_class = 0;
					state++;
				}
			
			}
			/*
			/************* OTHER POSSIBLE CLASS ************************
			else if((posX > 1002 && posX < 1036) && (posY > 185 && posY < 205))
			{
				if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
				{
					button.play();
					state++;
				}
			
			}
			*/
			
			/*********************PREVIOUS PREVIOUS PREVIOUS**************/
			else if((posX > 867 && posX < 952) && (posY > 85 && posY < 105))
			{
				if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
				{
					button.play();
					state--;
				}
				
			}
			
			
			
		}
		
		/**************GENDER MALE OR FEMALE*****************/
		//IF STATE = 3 AND IF CLICK HUMAN OR ELF THEN GO TO STATE 4 
		//or 
		//CLICK PREVIOUS AND GO BACK TO STATE 2
		if(state == 3)
		{
			//GENDER IS 1 FOR MALE AND 0 FOR FEMALE
			/*********MALE MALE MALE ***************/
			if((posX > 776 && posX < 827) && (posY > 180 && posY < 205))
			{
				if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
				{
					button.play();
					gender = 1;  //MALE 
					state++;
				}
			
			}
			
			/*************FEMALE FEMALE FEMALE ************************/
			else if((posX > 1002 && posX < 1073) && (posY > 185 && posY < 205))
			{
				if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
				{
					button.play();
					gender = 0; //FEMALE
					state++;
				}
			
			}
			
			/***************PREVIOUS PREVIOUS PREVIOUS **************/
			else if((posX > 867 && posX < 952) && (posY > 85 && posY < 105))
			{
				if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
				{
					button.play();
					state--;
				}
				
			}
			
			
			
		}
		
		
		/**************SIZE OF CHARACTER*****************/
		//IF STATE = 4 AND IF CLICK SMALL, MEDIUM, OR LARGE THEN GO TO STATE 5 
		//or 
		//CLICK PREVIOUS AND GO BACK TO STATE 3
		if(state == 4)
		{
			//1 for small 0 for medium and -1 for large
			
			/*********SMALL SMALL SMALL ***************/
			if((posX > 780 && posX < 836) && (posY > 180 && posY < 205))
			{
				if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
				{
					button.play();
					size = 1;
					state++;
				}
			
			}
			
			/*************MEDIUM MEDIUM MEDIUM ************************/
			else if((posX > 890 && posX < 960) && (posY > 185 && posY < 205))
			{
				if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
				{
					button.play();
					size = 0;
					state++;
				}
			
			}
			
			/***************LARGE LARGE LARGE **************/
			else if((posX > 1000 && posX < 1064) && (posY > 185 && posY < 204))
			{
				if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
				{
					button.play();
					size = -1;
					state++;
				}
				
			}
			
			/***************PREVIOUS PREVIOUS PREVIOUS **************/
			else if((posX > 867 && posX < 952) && (posY > 85 && posY < 105))
			{
				if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
				{
					button.play();
					state--;
				}
				
			}
		}
			
			
			/**************DONE*****************/
			//IF STATE = 5 AND IF CLICK DONE THEN GO TO MAIN MENU 
			//or 
			//CLICK PREVIOUS AND GO BACK TO STATE 4
			if(state == 5)
			{
				
				/*********CLICK DONE TO GO TO MAIN MENU***************/
				if((posX > 883 && posX < 936) && (posY > 124 && posY < 149))
				{
					if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
					{
						button.play();
						state = 0;
						sbg.enterState(0);
					}
				
				}
				
				/***************PREVIOUS PREVIOUS PREVIOUS **************/
				else if((posX > 867 && posX < 952) && (posY > 85 && posY < 105))
				{
					if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
					{
						button.play();
						state--;
					}
					
				}
			
			
			
		}
		
		//System.out.println(username.getText());
		//LOGIN button
//		if((posX > 185 && posX < 247) && (posY > 28 && posY < 47))
//		{
//			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
//			{
//				button.play();
//				sbg.enterState(2);
//			}
//		}
				
		//String input = textbox.getText();
		
		
		
		 
	      mouse = "Mouse position x: " + posX + " y: " + posY;
	      

		
	}
	
	public UnicodeFont getNewFont(String fontName , int fontSize)
    {
        font2 = new UnicodeFont(new Font(fontName , Font.PLAIN , fontSize));
//        font2.addGlyphs("@");
        font2.getEffects().add(new ColorEffect(java.awt.Color.white));
        return (font2);
    }
	
	public int getID()
	{
		return 6;
	}
	
	//this is what the button should do then move to the next
	public void setCharacterSheet(TextField username)
	{
		
		
		//CHRIS
		/*
		 * I NEED BUTTONS 
		 * x2 race buttons 1 human 1 elf. the human button value set to 1 and elf to 0
		 * x1 class button 1 barbarian barb button value set to 0
		 *
		 * x2 gender buttons 1M 1F
		 * x3 size buttons sm med lrg (the values set to those are 1 for sm 0 for med and -1 for lrg)
		 
		 */
		//fix with buttons
		
		//this method fills the characterSheet
		sheet.fillBasic(characterName.getText(), playerName.getText(), race, languages.getText(), size, gender, 
						Integer.parseInt(height.getText()), Integer.parseInt(weight.getText()), Integer.parseInt(age.getText()), alignments.getText(), deity.getText(), 
						background.getText(), occupation.getText());
		
		sheet.fillAbilities();
		
		//get class option button value
		
		
		//sheet.fillRecorder(sheet.chooseClass(class button value here)/*This needs the class option from button */);
		
		//sheet.fillAttacksAndDefense(/* the class option*/);
		
		//after this we need to probably make an equipment purchase thing to buy armor to equip and weapons
	
	}
	
	
	

	



}