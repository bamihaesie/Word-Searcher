package com.bogdan.wordsearch;

import com.bogdan.wordsearch.model.Color;
import com.bogdan.wordsearch.model.Grid;
import com.bogdan.wordsearch.model.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class WordSearcher {
    
    private Grid grid;
    private Stack<Character> stack;

    public WordSearcher(Grid grid) {
        this.grid = grid;
    }

    protected List<Square> findWord(String word) {
        stack = addLettersToStackInReverseOrder(word);
        for (Square startingSquare : getSquaresByValue(word.charAt(0))) {
            grid.resetParentsAndColors();
            Square solution = findCharsStartingAt(startingSquare);
            if (solution != null) {
                return buildPathFromSolution(solution);
            }
        }
        return new ArrayList<Square>();
    }

    private Square findCharsStartingAt(Square square) {
        square.setColor(Color.GREY);
        char topOfStack = stack.pop();
        if (square.getValue() == topOfStack) {
            if (stack.isEmpty()) {
                return square;
            }
            for (Square neighbor : grid.getNeighbors(square)) {      
                if (Color.WHITE.equals(neighbor.getColor())
                        || Color.BLACK.equals(neighbor.getColor())) {
                    neighbor.setParent(square);
                    Square solution = findCharsStartingAt(neighbor);
                    if (solution != null) {
                        return solution;
                    }
                }
            }
        }
        stack.push(topOfStack);
        square.setColor(Color.BLACK);
        return null;
    }

    private List<Square> buildPathFromSolution(Square solution) {
        List<Square> path = new ArrayList<Square>();
        Square currentSquare = solution;
        if (currentSquare != null) {
            while (currentSquare != null) {
                path.add(0, currentSquare);
                currentSquare = currentSquare.getParent();
            }
        }
        return path;
    }

    protected Stack<Character> addLettersToStackInReverseOrder(String word) {
        Stack letterStack = new Stack<Character>();
        if (word != null && word.length() > 0) {
            char[] letters = word.toCharArray();
            for (int i = letters.length - 1; i >= 0; i--) {
                letterStack.push(letters[i]);
            }
        }
        return letterStack;
    }
    
    private List<Square> getSquaresByValue(char value) {
        return grid.getSquaresByValue(value);
    }
}
