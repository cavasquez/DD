package DD.GUI;

import java.util.ArrayList;

import org.newdawn.slick.gui.TextField;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import java.awt.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class JoinLob extends BasicGameState
{
	
	
	Image screen;
	private String mouse = "No input yet!";
	//Image play;
	Sound button;
	//Music dungeon;
	UnicodeFont font;
	TextField textbox;
	ArrayList<Lobby> lobbyList = new ArrayList<Lobby>();
	
	public JoinLob(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
	{
		
		screen = new Image("Images/Menus/DD1.jpg");
		
		
		//play = new Image("Images/Menus/play button.png");
		//options = new Image("res/options button.png");
		//font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD,8),false);
		font = getNewFont("Arial" , 16);
		button = new Sound("Audio/dunSound.wav");
		//dungeon = new Music("Audio/dunEffect1.wav");
		//dungeon.loop();
		textbox = new TextField(gc, font, 100, 266, 250 , 50);
//		textbox.addListener(new KeyListener());
		textbox.setText("Test");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
	{
		screen.draw(0,0);
		//play.draw(100,100);
		//options.draw(100, 200);
		//about.draw(100, 300);
		g.drawString(mouse, 100, 400);
		g.drawString("LobbyIP: 123.43.345", 82, 266);
		g.drawString("LOGIN", 190, 604);
		g.drawString("BACK", 190, 552);
		g.setFont(font);
		
		
		textbox.render(gc, g);
//		textbox.setCursorVisible(true);
		
		
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg,int delta)throws SlickException
	{
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		font.loadGlyphs();
		//BACK button
		if((posX > 185 && posX < 240) && (posY > 80 && posY < 100))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(0);
			}
		}
		
		//LOGIN button
		if((posX > 185 && posX < 247) && (posY > 28 && posY < 47))
		{
			if(gc.getInput().isMousePressed(gc.getInput().MOUSE_LEFT_BUTTON))
			{
				button.play();
				sbg.enterState(2);
			}
		}
				
		//String input = textbox.getText();
		
		
		
		 
	      mouse = "Mouse position x: " + posX + " y: " + posY;
	      

		
	}
	
	public UnicodeFont getNewFont(String fontName , int fontSize)
    {
        font = new UnicodeFont(new Font(fontName , Font.PLAIN , fontSize));
        font.addGlyphs("@");
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        return (font);
    }
	
	public int getID()
	{
		return 1;
	}

}