package controllers;

public class Score {

	private int multiplier;
	private int letterBonus;
	private int numberConsecutive;
	private int numConsecMult;
	
	public Score() {
		numberConsecutive = 0;
		numConsecMult = 1;
		letterBonus = 0;
		Settings.score = 0;
		Settings.totalKilled = 0;
		Settings.totalMissed = 0;
		
	}

	public void enemyKilled(int wordLength) {
		Settings.totalKilled++;
		numberConsecutive++;
		
		if (numberConsecutive == 5){numConsecMult = 2;}
		if (numberConsecutive == 10){numConsecMult = 3;}
		if (numberConsecutive == 15){numConsecMult = 4;}
		if (numberConsecutive == 20){numConsecMult = 5;}
		
		multiplier = numConsecMult + Settings.difficulty;
		Settings.score = Settings.score + (multiplier * letterBonus(wordLength)); 
	}
	
	// This is a recursive summative addition loop that takes the words length as it's parameter.
	public int letterBonus(int length){

		if(length == 1){
			letterBonus = 10;
			return letterBonus;
		}
		else{
			letterBonus = length * 10;
			length--;
			return letterBonus + letterBonus(length);
		}
	}

	public void missedEnemy() {
		Settings.totalMissed++;
		
		numberConsecutive = 0;
		numConsecMult = 1;
		
	}
}
