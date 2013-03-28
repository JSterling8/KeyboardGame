package screens;

// import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import controllers.Game;
import controllers.Settings;

public class LevelCleared extends BasicGameState{

	private double accuracy;
	private double wpm;
	
	private String cleared;
	private String win;
	
	public LevelCleared(int state){}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{

		// Calculates the accuracy.
		accuracy = ((double)Settings.totalKilled / (double)(Settings.totalKilled + Settings.totalMissed)) * 100;
		// Rounds to two decimal places.
		accuracy = accuracy * 100;
		accuracy = Math.round(accuracy);
		accuracy = accuracy / 100;

		// Calculates words per minute.
		wpm = (double)Settings.totalKilled/((double)Play.secondsPlayed / 60.0);
		// Rounds to two decimal places.
		wpm = wpm * 100;
		wpm = Math.round(wpm);
		wpm = wpm / 100;

		cleared = "You've cleared level " + Settings.level + "\n\n" +
				"Stats:\n" +
				"You killed " + Settings.totalKilled + " enemies.\n" + 
				"You missed " + Settings.totalMissed + " enemies.\n" +
				"Words per minute: " + wpm + "\n" +
				"Accuracy: " + accuracy + " %\n" + 
				"(N)ext level\n" + 
				"(Q)uit";
		
		if(Settings.level == 7){

			cleared = "You've cleared level " + Settings.level + "\n\n" +
					"Stats:\n" +
					"You killed " + Settings.totalKilled + " enemies.\n" + 
					"You missed " + Settings.totalMissed + " enemies.\n" +
					"Words per minute: " + wpm + "\n" +
					"Accuracy: " + accuracy + " %\n";

			win = "Congratulations, you've won the game!\n" +
					"Would you like to add your score to the high score table?\n" +
					"(Y)es\n" +
					"No, I'd like to (Q)uit.";
		}
	}
	
	/* Draws GFX
	 */ 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawString(cleared, 50, 50);
		if(Settings.level == 7){
			g.drawString(win, 50, 250);
		}
	}
	
	/* Moves the GFX around
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();

		if(Settings.level != 7){
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

			// If they want to save.
			if (input.isKeyDown(Input.KEY_S)){
				// TODO Allow the user to save.
			}

		}
		
		else{
			if (input.isKeyDown(Input.KEY_Y)){
				// TODO go to high scores screen.  Make sure that it's saveable.
			}
			
		}
		
		// If they want to quit.
		if (input.isKeyDown(Input.KEY_Q)){
			System.exit(0);
		}
		
	}
	
	public int getID(){
		return Game.LEVEL_CLEARED_STATE;
	}
	
}
