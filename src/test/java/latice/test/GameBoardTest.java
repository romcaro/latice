package latice.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import latice.model.GameBoard;
import latice.model.Position;
import latice.model.Square;
import latice.model.SquareType;

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

}
