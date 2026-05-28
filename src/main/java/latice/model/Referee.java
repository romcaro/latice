package latice.model;

public class Referee {
	
	private int cycleCount;
	private GameBoard gameBoard;
	
	public Referee(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
		this.cycleCount = 0;
	}

	public int getCycleCount() {
		return cycleCount;
	}

	public void setCycleCount(int cycleCount) {
		this.cycleCount = cycleCount;
	}

	public GameBoard getGameBoard() {
		return gameBoard;
	}
	
	public boolean isValidMove(GameBoard board,Tile tile,int col,int row) {

		Square square = board.getSquare(col, row);
		
		// case déjà occupée
		if (square.isOccupied()) {
		return false;
		}
		
		//premiere tuile sur une lune 
		if (board.isEmpty() && square.getType() != SquareType.MOON) {
			return false;
			
		}
		
		if (!board.isEmpty() && !board.allNeighborsMatch(square, tile))
			return false;

		return true;
		}
	
		
	
	
	
}
