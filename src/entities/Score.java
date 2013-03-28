package entities;

public class Score {

	private int score;
	private String playerName;
	
	public Score(String playerName, int score) {
		this.playerName = playerName;
		this.score = score;
	}
	
	public String toString(){
		return "Player: " + playerName + "\n" +
				"Score: " + score + "\n";
	}

}
