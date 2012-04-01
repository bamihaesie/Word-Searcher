package com.bogdan.wordsearch;

import com.bogdan.wordsearch.model.Grid;
import com.bogdan.wordsearch.model.Square;

import java.util.ArrayList;
import java.util.List;

public class WordSearcher {
    
    Grid grid;

    public WordSearcher(Grid grid) {
        this.grid = grid;        
    }
    
    public List<List<Square>> findWords(List<String> words) {
        List<List<Square>> solution = new ArrayList<List<Square>>();
        for (String word : words) {
            solution.add(findWord(word));
        }
        return solution;
    }
    
    private List<Square> findWord(String word) {
        List<Square> startingSquares = getSquaresByValue(word.charAt(0));
        
        return null;
    }
    
    private List<Square> findCharsStartingAt(char[] chars, Square square) {
        return null;
    }
    
    private List<Square> getSquaresByValue(char value) {
        return grid.getSquaresByValue(value);
    }
}
