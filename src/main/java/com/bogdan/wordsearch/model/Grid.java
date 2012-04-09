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

    public List<Square> getNeighbors(Square square) {
        List<Square> neighbors = new ArrayList<Square>();

        Position northNeighbor = square.getPosition().decrementX();
        if (isValidPosition(northNeighbor)) {
            neighbors.add(matrix[northNeighbor.getX()][northNeighbor.getY()]);
        }

        Position northWestNeighbor = square.getPosition().decrementY().decrementX();
        if (isValidPosition(northWestNeighbor)) {
            neighbors.add(matrix[northWestNeighbor.getX()][northWestNeighbor.getY()]);
        }

        Position westNeighbor = square.getPosition().decrementY();
        if (isValidPosition(westNeighbor)) {
            neighbors.add(matrix[westNeighbor.getX()][westNeighbor.getY()]);
        }

        Position southWestNeighbor = square.getPosition().decrementY().incrementX();
        if (isValidPosition(southWestNeighbor)) {
            neighbors.add(matrix[southWestNeighbor.getX()][southWestNeighbor.getY()]);
        }

        Position southNeighbor = square.getPosition().incrementX();
        if (isValidPosition(southNeighbor)) {
            neighbors.add(matrix[southNeighbor.getX()][southNeighbor.getY()]);
        }

        Position southEastNeighbor = square.getPosition().incrementY().incrementX();
        if (isValidPosition(southEastNeighbor)) {
            neighbors.add(matrix[southEastNeighbor.getX()][southEastNeighbor.getY()]);
        }

        Position eastNeighbor = square.getPosition().incrementY();
        if (isValidPosition(eastNeighbor)) {
            neighbors.add(matrix[eastNeighbor.getX()][eastNeighbor.getY()]);
        }

        Position northEastNeighbor = square.getPosition().decrementX().incrementY();
        if (isValidPosition(northEastNeighbor)) {
            neighbors.add(matrix[northEastNeighbor.getX()][northEastNeighbor.getY()]);
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
