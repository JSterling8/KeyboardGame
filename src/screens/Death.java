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

	private double accuracy;					// The player's accuracy.
	private double wpm;							// The player's average words per minute.
	
	private String dead;						// The string that announces the player has died.
	
	private ScoreBoardCon sbc;					// The score-board controller for adding scores to the table.
	private TextField nameTF;					// The TextField for the user to enter their name.
	private String name;						// The user's name.
	private Score score;						// The user's score with their name attached to it.
	
	private boolean added;						// Has the score been added to the table?
	private boolean addScore;					// Does the user want to add their score to the table?
	private boolean noNameSpecified;			// Did the user actually enter a name when prompted?
	
	public Death(int state){}
	
	/**
	 * This is where everything is initialised.
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
		
		// Initialises the dead message.
		dead = "YOU DIED!\n\n" +
				"You killed " + Settings.totalKilled + " enemies.\n" + 
				"You missed " + Settings.totalMissed + " enemies.\n" +
				"Words per minute: " + wpm + "\n" +
				"Accuracy: " + accuracy + " %\n\n" + 
				"(T)ry again.\n" +
				"(C)hange settings.\n\n" +
				"(A)dd to highscores table.\n" +
				"(V)iew highscores table.\n\n" +
				"(Q)uit.";
		
		// Initialises the score board controller.
		sbc = new ScoreBoardCon();
		name = "";
		
		// Initializes the name text field.
		nameTF = new TextField((GUIContext)gc, gc.getDefaultFont(), 50, 380, 540, 20);
		
		// The user hasn't added their score yet and hasn't indicated that they want to.
		addScore = false;
		added = false;
		noNameSpecified = false;
	}
	
	/** This class renders things on the screen.
	 * @param gc The container for the game.
	 * @param sbg The game itself.
	 * @param g The graphics context to render to.
	 */ 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		// Draws the "You're dead" message on the screen.
		g.drawString(dead, 50, 50);
		
		// Draws the name TextField on the screen and instructions.
		if (addScore && !added){
			g.drawString("Enter your name and hit enter", 50, 360);
			
			// Draws the name text field.
			nameTF.render(gc, g);
			nameTF.setBackgroundColor(Color.black);
			nameTF.setFocus(true);
		}
		
		if (noNameSpecified){
			g.drawString("You must enter a name.", 50, 345);
		}
		
		// Confirms that the score has been added to the table.
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
		if (!addScore && input.isKeyDown(Input.KEY_T)){
			// Refresh the play state.
			sbg.getState(Game.PLAY_STATE).init(gc, sbg);
			// Restart them on level 1.
			Settings.score = 0;
			Settings.level = 1;
			// Enter the play state.
			sbg.enterState(Game.PLAY_STATE, new FadeOutTransition(), new FadeInTransition()); 
		}
		
		// If they want to quit, then exit the game.
		if (!addScore && input.isKeyDown(Input.KEY_Q)){
			System.exit(0);
		}
		
		// Shows the high score table if requested.
		if (!addScore && input.isKeyDown(Input.KEY_V)){
			sbg.enterState(Game.HIGHSCORE_STATE, new FadeOutTransition(), new FadeInTransition()); 
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
		
		// If the user presses A then show nameTF.
		if (!addScore && input.isKeyDown(Input.KEY_A)){
			addScore = true;
		}

		// If the nameTF is up and the user presses enter, add their score to the table.
		if (addScore && !added){
			if (input.isKeyDown(Input.KEY_ENTER)){
				name = nameTF.getText();

				if (!name.equals("")){
					score = new Score(name, Settings.score);
					sbc.addScore(score);
					addScore = false;
					added = true;
					noNameSpecified = false;
				}
				else {
					noNameSpecified = true;
				}
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
