package latice.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import latice.model.Pool;
import latice.model.Tile;

class PoolTest {
	
	private Pool pool;
	
    @BeforeEach
	void createPool() {
    	// Arrange
		pool = new Pool();
		
		//Act
		pool.generatePool();
	}

    @Test
    void shouldCreatePoolWith72Tiles() {
        // Assert
        assertEquals(72, pool.size());
    }
    
    @Test
	void shouldHaveTheSameSizeAfterShuffle() {
    	//Act
    	pool.shuffle();
    	
    	//Assert
    	assertEquals(72,pool.size());
    }
    
    @Test
	void shouldSplitPoolIntoTwoEqualPools() {
        // Act
        Pool[] pools = pool.splitIntoTwoPools();

        // Assert
        Assertions.assertEquals(36,pools[0].size());
		Assertions.assertEquals(36,pools[0].size());
        		        
    }
    
    @Test
    void shouldReduceTheSizeByOne() {
    	//Act
    	int sizeBefore = pool.size();
    	pool.draw();
    	
    	// Assert
    	assertEquals(sizeBefore - 1,pool.size());
    	
    }
    
    @Test 
    void shoulDrawFeatherYellow() { //First tile of the list
    	//Act
    	Tile expectedTile = pool.getTiles().get(0);
    	
    	//Assert
    	assertEquals(expectedTile, pool.draw());
    }
    
    @Test
    void shouldBeEmptyAfterDrawingAllTiles() {
    	//Act
        int size = pool.size();
        for (int i = 0; i < size; i++) {
            pool.draw();
        }
        
        // Assert
        assertTrue(pool.isEmpty());
    }
    
    @Test
    void shouldNotGeneratePoolTwice() {
    	// Act
        int sizeAfterFirstGenerate = pool.size();
        pool.generatePool();
        
        //Assert
        assertEquals(sizeAfterFirstGenerate, pool.size());
    }
    
    @Test
    void shouldThrowExceptionWhenDrawingFromEmptyPool() {
    	//Act
        int size = pool.size();
        for (int i = 0; i < size; i++) {
            pool.draw();
        }
        // Assert
        assertThrows(NoSuchElementException.class, () -> pool.draw());
    }
}
