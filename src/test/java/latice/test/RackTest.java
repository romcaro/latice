package latice.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import latice.model.*;

class RackTest {

    private Rack rack;

    @BeforeEach
    void createRack() {
        // Arrange
        rack = new Rack();
    }

    @Test
    void shouldAddTileWhenRackIsNotFull() {
        //Arrange
        Tile tile = new Tile(Color.GREEN, Shape.BIRD);

        // Act
        boolean result = rack.addTile(tile);

        // Assert
        assertTrue(result);
    }
    
    @Test
    void shouldNotAddTileWhenRackIsFull() {
        // Arrange
        rack.addTile(new Tile(Color.GREEN, Shape.BIRD));
        rack.addTile(new Tile(Color.RED, Shape.DOLPHIN));
        rack.addTile(new Tile(Color.NAVY, Shape.TURTLE));
        rack.addTile(new Tile(Color.TEAL, Shape.GECKO));
        rack.addTile(new Tile(Color.MAGENTA, Shape.FLOWER));
        Tile extraTile = new Tile(Color.YELLOW, Shape.FEATHER);

        // Act
        boolean result = rack.addTile(extraTile);

        // Assert
        assertFalse(result);
    }
    
    @Test
    void shouldNotExceedFiveTilesWhenFull() {
        // Arrange
        rack.addTile(new Tile(Color.GREEN, Shape.BIRD));
        rack.addTile(new Tile(Color.RED, Shape.DOLPHIN));
        rack.addTile(new Tile(Color.NAVY, Shape.TURTLE));
        rack.addTile(new Tile(Color.TEAL, Shape.GECKO));
        rack.addTile(new Tile(Color.MAGENTA, Shape.FLOWER));
        Tile extraTile = new Tile(Color.YELLOW, Shape.FEATHER);

        // Act
        rack.addTile(extraTile);

        // Assert
        assertEquals(5, rack.size());
    }
    
    @Test
    void shouldRemoveTileWhenPresent() {
        // Arrange
        Tile tile = new Tile(Color.GREEN, Shape.BIRD);
        rack.addTile(tile);

        //Act
        boolean result = rack.removeTile(tile);

        //Assert
        assertTrue(result);
    }
    
    @Test
    void shouldNotRemoveTileWhenNoTile() {
        //Arrange
        Tile tile = new Tile(Color.GREEN, Shape.BIRD);

        // Act
        boolean result = rack.removeTile(tile);

        // Assert
        assertFalse(result);
    }

}