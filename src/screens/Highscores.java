package screens;

// import org.lwjgl.input.Mouse;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import controllers.Game;
import controllers.ScoreBoardCon;
import entities.Score;

/**
 * This is the highscores screen.
 * The user can go back to the main menu, quit, or clear the scores.
 * 
 * @author Jonathan Sterling
 *
 */
public class Highscores extends BasicGameState{

	private static ScoreBoardCon sbc;
	
	public Highscores(int state){}
	
	/**
	 * This is where everything is initialised.
	 * 
	 * @param gc The container for the game.
	 * @param sbg The game itself.
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		// Initialises a new score board controller.
		sbc = new ScoreBoardCon();
	}
	
	/**
	 * This class renders things on the screen.
	 * 
	 * @param gc The container for the game.
	 * @param sbg The game itself.
	 * @param g The graphics context to render to.
	 */ 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		// Draws the word "Highscores:" followed by a list of scores.
		g.setColor(Color.red);
		g.drawString("Highscores:\n", 150, 10);
		g.drawString(sbc.toString(), 150, 35);
		
		// Draws the user's options.
		g.drawString("To clear scores press f1", 350, 310);
		g.drawString("(M)ain Menu", 350, 325);
		g.drawString("(E)xit", 350, 340);
	}
	
	/**
	 * This method is called every frame to check if events have occurred and updates render() accordingly.
	 * 
	 * @param gc The games container.
	 * @param sbg The game that holds the state.
	 * @param delta The time in milliseconds between frames.
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		
		// If the user presses f1, clear the highscores.
		if (input.isKeyPressed(Input.KEY_F1)){
			if(sbg.getCurrentStateID() == Game.HIGHSCORE_STATE){
				sbc.clear();
			}
		}
		
		// If the user presses "m", go back to the main menu.
		if (input.isKeyPressed(Input.KEY_M)){
			sbg.enterState(Game.MAIN_MENU_STATE, new FadeOutTransition(), new FadeInTransition());
		}
		
		// If the user presses "e", exit the game.
		if (input.isKeyDown(Input.KEY_E)){
			System.exit(0);
		}
		
	}
	
	/**
	 * Gets this state's ID.
	 * 
	 * @return This state's ID.
	 */
	public int getID(){
		return Game.HIGHSCORE_STATE;
	}

	
}
