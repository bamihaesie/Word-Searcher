package com.bogdan.wordsearch;

import com.bogdan.wordsearch.model.Grid;
import com.bogdan.wordsearch.model.Square;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    public void testFindWords() throws Exception {
        wordsToFind.add("COW");
        List<List<Square>> solution = wordSearcher.findWords(wordsToFind);
        assertNotNull("Solution should not be null!", solution);
        assertEquals(1, solution.size());
        assertEquals(1, solution.get(0).size());
        assertSquaresEqual("COW", solution.get(0));
    }
    
    private void assertSquaresEqual(String word, List<Square> squares) {
        assertEquals(word.toCharArray().length, word.toCharArray().length);
        for (int i=0; i < word.toCharArray().length; i++) {
            assertEquals(word.toCharArray()[i], squares.get(0).getValue());
        }
    }
}
