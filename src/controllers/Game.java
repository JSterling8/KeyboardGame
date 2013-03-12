package controllers;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import screens.Death;
import screens.Difficulty;
import screens.MainMenu;
import screens.Options;
import screens.Play;


public class Game extends StateBasedGame{
	
	public static final String gameName = "Keyboard Warrior!";
	public static final int MAIN_MENU_STATE = 0;
	public static final int DIFFICULTY_STATE = 1;
	public static final int PLAY_STATE = 2;
	public static final int OPTIONS_STATE = 3;
	public static final int DEATH_STATE = 4;


	public Game(String gameName) {
		super(gameName);
		this.addState(new MainMenu(MAIN_MENU_STATE));
		this.addState(new Difficulty(DIFFICULTY_STATE));
		this.addState(new Play(PLAY_STATE));
		this.addState(new Options(OPTIONS_STATE));
		this.addState(new Death(DEATH_STATE));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(MAIN_MENU_STATE).init(gc, this);
		this.getState(DIFFICULTY_STATE).init(gc, this);
		this.getState(PLAY_STATE).init(gc, this);
		this.getState(OPTIONS_STATE).init(gc, this);
		this.getState(DEATH_STATE).init(gc, this);
		this.enterState(MAIN_MENU_STATE);
		gc.setShowFPS(false);
	}

	public static void main(String[] args) throws Exception {
		// System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		// System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
		
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new Game(gameName));
			appgc.setDisplayMode(620, 400, false);
		    appgc.setTargetFrameRate(60);
		    appgc.setMaximumLogicUpdateInterval(1000/60);
		    appgc.setMinimumLogicUpdateInterval(1000/60);
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}
}
