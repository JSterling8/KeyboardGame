package entities;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

import controllers.RandomLocation;
import controllers.Settings;

public class WordList implements Serializable {

	public static HashMap<Integer, String> dic;			// A HashMap of all of the words in the generated dictionary.
	private InputStream input;
	
	public WordList() throws IOException {}

	/**
	 * Generates a new dictionary HashMap based on the current level.
	 */
	public void populateList() { 
		dic = new HashMap<>();
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if(Settings.level == 1){input = classLoader.getResourceAsStream("oneLetterWords.txt");}
		else if(Settings.level == 2){input = classLoader.getResourceAsStream("twoLetterWords.txt");}
		else if(Settings.level == 3){input = classLoader.getResourceAsStream("threeLetterWords.txt");}
		else if(Settings.level == 4){input = classLoader.getResourceAsStream("fourLetterWords.txt");}
		else if(Settings.level == 5){input = classLoader.getResourceAsStream("fiveLetterWords.txt");}
		else if(Settings.level == 6){input = classLoader.getResourceAsStream("sixLetterWords.txt");}
		else if(Settings.level == 7){input = classLoader.getResourceAsStream("twonoLimitWords.txt");}
		
			// This scans through the word file and for each line it adds a new entry to the HashMap and gives it a sequential number.
		    try (Scanner scanner =  new Scanner(input)){
		    	Integer i = 0; 
		    	while (scanner.hasNextLine()){
		    		dic.put(i, scanner.nextLine());
		    		i++;
		    	}      
		    } catch (Exception e) {
				e.printStackTrace();
			}	
	}


	/**
	 *  Prints the contents of the dictionary.  This was for testing purposes.
	 */
	public void dumpDic(){
		Integer i = 0;
		while (dic.containsKey(i)){
			System.out.println(dic.get(i));
			i++;
		}
	}
	
	/**
	 * Pulls a random word from the current dictionary.
	 * 
	 * @return A random word from the dictionary.
	 * @throws Exception If the random location requested is less than 0.
	 */
	public String getWord() throws Exception{
		RandomLocation rand = new RandomLocation();
		Integer i = rand.getRand(dic.size()-1);

		
		String randWord = dic.get(i);
		return randWord;
	}
	
	/**
	 * The dictionaries size.
	 * 
	 * @return The number of words in the dictionary.
	 */
	public int size(){
		return dic.size();
	}

}
