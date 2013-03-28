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

/**
 * This class controls the high score board.
 * It can save and load a high scores file for permanent storage.
 * 
 * @author Jonathan Sterling
 *
 */
public class ScoreBoardCon implements Serializable {
	
	private static ArrayList<Score> scores;						// An ArrayList of all of the high scores.
	
	/**
	 * Initialises the scores ArrayList and tries to load one if one already exists.
	 */
	public ScoreBoardCon() {
		scores = new ArrayList<Score>();

		// Sets the path to C:/tmp/highscores.save
		Path path = Paths.get("C:/tmp/highscores.save");
		File file = new File(path.toString());
		
		// If the file already exists, load it.
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
	
	/**
	 * Adds a score to the scores ArrayList and saves the list to the hard disk..
	 * 
	 * @param scoreToAdd The score to add to the scores ArrayList.
	 */
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
	
	/**
	 * Saves the high scores table to C:/tmp/highscores.save
	 * 
	 * @throws FileNotFoundException 
	 * @throws IOException Indicates an error whilst saving.
	 */
	public void save() throws IOException{
		ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("C:/tmp/highscores.save"));
		objOut.writeObject(scores);
		objOut.flush();
		objOut.close();
	}
	
	/**
	 * Loads the high scores from C:/tmp/highscores.save
	 * 
	 * @throws FileNotFoundException Indicates that the save file couldn't be found.
	 * @throws IOException Indicates that there was an error loading the file.
	 * @throws ClassNotFoundException Indicates that the class the serializable object is being cast to cannot be found.
	 */
	public void load() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("C:/tmp/highscores.save"));
		scores = (ArrayList<Score>)objIn.readObject();
		objIn.close();
	}
	
	/**
	 * Removes all of the scores from the save file.
	 */
	public void clear(){
		scores = new ArrayList<Score>();
		try {
			save();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
