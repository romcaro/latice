package latice.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import latice.model.Pool;

public class PoolTest {

    @Test
    public void shouldCreatePoolWith80Tiles() {

        // Arrange
        Pool pool = new Pool();

        // Act
        pool.generatePool();

        // Assert
        assertEquals(72, pool.size());
    }
}
