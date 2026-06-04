package latice.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import latice.model.Color;
import latice.model.Shape;
import latice.model.Tile;

class TileTest {

    @Test
    void shouldReturnCorrectColor() {
        // Arrange
        Tile tile = new Tile(Color.RED, Shape.BIRD);

        // Act & Assert
        assertEquals(Color.RED, tile.getColor());
    }

    @Test
    void shouldReturnCorrectShape() {
        // Arrange
        Tile tile = new Tile(Color.YELLOW, Shape.FEATHER);

        // Act & Assert
        assertEquals(Shape.FEATHER, tile.getShape());
    }

    @Test
    void shouldRetainBothColorAndShape() {
        // Arrange
        Tile tile = new Tile(Color.TEAL, Shape.GECKO);

        // Act & Assert
        assertAll(
            () -> assertEquals(Color.TEAL, tile.getColor()),
            () -> assertEquals(Shape.GECKO, tile.getShape())
        );
    }

    @Test
    void shouldCreateTilesWithAllColorCombinations() {
        // Arrange & Act
        Tile yellowFeather = new Tile(Color.YELLOW, Shape.FEATHER);
        Tile navyTurtle   = new Tile(Color.NAVY,   Shape.TURTLE);
        Tile magentaFlower = new Tile(Color.MAGENTA, Shape.FLOWER);
        Tile greenBird    = new Tile(Color.GREEN,  Shape.BIRD);
        Tile redDolphin   = new Tile(Color.RED,    Shape.DOLPHIN);
        Tile tealGecko    = new Tile(Color.TEAL,   Shape.GECKO);

        // Assert — 
        assertAll(
            () -> assertEquals(Color.YELLOW,  yellowFeather.getColor()),
            () -> assertEquals(Shape.FEATHER, yellowFeather.getShape()),
            () -> assertEquals(Color.NAVY,    navyTurtle.getColor()),
            () -> assertEquals(Shape.TURTLE,  navyTurtle.getShape()),
            () -> assertEquals(Color.MAGENTA, magentaFlower.getColor()),
            () -> assertEquals(Shape.FLOWER,  magentaFlower.getShape()),
            () -> assertEquals(Color.GREEN,   greenBird.getColor()),
            () -> assertEquals(Shape.BIRD,    greenBird.getShape()),
            () -> assertEquals(Color.RED,     redDolphin.getColor()),
            () -> assertEquals(Shape.DOLPHIN, redDolphin.getShape()),
            () -> assertEquals(Color.TEAL,    tealGecko.getColor()),
            () -> assertEquals(Shape.GECKO,   tealGecko.getShape())
        );
    }
}
