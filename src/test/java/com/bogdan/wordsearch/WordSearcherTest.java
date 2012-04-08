package com.bogdan.wordsearch;

import com.bogdan.wordsearch.model.Grid;
import com.bogdan.wordsearch.model.Square;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class WordSearcherTest {

    WordSearcher wordSearcher;
    List<String> wordsToFind;

    @Before
    public void setUp() {
        URL url = this.getClass().getResource("/grid1.txt");
        assertNotNull("Can't open input file!", url);
        File gridFile = new File(url.getFile());
        wordSearcher = new WordSearcher(new Grid(gridFile));
        wordsToFind = new ArrayList<String>();
    }

    @Test
    public void testPopulateStack() {
        Stack<Character> stack = wordSearcher.addLettersToStackInReverseOrder("abcd");
        assertNotNull(stack);
        assertEquals(new Character('a'), stack.pop());
        assertEquals(new Character('b'), stack.pop());
        assertEquals(new Character('c'), stack.pop());
        assertEquals(new Character('d'), stack.pop());
        assertTrue(stack.isEmpty());

    }

    @Test
    public void testFindWord() throws Exception {
        List<Square> solution = wordSearcher.findWord("COW");
        assertSquaresEqual("COW", solution);
    }
    
    private void assertSquaresEqual(String word, List<Square> squares) {
        assertNotNull("Solution should not be null!", squares);
        assertEquals(word.length(), squares.size());
        for (int i=0; i < word.toCharArray().length; i++) {
            assertEquals(word.toCharArray()[i], squares.get(i).getValue());
        }
    }
}
