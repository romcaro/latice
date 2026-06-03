package latice.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import latice.model.Position;

class PositionTest {

    // --- getX / getY ---

    @Test
    void shouldReturnCorrectX() {
        // Arrange
        Position position = new Position(5, 3);

        // Assert
        assertEquals(5, position.getX());
    }

    @Test
    void shouldReturnCorrectY() {
        // Arrange
        Position position = new Position(5, 3);

        // Assert
        assertEquals(3, position.getY());
    }

    // --- equals ---

    @Test
    void shouldBeEqualToItself() {
        // Arrange
        Position position = new Position(2, 7);

        // Assert
        assertEquals(position, position);
    }

    @Test
    void shouldBeEqualToPositionWithSameCoordinates() {
        // Arrange
        Position p1 = new Position(4, 6);
        Position p2 = new Position(4, 6);

        // Assert
        assertEquals(p1, p2);
    }

    @Test
    void shouldNotBeEqualWhenXDiffers() {
        // Arrange
        Position p1 = new Position(1, 3);
        Position p2 = new Position(2, 3);

        // Assert
        assertNotEquals(p1, p2);
    }

    @Test
    void shouldNotBeEqualWhenYDiffers() {
        // Arrange
        Position p1 = new Position(3, 1);
        Position p2 = new Position(3, 2);

        // Assert
        assertNotEquals(p1, p2);
    }

    @Test
    void shouldNotBeEqualToNull() {
        // Arrange
        Position position = new Position(0, 0);

        // Assert
        assertNotEquals(position, null);
    }
}
