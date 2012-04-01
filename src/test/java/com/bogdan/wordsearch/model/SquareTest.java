package com.bogdan.wordsearch.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SquareTest {

    @Test
    public void testEquals() {
        Square square1 = new Square(new Position(0, 1), 'A');
        Square square2 = new Square(new Position(0, 1), 'A');
        assertEquals("Squares with same position and same value should be equal!",
                square1, square2);
    }

    @Test
    public void testNotEqualsSamePosition() {
        Square square1 = new Square(new Position(0, 1), 'A');
        Square square2 = new Square(new Position(0, 1), 'B');
        assertFalse("Squares with same position and different value should not be equal!",
                square1.equals(square2));
    }

    @Test
    public void testNotEqualsSameValue() {
        Square square1 = new Square(new Position(0, 1), 'A');
        Square square2 = new Square(new Position(1, 1), 'A');
        assertFalse("Squares with different position and same value should not be equal!",
                square1.equals(square2));
    }

}
