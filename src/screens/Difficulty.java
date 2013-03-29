package screens;

// import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import controllers.Game;
import controllers.Settings;

/**
 * This is the difficulty screen. 
 * It's where the user selects the difficulty of the game.
 * 
 * @author Jonathan Sterling
 *
 */
public class Difficulty extends BasicGameState{

	// This is the difficulty text.
	private Image difficultyText;	

	// These are the various difficulties while not selected.
	private Image easyButton;						
	private Image mediumButton;
	private Image hardButton;
	private Image insaneButton;

	// These are the various difficulties while selected.
	private Image easyButtonSel;
	private Image mediumButtonSel;
	private Image hardButtonSel;
	private Image insaneButtonSel;

	// These are the play and back buttons.
	private Image playButton;
	private Image backButton;

	// The GameContainer and StateBasedGame for passing between methods.
	private GameContainer gc;
	private StateBasedGame sbg;


	public Difficulty(int state){}
	
	/**
	 * This is where everything is initialised.
	 * 
	 * @param gc The container for the game.
	 * @param sbg The game itself.
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		// Initializes the local pointer to sbg  and gc for use in other methods.
		this.sbg = sbg;
		this.gc = gc;

		// Initialises the difficulty and amount of characters selected to 0 to indicate no selection.
		Settings.difficulty = 0;

		// Initialises the "Difficulty" text.
		difficultyText = new Image("/res/buttons/difficulty.png");

		// Initialises the difficulty selection images for when they're not selected.
		easyButton = new Image("/res/buttons/easy.png");
		mediumButton = new Image("/res/buttons/medium.png");
		hardButton = new Image("/res/buttons/hard.png");
		insaneButton = new Image("/res/buttons/insane.png");

		// Initialises the difficulty selection images for when they are selected.
		easyButtonSel = new Image("/res/buttons/easySel.png");
		mediumButtonSel = new Image("/res/buttons/mediumSel.png");
		hardButtonSel = new Image("/res/buttons/hardSel.png");
		insaneButtonSel = new Image("/res/buttons/insaneSel.png");

		// Initialises the Play and Back buttons.
		playButton = new Image("/res/buttons/play.png");
		backButton = new Image("/res/buttons/back.png");
	}

	/** 
	 * This class renders things on the screen.
	 * 
	 * @param gc The container for the game.
	 * @param sbg The game itself.
	 * @param g The graphics context to render to.
	 */ 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		// Draw the "Difficulty" text.
		g.drawImage(difficultyText, 170, 0);

		// Draw the difficulties, and highlight the selected one.
		if(Settings.difficulty != 1){g.drawImage(easyButton, 210, 100);}
		else{g.drawImage(easyButtonSel, 210, 100);}
		if(Settings.difficulty != 2){g.drawImage(mediumButton, 210, 150);}
		else{g.drawImage(mediumButtonSel, 210, 150);}
		if(Settings.difficulty != 3){g.drawImage(hardButton, 210, 200);}
		else{g.drawImage(hardButtonSel, 210, 200);}
		if(Settings.difficulty != 4){g.drawImage(insaneButton, 210, 250);}
		else{g.drawImage(insaneButtonSel, 210, 250);}


		// Draw the Play and Back buttons.
		g.drawImage(playButton, 0, 315);
		g.drawImage(backButton, 490, 315);
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

		// If the user presses enter.
		if (input.isKeyDown(Input.KEY_ENTER)){
			// If the difficulty has been selected.
			if(Settings.difficulty != 0){
				Settings.level = 1;
				
				// Transitions to Play state
				sbg.getState(Game.PLAY_STATE).init(gc, sbg);
				sbg.enterState(Game.PLAY_STATE, new FadeOutTransition(), new FadeInTransition()); 
			}
		}
	}

	/**
	 *  If the mouse is pressed, this is called.
	 */
	@Override
	public void mousePressed(int button, int x, int y) {
		// If the user clicks easy, highlight it and set Settings.difficulty to the corresponding value.
		if ((x >= 210 && x <= 410) && (y >= 100 && y <= 150)){				 
			Settings.difficulty = 1;
		}

		// If the user clicks medium, highlight it and set Settings.difficulty to the corresponding value.
		if ((x >= 210 && x <= 410) && (y >= 150 && y <= 200)){		 
			Settings.difficulty = 2;
		}

		// If the user clicks hard, highlight it and set Settings.difficulty to the corresponding value.
		if ((x >= 210 && x <= 410) && (y >= 200 && y <= 250)){	 
			Settings.difficulty = 3;
		}

		// If the user clicks insane, highlight it and set Settings.difficulty to the corresponding value.
		if ((x >= 210 && x <= 410) && (y >= 250 && y <= 300)){				 
			Settings.difficulty = 4;
		}

		// If the user clicks on the back button, then go back to the main menu.
		if ((x >= 490 && x <= 640) && (y >= 315 && y < 400)){			 
			sbg.enterState(Game.MAIN_MENU_STATE, new FadeOutTransition(), new FadeInTransition()); 
		}

		// If the user clicks on the play button, then go to the play state.
		if ((x >= 0 && x <= 150) && (y >= 315 && y < 400)){	
			Settings.level = 1;
			Settings.currentPlayer = 1;
			if(Settings.difficulty != 0){
				// Refresh the play state.
				try {
					sbg.getState(Game.PLAY_STATE).init(gc, sbg);
				} catch (SlickException e) {
					e.printStackTrace();
				}
				sbg.enterState(Game.PLAY_STATE, new FadeOutTransition(), new FadeInTransition()); 
			}
		}
	}

	/**
	 * Gets this state's ID.
	 * 
	 * @return This state's ID.
	 */
	public int getID(){
		return Game.DIFFICULTY_STATE;
	}

}
