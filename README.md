**Problem Statement**

**Write a program that:**

1. _Reads provided files (Input_01.txt and Input_02.txt) containing alphabetically sorted words list (one
word per line, no spaces, all lower case)._

2. _Identifies & display below given data in logs/console/output file._

o _longest compounded word._

o _second longest compounded word._

o _time taken to process the input file._

**Note: A compounded word is one that can be constructed by combining (concatenating) shorter words
also found in the same file** 


**Description of the algorithm:**

The algorithm browses the file line by line and forms a trie by using the words in the file. Before injecting each word into the trie, it will check if the word has any prefixes. If yes, it will produce word-suffix pairs and append them into a queue.

Then, the algorithm will pop a pair from the queue to see if the suffix in the word-suffix pair is a word in the file. If yes, the word is a compound word. If it is longer than the previous compound word, it will update the variables longestCompoundWord and secondLongestCompoundWord.

If the suffix in the word-suffix pair is not a word in the file, it will check if the suffix has any prefixes. If yes, it will create new word-suffix pairs and add them to the queue. Otherwise, it will just discard the pair and pop a new pair from the queue, will repeat the process until the queue is empty.

The algorithm runs in linear, **O(LN)**, where **N** is the number of words in the input file, and **L** is the maximum number of words in a compound word.
