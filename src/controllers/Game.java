package controllers;

import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import screens.Difficulty;
import screens.EndOfGame;
import screens.Highscores;
import screens.LevelCleared;
import screens.MainMenu;
import screens.Play;

/**
 * This is the control hub for the game.  It holds the state IDs
 * and initialises most of them.  This class also contains the main method.
 * 
 * Some aspects of this game were adapted from
 * Sean McGee's QWERTY Warrior game: http://diseasedproductions.net/qwerty.html
 * 
 * Specifics used:
 * Click to start
 * Detonate and full-health pack
 * Bottom TextField layout
 * Enemies with words associated to them
 * Health system
 * 
 * All aspects were used with permission.
 * 
 * 
 * 
 * NOTE: IF YOU'RE RUNNING THIS GAME IN AN OPERATING SYSTEM OTHER THAN WINDOWS:
 * 
 * Right click on the project
 * Click "Properties"
 * Click "Java Build Path"
 * Open the "Libraries" tab
 * Expand "lwjgl.jar"
 * Click on "Native library location:"
 * Click "Edit..."
 * Click "Workspace"
 * Select KMD/lib/native/YOUR_OS
 * Click OK
 * Click OK
 * Click OK
 * Run the game.
 * 
 * @author Jonathan Sterling
 */
public class Game extends StateBasedGame{
	
	// The game's title.
	public static final String GAME_TITLE = "Keyboard Missile Defense!";				
	
	// The game's state ID's.
	public static final int MAIN_MENU_STATE = 0;
	public static final int DIFFICULTY_STATE = 1;
	public static final int PLAY_STATE = 2;
	public static final int DEATH_STATE = 3;
	public static final int LEVEL_CLEARED_STATE = 4;
	public static final int END_OF_GAME_STATE = 5;
	public static final int HIGHSCORE_STATE = 6;
	public static final int TRANSIENT_STATE = 7;
	

	// Adds the states to the game.
	public Game(String gameName) {
		super(gameName);
		this.addState(new MainMenu(MAIN_MENU_STATE));
		this.addState(new Difficulty(DIFFICULTY_STATE));
		this.addState(new Play(PLAY_STATE));
		this.addState(new LevelCleared(LEVEL_CLEARED_STATE));
		this.addState(new EndOfGame(END_OF_GAME_STATE));
		this.addState(new Highscores(HIGHSCORE_STATE));
	}
	
	// Initialises the states.
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(MAIN_MENU_STATE).init(gc, this);
		this.getState(DIFFICULTY_STATE).init(gc, this);
		this.getState(PLAY_STATE).init(gc, this);
		this.getState(LEVEL_CLEARED_STATE).init(gc, this);
		this.getState(END_OF_GAME_STATE).init(gc, this);
		this.getState(HIGHSCORE_STATE).init(gc, this);
		this.enterState(MAIN_MENU_STATE);
		gc.setShowFPS(false);
	}

	public static void main(String[] args) throws Exception {	
		System.setProperty("org.lwjgl.librarypath", new File(new File("lib\\native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
		
		try{
			AppGameContainer appgc = 
					new AppGameContainer(new Game(GAME_TITLE));			// Makes a new game container with the specified title.
			appgc.setDisplayMode(640, 400, false);						// Sets the screen size to 640x400.
		    appgc.setTargetFrameRate(60);								// Sets the frame rate to 60.
			appgc.start();												// Starts the game.
		}catch(SlickException e){
			e.printStackTrace();										// Prints an error if it doesn't work.
		}
	}
}
