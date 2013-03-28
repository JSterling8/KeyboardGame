package controllers;

import java.util.Random;

/**
 * This controller is used to generate random numbers, mainly for entity locations.
 * 
 * @author Jonathan Sterling
 *
 */
public class RandomLocation {
	
	private Random randGen;							// A random number generator.
	private int randX;								// A random number.
	
	public RandomLocation(){
		// Initalises the generator.
		randGen = new Random();
		
		// Seeds the generator with the systems current time in milliseconds.
		randGen.setSeed(System.currentTimeMillis());
	}
	
	/**
	 * Generates a random int in a range changing with the size of the words being used.
	 * 
	 * @return A random X coordinate.
	 */
	public int getX(){
		// Generates a number in range of 0 - 600
		if (Settings.level == 1){
			randX = randGen.nextInt((600 - 0 + 1) + 0); 
		}
		// Generates a number in range of 0 - 590
		else if(Settings.level == 2){
			randX = randGen.nextInt((590-0+1) + 0);
		}
		// Generates a number in range of 0 - 570
		else if(Settings.level == 3){
			randX = randGen.nextInt((570-0+1) + 0);
		}
		// Generates a number in range of 0 - 550
		else if(Settings.level == 4){
			randX = randGen.nextInt((550-0+1) + 0);
		}
		// Generates a number in range of 0 - 530
		else if(Settings.level == 5){
			randX = randGen.nextInt((530-0+1) + 0);
		}
		// Generates a number in range of 0 - 510
		else if(Settings.level == 6){
			randX = randGen.nextInt((510-0+1) + 0);
		}
		// Generates a number in range of 0 - 490
		else if(Settings.level == 7){
			randX = randGen.nextInt((490-0+1) + 0);
		}
		return randX;
	}
	
	/**
	 * Generates a random int between 0 and a specified maximum.
	 * 
	 * @param max The largest number that will be returned.
	 * @return A random int between 0 and a specified maximum.
	 */
	public Integer getRand(int max){
		Integer rand = randGen.nextInt((max - 0 + 1) + 0);
		return rand;
	}
}
