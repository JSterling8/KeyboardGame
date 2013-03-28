package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import entities.Score;

public class ScoreBoardCon implements Serializable {
	
	private static ArrayList<Score> scores;
	
	public ScoreBoardCon() {
		scores = new ArrayList<Score>();
	}
	
	public void addScore(Score scoreToAdd){
		scores.add(scoreToAdd);
		scores.add(scoreToAdd);
	}
	
	public String toString(){
		String s = "";
		for (int i = 0; i < scores.size(); i++){
			s = s + scores.get(i);
		}
		
		return s;
	}
	
	public void save() throws FileNotFoundException, IOException{
		ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("/res/save/highscores.save"));
		objOut.writeObject(scores);
		objOut.flush();
		objOut.close();
	}
	
	public void load() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("/res/save/highscores.save"));
		scores = (ArrayList)objIn.readObject();
		objIn.close();
	}

}
