package screens;

// import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controllers.Game;
import controllers.ScoreBoardCon;
import entities.Score;

public class Highscores extends BasicGameState{

	private static ScoreBoardCon sbc = new ScoreBoardCon();
	private Score placeholder;
	
	public Highscores(int state){}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		placeholder = new Score("Jon", 9001);
		sbc.addScore(placeholder);
		
	}
	
	/* Draws GFX
	 */ 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawString("Highscores:\n", 150, 50);
		g.drawString(sbc.toString(), 150, 75);
	}
	
	/* Moves the GFX around
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		
	}
	
	public int getID(){
		return Game.HIGHSCORE_STATE;
	}

	
}
