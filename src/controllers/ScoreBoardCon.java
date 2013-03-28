package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import entities.Score;

public class ScoreBoardCon implements Serializable {
	
	private static ArrayList<Score> scores;
	
	public ScoreBoardCon() {
		scores = new ArrayList<Score>();

		Path path = Paths.get("C:/tmp/highscores.save");
		File file = new File(path.toString());
		
		if(file.exists()) {
			try {
				load();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void addScore(Score scoreToAdd){
		scores.add(scoreToAdd);
		try {
			save();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String toString(){
		String s = "";
		for (int i = 0; i < scores.size(); i++){
			s = s + scores.get(i);
		}
		
		return s;
	}
	
	public void save() throws FileNotFoundException, IOException{
		ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("C:/tmp/highscores.save"));
		objOut.writeObject(scores);
		objOut.flush();
		objOut.close();
	}
	
	public void load() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("C:/tmp/highscores.save"));
		scores = (ArrayList<Score>)objIn.readObject();
		objIn.close();
	}
	
	public void clear(){
		scores = new ArrayList<Score>();
		try {
			save();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
