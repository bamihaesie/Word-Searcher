package com.bogdan.wordsearch.model;

public class Square {

    private Position position;
    private char value;
    
    public Square(Position position, char value) {
        this.position = position;
        this.value = value;
    }

    public Position getPosition() {
        return position;
    }

    public char getValue() {
        return value;
    }
    
    public boolean equals(Object otherObject) {
        if (otherObject instanceof Square) {
            Square otherSquare = (Square) otherObject;
            if (otherSquare.getPosition().equals(this.getPosition()) &&
                    otherSquare.getValue() == this.getValue()) {
                return true;
            }
        }
        return false;        
    }
    
    public String toString() {
        return "(" + position.getX() + ", " + position.getY() + ", " + value + ")";
    }

}
