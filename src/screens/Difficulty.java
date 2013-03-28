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

	// These are the play, back, and options buttons.
	private Image playButton;
	private Image backButton;
	private Image optionsButton;

	private GameContainer gc;
	
	private StateBasedGame sbg;

	public Difficulty(int state){

	}

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

		// Initialises the Options, Play, and Back buttons.
		optionsButton = new Image("/res/buttons/options.png");
		playButton = new Image("/res/buttons/play.png");
		backButton = new Image("/res/buttons/back.png");
	}

	/* Draws GFX
	 */ 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		// Draw the "Difficulty" text.
		g.drawImage(difficultyText, 10, 0);

		// Draw the difficulties, and highlight the selected one.
		if(Settings.difficulty != 1){g.drawImage(easyButton, 50, 100);}
		else{g.drawImage(easyButtonSel, 50, 100);}
		if(Settings.difficulty != 2){g.drawImage(mediumButton, 50, 150);}
		else{g.drawImage(mediumButtonSel, 50, 150);}
		if(Settings.difficulty != 3){g.drawImage(hardButton, 50, 200);}
		else{g.drawImage(hardButtonSel, 50, 200);}
		if(Settings.difficulty != 4){g.drawImage(insaneButton, 50, 250);}
		else{g.drawImage(insaneButtonSel, 50, 250);}


		// Draw the Play, Back, and Options buttons.
		g.drawImage(playButton, 20, 315);
		g.drawImage(backButton, 195, 315);
		g.drawImage(optionsButton, 370, 315);
	}

	/* Moves the GFX around
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

	// If the mouse is pressed, then do this: 
	@Override
	public void mousePressed(int button, int x, int y) {
		// If the user clicks options, then go to the options state.
		if((x >= 370 && x <= 620) && (y >= 315 && y <= 415)){
			sbg.enterState(Game.OPTIONS_STATE, new FadeOutTransition(), new FadeInTransition()); 
		}

		// If the user clicks easy, highlight it and set Settings.difficulty to the corresponding value.
		if ((x >= 50 && x <= 250) && (y >= 100 && y <= 150)){				 
			Settings.difficulty = 1;
		}

		// If the user clicks medium, highlight it and set Settings.difficulty to the corresponding value.
		if ((x >= 50 && x <= 250) && (y >= 150 && y <= 200)){		 
			Settings.difficulty = 2;
		}

		// If the user clicks hard, highlight it and set Settings.difficulty to the corresponding value.
		if ((x >= 50 && x <= 250) && (y >= 200 && y <= 250)){	 
			Settings.difficulty = 3;
		}

		// If the user clicks insane, highlight it and set Settings.difficulty to the corresponding value.
		if ((x >= 50 && x <= 250) && (y >= 250 && y <= 300)){				 
			Settings.difficulty = 4;
		}

		// If the user clicks on the back button, then go back to the main menu.
		if ((x >= 195 && x <= 345) && (y >= 315 && y < 415)){			 
			sbg.enterState(Game.MAIN_MENU_STATE, new FadeOutTransition(), new FadeInTransition()); 
		}

		// If the user clicks on the play button, then go to the play state.
		if ((x >= 20 && x <= 170) && (y >= 315 && y < 415)){	
			Settings.level = 1;
			if(Settings.difficulty != 0){
				// Refresh the play state.
				try {
					sbg.getState(Game.PLAY_STATE).init(gc, sbg);
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sbg.enterState(Game.PLAY_STATE, new FadeOutTransition(), new FadeInTransition()); 
			}
			else{

			}
		}

	}

	public int getID(){
		return Game.DIFFICULTY_STATE;
	}

}
