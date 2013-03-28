package screens;

// import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import controllers.Game;
import controllers.ScoreBoardCon;
import controllers.Settings;
import entities.Score;

/**
 * This screen is shown after every level is completed, except for the last one.
 * 
 * @author Jonathan Sterling
 *
 */
public class LevelCleared extends BasicGameState{

	private double accuracy;				// The player's accuracy for the level they just completed.
	private double wpm;						// The player's average words per minute for the level they just completed.
	private int accuracyBonus;				// An accuracy score bonus.
	private int wpmBonus;					// A words per minute score bonus.
	private int totalBonus;					// The user's total bonus for this level.
	
	private String cleared;					// The level cleared message.
	
	public LevelCleared(int state){}
	
	/**
	 * This is where everything is initialized.
	 * @param gc The container for the game.
	 * @param sbg The game itself.
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{

		// Calculates the accuracy and accuracy bonus.
		accuracy = ((double)Settings.totalKilled / (double)(Settings.totalKilled + Settings.totalMissed)) * 100;
		// Rounds to two decimal places.
		accuracy = accuracy * 100;
		accuracy = Math.round(accuracy);
		accuracy = accuracy / 100;
		accuracyBonus = (int) Math.round(accuracy) * 11; 
		
		// Calculates words per minute and wpm bonus.
		wpm = (double)Settings.totalKilled/((double)Play.secondsPlayed / 60.0);
		// Rounds to two decimal places.
		wpm = wpm * 100;
		wpm = Math.round(wpm);
		wpm = wpm / 100;
		wpmBonus = (int) Math.round(wpm) * 11;

		// Calculates the total bonus and adds it to the score.
		totalBonus = accuracyBonus + wpmBonus;
		Settings.score += totalBonus;
		
		// Initialises the level cleared message and stats.
		cleared = "You've cleared level " + Settings.level + "\n\n" +
				"Stats:\n" +
				"You killed " + Settings.totalKilled + " enemies.\n" + 
				"You missed " + Settings.totalMissed + " enemies.\n" +
				"Words per minute: " + wpm + "     Bonus: " + wpmBonus + "\n" +
				"Accuracy: " + accuracy + " %     Bonus: " + accuracyBonus + "\n" + 
				"(N)ext level\n" + 
				"(Q)uit";
	}
	
	/** This class renders things on the screen.
	 * @param gc The container for the game.
	 * @param sbg The game itself.
	 * @param g The graphics context to render to.
	 */ 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		// Draws the level cleared message.
		g.drawString(cleared, 50, 50);
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

		// If they want to go to the next level.
		if (input.isKeyDown(Input.KEY_N)){
			// Puts totalKilled and totalMissed back to zero.
			Settings.totalKilled = 0;
			Settings.totalMissed = 0;
			// Increases the level.
			Settings.level++;
			// Refresh the play state.
			sbg.getState(Game.PLAY_STATE).init(gc, sbg);
			// Enter the play state.
			sbg.enterState(Game.PLAY_STATE, new FadeOutTransition(), new FadeInTransition()); 
		}
		
		// If they want to quit.
		if (input.isKeyDown(Input.KEY_Q)){
			System.exit(0);
		}
		
	}
	
	/**
	 * Gets this state's ID.
	 * 
	 * @return This state's ID.
	 */
	public int getID(){
		return Game.LEVEL_CLEARED_STATE;
	}
	
}
