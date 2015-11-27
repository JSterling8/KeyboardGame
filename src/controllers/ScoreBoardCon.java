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
import java.util.Collections;
import java.util.Comparator;

import entities.Score;

/**
 * This class controls the high score board.
 * It can save and load a high scores file for permanent storage.
 * 
 * @author Jonathan Sterling
 *
 */
@SuppressWarnings("serial")
public class ScoreBoardCon implements Serializable, Comparator<Score> {
	
	private static ArrayList<Score> scores;						// An ArrayList of all of the high scores.
	private String fs;											// The System's file separator.
	private Path path;											// The path to the save file.
	
	/**
	 * Initialises the scores ArrayList and tries to load one if one already exists.
	 */
	public ScoreBoardCon() {
		scores = new ArrayList<Score>();

		// Sets the path to the users current working directory/save/highscores.save
		fs = System.getProperty("file.separator");
		path = Paths.get(System.getProperty("user.dir") + fs + "save" + fs + "highscores.save");
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
		sortList();
		
		String s = "";
		int pos = 0;
		for (int i = 0; i < scores.size(); i++){
			pos = i+1;
			s = s + pos + ". " + scores.get(i);
		}
		
		return s;
	}
	
	/**
	 * Saves the high scores table to the current working dir/save/highscores.save
	 * 
	 * @throws FileNotFoundException 
	 * @throws IOException Indicates an error whilst saving.
	 */
	public void save() throws IOException{
		ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(path.toString()));
		objOut.writeObject(scores);
		objOut.flush();
		objOut.close();
	}
	
	/**
	 * Loads the high scores from the current working dir/save/highscores.save
	 * 
	 * @throws FileNotFoundException Indicates that the save file couldn't be found.
	 * @throws IOException Indicates that there was an error loading the file.
	 * @throws ClassNotFoundException Indicates that the class the serializable object is being cast to cannot be found.
	 */
	@SuppressWarnings("unchecked")
	public void load() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(path.toString()));
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
	
	public void sortList(){
		Collections.sort(scores, this);
	}

	/**
	 * This is a custom Comparator.
	 * Used to sort scores.
	 * 
	 */
	@Override
	public int compare(Score inp1, Score inp2) {
		int score1 = inp1.getScore();
        int score2 = inp2.getScore();
 
        if (score1 > score2){
            return -1;
        }else if (score1 < score2){
            return +1;
        }else{
            return 0;
        }
	}
}
