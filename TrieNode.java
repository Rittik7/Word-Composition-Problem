import java.util.ArrayList;
import java.util.HashMap;

//Trie Node, which stores a character and children in a HashMap
public class TrieNode {
	private char letter;
	
	// The HashMap is used to find a child letter quickly
	private HashMap<Character, TrieNode> children;
	// Flag used to tell if a node in the trie is the terminal letter of a word
	private boolean isWord;
	
	public TrieNode(char l) {
		letter = l;
		isWord = false;
		children = new HashMap<Character, TrieNode>();
	}
	
	// Insert a word into the trie
	public void insertWord(String word) {
		TrieNode current = this;
		int length = word.length();
		
		// Iterate all the letters in the word
		for (int i = 0; i < length; i++) {
			HashMap<Character, TrieNode> subNodes = current.getChildren();
			char l = word.charAt(i);

			// If current letter is already in the trie, continue. Otherwise, create a new node
			if (subNodes.containsKey(l)) {
				current = subNodes.get(l);
			} else {
				TrieNode newChild = new TrieNode(l);
				subNodes.put(l, newChild);
				current = newChild;
			}
		}
		// Mark the final letter to tell this is the end of a word
		current.setIsWord(true);
	}
	
	public boolean contain(String word) {
		TrieNode current = this;
		int length = word.length();
		
		// Iterate all the letters in the word
		for (int i = 0; i < length; i++) {
			HashMap<Character, TrieNode> subNodes = current.getChildren();
			char l = word.charAt(i);
			// If current letter is already in the trie, continue. Otherwise, return false
			if (subNodes.containsKey(l)) {
				current = subNodes.get(l);
			} else return false;
		}
		// Check if the flag of the final letter is true. If yes, then the word is in the trie
		if (current.isWord()) return true;
		return false;
	}
	
	public ArrayList<String> getAllPrefixes(String word) {
		// Array used to store prefixes
		ArrayList<String> prefixes = new ArrayList<String>();
		// StringBuffer used to store a prefix
		StringBuffer prefix = new StringBuffer();
		
		TrieNode current = this;
		int length = word.length();
		
		// Iterate all the letters in the word
		for (int i = 0; i < length; i++) {
			HashMap<Character, TrieNode> subNodes = current.getChildren();
			char l = word.charAt(i);
			// Check if the current letter is in the trie
			if (subNodes.containsKey(l)) {
				prefix.append(l);
				TrieNode subNode = subNodes.get(l);
				// Check if the current letter is the end of a word
				if (subNode.isWord()) {
					String temp = prefix.toString();
					prefixes.add(temp);
				}
				current = subNode;
				
			} 
			// If the current letter is not in the trie, return prefixes that has been found
			else return prefixes;
		}
		
		return prefixes;
	} 
	
	// Getters and setters
	public HashMap<Character, TrieNode> getChildren() {
		return children;
	}

	public char getValue() {
		return letter;
	}

	public void setIsWord(boolean r) {
		isWord = r;
	}

	public boolean isWord() {
		return isWord;
	}
}