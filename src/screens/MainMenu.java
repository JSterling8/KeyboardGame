package screens;

// import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controllers.Game;
import controllers.Settings;

public class MainMenu extends BasicGameState{
	
	
	// These variables store the mouses x and y coordinates.
	private double mouseX;
	private double mouseY;
	
	private Image newGameButton;
	private Image loadGameButton;
	private Image exitButton;
	
	
	public MainMenu(int state){}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		newGameButton = new Image("/res/buttons/newGame.png");
		loadGameButton = new Image("/res/buttons/loadGame.png");
		exitButton = new Image("/res/buttons/exit.png");
	}
	
	/* Draws GFX
	 */ // 211 51, 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawImage(newGameButton, 20, 80);
		g.drawImage(loadGameButton, 320, 80);
		g.drawImage(exitButton, 535, 320);

	}
	
	/* Moves the GFX around
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		
		mouseX = input.getMouseX();
		mouseY = input.getMouseY();

		if(input.isKeyPressed(Input.KEY_ENTER)){
			sbg.enterState(Game.DIFFICULTY_STATE);
		}
		
		// If new game is clicked.
		if((mouseX >= 20 && mouseX < 320) && (mouseY >= 80 && mouseY <= 280)){
			if(input.isMousePressed(0)){
				sbg.enterState(Game.DIFFICULTY_STATE);
			}
		}
		
		// If load game is clicked.
		if((mouseX >= 320 && mouseX < 640) && (mouseY >= 80 && mouseY <= 280)){
			if(input.isMousePressed(0)){
				Settings.fromSave = true;
				sbg.enterState(Game.PLAY_STATE);
			}
		}
		
		// If exit is clicked.
		if((mouseX >= 535 && mouseX <= 635) && (mouseY >= 320 && mouseY <= 420)){
			if(input.isMousePressed(0)){
				System.exit(0);
			}
		}
		
	}
	
	public int getID(){
		return Game.MAIN_MENU_STATE;
	}
	
}
