package latice.model;

public class GameBoard {
	
	private Square[][] squares;
	private int width; //pour la gestion des erreurs dans le futur pour la v8
	private int height; //pour la gestion des erreurs dans le futur pour la v8

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
	
	public void initSpecialSquares() {
	    // Moon center
	    squares[4][4].setType(SquareType.MOON);

	    // Alone
	    squares[0][4].setType(SquareType.SUN);
	    squares[4][8].setType(SquareType.SUN);
	    squares[8][4].setType(SquareType.SUN);
	    squares[4][0].setType(SquareType.SUN);

	    // Up left
	    squares[0][0].setType(SquareType.SUN);
	    squares[1][1].setType(SquareType.SUN);
	    squares[2][2].setType(SquareType.SUN);

	    // Up right
	    squares[0][8].setType(SquareType.SUN);
	    squares[1][7].setType(SquareType.SUN);
	    squares[2][6].setType(SquareType.SUN);

	    // Bottom right
	    squares[8][8].setType(SquareType.SUN);
	    squares[7][7].setType(SquareType.SUN);
	    squares[6][6].setType(SquareType.SUN);

	    // Bottom left
	    squares[8][0].setType(SquareType.SUN);
	    squares[7][1].setType(SquareType.SUN);
	    squares[6][2].setType(SquareType.SUN);
	}
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(squares[x][y]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}