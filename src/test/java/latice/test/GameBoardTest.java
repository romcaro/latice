package latice.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import latice.model.Color;
import latice.model.GameBoard;
import latice.model.Position;
import latice.model.Shape;
import latice.model.Square;
import latice.model.SquareType;
import latice.model.Tile;

class GameBoardTest {

    private static final int WIDTH  = 9;
    private static final int HEIGHT = 9;

    private GameBoard board;

    @BeforeEach
    void createBoard() {
        // GameBoard(int width, int height)
        board = new GameBoard(WIDTH, HEIGHT);
    }

    // --- getSquare ---

    @Test
    void shouldReturnNonNullSquareForValidCoordinates() {
        // Act
        Square square = board.getSquare(0, 0);

        // Assert
        assertNotNull(square);
    }

    @Test
    void shouldReturnSquareAtCorrectPosition() {
        // Act
        Square square = board.getSquare(3, 5);

        // Assert
        assertNotNull(square.getPosition());
        assertEquals(3, square.getPosition().getX());
        assertEquals(5, square.getPosition().getY());
    }

    @Test
    void shouldReturnDifferentSquaresForDifferentCoordinates() {
        // Act
        Square s1 = board.getSquare(0, 0);
        Square s2 = board.getSquare(1, 1);

        // Assert
        assertNotSame(s1, s2);
    }

    // --- setSquare ---

    @Test
    void shouldPlaceSquareAtGivenCoordinates() {
        // Arrange
        Square newSquare = new Square(new Position(2, 2));
        newSquare.setType(SquareType.SUN);

        // Act
        board.setSquare(2, 2, newSquare);

        // Assert
        assertEquals(SquareType.SUN, board.getSquare(2, 2).getType());
    }

    // --- default square state ---

    @Test
    void shouldBeNormalTypeByDefault() {
        // Assert — every square is NORMAL before initSpecialSquares is called
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                assertEquals(SquareType.NORMAL, board.getSquare(x, y).getType());
            }
        }
    }

    @Test
    void shouldNotBeOccupiedOnEmptyBoard() {
        // Assert
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                assertFalse(board.getSquare(x, y).isOccupied());
            }
        }
    }

    // --- initSpecialSquares ---

    @Test
    void shouldHaveAtLeastOneSunSquareAfterInit() {
        // Arrange
        board.initSpecialSquares();

        // Act
        boolean foundSun = false;
        for (int x = 0; x < WIDTH && !foundSun; x++) {
            for (int y = 0; y < HEIGHT && !foundSun; y++) {
                if (board.getSquare(x, y).getType() == SquareType.SUN) {
                    foundSun = true;
                }
            }
        }

        // Assert
        assertTrue(foundSun);
    }

    @Test
    void shouldHaveAtLeastOneMoonSquareAfterInit() {
        // Arrange
        board.initSpecialSquares();

        // Act
        boolean foundMoon = false;
        for (int x = 0; x < WIDTH && !foundMoon; x++) {
            for (int y = 0; y < HEIGHT && !foundMoon; y++) {
                if (board.getSquare(x, y).getType() == SquareType.MOON) {
                    foundMoon = true;
                }
            }
        }

        // Assert
        assertTrue(foundMoon);
    }

    @Test
    void shouldHaveMoonAtCenterAfterInit() {
        // Arrange
        board.initSpecialSquares();

        // Assert
        assertEquals(SquareType.MOON, board.getSquare(4, 4).getType());
    }
    
 // --- placing tiles ---

    @Test
    void shouldMarkSquareAsOccupiedAfterTilePlaced() {
        // Arrange
        Tile tile = new Tile(Color.RED, Shape.BIRD);

        // Act
        board.getSquare(4, 4).setTile(tile);

        // Assert
        assertTrue(board.getSquare(4, 4).isOccupied());
    }

    @Test
    void shouldReturnCorrectTileAfterPlacement() {
        // Arrange
        Tile tile = new Tile(Color.NAVY, Shape.TURTLE);

        // Act
        board.getSquare(1, 1).setTile(tile);

        // Assert
        assertEquals(tile, board.getSquare(1, 1).getTile());
    }
}
