package controllers;

public class ScoreCon {

	private int multiplier;
	private int letterBonus;
	private int numberConsecutive;
	private int numConsecMult;
	
	public ScoreCon() {
		numberConsecutive = 0;
		numConsecMult = 1;
		letterBonus = 0;
		multiplier =  Settings.difficulty;
		Settings.score = 0;
		Settings.totalKilled = 0;
		Settings.totalMissed = 0;
		
	}

	public void enemyKilled(int wordLength) {
		Settings.totalKilled++;
		numberConsecutive++;
		
		if (numberConsecutive <  5){numConsecMult = 0;}
		else if (numberConsecutive < 10){numConsecMult = 1;}
		else if (numberConsecutive < 15){numConsecMult = 2;}
		else if (numberConsecutive < 20){numConsecMult = 3;}
		else if (numberConsecutive < 25){numConsecMult = 4;}
		else if (numberConsecutive >= 40){numConsecMult = 6;}
		
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
		multiplier = Settings.difficulty;
	}
	
	public int getMultiplier(){
		return multiplier;
	}
	
	public void setDefaultMultiplier(){
		numberConsecutive = 0;
		numConsecMult = 1;
		multiplier = Settings.difficulty;
	}
}
