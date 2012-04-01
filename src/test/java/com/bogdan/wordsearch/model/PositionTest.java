package com.bogdan.wordsearch.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PositionTest {

    @Test
    public void testEquals() {
        Position position1 = new Position(1, 0);
        Position position2 = new Position(1, 0);
        assertEquals("Same coordinates should mean equal positions!",
                position1, position2);
    }

    @Test
    public void testNotEquals() {
        Position position1 = new Position(1, 0);
        Position position2 = new Position(2, 4);
        assertFalse("Different coordinates should mean different positions!",
                position1.equals(position2));
    }

}
