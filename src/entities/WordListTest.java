package entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import controllers.Settings;

public class WordListTest {

	private WordList wl;
	
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Makes sure all of the words in the text files get put in the list.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testPopulateList() throws IOException {
		Settings.level = 1;
		wl = new WordList();
		
		wl.populateList();
		assertTrue(wl.size() > 0);
	}

	/**
	 * Makes sure the test method dumpDic() returns an ArrayList of Strings.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testDumpDic() throws IOException {
		Settings.level = 1;
		wl = new WordList();
		wl.populateList();
		
		assertNotNull(wl.dumpDic());
	}

	/**
	 * Makes a new WordList and makes sure that it returns a word of length 1.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetWord() throws Exception {
		Settings.level = 1;
		wl = new WordList();
		wl.populateList();
		
		assertEquals(1, wl.getWord().length());
	}

	/**
	 * Makes sure that all of the words in the text files get put in the list.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testSize() throws IOException {
		Settings.level = 1;
		wl = new WordList();
		wl.populateList();
		
		assertEquals(34, wl.size());
		
		Settings.level = 2;
		wl = new WordList();
		wl.populateList();
		
		assertEquals(120, wl.size());
		
		Settings.level = 4;
		wl = new WordList();
		wl.populateList();
		
		assertEquals(6196, wl.size());
	}

}
