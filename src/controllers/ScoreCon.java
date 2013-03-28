package controllers;

import java.io.Serializable;

/**
 * This class should not be confused with ScoreBoardCon.
 * ScoreBoardCon controls the Score entities.
 * ScoreCon controls the Settings.score variable,
 * amongst other things.
 * 
 * @author Jonathan Sterling
 *
 */
public class ScoreCon {

	private int multiplier;							// The total score multiplier.
	private int letterBonus;						// So that bigger words give more points.
	private int numberConsecutive;					// The number of words entered correctly in a row.
	private int numConsecMult;						// The bonus for consecutively entering words correctly.
	
	public ScoreCon() {
		// Initialises all of the variables.
		numberConsecutive = 0;
		numConsecMult = 1;
		letterBonus = 0;
		Settings.totalKilled = 0;
		Settings.totalMissed = 0;
		
		// Sets the multiplier equal to the difficulty chosen.
		multiplier =  Settings.difficulty;
	}

	/**
	 * When an enemy is killed, increment the score and various multipliers.
	 * 
	 * @param wordLength The length of the word successfully entered.
	 */
	public void enemyKilled(int wordLength) {
		// Increments the total number killed and number consecutively killed.
		Settings.totalKilled++;
		numberConsecutive++;
		
		// Sets the number consecutive bonuses.
		if (numberConsecutive <  5){numConsecMult = 0;}
		else if (numberConsecutive < 10){numConsecMult = 1;}
		else if (numberConsecutive < 15){numConsecMult = 2;}
		else if (numberConsecutive < 20){numConsecMult = 3;}
		else if (numberConsecutive < 25){numConsecMult = 4;}
		else if (numberConsecutive >= 40){numConsecMult = 6;}
		
		// Sets the total multiplier.
		multiplier = numConsecMult + Settings.difficulty;
		
		// Adds to the score based on the multipliers and bonuses.
		Settings.score = Settings.score + (multiplier * letterBonus(wordLength)); 
	}
	
	/**
	 *  This is a recursive summative addition loop that takes the words length as it's parameter.
	 *  
	 * @param length The length of the word correctly entered.
	 * @return The number of bonus points for that word length.
	 */
	public int letterBonus(int length){

		// Base case: If the words length is 1.
		if(length == 1){
			letterBonus = 10;
			return letterBonus;
		}
		// If not the base case, then the letter bonus = (length*10) + letterBonus(length-1)
		else{
			letterBonus = length * 10;
			length--;
			return letterBonus + letterBonus(length);
		}
	}

	/**
	 * If an enemy is missed, notify the variable in Settings and reset the number consecutive multiplier.
	 */
	public void missedEnemy() {
		Settings.totalMissed++;
		
		numberConsecutive = 0;
		numConsecMult = 1;
		multiplier = Settings.difficulty;
	}
	
	/**
	 * Gets the multiplier for displaying on the Play screen.
	 * 
	 * @return The current total multiplier.
	 */
	public int getMultiplier(){
		return multiplier;
	}
	
	/**
	 * Puts the multiplier back at its' default (just the difficulty).
	 */
	public void setDefaultMultiplier(){
		numberConsecutive = 0;
		numConsecMult = 1;
		multiplier = Settings.difficulty;
	}
}
