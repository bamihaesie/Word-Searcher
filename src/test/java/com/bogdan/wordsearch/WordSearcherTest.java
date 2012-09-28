package com.bogdan.wordsearch;

import com.bogdan.wordsearch.model.Grid;
import com.bogdan.wordsearch.model.Square;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.*;

public class WordSearcherTest {

    @Test
    public void testFindWord1() throws Exception {
        WordSearcher wordSearcher = buildWordSearcherFromFileName("grid1.txt");
        List<String> wordsToFind = readWordsToFindFromFile("grid1_solution.txt");
        for (String wordToFind : wordsToFind) {
            assertSquaresEqual(wordToFind, wordSearcher.findWord(wordToFind));
        }
    }

    @Test
    public void testFindWord2() throws Exception {
        WordSearcher wordSearcher = buildWordSearcherFromFileName("grid2.txt");
        List<String> wordsToFind = readWordsToFindFromFile("grid2_solution.txt");
        for (String wordToFind : wordsToFind) {
            assertSquaresEqual(wordToFind, wordSearcher.findWord(wordToFind));
        }
    }

    @Test
    public void testPopulateStack() {
        WordSearcher wordSearcher = buildWordSearcherFromFileName("grid1.txt");
        Stack<Character> stack = wordSearcher.addLettersToStackInReverseOrder("abcd");
        assertNotNull(stack);
        assertEquals(new Character('a'), stack.pop());
        assertEquals(new Character('b'), stack.pop());
        assertEquals(new Character('c'), stack.pop());
        assertEquals(new Character('d'), stack.pop());
        assertTrue(stack.isEmpty());
    }

    private void assertSquaresEqual(String word, List<Square> squares) {
        assertNotNull("Solution null when searching for: " + word, squares);
        assertEquals("Invalid solution size when searching for: " + word,
                word.length(), squares.size());
        for (int i = 0; i < word.toCharArray().length; i++) {
            assertEquals("Invalid square returned when searching for: " + word,
                    word.toCharArray()[i], squares.get(i).getValue());
        }
    }

    private WordSearcher buildWordSearcherFromFileName(String fileName) {
        File gridFile = createFileFromName(fileName);
        return new WordSearcher(new Grid(gridFile));
    }

    private List<String> readWordsToFindFromFile(String fileName) {
        File wordsFile = createFileFromName(fileName);
        List<String> words = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(wordsFile));
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
        } catch (Exception e) {
            fail("Error reading solution from file!");
        }
        return words;
    }

    private File createFileFromName(String fileName) {
        URL url = this.getClass().getResource("/" + fileName);
        assertNotNull("Can't open input file!", url);
        return new File(url.getFile());
    }
}
