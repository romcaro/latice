package latice.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import latice.model.*;

class RefereeTest {

    private GameBoard board;
    private Referee referee;
    private Game game;

    @BeforeEach
    void setup() {
        board = new GameBoard(9, 9);
        board.initSpecialSquares();
        referee = new Referee(board);
        game = new Game("Alice", "Nolann");
    }

    // --- isValidMove --- (Empty Board)
    
    @Test
    void shouldAllowFirstTileOnMoonSquare() {
        // Arrange 
        Tile tile = new Tile(Color.RED, Shape.BIRD);

        // Act
        boolean result = referee.isValidMove(game, board, tile, 4, 4);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldRejectFirstTileNotOnMoonSquare() {
        // Arrange 
        Tile tile = new Tile(Color.RED, Shape.BIRD);

        // Act
        boolean result = referee.isValidMove(game, board, tile, 0, 0);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldRejectMoveOnOccupiedSquare() {
        // Arrange 
        board.getSquare(4, 4).setTile(new Tile(Color.RED, Shape.BIRD));

        Tile tile = new Tile(Color.RED, Shape.TURTLE);

        // Act
        boolean result = referee.isValidMove(game, board, tile, 4, 4);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldRejectMoveIfPlayerAlreadyPlayedThisTurn() {
        // Arrange
        board.getSquare(4, 4).setTile(new Tile(Color.RED, Shape.BIRD));
        game.getCurrentPlayer().setHasPlayedThisTurn(true);

        Tile tile = new Tile(Color.RED, Shape.TURTLE);

        // Act
        boolean result = referee.isValidMove(game, board, tile, 4, 3);

        // Assert
        assertFalse(result);
    }


    // ---isValidMove --- 

    @Test
    void shouldAllowMoveMatchingColorWithNeighbor() {
        // Arrange 
        board.getSquare(4, 4).setTile(new Tile(Color.RED, Shape.BIRD));
        Tile tile = new Tile(Color.RED, Shape.TURTLE);

        // Act
        boolean result = referee.isValidMove(game, board, tile, 4, 3);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldAllowMoveMatchingShapeWithNeighbor() {
        // Arrange 
        board.getSquare(4, 4).setTile(new Tile(Color.RED, Shape.BIRD));
        Tile tile = new Tile(Color.NAVY, Shape.BIRD);

        // Act
        boolean result = referee.isValidMove(game, board, tile, 4, 3);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldRejectMoveWithNoMatchingNeighbor() {
        // Arrange 
        board.getSquare(4, 4).setTile(new Tile(Color.RED, Shape.BIRD));
        Tile tile = new Tile(Color.NAVY, Shape.TURTLE);

        // Act
        boolean result = referee.isValidMove(game, board, tile, 4, 3);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldRejectMoveWithNoAdjacentNeighbor() {
        // Arrange 
        board.getSquare(4, 4).setTile(new Tile(Color.RED, Shape.BIRD));
        Tile tile = new Tile(Color.RED, Shape.BIRD);

        // Act
        boolean result = referee.isValidMove(game, board, tile, 0, 0);

        // Assert
        assertFalse(result);
    }
    
    // --- calculatePoints ---

    @Test
    void shouldReturnZeroPointsWithOneMatchingNeighbor() {
        // Arrange
        board.getSquare(4, 4).setTile(new Tile(Color.RED, Shape.BIRD));
        Tile tile = new Tile(Color.RED, Shape.TURTLE);

        // Act
        int points = referee.calculatePoints(board, tile, 4, 3);

        // Assert
        assertEquals(0, points);
    }

    @Test
    void shouldReturnOnePointWithTwoMatchingNeighbors() {
        // Arrange
        board.getSquare(4, 4).setTile(new Tile(Color.RED, Shape.BIRD));
        board.getSquare(4, 2).setTile(new Tile(Color.RED, Shape.TURTLE));
        Tile tile = new Tile(Color.RED, Shape.GECKO);

        // Act
        int points = referee.calculatePoints(board, tile, 4, 3);

        // Assert
        assertEquals(1, points);
    }

    @Test
    void shouldReturnTwoPointsWithThreeMatchingNeighbors() {
        // Arrange
        board.getSquare(4, 4).setTile(new Tile(Color.RED, Shape.BIRD));
        board.getSquare(4, 2).setTile(new Tile(Color.RED, Shape.TURTLE));
        board.getSquare(3, 3).setTile(new Tile(Color.RED, Shape.GECKO));
        Tile tile = new Tile(Color.RED, Shape.FLOWER);

        // Act
        int points = referee.calculatePoints(board, tile, 4, 3);

        // Assert
        assertEquals(2, points);
    }

    @Test
    void shouldAddTwoExtraPointsForSunSquare() {
        // Arrange 
        board.getSquare(1, 4).setTile(new Tile(Color.RED, Shape.BIRD));
        Tile tile = new Tile(Color.RED, Shape.TURTLE);

        // Act 
        int points = referee.calculatePoints(board, tile, 0, 4);

        // Assert
        assertEquals(2, points);
    }

    @Test
    void shouldReturnZeroPointsOnNormalSquareWithOneNeighbor() {
        // Arrange
        board.getSquare(4, 4).setTile(new Tile(Color.RED, Shape.BIRD));
        Tile tile = new Tile(Color.RED, Shape.TURTLE);

        // Act
        int points = referee.calculatePoints(board, tile, 4, 3);

        // Assert
        assertEquals(0, points);
    }


    // --- isGameFinished ---

    @Test
    void shouldNotBeFinishedAtStart() {
        // Assert
        assertFalse(referee.isGameFinished(game));
    }


    // --- getResults ---

    @Test
    void shouldReturnFirstPlayerWinsWhenHigherScore() {
        // Arrange
        game.getPlayers()[0].addScore(10);

        // Act & Assert
        assertEquals("Alice wins!", referee.getResults(game));
    }

    @Test
    void shouldReturnSecondPlayerWinsWhenHigherScore() {
        // Arrange
        game.getPlayers()[1].addScore(10);

        // Act & Assert
        assertEquals("Nolann wins!", referee.getResults(game));
    }

    @Test
    void shouldReturnDrawWhenEqualScores() {
        // Arrange 

        // Act & Assert
        assertEquals("Draw!", referee.getResults(game));
    }

}
