package latice.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import latice.model.Color;
import latice.model.Position;
import latice.model.Shape;
import latice.model.Square;
import latice.model.SquareType;
import latice.model.Tile;

class SquareTest {

    private Square square;
    private Position position;

    @BeforeEach
    void createSquare() {
        // Arrange
        position = new Position(3, 4);
        square = new Square(position);
    }

    // --- isOccupied ---

    @Test
    void shouldNotBeOccupiedWhenCreated() {
        // Assert
        assertFalse(square.isOccupied());
    }

    @Test
    void shouldBeOccupiedAfterTilePlaced() {
        // Act
        square.setTile(new Tile(Color.GREEN, Shape.BIRD));

        // Assert
        assertTrue(square.isOccupied());
    }

    @Test
    void shouldNotBeOccupiedAfterTileRemoved() {
        // Arrange
        square.setTile(new Tile(Color.RED, Shape.DOLPHIN));

        // Act
        square.setTile(null);

        // Assert
        assertFalse(square.isOccupied());
    }

    // --- setTile / getTile ---

    @Test
    void shouldReturnNullTileWhenEmpty() {
        // Assert
        assertNull(square.getTile());
    }

    @Test
    void shouldReturnCorrectTileAfterSet() {
        // Arrange
        Tile tile = new Tile(Color.TEAL, Shape.GECKO);

        // Act
        square.setTile(tile);

        // Assert
        assertEquals(tile, square.getTile());
    }

    // --- getPosition ---

    @Test
    void shouldReturnCorrectPosition() {
        // Assert
        assertEquals(position, square.getPosition());
    }

    // --- getType / setType ---

    @Test
    void shouldBeNormalTypeByDefault() {
        // Assert
        assertEquals(SquareType.NORMAL, square.getType());
    }

    @Test
    void shouldReturnSunTypeAfterSet() {
        // Act
        square.setType(SquareType.SUN);

        // Assert
        assertEquals(SquareType.SUN, square.getType());
    }

    @Test
    void shouldReturnMoonTypeAfterSet() {
        // Act
        square.setType(SquareType.MOON);

        // Assert
        assertEquals(SquareType.MOON, square.getType());
    }
}
