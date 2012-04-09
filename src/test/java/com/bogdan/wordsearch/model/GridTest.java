package com.bogdan.wordsearch.model;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class GridTest {

    Grid grid;
    
    @Before
    public void setUp() {
        URL url = this.getClass().getResource("/grid1.txt");
        assertNotNull("Can't open input file!", url);
        File gridFile = new File(url.getFile());
        grid = new Grid(gridFile);
    }
    
    @Test
    public void testGetSquareAtZero() {
        Square square = grid.getSquareAt(0, 0);
        assertEquals("Wrong value for square (0,0)",
                'H', square.getValue());
    }

    @Test
    public void testGetSquareAtOne() {
        Square square = grid.getSquareAt(1, 1);
        assertEquals("Wrong value for square (1,1)",
                'O', square.getValue());
    }

    @Test
    public void testGetNeighbors() {
        Square square = grid.getSquareAt(0, 0);
        List<Square> neighbors = grid.getNeighbors(square);
        assertEquals(3, neighbors.size());
    }
    
    @Test
    public void testGetSquaresByValue() {
        assertEquals("Wrong number of matches returned!",
                6, grid.getSquaresByValue('C').size());
    }
    
    @Test
    public void testResetParentsAndColors() {
        Square square1 = grid.getSquareAt(0, 0);
        Square square2 = grid.getSquareAt(1, 1);
        square1.setParent(square2);
        square1.setColor(Color.BLACK);
        square2.setColor(Color.GREY);
        grid.resetParentsAndColors();
        assertNull("Parent was not reset!",
                square1.getParent());
        assertEquals("Color was not reset!",
                Color.WHITE, square1.getColor());
        assertEquals("Color was not reset!",
                Color.WHITE, square2.getColor());
    }

}
