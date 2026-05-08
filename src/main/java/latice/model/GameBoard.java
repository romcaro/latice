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

    public Square getCase(int x, int y) {
        return squares.get(new Position(x, y));
    }
}
