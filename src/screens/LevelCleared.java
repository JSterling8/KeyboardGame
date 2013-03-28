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

public class LevelCleared extends BasicGameState{

	private double accuracy;
	private double wpm;
	private int accuracyBonus;
	private int wpmBonus;
	private int totalBonus;
	
	private String cleared;
	private String win;
	
	private boolean added;
	private boolean addScore;
	
	private ScoreBoardCon sbc;
	private TextField nameTF;
	private String name;
	private Score score;
	
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
		
		cleared = "You've cleared level " + Settings.level + "\n\n" +
				"Stats:\n" +
				"You killed " + Settings.totalKilled + " enemies.\n" + 
				"You missed " + Settings.totalMissed + " enemies.\n" +
				"Words per minute: " + wpm + "     Bonus: " + wpmBonus + "\n" +
				"Accuracy: " + accuracy + " %     Bonus: " + accuracyBonus + "\n" + 
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
		
		sbc = new ScoreBoardCon();
		name = "";
		
		// Initializes the name text field.
		nameTF = new TextField((GUIContext)gc, gc.getDefaultFont(), 50, 380, 540, 20);
		
		addScore = false;
		added = false;
	}
	
	/** This class renders things on the screen.
	 * @param gc The container for the game.
	 * @param sbg The game itself.
	 * @param g The graphics context to render to.
	 */ 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawString(cleared, 50, 50);
		if(Settings.level == 7){
			g.drawString(win, 50, 250);
		}
		
		if (addScore && !added){
			g.drawString("Enter your name and hit enter", 50, 360);
			
			
			// Draws the name text field.
			nameTF.render(gc, g);
			nameTF.setBackgroundColor(Color.black);
			nameTF.setFocus(true);
		}
		
		if (added){
			g.drawString("Your score has been added to the highscores table", 50, 360);
		}
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
		}
		
		else{
			if (input.isKeyPressed(Input.KEY_A)){
				addScore = true;
			}

			if (addScore && !added){
				if (input.isKeyPressed(Input.KEY_ENTER)){
					name = nameTF.getText();
					score = new Score(name, Settings.score);
					sbc.addScore(score);
					added = true;
				}
			}
			
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
