package slick.path2glory.blockgame;
 
import java.awt.Font;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
 
public class MainMenuState extends BasicGameState{
 
    Image background = null;
    Image startGameOption = null;
    Image exitOption = null;
 
    int stateID = 0;
 
    Highscores highscores = null;
 
    private static int menuX = 410;
    private static int menuY = 160;
 
    float startGameScale = 1;
    float exitScale = 1;
 
    Sound fx = null;
 
    public MainMenuState(int stateID )
    {
        this.stateID = stateID;
    }
 
    @Override
    public int getID() {
        return stateID;
    }
 
    TrueTypeFont trueTypeFont = null;
 
    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
        background = new Image("Images/BlockGame/main menu.jpg");
 
        // Load the menu images
        Image menuOptions = new Image("Images/BlockGame/start end.png");
        startGameOption = menuOptions.getSubImage(0, 0, 377, 71);
 
        exitOption = menuOptions.getSubImage(0, 71, 377, 71);
 
        //--------------------------------------------------
 
        /*fx = new Sound("data/Desktop/Pictures/pulse.wav");*/
 
        //--------------------------------------------------
 
        Font font = new Font("Verdana", Font.BOLD, 20);
        trueTypeFont = new TrueTypeFont(font, true);
 
        highscores = Highscores.getInstance();
    }
 
 
 
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
        // render the background
        background.draw(0, 0);
 
        // Draw menu
        startGameOption.draw(menuX, menuY, startGameScale);
 
        exitOption.draw(menuX, menuY+80, exitScale);
 
        // Draw Highscores
        int index = 1;
        int posY = 300;
 
        ArrayList<Integer> highScoreList = highscores.getScores();
 
        for(Integer score : highScoreList )
        {
            trueTypeFont.drawString(20, posY, " " + ( index < highScoreList.size() ? "0" + index : "" + index) + "  ." + score, Color.orange);
            index++;
            posY += 20;
        }
    }
 
    float scaleStep = 0.0001f;
 
    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException {
        Input input = gc.getInput();
 
        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();
 
        boolean insideStartGame = false;
        boolean insideExit = false;
 
        if( ( mouseX >= menuX && mouseX <= menuX + startGameOption.getWidth()) &&
            ( mouseY >= menuY && mouseY <= menuY + startGameOption.getHeight()) )
        {
            insideStartGame = true;
        }else if( ( mouseX >= menuX && mouseX <= menuX+ exitOption.getWidth()) &&
            ( mouseY >= menuY+80 && mouseY <= menuY+80 + exitOption.getHeight()) )
        {
            insideExit = true;
        }
 
        if(insideStartGame)
        {
            if(startGameScale < 1.05f)
                startGameScale += scaleStep * delta;
 
            if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
                /*fx.play();*/
                sb.enterState(SlickBlocksGame.GAMEPLAYSTATE);
            }
        }else{
            if(startGameScale > 1.0f)
                startGameScale -= scaleStep * delta;
 
            if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
                gc.exit();
        }
 
        if(insideExit)
        {
            if(exitScale < 1.05f)
                exitScale +=  scaleStep * delta;
        }else{
            if(exitScale > 1.0f)
                exitScale -= scaleStep * delta;
        }
    }
 
}