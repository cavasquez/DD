package DD.GUI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import java.util.Scanner;

public class CharCreate implements ComponentListener, GameState {
	UnicodeFont font;
	TextField playerName;
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
	
	/*
	
	public TextField (GUIContext gc, Font font,  int x, int y, int width, int height, ComponentListener listener)
	{
		
	}
	*/

	@Override
	public void componentActivated(AbstractComponent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int arg0, char arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int arg0, char arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerButtonPressed(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerButtonReleased(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerLeftPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerLeftReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerRightPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerRightReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerUpPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerUpReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRenderPaused() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUpdatePaused() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pauseRender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pauseUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRenderPaused(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUpdatePaused(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unpauseRender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unpauseUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
