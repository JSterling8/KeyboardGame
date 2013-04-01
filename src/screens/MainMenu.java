package screens;

// import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import controllers.Game;
import controllers.Settings;

/**
 * This is the main menu screen.
 * The user can select single or multiplayer.
 * The user can also either exit the game or view the highscores.
 * 
 * @author Jonathan Sterling
 */
public class MainMenu extends BasicGameState{


	// These variables store the mouses x and y coordinates.
	private double mouseX;
	private double mouseY;

	// Buttons
	private Image singlePlayerButton;
	private Image multiplayerButton;
	private Image exitButton;

	private TextField playNumTF;
	private boolean multiplayer;				// Whether or not multiplayer is clicked.  Used to display playNumTF.
	private boolean validPlayerNum;				// Whether or not the user entered a valid player number.
	private boolean clear;						// Whether or not to clear playNumTF

	private Input input;						// The users input.
	private Image highscores;					// The word "Highscores"

	public MainMenu(int state){}

	/**
	 * This is where everything is initialised.
	 * @param gc The container for the game.
	 * @param sbg The game itself.
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		singlePlayerButton = new Image("/res/buttons/singlePlayer.png");
		multiplayerButton = new Image("/res/buttons/multiplayer.png");
		exitButton = new Image("/res/buttons/exit.png");

		// Clear the key-press record.
		input = gc.getInput();
		input.clearKeyPressedRecord();
		
		// Initialises multiplayer to false;
		multiplayer = false;

		// Initialises the player number text field.
		playNumTF = new TextField((GUIContext)gc, gc.getDefaultFont(), 200, 380, 290, 20);

		// Entry for number of players is considered true until proven false.
		validPlayerNum = true;
		clear = false;

		highscores = new Image("/res/buttons/highscores.png");
	}

	/** This class renders things on the screen.
	 * @param gc The container for the game.
	 * @param sbg The game itself.
	 * @param g The graphics context to render to.
	 */ 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawImage(singlePlayerButton, 20, 80);
		g.drawImage(multiplayerButton, 320, 80);
		g.drawImage(exitButton, 535, 320);

		if (multiplayer){
			g.drawString("Enter the number of players:", 200, 360);


			// Draws the name text field.
			playNumTF.render(gc, g);
			playNumTF.setBackgroundColor(Color.black);
			playNumTF.setFocus(true);
		}

		if (multiplayer && !validPlayerNum){
			g.drawString("You must enter a number from 2-7.", 200, 340);
			if (clear){
				playNumTF.setText("");
				clear = false;
			}
		}

		// Draws the high scores button.
		g.drawImage(highscores, 0, 315);
	}

	/**
	 * This method is called every frame to check if events have occurred and updates render() accordingly.
	 * 
	 * @param gc The games container.
	 * @param sbg The game that holds the state.
	 * @param delta The time in milliseconds between frames.
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		input = gc.getInput();

		mouseX = input.getMouseX();
		mouseY = input.getMouseY();
		
		input.clearKeyPressedRecord();

		// If the user presses enter, assume that they want single player and go to the difficulty state.
		if(!multiplayer && input.isKeyPressed(Input.KEY_ENTER)){
			sbg.enterState(Game.DIFFICULTY_STATE, new FadeOutTransition(), new FadeInTransition()); 
		}

		// If single player is clicked.
		if((mouseX >= 20 && mouseX < 320) && (mouseY >= 80 && mouseY <= 280)){
			if(input.isMousePressed(0)){
				// Set number of players to 1.
				Settings.players = 1;
				
				// Reset the boolean variables.
				multiplayer = false;
				validPlayerNum = false;
				clear = true;
				sbg.enterState(Game.DIFFICULTY_STATE, new FadeOutTransition(), new FadeInTransition()); 
			}
		}

		// If multiplayer is clicked.
		if((mouseX >= 320 && mouseX < 640) && (mouseY >= 80 && mouseY <= 280)){
			if(input.isMousePressed(0)){
				multiplayer = true;
			}
		}

		// If multiplayer has been clicked and the click enter..
		if (multiplayer && input.isKeyDown(Input.KEY_ENTER)){
			try{
				Settings.players = Integer.parseInt(playNumTF.getText());
				if(Settings.players <=7 && Settings.players >1){
					validPlayerNum = true;
				}
				else{
					validPlayerNum = false;
					clear = true;
				}
			}catch(Exception e){
				validPlayerNum = false;
				clear = true;
			}

			if (validPlayerNum){
				// Reset the boolean variables.
				multiplayer = false;
				validPlayerNum = false;
				clear = true;
				
				// Go to the difficulty state.
				sbg.enterState(Game.DIFFICULTY_STATE, new FadeOutTransition(), new FadeInTransition()); 
			}
		}

		// If exit is clicked.
		if((mouseX >= 535 && mouseX <= 635) && (mouseY >= 320 && mouseY <= 420)){
			if(input.isMousePressed(0)){
				System.exit(0);
			}
		}

		// If the user clicks on the highscores button, then go to the highscores state.
		if ((input.getMouseX() >= 0 && input.getMouseX() <= 200) && (input.getMouseY() >= 315 && input.getMouseY() < 400)){
			if(input.isMousePressed(0)){
				// Reset the boolean variables.
				multiplayer = false;
				validPlayerNum = false;
				clear = true;
				
				sbg.enterState(Game.HIGHSCORE_STATE, new FadeOutTransition(), new FadeInTransition()); 
			}
		}

	}

	/**
	 * Gets this state's ID.
	 * 
	 * @return This state's ID.
	 */
	public int getID(){
		return Game.MAIN_MENU_STATE;
	}

}
