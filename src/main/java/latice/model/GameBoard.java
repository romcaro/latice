package latice.model;

import java.util.HashMap;
import java.util.Map;

public class GameBoard {
	
	private Map<Position, Cell> cells = new HashMap<>();

    public GameBoard(int width, int height) {

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                Position position = new Position(x, y);

                cells.put(position, new Cell(position));
            }
        }
    }

    public Cell getCase(int x, int y) {
        return cells.get(new Position(x, y));
    }
}
