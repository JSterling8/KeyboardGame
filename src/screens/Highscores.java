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

	private static ScoreBoardCon sbc = new ScoreBoardCon();
	
	public Highscores(int state){}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
	}
	
	/* Draws GFX
	 */ 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.setColor(Color.red);
		g.drawString("Highscores:\n", 150, 10);
		g.drawString(sbc.toString(), 150, 35);
		
		g.drawString("(C)lear scores", 350, 310);
		g.drawString("(M)ain Menu", 350, 325);
		g.drawString("(E)xit", 350, 340);
	}
	
	/* Moves the GFX around
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_C)){
			sbc.clear();
		}
		
		if (input.isKeyPressed(Input.KEY_M)){
			sbg.enterState(Game.MAIN_MENU_STATE, new FadeOutTransition(), new FadeInTransition());
		}
		
		if (input.isKeyDown(Input.KEY_E)){
			System.exit(0);
		}
		
	}
	
	public int getID(){
		return Game.HIGHSCORE_STATE;
	}

	
}
