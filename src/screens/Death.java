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

public class Death extends BasicGameState{

	private double accuracy;
	private double wpm;
	
	private String dead;
	
	private ScoreBoardCon sbc;
	private TextField nameTF;
	private String name;
	private Score score;
	
	private boolean added;
	private boolean addScore;
	
	public Death(int state){}
	
	/**
	 * This is where everything is initialized.
	 * @param gc The container for the game.
	 * @param sbg The game itself.
	 */
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
		
		dead = "YOU DIED!\n\n" +
				"You killed " + Settings.totalKilled + " enemies.\n" + 
				"You missed " + Settings.totalMissed + " enemies.\n" +
				"Words per minute: " + wpm + "\n" +
				"Accuracy: " + accuracy + " %\n" + 
				"Would you like to try again?\n" +
				"(Y)es\n" +
				"(N)o\n" +
				"(C)hange settings\n" +
				"(A)dd to highscores table.";
		
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
		g.drawString(dead, 50, 50);
		
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
		
		// If they want to retry the level.
		if (!addScore && input.isKeyDown(Input.KEY_Y)){
			// Refresh the play state.
			sbg.getState(Game.PLAY_STATE).init(gc, sbg);
			// Restart them on level 1.
			Settings.score = 0;
			Settings.level = 1;
			// Enter the play state.
			sbg.enterState(Game.PLAY_STATE, new FadeOutTransition(), new FadeInTransition()); 
		}
		
		if (!addScore && input.isKeyDown(Input.KEY_N)){
			System.exit(0);
		}
		
		// If they want to change the settings
		if (!addScore && input.isKeyDown(Input.KEY_C)){
			// Refresh the main menu, difficulty, and play states.
			sbg.getState(Game.MAIN_MENU_STATE).init(gc, sbg);
			sbg.getState(Game.DIFFICULTY_STATE).init(gc, sbg);
			sbg.getState(Game.PLAY_STATE).init(gc, sbg);
			// Enter the main menu state.
			sbg.enterState(Game.MAIN_MENU_STATE, new FadeOutTransition(), new FadeInTransition()); 
		}
		
		if (!addScore && input.isKeyPressed(Input.KEY_A)){
			addScore = true;
		}

		if (addScore && !added){
			if (input.isKeyPressed(Input.KEY_ENTER)){
				name = nameTF.getText();
				score = new Score(name, Settings.score);
				sbc.addScore(score);
				added = true;
				addScore = false;
			}
		}
		
	}
	
	/**
	 * Gets this state's ID.
	 * 
	 * @return This state's ID.
	 */
	public int getID(){
		return Game.DEATH_STATE;
	}
	
}
