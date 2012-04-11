package com.bogdan.wordsearch.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Grid {

    private int rows, columns;
    private Square[][] matrix;

    public Grid(File inputFile) {
        buildFromFile(inputFile);
    }
    
    public Square getSquareAt(int x, int y) {
        return matrix[x][y];
    }

    public Square getSquareAt(Position position) {
        return getSquareAt(position.getX(), position.getY());
    }
    
    private Position getNeighbor(Position position, Heading heading) {
        switch (heading) {
            case NORTH:         return position.decrementX();
            case NORTH_WEST:    return position.decrementX().decrementY();
            case WEST:          return position.decrementY();
            case SOUTH_WEST:    return position.incrementX().decrementY();
            case SOUTH:         return position.incrementX();
            case SOUTH_EAST:    return position.incrementX().incrementY();
            case EAST:          return position.incrementY();
            case NORTH_EAST:    return position.decrementX().incrementY();
        }
        return null;
    }

    public List<Square> getNeighbors(Square square) {
        List<Square> neighbors = new ArrayList<Square>();
        for (Heading heading : Heading.values()) {
            Position neighbor = getNeighbor(square.getPosition(), heading);
            if (isValidPosition(neighbor)) {
                neighbors.add(getSquareAt(neighbor));
            }
        }
        return neighbors;
    }

    private boolean isValidPosition(Position position) {
        if (    position.getX() < 0 ||
                position.getX() >= rows ||
                position.getY() < 0 ||
                position.getY() >= columns) {
            return false;
        }
        return true;
    }
    
    public List<Square> getSquaresByValue(char value) {
        List<Square> matching = new ArrayList<Square>();
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                if (matrix[i][j].getValue() == value) {
                    matching.add(matrix[i][j]);
                }
            }            
        }
        return matching;
    }
    
    private void buildFromFile(File inputFile) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            String line=bufferedReader.readLine();
            setDimensions(line);
            int i=0;
            while ((line=bufferedReader.readLine()) != null) {
                matrix[i] = getArrayFromLine(line, i);
                i++;
            }            
        } catch (FileNotFoundException fnfe) {
            System.out.println("Input file not found!");
        } catch (IOException ioe) {
            System.out.println("Error reading input file!");
        }        
    }
    
    private void setDimensions(String line) {
        String[] tokens = line.split(" ");
        rows = Integer.parseInt(tokens[0]);
        columns = Integer.parseInt(tokens[1]);
        matrix = new Square[rows][columns];
    }
    
    private Square[] getArrayFromLine(String line, int i) {
        Square[] squares = new Square[line.length()];
        int j=0;
        for (char letter: line.toCharArray()) {
            squares[j] = new Square(new Position(i, j), letter);
            j++;
        }
        return squares;
    }

    public void resetParentsAndColors() {
        for (int i=0 ; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                matrix[i][j].setColor(Color.WHITE);
                matrix[i][j].setParent(null);
            }
        }
    }
    
    public String toString() {
        for (int i=0 ; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        return "";
    }

}
