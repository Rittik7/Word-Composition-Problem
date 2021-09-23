
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class FindLongestFirstAndSecondCompoundWord {

	public static void main(String[] args) {
		//input file
		String fileName = "D:\\Java_@Rittik\\DSA-I\\src\\Input_02.txt";
		//root of trie
		TrieNode root = new TrieNode((char) 0);

		String longestCompoundWord = ""; 

		String secondLongestCompoundWord = "";
		
		//Store word suffix pairs
		LinkedList<WordSuffix> wordSuffixes = new LinkedList<WordSuffix>();
		//Store all the prefixes of a word
		ArrayList<String> prefixes;
		//Store a word suffix pair
		WordSuffix wordsuffix;
		
		// Read strings from input file:
		try {
			BufferedReader bufferReader = new BufferedReader(new FileReader(fileName));
			String word;
			
			//Word into trie.
			while ((word = bufferReader.readLine()) != null) {
				prefixes = root.getAllPrefixes(word);
				if (!prefixes.isEmpty()) 
					for(String prefix : prefixes) {
						wordsuffix = new WordSuffix(word, word.substring(prefix.length()));		
						wordSuffixes.add(wordsuffix);
					}
				root.insertWord(word);
			}
			// Close the buffer reader
			bufferReader.close();
		} catch (Exception e) {
			System.out.println("Error while reading file line by line:" + e.getMessage()); 
		}
		
		/* Iterate the queue of suffix pairs, check if a word is compound word 
		 by repeatedly checking if the suffix in a pair is a word in the file*/
		while (!wordSuffixes.isEmpty()) {
			// Get a suffix pair from the queue
			wordsuffix = wordSuffixes.remove();
			String word = wordsuffix.word;
			String suffix = wordsuffix.suffix;
			
			// If the suffix is a word in the file, then the word is a compound word
			if (root.contain(suffix)) {
				/* if this compound word is longer than the previous longest compound word, 
				 update the previous longest and second compound word*/
				if (word.length() >= longestCompoundWord.length()) {
					secondLongestCompoundWord = longestCompoundWord;
					longestCompoundWord = word;
				}
			} else 
			// If the suffix is not a word in the file, check if the suffix is a compound word itself
			{
				// Check if the suffix has any prefixes
				prefixes = root.getAllPrefixes(suffix);
				
				// If the suffix has prefixes, create new suffix pair, and add it to the queue
				if (!prefixes.isEmpty()) {
					for(String prefix : prefixes) {
						wordsuffix = new WordSuffix(word, suffix.substring(prefix.length()));
						wordSuffixes.add(wordsuffix);
					}
				}
			}
		}
		
		//Result
		System.out.println("Longest Compound Word: " + longestCompoundWord);
		System.out.println("Second Longest Compound Word: " + secondLongestCompoundWord);
	}
}

//Store word-suffix pair
class WordSuffix {
	public WordSuffix(String w, String s) {
		this.word = w;
		this.suffix = s;
	}
	
	public String word;
	public String suffix;
}
