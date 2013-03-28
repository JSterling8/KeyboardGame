package controllers;

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


public class Game extends StateBasedGame{
	
	public static final String gameName = "Keyboard Warrior!";
	public static final int MAIN_MENU_STATE = 0;
	public static final int DIFFICULTY_STATE = 1;
	public static final int PLAY_STATE = 2;
	public static final int DEATH_STATE = 3;
	public static final int LEVEL_CLEARED_STATE = 4;
	public static final int END_OF_GAME_STATE = 5;
	public static final int HIGHSCORE_STATE = 6;
	


	public Game(String gameName) {
		super(gameName);
		this.addState(new MainMenu(MAIN_MENU_STATE));
		this.addState(new Difficulty(DIFFICULTY_STATE));
		this.addState(new Play(PLAY_STATE, 1));
		// this.addState(new Death(DEATH_STATE));
		this.addState(new LevelCleared(LEVEL_CLEARED_STATE));
		this.addState(new EndOfGame(END_OF_GAME_STATE));
		this.addState(new Highscores(HIGHSCORE_STATE));
	}
	
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
		// System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		// System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
		
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new Game(gameName));
			appgc.setDisplayMode(640, 400, false);
		    appgc.setTargetFrameRate(60);
		    appgc.setMaximumLogicUpdateInterval(1000/60);
		    appgc.setMinimumLogicUpdateInterval(1000/60);
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}
}
