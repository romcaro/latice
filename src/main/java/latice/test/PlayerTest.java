package latice.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import latice.model.Player;

public class PlayerTest {
	
	@Test
	public void shouldAddPointsToPlayer() {
		// Arrange
		Player player = new Player("Alice", null);
		
		// Act
		player.addScore(10);
		
		// Assert
		assertEquals(10, player.getScore());
	}
	

}
