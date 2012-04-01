package com.bogdan.wordsearch.model;

public class Position {

    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position incrementX() {
        return new Position(x + 1, y);
    }

    public Position decrementX() {
        return new Position(x - 1, y);
    }

    public Position incrementY() {
        return new Position(x, y + 1);
    }

    public Position decrementY() {
        return new Position(x, y - 1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public boolean equals(Object otherObject) {
        if (otherObject instanceof Position) {
            Position otherPosition = (Position) otherObject;
            if (otherPosition.getX() == this.getX() &&
                    otherPosition.getY() == this.getY()) {
                return true;
            }
        }
        return false;
    }
    
    public int hashCode() {
        int base = 17;
        base += 13 * this.getX() + 7;
        base += 17 * this.getY() + 11;
        return base;
    }
    
}
