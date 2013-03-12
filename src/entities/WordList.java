package entities;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

import maths.RandomLocation;
import controllers.Settings;

public class WordList {

	public static HashMap<Integer, String> dic;
	private Path path;
	
	public WordList() throws IOException {
		System.out.println("This is the word list constructor being called again.");
	}

	public void populateList() { 
		dic = new HashMap<>();
		
		if (Settings.wordSize == 1){
			path = Paths.get("G:/UniversityWork/Software Design and Development/Modelling/Eclipse files/Keyboard Missile Defense/res/dic/oneLetterWords.txt");
		}
		if(Settings.wordSize == 2){
			path = Paths.get("G:/UniversityWork/Software Design and Development/Modelling/Eclipse files/Keyboard Missile Defense/res/dic/twoLetterWords.txt");
		}
		else if(Settings.wordSize == 3){
			path = Paths.get("G:/UniversityWork/Software Design and Development/Modelling/Eclipse files/Keyboard Missile Defense/res/dic/threeLetterWords.txt");

		}
		else if(Settings.wordSize == 4){
			path = Paths.get("G:/UniversityWork/Software Design and Development/Modelling/Eclipse files/Keyboard Missile Defense/res/dic/fourLetterWords.txt");

		}
		else if(Settings.wordSize == 5){
			path = Paths.get("G:/UniversityWork/Software Design and Development/Modelling/Eclipse files/Keyboard Missile Defense/res/dic/fiveLetterWords.txt");

		}
		else if(Settings.wordSize == 6){
			path = Paths.get("G:/UniversityWork/Software Design and Development/Modelling/Eclipse files/Keyboard Missile Defense/res/dic/sixLetterWords.txt");
		}
		else if(Settings.wordSize == 7){
			path = Paths.get("G:/UniversityWork/Software Design and Development/Modelling/Eclipse files/Keyboard Missile Defense/res/dic/noLimitWords.txt");
		}
		
		System.out.println(Settings.wordSize);
		
		if (path != null){
			// This scans through the word file and for each line it adds a new entry to the HashMap and gives it a sequential number.
		    try (Scanner scanner =  new Scanner(path)){
		    	Integer i = 0; 
		    	while (scanner.hasNextLine()){
		    		dic.put(i, scanner.nextLine());
		    		i++;
		    	}      
		    } catch (IOException e) {
				e.printStackTrace();
			}	
	    }
	    
	   
	}


	// Prints the contents of the dictionary.  This was initially for testing purposes.
	public void dumpDic(){
		Integer i = 0;
		while (dic.containsKey(i)){
			System.out.println(dic.get(i));
			i++;
		}
	}
	
	// Pulls a random word from the current dictionary.
	public String getWord(){
		RandomLocation rand = new RandomLocation();
		Integer i = rand.getRand(dic.size()-1);
		
		String randWord = dic.get(i);
		return randWord;
	}

}
