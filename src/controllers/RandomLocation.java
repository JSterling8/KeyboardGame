package controllers;

import java.util.Random;


public class RandomLocation {
	
	private Random randGen;
	private int randX;
	
	public RandomLocation(){
		randGen = new Random();
		randGen.setSeed(System.currentTimeMillis());
	}
	
	/**
	 * Generates a random int in a range changing with the size of the words being used.
	 * @return A random X coordinate.
	 */
	public int getX(){
		// Generates a number in range of 0 - 600
		if (Settings.wordSize == 1){
			randX = randGen.nextInt((600 - 0 + 1) + 0); 
		}
		// Generates a number in range of 0 - 590
		else if(Settings.wordSize == 2){
			randX = randGen.nextInt((590-0+1) + 0);
		}
		// Generates a number in range of 0 - 570
		else if(Settings.wordSize == 3){
			randX = randGen.nextInt((570-0+1) + 0);
		}
		// Generates a number in range of 0 - 550
		else if(Settings.wordSize == 4){
			randX = randGen.nextInt((550-0+1) + 0);
		}
		// Generates a number in range of 0 - 530
		else if(Settings.wordSize == 5){
			randX = randGen.nextInt((530-0+1) + 0);
		}
		// Generates a number in range of 0 - 510
		else if(Settings.wordSize == 6){
			randX = randGen.nextInt((510-0+1) + 0);
		}
		// Generates a number in range of 0 - 490
		else if(Settings.wordSize == 7){
			randX = randGen.nextInt((490-0+1) + 0);
		}
		return randX;
	}
	
	/**
	 * Generates a random int between 0 and a specified maximum.
	 * @param max The largest number that will be returned.
	 * @return A random int between 0 and a specified maximum.
	 */
	public Integer getRand(int max){
		Integer rand = randGen.nextInt((max - 0 + 1) + 0);
		return rand;
	}
}
