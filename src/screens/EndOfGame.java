package screens;

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
 * This is the screen at the end of the game.
 * The user can enter their score to the high scores table,
 * View the high scores table, play again, or exit.
 * 
 * @author Jonathan Sterling
 *
 */
public class EndOfGame extends BasicGameState{

	private String winner;				// The String that announces the game is won.
	private String name;				// The user's name.
	private ScoreBoardCon sbc;			// A score-board controller.
	private TextField nameTF;			// The name TexField.
	private boolean addScore;			// Does the user want to add their score?
	private boolean added;				// Has the user added this score already?
	private Score score;				// The user's score with their name attached.
	
	public EndOfGame(int state){}
	
	/**
	 * This is where everything is initialised.
	 * @param gc The container for the game.
	 * @param sbg The game itself.
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		// Initialises the score-board controller
		sbc = new ScoreBoardCon();
		
		// Initialises winner.
		winner = "Congratulations, you've finished the game!\n\n" +
				"Score: " + Settings.score + "\n\n" + 
				"(A)dd score to highscores table.\n" +
				"(V)iew the highscores\n" + 
				"(P)lay again.\n" +
				"(Q)uit";
		
		// Initialises the name of the player.
		name = "";
		
		// Initialises the name text field.
		nameTF = new TextField((GUIContext)gc, gc.getDefaultFont(), 50, 380, 540, 20);
		
		// Sets the two boolean checks to false.
		addScore = false;
		added = false;
	}
	
	/** This class renders things on the screen.
	 * @param gc The container for the game.
	 * @param sbg The game itself.
	 * @param g The graphics context to render to.
	 */ 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		
		// Draws the winner variable on the screen in red.
		g.setColor(Color.red);
		g.drawString(winner, 50, 50);
		
		// If the user wants to add their score, pop up a box that lets them enter their name.
		if (addScore && !added){
			g.drawString("Enter your name and hit enter", 50, 360);
			
			
			// Draws the name text field.
			nameTF.render(gc, g);
			nameTF.setBackgroundColor(Color.black);
			nameTF.setFocus(true);
		}
		
		// Confirmation that their score has been entered.
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

		// If the user presses "a", allow them to add their score.
		if (input.isKeyPressed(Input.KEY_A)){
			addScore = true;
		}

		// If the user has typed in their name and clicked enter, add their score.
		if (addScore && !added){
			if (input.isKeyPressed(Input.KEY_ENTER)){
				name = nameTF.getText();
				score = new Score(name, Settings.score);
				sbc.addScore(score);
				added = true;
			}
		}

		// If the user presses "p", go to the main menu state.
		if (input.isKeyPressed(Input.KEY_P)){
			sbg.enterState(Game.MAIN_MENU_STATE, new FadeOutTransition(), new FadeInTransition());
		}

		// If the user presses "v", go to the high scores state.
		if (input.isKeyPressed(Input.KEY_V)){
			sbg.enterState(Game.HIGHSCORE_STATE, new FadeOutTransition(), new FadeInTransition());
		}
		
		// If the user presses "q", close the game.
		if (input.isKeyPressed(Input.KEY_Q)){
			System.exit(0);
		}
	}
	
	/**
	 * Gets this state's ID.
	 * 
	 * @return This state's ID.
	 */
	public int getID(){
		return Game.END_OF_GAME_STATE;
	}
}
