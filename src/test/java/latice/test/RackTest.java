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
}