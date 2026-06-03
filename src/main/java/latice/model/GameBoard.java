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
	
	public boolean isEmpty() {
		for (int x = 0; x < width; x++) {
	        for (int y = 0; y < height; y++) {
	            if (squares[x][y].isOccupied()) {
	                return false;
	            }
	        }
	    }
	    return true;
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
	
	
	public boolean allNeighborsMatch(Square square, Tile tile) {
	    Position position = square.getPosition();
	    int col = position.getX();
	    int row = position.getY();

	    boolean hasNeighbor = false;

	    if (col > 0 && squares[col - 1][row].isOccupied()) {
	        if (!matchesNeighbor(squares[col - 1][row], tile)) 
	        	return false; // gauche
	        hasNeighbor = true;
	    }
	    
	    if (col < width - 1 && squares[col + 1][row].isOccupied()) {
	        if (!matchesNeighbor(squares[col + 1][row], tile)) 
	        	return false; // droite
	        hasNeighbor = true;
	    }
	    
	    if (row > 0 && squares[col][row - 1].isOccupied()) {
	        if (!matchesNeighbor(squares[col][row - 1], tile)) 
	        	return false; // haut
	        hasNeighbor = true;
	    }
	    
	    if (row < height - 1 && squares[col][row + 1].isOccupied()) {
	        if (!matchesNeighbor(squares[col][row + 1], tile)) 
	        	return false; // bas
	        hasNeighbor = true;
	    }

	    return hasNeighbor;
	}

	private boolean matchesNeighbor(Square neighbor, Tile tile) {
	    Tile neighborTile = neighbor.getTile();
	    return neighborTile.getColor() == tile.getColor() || neighborTile.getShape() == tile.getShape();
	}
	
	public int countMatchingNeighbors(Square square, Tile tile) {
	    Position position = square.getPosition();
	    int col = position.getX();
	    int row = position.getY();

	    int count = 0;

	    if (col > 0 && squares[col - 1][row].isOccupied()
	            && matchesNeighbor(squares[col - 1][row], tile)) {
	        count++;
	    }

	    if (col < width - 1 && squares[col + 1][row].isOccupied()
	            && matchesNeighbor(squares[col + 1][row], tile)) {
	        count++;
	    }

	    if (row > 0 && squares[col][row - 1].isOccupied()
	            && matchesNeighbor(squares[col][row - 1], tile)) {
	        count++;
	    }

	    if (row < height - 1 && squares[col][row + 1].isOccupied()
	            && matchesNeighbor(squares[col][row + 1], tile)) {
	        count++;
	    }

	    return count;
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