package latice.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import latice.model.*;

class GameTest {

    private Game game;

    @BeforeEach
    void createGame() {
        game = new Game("Alice", "Bob");
    }

    // --- setup ---

    @Test
    void shouldGiveEachPlayerANonNullPool() {
        // Act
        game.setup();

        // Assert
        assertNotNull(game.getPlayers()[0].getPool());
        assertNotNull(game.getPlayers()[1].getPool());
    }

    @Test
    void shouldGiveEachPlayerAPoolOf36Tiles() {
        // Act
        game.setup();

        // Assert
        int p0 = game.getPlayers()[0].getPool().size();
        int p1 = game.getPlayers()[1].getPool().size();
        assertEquals(31, p0);
        assertEquals(31, p1);
    }

    @Test
    void shouldFillEachPlayerRackWith5Tiles() {
        // Act
        game.setup();

        // Assert
        assertEquals(5, game.getPlayers()[0].getRack().size());
        assertEquals(5, game.getPlayers()[1].getRack().size());
    }

    // --- chooseStartingPlayer & getCurrentPlayer ---

    @Test
    void shouldSetCurrentPlayerToOneOfTheTwoPlayers() {
        // Act
        game.chooseStartingPlayer();
        Player current = game.getCurrentPlayer();

        // Assert
        assertTrue(
            current == game.getPlayers()[0] || current == game.getPlayers()[1]
        );
    }

    // --- getBoard ---

    @Test
    void shouldReturnNonNullBoard() {
        // Assert
        assertNotNull(game.getBoard());
    }

    @Test
    void shouldHaveMoonAtCenterOnBoard() {
        // Assert — 
        assertEquals(SquareType.MOON, game.getBoard().getSquare(4, 4).getType());
    }

    // --- nextPlayer & cycleCount ---

    @Test
    void shouldStartWithZeroCycles() {
        // Assert
        assertEquals(0, game.getCycleCount());
    }

    @Test
    void shouldIncrementCycleAfterFullRound() {
        // Arrange
        game.chooseStartingPlayer();

        // Act
        game.nextPlayer();
        game.nextPlayer(); 

        // Assert
        assertEquals(1, game.getCycleCount());
    }

    @Test
    void shouldAlternateBetweenTwoPlayers() {
        // Arrange
        game.chooseStartingPlayer();
        Player first = game.getCurrentPlayer();

        // Act
        game.nextPlayer();
        Player second = game.getCurrentPlayer();

        // Assert
        assertNotSame(first, second);
    }

    @Test
    void shouldReturnToFirstPlayerAfterTwoNextPlayerCalls() {
        // Arrange
        game.chooseStartingPlayer();
        Player first = game.getCurrentPlayer();

        // Act
        game.nextPlayer();
        game.nextPlayer();

        // Assert
        assertSame(first, game.getCurrentPlayer());
    }
}
