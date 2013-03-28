package screens;

import java.io.IOException;
import java.util.ArrayList;

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

import controllers.EnemySpawner;
import controllers.Game;
import controllers.RandomLocation;
import controllers.ScoreCon;
import controllers.Settings;
import entities.Bullet;
import entities.Enemy;
import entities.WordList;


public class Play extends BasicGameState{

	public static WordList words;
	public static ArrayList<Enemy> enemiesOnScreen;			// An ArrayList of the current enemies on the screen.
	public static ArrayList<Bullet> bulletList;  			// An ArrayList of bullets on the screen.
	public static int secondsPlayed;
	public static RandomLocation randLoc;

	private Input input;


	private boolean wordListGenerated;
	private Image gameBackground;						// This is the games background
	private Image enemyImage;
	// private Image turret90;							// This is the turret at the bottom of the screen that fires
	private Image bomb;
	private Image fullhealth;

	private int randX;
	private int randY;

	private boolean bombOnScreen; 
	private boolean fullhealthOnScreen;

	private TextField wordEnteredTF;			// User input text field.
	private TextField scoreTF;					// Score text field.
	private ScoreCon score;						// Score variable.
	private TextField healthTF;					// Health text field.
	private TextField multiplierTF;				// Multiplier text field.
	private boolean clear;						// A boolean variable to keep track of when to clear the user input text field.
	private int time;							// The time that the level has been running.
	private int timeLeft;						// The time until the level is over.
	private int playerNumber;					// The players number.

	private EnemySpawner spawner;
	private boolean missed;
	private boolean paused;
	private boolean started;
	private boolean saving;

	public Play(int state){}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		// Initializes the time left to play.
		if(Settings.level == 1){timeLeft = 60;}
		if(Settings.level == 2){timeLeft = 80;}
		if(Settings.level == 3){timeLeft = 100;}
		if(Settings.level == 4){timeLeft = 120;}
		if(Settings.level == 5){timeLeft = 140;}
		if(Settings.level == 6){timeLeft = 160;}
		if(Settings.level == 7){timeLeft = 180;}

		// Initializes the player number.
		playerNumber = 1;

		// Initializes the enemy spawner.
		spawner = new EnemySpawner();

		// Initializes the background image
		gameBackground = new Image("res/gamebg.png");
		// turret90 = new Image ("/res/turrets/standard/turret90.png");

		//Initializes the enemy's image.
		enemyImage = new Image("res/enemies/blue.png");

		// Initializes the bomb image.
		bomb = new Image("res/bomb.png");

		// Initializes the fullhealth image.
		fullhealth = new Image("res/fullhealth.png");

		// Initializes bomb on screen to false.
		bombOnScreen = false;

		// Initializes fullhealth on screen to false.
		fullhealthOnScreen = false;

		// Initializes the keyboard input text field.
		wordEnteredTF = new TextField((GUIContext)gc, gc.getDefaultFont(), 0, 380, 200, 20);

		// Initializes the score text field.
		scoreTF = new TextField((GUIContext)gc, gc.getDefaultFont(), 200, 380, 150, 20);


		// Initializes the health text field.
		healthTF = new TextField((GUIContext)gc, gc.getDefaultFont(), 485, 380, 110, 20);

		// Initializes the multiplier text field.
		multiplierTF = new TextField((GUIContext)gc, gc.getDefaultFont(), 350, 380, 135, 20);

		// Initializes the missed target variable to false.
		missed = false;

		// Initializes the Random Location variable
		randLoc = new RandomLocation();

		// Initializes the paused variable to true.
		paused = true;

		// Initializes the started variable to false.
		started = false;

		// Initializes the boolean variable to whether or not a Word List has been generated to false.
		wordListGenerated = false;

		// Initializes the level time to 0 seconds.
		time = 0;

		// Initializes the score.
		score = new ScoreCon();

		// Initializes the player's health to 100 and score to 0 if they're just starting the first level.
		if (Settings.level == 1){
			Settings.health = 100;
			Settings.score = 0;
		}

		// Initializes the enemies on screen ArrayList.
		enemiesOnScreen = new ArrayList<Enemy>();

		// Initializes the bullets on the screen ArrayList.
		bulletList = new ArrayList<Bullet>();

		// Sets the multiplier to its default for the level.
		score.setDefaultMultiplier();




	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{

		// Draws the lvel's background image.
		g.drawImage(gameBackground, 0 ,0);

		// Draws the turret.
		// g.drawImage(turret90, 285, 300);

		// Draws the input text field.
		wordEnteredTF.render(gc, g);
		wordEnteredTF.setBackgroundColor(Color.black);
		wordEnteredTF.setFocus(true);

		// Clears the text field when enter is pressed.
		if(clear){
			wordEnteredTF.setText("");
			clear = false;
		}

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
			// Has a red trim
			float rectXSize = (enemy.getWord().length() * 10) + 2;
			g.setColor(Color.red);
			g.fillRect((float)enemy.returnX() + 13, (float)enemy.returnY() - 7, rectXSize + 4, 24);

			g.setColor(Color.black);
			g.fillRect((float)enemy.returnX() + 15, (float)enemy.returnY() - 5, rectXSize, 20);

			// Draws a red word in that black rectangle.
			g.setColor(Color.red);
			g.drawString(enemy.getWord(), (float)enemy.returnX() + 15, (float)enemy.returnY() - 5);	
		}

		// Draws the player number on the screen
		g.drawString("Player: " + playerNumber, 500, 20);

		// Draws the level number on the screen
		g.drawString("Level: " + Settings.level, 500, 35);

		// Draws the time on the screen.
		g.drawString("Time left: " + timeLeft, 500, 50);

		// Randomly spawn either a fullhealth or a bomb.
		if (!saving && !paused && started && secondsPlayed > 0 && secondsPlayed % 10 == 0 && !bombOnScreen && !fullhealthOnScreen){
			// Gets random X,Y coordinates that will be on the bottom of the screen and make sure the word doesn't spawn off of it.
			randX = randLoc.getRand(530);
			randY = randLoc.getRand(150) + 200;

			// Gets a random number between 1 and 240.
			int randCheck = randLoc.getRand(240);

			// Checks if the number is divisible by 240.  
			// The random number will be 240 one in 240 times.
			// A new number is made 60 times per second due to the frame rate.
			// Therefore, there is a 60 out of 240 (25%) chance that this if statement will be true.
			// Simply put, every ten seconds, the player has a 1 in 4 chance of getting either a fullhealth or a bomb.
			if (randCheck % 240 == 0){
				// If the users health is greater than 60, don't waste their time by giving them a fullhealth.
				if (Settings.health > 60){
					bombOnScreen = true;
				}
				// Else, 50%(approx.) chance they get a bomb and 50% chance(approx.) that they get a fullhealth.
				else if (randCheck <= 120){
					bombOnScreen = true;
				}
				else if (randCheck > 120){
					fullhealthOnScreen = true;
				}

			}
		}

		if (bombOnScreen){
			g.drawImage(bomb, randX, randY);
			g.drawString("detonate", randX + 25, randY + 25);
		}
		if (fullhealthOnScreen){
			g.drawImage(fullhealth, randX, randY);
			g.drawString("fullhealth", randX + 25, randY + 25);
		}

		if (paused){
			g.setColor(Color.red);
			g.fillRect(40, 50, 560, 215);

			g.setColor(Color.black);
			g.fillRect(42, 52, 556, 211);

			// Draws a red word in that black rectangle.
			g.setColor(Color.red);
			g.drawString("While playing:\n\n" +
					"Type 'pause' and press enter to pause.\n\n\n" +
					"While paused:\n\n" +
					"Type 'resume' and press enter to continue playing.\n" +
					"Type 'quit' and press enter to quit the game without saving.", 53, 58);	
		}

		// If the game hasn't been started.
		if (!started){
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
		

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		input = gc.getInput();

		// If there's not a Word List already.
		if (!wordListGenerated){
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

		if (!saving && !paused && started){
			// Stores the current time.
			int secondsPlayedOld = secondsPlayed;

			// Increments the time based on delta.
			time += delta;
			secondsPlayed = time/1000;

			// If it's been one second.
			if (secondsPlayed - secondsPlayedOld != 0){
				timeLeft--;
				if(timeLeft == 0){
					// Refresh and enter the level cleared state.
					sbg.getState(Game.LEVEL_CLEARED_STATE).init(gc, sbg);
					sbg.enterState(Game.LEVEL_CLEARED_STATE);
				}
			}

			spawner.timedSpawn();
		}



		// Update the score when a target is hit. and set the clear variable to true.
		if (!saving && input.isKeyPressed(Input.KEY_ENTER)){
			for(int i = 0; i < enemiesOnScreen.size(); i++){
				Enemy enemy = enemiesOnScreen.get(i);
				if(!paused && wordEnteredTF.getText().equals(enemy.getWord())){
					addNewBullet((float)enemy.returnX(), (float) enemy.returnY());

					score.enemyKilled(enemy.getWord().length());

					// Remove the enemy from the screen.
					enemiesOnScreen.remove(i);
					missed = false;
					break;
				}

				// If the user hit enter but the word was incorrect and a bomb or fullhealth isn't on the screen.
				else{
					missed = true;
				}
			}

			if (!paused && bombOnScreen && wordEnteredTF.getText().equals("detonate")){
				for(int i = 0; i < enemiesOnScreen.size(); i++){
					Enemy enemy = enemiesOnScreen.get(i);
					score.enemyKilled(enemy.getWord().length());
				}

				enemiesOnScreen = new ArrayList<Enemy>();
				missed = false;
				bombOnScreen = false;
			}

			else if (!paused && fullhealthOnScreen && wordEnteredTF.getText().equals("fullhealth")){
				Settings.health = 100;
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

			else if (paused && wordEnteredTF.getText().equals("quit")){
				System.exit(0);
			}
			
			else if (paused && wordEnteredTF.getText().equals("highscores")){
				sbg.enterState(Game.HIGHSCORE_STATE);
			}

			if (!paused && missed){
				score.missedEnemy();
			}

			// Clears the input text field.
			clear = true;													


			// spawner.addNewEnemy();										
		}

		// If the text field isn't currently in focus, make it in focus.
		if (!wordEnteredTF.hasFocus()){
			wordEnteredTF.setFocus(true);
		}


		if (!paused){
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
				if (enemy.returnY() > 369 ){
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
	}

	/**
	 * Shoots a bullet at the current target.
	 * @param targetX The x coordinate that the bullet will head towards.
	 * @param targetY The y coordinate that the bullet will head towards.
	 */
	private void addNewBullet(float targetX, float targetY){
		bulletList.add(new Bullet(320, 400, targetX, targetY));
	}

	// If the mouse is pressed, then do this: 
	@Override
	public void mousePressed(int button, int x, int y) {
		// If input box is clicked, clear the box, unpause the game, and start the game.
		if (!saving && !started && x <= 200 && y >= 380){
			// Clears the input text field.
			clear = true;
			started = true;
			paused = false;

			try {
				spawner.addNewEnemy();
			} catch (SlickException e) {
				System.out.println("Unable to add a new enemy");
				e.printStackTrace();
			}
		}
	}

	public ScoreCon getScoreCon(){
		return score;
	}

	public boolean getBombOnScreen(){
		return bombOnScreen;
	}

	public boolean getFullhealthOnScreen(){
		return fullhealthOnScreen;
	}

	public int getID(){
		return Game.PLAY_STATE;
	}
}


