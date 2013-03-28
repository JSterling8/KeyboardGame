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

public class Highscores extends BasicGameState{

	private static ScoreBoardCon sbc;
	
	public Highscores(int state){}
	
	/**
	 * This is where everything is initialized.
	 * @param gc The container for the game.
	 * @param sbg The game itself.
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		sbc = new ScoreBoardCon();
	}
	
	/** This class renders things on the screen.
	 * @param gc The container for the game.
	 * @param sbg The game itself.
	 * @param g The graphics context to render to.
	 */ 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.setColor(Color.red);
		g.drawString("Highscores:\n", 150, 10);
		g.drawString(sbc.toString(), 150, 35);
		
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
		
		if (input.isKeyPressed(Input.KEY_F1)){
			if(sbg.getCurrentStateID() == Game.HIGHSCORE_STATE){
				sbc.clear();
			}
		}
		
		if (input.isKeyPressed(Input.KEY_M)){
			sbg.enterState(Game.MAIN_MENU_STATE, new FadeOutTransition(), new FadeInTransition());
		}
		
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
