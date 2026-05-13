package latice.model;

public class GameBoard {
	
	private Square[][] squares;
	private int width;
	private int height;

	public GameBoard(int width, int height) {
	    this.width = width;
	    this.height = height;
	    this.squares = new Square[width][height];

	    for (int x = 0; x < width; x++) {
	        for (int y = 0; y < height; y++) {
	            squares[x][y] = new Square(new Position(x, y));
	        }
	    }
	}

	public Square getSquare(int x, int y) {
	    return squares[x][y];
	}

	public void setSquare(int x, int y, Square square) {
	    squares[x][y] = square;
	}
}