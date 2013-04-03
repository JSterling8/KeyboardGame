package screens;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controllers.EnemySpawner;
import controllers.Game;
import controllers.RandomLocation;
import controllers.ScoreCon;
import controllers.Settings;
import entities.Bullet;
import entities.Enemy;
import entities.WordList;

/**
 * This is the actual play screen.
 * The user has to type in the words shown and
 * press enter before they reach the bottom of the screen.
 * If 10 words slip past the player, it's game over.
 * 
 * @author Jonathan Sterling
 *
 */
public class Play extends BasicGameState{

	public static WordList words;
	public static ArrayList<Enemy> enemiesOnScreen;			// An ArrayList of the current enemies on the screen.
	public static ArrayList<Bullet> bulletList;  			// An ArrayList of bullets on the screen.
	public static int secondsPlayed;						// The number of seconds into the round.
	public static RandomLocation randLoc;					// A random location generator.

	private EnemySpawner spawner;				// This controls when the enemies spawn based on the round time.
	private Input input;								// The users keyboard and mouse inputs.

	private boolean wordListGenerated;					// Has the word list been generated?
	private Image gameBackground;						// The games background.
	private Image enemyImage;							// The enemy image.
	private Image bomb;									// An image of a bomb.
	private Image fullhealth;							// An image of a full-health pack.

	private int randX;									// A random x coordinate.
	private int randY;									// A random y coordinate.

	private boolean bombOnScreen; 						// Is there a bomb on the screen?
	private boolean fullhealthOnScreen;					// Is there a full-health pack on the screen?

	private TextField wordEnteredTF;			// User input text field.
	private TextField scoreTF;					// Score text field.
	private ScoreCon score;						// Score variable.
	private TextField healthTF;					// Health text field.
	private TextField multiplierTF;				// Multiplier text field.
	private boolean clear;						// A boolean variable to keep track of when to clear the user input text field.
	private int time;							// The time that the level has been running.
	private int timeLeft;						// The time until the level is over.

	private boolean missed;						// Was an enemy missed?
	private boolean paused;						// Is the game paused?
	private boolean started;					// Is the game started?

	private Sound explode;						// The sound when an enemy is shot.
	private Sound healthRestored;				// The sound when a full-health pack is used.
	private Sound detonate;						// The sound when a bomb is detonated.
	
	public Play(int state){
	}

	/**
	 * This is where everything is initialised.
	 * @param gc The container for the game.
	 * @param sbg The game itself.
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		// Initialises the time left to play.
		if(Settings.level == 1){timeLeft = 60;}
		if(Settings.level == 2){timeLeft = 80;}
		if(Settings.level == 3){timeLeft = 100;}
		if(Settings.level == 4){timeLeft = 120;}
		if(Settings.level == 5){timeLeft = 140;}
		if(Settings.level == 6){timeLeft = 160;}
		if(Settings.level == 7){timeLeft = 180;}
		
		// Initialises the enemy spawner.
		spawner = new EnemySpawner();

		// Initialises the background image
		gameBackground = new Image("res/gamebg.png");

		//Initialises the enemy's image.
		enemyImage = new Image("res/enemies/missile.png");

		// Initialises the bomb image.
		bomb = new Image("res/bomb.png");

		// Initialises the full health image.
		fullhealth = new Image("res/fullhealth.png");

		// Initialises bomb on screen to false.
		bombOnScreen = false;

		// Initialises full-health on screen to false.
		fullhealthOnScreen = false;

		// Initialises the keyboard input text field.
		wordEnteredTF = new TextField((GUIContext)gc, gc.getDefaultFont(), 0, 380, 200, 20);

		// Initialises the score text field.
		scoreTF = new TextField((GUIContext)gc, gc.getDefaultFont(), 200, 380, 190, 20);

		// Initialises the multiplier text field.
		multiplierTF = new TextField((GUIContext)gc, gc.getDefaultFont(), 390, 380, 140, 20);

		// Initialises the health text field.
		healthTF = new TextField((GUIContext)gc, gc.getDefaultFont(), 530, 380, 110, 20);

		// Initialises the missed target variable to false.
		missed = false;

		// Initialises the Random Location variable
		randLoc = new RandomLocation();

		// Initialises the paused variable to true.
		paused = true;

		// Initialises the started variable to false.
		started = false;

		// Initialises the boolean variable to whether or not a Word List has been generated to false.
		wordListGenerated = false;

		// Initialises the level time to 0 seconds.
		time = 0;

		// Initialises the score.
		score = new ScoreCon();

		// Initialises the player's health to 100 and score to 0 if they're just starting the first level.
		if (Settings.level == 1){
			Settings.health = 100;
			Settings.score = 0;
		}

		// Initialises the enemies on screen ArrayList.
		enemiesOnScreen = new ArrayList<Enemy>();

		// Initialises the bullets on the screen ArrayList.
		bulletList = new ArrayList<Bullet>();

		// Sets the multiplier to its default for the level.
		score.setDefaultMultiplier();
		
		// Initialises the sounds.
		healthRestored = new Sound("/res/sounds/healthRestored.ogg");
		explode = new Sound("/res/sounds/explode.ogg");
		detonate = new Sound("/res/sounds/detonate.ogg");
	}

	/** This class renders things on the screen.
	 * @param gc The container for the game.
	 * @param sbg The game itself.
	 * @param g The graphics context to render to.
	 */ 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{

		// Draws the lvel's background image.
		g.drawImage(gameBackground, 0 ,0);

		// Draws the text fields at the bottom of the screen.
		drawTextFields(gc, g);

		// Clears the text field when enter is pressed.
		if(clear){
			wordEnteredTF.setText("");
			clear = false;
		}

		// Draws the player number, level, and time left on the top right of the screen.
		drawPlayerInfo(g);
		
		// Draws the enemies and bullets.
		drawEnemiesAndBullets(g);

		// Randomly spawn either a full-health or a bomb.
		if (!paused && started && secondsPlayed > 0 && secondsPlayed % 10 == 0 && !bombOnScreen && !fullhealthOnScreen){
			try {
				specialItemSpawner();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Draws the bomb on the screen if it was spawned.
		if (bombOnScreen){
			g.drawImage(bomb, randX, randY);
			g.drawString("detonate", randX + 25, randY + 25);
		}

		// Draws the full-health on the screen if it was spawned.
		if (fullhealthOnScreen){
			g.drawImage(fullhealth, randX, randY);
			g.drawString("fullhealth", randX + 25, randY + 25);
		}

		// Displays the pause instructions.
		if (paused){
			displayPauseInstructions(g);	
		}

		// If the game hasn't been started, display the start game instructions.
		if (!started){
			displayStartInstructions(g);
		}
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

		// If there's not a Word List already.
		if (!wordListGenerated){
			generateWordList();
		}

		if (!paused && started){
			calcTime(gc, sbg, delta);
		}

		// Update the score when a target is hit. and set the clear variable to true.
		if (input.isKeyPressed(Input.KEY_ENTER)){
			parseUserInput(sbg);																						
		}

		// If the text field isn't currently in focus, make it in focus.
		if (!wordEnteredTF.hasFocus()){
			wordEnteredTF.setFocus(true);
		}


		if (!paused){
			updateLocations(gc, sbg);
		}
	}

	/**
	 * Draws the player's number and level, along with the time left on the top right of the screen.
	 * 
	 * @param g The graphics context to render to.
	 */
	private void drawPlayerInfo(Graphics g) {
		// Draws the player number on the screen
		g.drawString("Player: " + Settings.currentPlayer, 500, 20);

		// Draws the level number on the screen
		g.drawString("Level: " + Settings.level, 500, 35);

		// Draws the time on the screen.
		g.drawString("Time left: " + timeLeft, 500, 50);
	}

	/**
	 * Draws the text fields on the bottom of the screen.
	 * 
	 * @param gc The container that holds the game.
	 * @param g	The graphics context to render to.
	 */
	private void drawTextFields(GameContainer gc, Graphics g) {
		// Draws the input text field.
		wordEnteredTF.render(gc, g);
		wordEnteredTF.setBackgroundColor(Color.black);
		wordEnteredTF.setFocus(true);

		// Draws the score text field.
		scoreTF.render(gc, g);
		scoreTF.setBackgroundColor(Color.black);
		scoreTF.setText("Score: " + Settings.score);

		// Draws the health text field.
		healthTF.render(gc, g);
		healthTF.setBackgroundColor(Color.black);
		healthTF.setText("Health: " + Settings.health);

		// Draws the multiplier text field.
		multiplierTF.render(gc, g);
		multiplierTF.setBackgroundColor(Color.black);
		multiplierTF.setText("Multiplier: " + score.getMultiplier() + "x");
	}

	/**
	 * Draws the enemies and bullets.
	 * 
	 * @param g The graphics context to render to.
	 */
	private void drawEnemiesAndBullets(Graphics g) {
		// Draw the bullets
		g.setColor(Color.red);
		for(int i = 0; i < bulletList.size(); i++){
			Bullet bullet = bulletList.get(i);
			g.fillRect(bullet.returnX(), bullet.returnY(), 5, 5);
		}

		// Draw the enemies.
		for(int i = 0; i < enemiesOnScreen.size(); i++){
			Enemy enemy = enemiesOnScreen.get(i);
			g.drawImage(enemyImage, (float)enemy.returnX(), (float)enemy.returnY());

			// Draws a black rectangle above the enemy, varying in size by the size of the word associated to the enemy.
			// The black rectangle has a red trim
			float rectXSize = (enemy.getWord().length() * 10) + 2;
			g.setColor(Color.red);
			g.fillRect((float)enemy.returnX() + 13, (float)enemy.returnY() - 22, rectXSize + 4, 24);

			g.setColor(Color.black);
			g.fillRect((float)enemy.returnX() + 15, (float)enemy.returnY() - 20, rectXSize, 20);

			// Draws a red word in that black rectangle.
			g.setColor(Color.red);
			g.drawString(enemy.getWord(), (float)enemy.returnX() + 15, (float)enemy.returnY() - 20);	
		}
	}

	/**
	 * Spawns either a bomb or full-health pack 25% of the time every 10 seconds.
	 * 
	 * @throws Exception Throws exception if the random number requested is less that 0.
	 */
	private void specialItemSpawner() throws Exception {
		// Gets random X,Y coordinates that will be on the bottom of the screen and make sure the word doesn't spawn off of it.
		randX = randLoc.getRand(530);
		randY = randLoc.getRand(150) + 150;

		// Gets a random number between 1 and 240.
		int randCheck = randLoc.getRand(240);

		/** Checks if the number is divisible by 240.  
		 * The random number will be 240 one in 240 times.
		 * A new number is made 60 times per second due to the frame rate and the way Update() works.
		 * Therefore, there is a 60 out of 240 (25%) chance that this if statement will be true.
		 * Simply put, every ten seconds, the player has a 1 in 4 chance of getting either a full-health pack or a bomb.
		 */
		if (randCheck % 240 == 0){
			// If the users health is greater than 60, don't waste their time by giving them a full-health.
			if (Settings.health > 60){
				bombOnScreen = true;
			}
			// 50%(approx.) chance they get a bomb.
			else if (randCheck <= 120){
				bombOnScreen = true;
			}
			// 50% chance(approx.) that they get a full-health.
			else if (randCheck > 120){
				fullhealthOnScreen = true;
			}

		}
	}

	/**
	 * Overlays the pause instructions over the game while it's paused.
	 * 
	 * @param g The graphics context to render to.
	 */
	private void displayPauseInstructions(Graphics g) {
		// Makes the red border of the instruction box.
		g.setColor(Color.red);
		g.fillRect(40, 50, 560, 215);

		// Makes the black inside of the instruction box.
		g.setColor(Color.black);
		g.fillRect(42, 52, 556, 211);

		// Draws a red word in that black instruction box.
		g.setColor(Color.red);
		g.drawString("While playing:\n\n" +
				"Type 'pause' and press enter to pause.\n\n\n" +
				"While paused:\n\n" +
				"Type 'resume' and press enter to continue playing.\n" +
				"Type 'quit' and press enter to quit the game without saving.", 53, 58);
	}

	/**
	 * Displays the start instructions before the level begins.
	 * 
	 * @param g The graphics context to render to.
	 */
	private void displayStartInstructions(Graphics g) {
		// Draw a blue line from the score text field to the box with a string in it.
		g.setColor(Color.cyan);
		g.setLineWidth(3);
		g.drawLine(100, 380, 100, 360);

		// Draws a blue arrowhead on the line.
		g.drawLine(100, 380, 90, 375);
		g.drawLine(100, 380, 110, 375);
		g.drawLine(90, 375, 110, 375);

		// Draw the frame of the rectangle
		g.fillRect(0, 335, 200, 25);

		// Draw the inside of the rectangle
		g.setColor(Color.black);
		g.fillRect(2, 337, 196, 21);

		// Draw the text of the rectangle
		g.setColor(Color.red);
		g.drawString("Click in box to start.", 3, 338);
	}

	/**
	 * Creates a Word List.
	 */
	private void generateWordList() {
		try {
			// Makes a new Word List
			words = new WordList();
			// Fills it with words.
			words.populateList();
			// Sets the boolean variable to true to indicated that a Word List has been created.
			wordListGenerated = true;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("An exception occurred while making a Word List.");
		}
	}

	/**
	 * Updates the enemy and bullet locations.
	 * 
	 * @param gc The game container.
	 * @param sbg The game that holds the state.
	 * @throws SlickException Indicates a failure to render something.
	 */
	private void updateLocations(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		//Update the bullet's position.
		for(int i = 0;i<bulletList.size();i++){
			Bullet bullet = bulletList.get(i);
			bullet.move();

			// Removes bullets that go off screen.
			if ((bullet.returnX() > 639 || bullet.returnX() <= 1) || (bullet.returnY() < 1 || bullet.returnY() > 399)){
				bulletList.remove(i);     
			}
		}

		// Update the enemies' positions.
		for(int i = 0; i < enemiesOnScreen.size(); i++){
			Enemy enemy = enemiesOnScreen.get(i);

			enemy.move();

			// Removes enemies that go off screen. 
			// Decrements the player's health by 10.
			// Sets the multiplier back down.
			if (enemy.returnY() > 389 ){
				enemiesOnScreen.remove(i);
				Settings.health -= 10;
				score.setDefaultMultiplier();

				if(Settings.health == 0){
					sbg.addState(new Death(Game.DEATH_STATE));
					sbg.getState(Game.DEATH_STATE).init(gc, sbg);
					sbg.enterState(Game.DEATH_STATE);
				}
			}
		}
	}

	/**
	 * This method checks the users input when they press enter, see's if it matches anything relevant, and acts accordingly.
	 * If it doesn't match, totalMissed is incremented.
	 * 
	 * @param sbg The game that holds the state.
	 */
	private void parseUserInput(StateBasedGame sbg) {
		for(int i = 0; i < enemiesOnScreen.size(); i++){
			Enemy enemy = enemiesOnScreen.get(i);
			if(!paused && wordEnteredTF.getText().equals(enemy.getWord())){
				addNewBullet((float)enemy.returnX()+25, (float) enemy.returnY() + 25);

				score.enemyKilled(enemy.getWord().length());

				// Remove the enemy from the screen.
				enemiesOnScreen.remove(i);
				missed = false;
				break;
			}

			// If the user hit enter but the word was incorrect and a bomb or full-health isn't on the screen.
			else{
				missed = true;
			}
		}

		// If there's a bomb on the screen and the user types detonate, kill all the enemies.
		if (!paused && bombOnScreen && wordEnteredTF.getText().equals("detonate")){
			for(int i = 0; i < enemiesOnScreen.size(); i++){
				Enemy enemy = enemiesOnScreen.get(i);
				score.enemyKilled(enemy.getWord().length());
			}

			enemiesOnScreen = new ArrayList<Enemy>();
			detonate.play();
			missed = false;
			bombOnScreen = false;
		}

		// If there's a full-health pack on the screen and the user types full-health, restore their health to 100.
		else if (!paused && fullhealthOnScreen && wordEnteredTF.getText().equals("fullhealth")){
			Settings.health = 100;
			healthRestored.play();
			missed = false;
			fullhealthOnScreen = false;
		}

		// If the game is not paused and the user types pause, then pause the game.
		else if (!paused && wordEnteredTF.getText().equals("pause")){
			paused = true;
			missed = false;
		}

		// If the game is paused and the user types resume, then resume the game.
		else if (paused && wordEnteredTF.getText().equals("resume")){
			paused = false;
			missed = false;
		}

		// If the user types quite when the game is paused, exit the game.
		else if (paused && wordEnteredTF.getText().equals("quit")){
			System.exit(0);
		}

		// If the user missed an enemy, let score know.
		if (!paused && missed){
			score.missedEnemy();
		}

		// Clears the input text field.
		clear = true;
	}

	/**
	 * Calculates the time played and time left.  Ends the level when the time is up.
	 * 
	 * @param gc The container that holds the game.
	 * @param sbg The game that holds the state.
	 * @param delta The time in milliseconds between frames.
	 * @throws SlickException Indicates an internal error.
	 */
	private void calcTime(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// Stores the current time.
		int secondsPlayedOld = secondsPlayed;

		// Increments the time based on delta.
		time += delta;
		secondsPlayed = time/1000;

		// If it's been one second.
		if (secondsPlayed - secondsPlayedOld != 0){
			timeLeft--;
			if(timeLeft == 0 && Settings.level < 7){
				// Increments the player number for multiplayer.
				if (Settings.currentPlayer < Settings.players){
					Settings.currentPlayer++;
				}
				
				// Sets the current player back to 1 after everyone has had a turn.
				else if (Settings.currentPlayer == Settings.players){
					Settings.currentPlayer = 1;
				}
				
				// Refresh and enter the level cleared state.
				sbg.getState(Game.LEVEL_CLEARED_STATE).init(gc, sbg);
				sbg.enterState(Game.LEVEL_CLEARED_STATE);
			}

			else if (timeLeft == 0 && Settings.level == 7){
				sbg.addState(new EndOfGame(Game.END_OF_GAME_STATE));
				sbg.getState(Game.END_OF_GAME_STATE).init(gc, sbg);
				sbg.enterState(Game.END_OF_GAME_STATE);
			}
		}

		spawner.timedSpawn();
	}

	/**
	 * Shoots a bullet at the current target.
	 * 
	 * @param targetX The x coordinate that the bullet will head towards.
	 * @param targetY The y coordinate that the bullet will head towards.
	 */
	private void addNewBullet(float targetX, float targetY){
		bulletList.add(new Bullet(320, 400, targetX, targetY));
		explode.play();
	}

	/** 
	 * If the mouse is pressed, then do this.
	 * 
	 */
	@Override
	public void mousePressed(int button, int x, int y) {
		// If input box is clicked, clear the box, unpause the game, and start the game.
		if (!started && x <= 200 && y >= 380){
			clear = true;
			started = true;
			paused = false;

			// Spawn an enemy.
			try {
				spawner.addNewEnemy();
			} catch (SlickException e) {
				System.out.println("Unable to add a new enemy");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gets this state's ID.
	 * 
	 * @return This state's ID.
	 */
	public int getID(){
		return Game.PLAY_STATE;
	}
}


