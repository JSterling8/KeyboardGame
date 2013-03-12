package screens;

// import org.lwjgl.input.Mouse;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controllers.Game;
import controllers.Settings;

public class Difficulty extends BasicGameState{
	
	private int difficulty;
	private int wordSize;
	
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
	
	// This is the "Mode" text.
	private Image modeText;
	
	// These are the number of character buttons while not selected.
	private Image oneCharsButton;
	private Image twoCharsButton;
	private Image threeCharsButton;
	private Image fourCharsButton;
	private Image fiveCharsButton;
	private Image sixCharsButton;
	private Image noLimitButton;
	
	// These are the number of character buttons while selected.
	private Image oneCharsButtonSel;
	private Image twoCharsButtonSel;
	private Image threeCharsButtonSel;
	private Image fourCharsButtonSel;
	private Image fiveCharsButtonSel;
	private Image sixCharsButtonSel;
	private Image noLimitButtonSel;
	
	// These are the play, back, and options buttons.
	private Image playButton;
	private Image backButton;
	private Image optionsButton;
	
	// These variables store the mouses x and y coordinates.
	private double mouseX;
	private double mouseY;
	
	public Difficulty(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		// Initialises the difficulty and amount of characters selected to 0 to indicate no selection.
		difficulty = 0;
		wordSize = 0;

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

		
		
		// Initialises the "Mode" text.
		modeText = new Image("/res/buttons/mode.png");
		
		// Initialises the character number selection images for when they're not selected.
		oneCharsButton = new Image("/res/buttons/oneChars.png");
		twoCharsButton = new Image("/res/buttons/twoChars.png");
		threeCharsButton = new Image("/res/buttons/threeChars.png");
		fourCharsButton = new Image("/res/buttons/fourChars.png");
		fiveCharsButton = new Image("/res/buttons/fiveChars.png");
		sixCharsButton = new Image("/res/buttons/sixChars.png");
		noLimitButton = new Image("/res/buttons/noLimit.png");
		
		// Initialises the character number selection images for when they are selected.
		oneCharsButtonSel = new Image("/res/buttons/oneCharsSel.png");
		twoCharsButtonSel = new Image("/res/buttons/twoCharsSel.png");
		threeCharsButtonSel = new Image("/res/buttons/threeCharsSel.png");
		fourCharsButtonSel = new Image("/res/buttons/fourCharsSel.png");
		fiveCharsButtonSel = new Image("/res/buttons/fiveCharsSel.png");
		sixCharsButtonSel = new Image("/res/buttons/sixCharsSel.png");
		noLimitButtonSel = new Image("/res/buttons/noLimitSel.png");
		
		
		
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
		if(difficulty != 1){g.drawImage(easyButton, 50, 100);}
		else{g.drawImage(easyButtonSel, 50, 100);}
		if(difficulty != 2){g.drawImage(mediumButton, 50, 150);}
		else{g.drawImage(mediumButtonSel, 50, 150);}
		if(difficulty != 3){g.drawImage(hardButton, 50, 200);}
		else{g.drawImage(hardButtonSel, 50, 200);}
		if(difficulty != 4){g.drawImage(insaneButton, 50, 250);}
		else{g.drawImage(insaneButtonSel, 50, 250);}


		// Draw the "Mode" text.
		g.drawImage(modeText, 370, 0);

		// Draw the number of character options, and highlight the selected option.
		if(wordSize != 1){g.drawImage(oneCharsButton, 345, 75);}
		else{g.drawImage(oneCharsButtonSel, 345, 75);}
		if(wordSize != 2){g.drawImage(twoCharsButton, 345, 110);}
		else{g.drawImage(twoCharsButtonSel, 345, 110);}
		if(wordSize != 3){g.drawImage(threeCharsButton, 345, 145);}
		else{g.drawImage(threeCharsButtonSel, 345, 145);}
		if(wordSize != 4){g.drawImage(fourCharsButton, 345, 180);}
		else{g.drawImage(fourCharsButtonSel, 345, 180);}
		if(wordSize != 5){g.drawImage(fiveCharsButton, 345, 215);}
		else{g.drawImage(fiveCharsButtonSel, 345, 215);}
		if(wordSize != 6){g.drawImage(sixCharsButton,345, 250);}
		else{g.drawImage(sixCharsButtonSel,345, 250);}
		if(wordSize != 7){g.drawImage(noLimitButton, 345, 285);}
		else{g.drawImage(noLimitButtonSel, 345, 285);}



		// Draw the Play, Back, and Options buttons.
		g.drawImage(playButton, 20, 315);
		g.drawImage(backButton, 195, 315);
		g.drawImage(optionsButton, 370, 315);

		// Draw the mouse coordinates.
		g.setColor(Color.white);
		g.drawString("" + mouseX, 0, 70);
		g.drawString("" + mouseY, 0, 100);
	}
	
	/* Moves the GFX around
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		
		
		// If the user presses enter.
//		if (input.isKeyDown(Input.KEY_ENTER)){
//			if (input.isMouseButtonDown(0) || input.isKeyDown(Input.KEY_ENTER)){
//				sbg.enterState(Game.PLAY_STATE, new FadeOutTransition(), new FadeInTransition()); // Transitions to Play state
//			}
//		}
		
		// Get the mouses x and y coordinates.
		mouseX = input.getMouseX();
		mouseY = input.getMouseY();
		
		
		// If the user clicks options, then go to the options state.
		if((mouseX >= 370 && mouseX <= 620) && (mouseY >= 315 && mouseY <= 415)){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(Game.OPTIONS_STATE);
			}
		}
		
		// If the user clicks easy, highlight it and set Settings.difficulty to the corresponding value.
		if ((mouseX >= 50 && mouseX <= 250) && (mouseY >= 100 && mouseY <= 150)){
			if(Mouse.isButtonDown(0)){
				difficulty = 1;
				Settings.difficulty = difficulty;
			}
		}
		
		// If the user clicks medium, highlight it and set Settings.difficulty to the corresponding value.
		if ((mouseX >= 50 && mouseX <= 250) && (mouseY >= 150 && mouseY <= 200)){
			if(Mouse.isButtonDown(0)){
				difficulty = 2;
				Settings.difficulty = difficulty;
			}
		}
		
		// If the user clicks hard, highlight it and set Settings.difficulty to the corresponding value.
		if ((mouseX >= 50 && mouseX <= 250) && (mouseY >= 200 && mouseY <= 250)){
			if(Mouse.isButtonDown(0)){
				difficulty = 3;
				Settings.difficulty = difficulty;
			}
		}
		
		// If the user clicks insane, highlight it and set Settings.difficulty to the corresponding value.
		if ((mouseX >= 50 && mouseX <= 250) && (mouseY >= 250 && mouseY <= 300)){
			if(Mouse.isButtonDown(0)){
				difficulty = 4;
				Settings.difficulty = difficulty;
			}
		}
		
		// If the user clicks 1 char, highlight it and set Settings.wordSize to the corresponding value.
		if ((mouseX >= 345 && mouseX <= 595) && (mouseY >= 75 && mouseY < 110)){
			if(Mouse.isButtonDown(0)){
				wordSize = 1;
				Settings.wordSize = wordSize;
			}
		}
		
		// If the user clicks 2 chars, highlight it and set Settings.wordSize to the corresponding value.
		if ((mouseX >= 345 && mouseX <= 595) && (mouseY >= 110 && mouseY < 145)){
			if(Mouse.isButtonDown(0)){
				wordSize = 2;
				Settings.wordSize = wordSize;
			}
		}
		
		// If the user clicks 3 chars, highlight it and set Settings.wordSize to the corresponding value.
		if ((mouseX >= 345 && mouseX <= 595) && (mouseY >= 145 && mouseY < 180)){
			if(Mouse.isButtonDown(0)){
				wordSize = 3;
				Settings.wordSize = wordSize;
			}
		}
		
		// If the user clicks 4 chars, highlight it and set Settings.wordSize to the corresponding value.
		if ((mouseX >= 345 && mouseX <= 595) && (mouseY >= 180 && mouseY < 215)){
			if(Mouse.isButtonDown(0)){
				wordSize = 4;
				Settings.wordSize = wordSize;
			}
		}
		
		// If the user clicks 5 chars, highlight it and set Settings.wordSize to the corresponding value.
		if ((mouseX >= 345 && mouseX <= 595) && (mouseY >= 215 && mouseY < 250)){
			if(Mouse.isButtonDown(0)){
				wordSize = 5;
				Settings.wordSize = wordSize;
			}
		}
		
		// If the user clicks 6 chars, highlight it and set Settings.wordSize to the corresponding value.
		if ((mouseX >= 345 && mouseX <= 595) && (mouseY >= 250 && mouseY < 285)){
			if(Mouse.isButtonDown(0)){
				wordSize = 6;
				Settings.wordSize = wordSize;
			}
		}
		
		// If the user clicks no char limit, highlight it and set Settings.wordSize to the corresponding value.
		if ((mouseX >= 345 && mouseX <= 595) && (mouseY >= 285 && mouseY < 320)){
			if(Mouse.isButtonDown(0)){
				wordSize = 7;
				Settings.wordSize = wordSize;
			}
		}
		
		// If the user clicks on the back button, then go back to the main menu.
		if ((mouseX >= 195 && mouseX <= 345) && (mouseY >= 315 && mouseY < 415)){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(Game.MAIN_MENU_STATE);
			}
		}
		
		// If the user clicks on the play button, then go to the play state.
		if ((mouseX >= 20 && mouseX <= 170) && (mouseY >= 315 && mouseY < 415)){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(Game.PLAY_STATE);
			}
		}
	}
	
	public int getID(){
		return Game.DIFFICULTY_STATE;
	}
	
}
