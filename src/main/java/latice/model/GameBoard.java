package latice.model;

import java.util.HashMap;
import java.util.Map;

public class GameBoard {
	
	private Map<Position, Square> squares = new HashMap<>();

    public GameBoard(int width, int height) {

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                Position position = new Position(x, y);

                squares.put(position, new Square(position));
            }
        }
    }

    public Square getSquare(int x, int y) {
        return squares.get(new Position(x, y));
    }
    
    public Square setSquare(int x, int y, Square square) {
    	return squares.put(new Position(x, y) ,square);
    }
}
