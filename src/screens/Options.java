package screens;

// import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controllers.Game;

public class Options extends BasicGameState{

	private String options;
	
	public Options(int state){}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		options = "(B)ack" +
				"\n\n" + "(Q)uit";

	}
	
	/**
	 * (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 * This method draws things on the screen.
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawString(options, 100, 100);
	}
	
	/* Moves the GFX around
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_B)){
			sbg.enterState(Game.DIFFICULTY_STATE);
		}
	
		if (input.isKeyPressed(Input.KEY_Q)){
			System.exit(0);
		}
	}
	
	// This one is pretty self explanatory.
	public int getID(){
		return Game.OPTIONS_STATE;
	}
	
}
