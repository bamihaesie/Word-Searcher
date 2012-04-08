package com.bogdan.wordsearch.model;

public class Square {

    private Position position;
    private char value;
    private Square parent;
    private Color color;
    
    public Square(Position position, char value) {
        this.position = position;
        this.value = value;
        this.color = Color.WHITE;
    }

    public Position getPosition() {
        return position;
    }

    public char getValue() {
        return value;
    }

    public void setParent(Square parent) {
        this.parent = parent;
    }

    public Square getParent() {
        return parent;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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

