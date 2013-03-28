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

public class EndOfGame extends BasicGameState{

	private String winner;
	private String name;
	private ScoreBoardCon sbc;
	private TextField nameTF;
	private boolean addScore;
	private boolean added;
	private Score score;
	
	public EndOfGame(int state){}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		sbc = new ScoreBoardCon();
		
		winner = "Congratulations, you've finished the game!\n\n" +
				"Score: " + Settings.score + "\n\n" + 
				"(A)dd score to highscores table.\n" +
				"(V)iew the highscores\n" + 
				"(P)lay again.\n" +
				"(Q)uit";
		
		name = "";
		
		// Initializes the score text field.
		nameTF = new TextField((GUIContext)gc, gc.getDefaultFont(), 50, 380, 540, 20);
		
		addScore = false;
		added = false;
	}
	
	/* Draws GFX
	 */ 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.setColor(Color.red);
		
		g.drawString(winner, 50, 50);
		
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
	
	/* Moves the GFX around
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();

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

		if (input.isKeyPressed(Input.KEY_P)){
			sbg.enterState(Game.MAIN_MENU_STATE, new FadeOutTransition(), new FadeInTransition());
		}

		if (input.isKeyPressed(Input.KEY_V)){
			sbg.enterState(Game.HIGHSCORE_STATE, new FadeOutTransition(), new FadeInTransition());
		}
		
		if (input.isKeyPressed(Input.KEY_Q)){
			System.exit(0);
		}
	}
	
	public int getID(){
		return Game.END_OF_GAME_STATE;
	}
}
