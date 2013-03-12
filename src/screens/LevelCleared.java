package screens;

// import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controllers.Game;

public class LevelCleared extends BasicGameState{

	private String passed;
	
	public LevelCleared(int state){}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		passed = "You beat the level." +
				"\n" +
				"(N)ext Level" +
				"\n" +
				"(Q)uit";
	}
	
	/* Draws GFX
	 */ 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawString(passed, 50, 50);
	}
	
	/* Moves the GFX around
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		
		// If they want to retry the level.
		if (input.isKeyDown(Input.KEY_N)){
			// Refresh the play state.
			sbg.getState(Game.PLAY_STATE).init(gc, sbg);
			// Enter the play state.
			sbg.enterState(Game.PLAY_STATE);
		}
		
		if (input.isKeyDown(Input.KEY_Q)){
			System.exit(0);
		}
		
	}
	
	public int getID(){
		return Game.LEVEL_CLEARED_STATE;
	}
	
}
