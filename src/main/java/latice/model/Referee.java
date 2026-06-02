package latice.model;

public class Referee {
	
	private static final int MAX_CYCLES = 10;
	
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
	
	public boolean isValidMove(Game game, 
								GameBoard board,
								Tile tile,
								int col,
								int row) {
		
		Square square = board.getSquare(col, row);
		
	    if (game.getCurrentPlayer().hasPlayedThisTurn()) {
	        return false;
	    }
		
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
	
	public int calculatePoints(GameBoard board, Tile tile, int col, int row) {
	    Square square = board.getSquare(col, row);

	    int matches = board.countMatchingNeighbors(square, tile);
	    int points = 0;

	    if (matches == 1) {
	        points = 0; // pas de point avec un seul voisin
	    }else if (matches == 2) {
	        points += 1;
	    } else if (matches == 3) {
	        points += 2;
	    } else if (matches == 4) {
	        points += 4;
	    }

	    if (square.getType() == SquareType.SUN) {
	        points += 2;
	    }

	    return points;
	}

	public boolean isGameFinished(Game game) {
	    return game.getCycleCount() >= MAX_CYCLES;
	}

	public String getResults(Game game) {

	    Player[] players = game.getPlayers();

	    if (players[0].getScore() > players[1].getScore()) {
	        return players[0].getName() + " wins!";
	    }

	    if (players[1].getScore() > players[0].getScore()) {
	        return players[1].getName() + " wins!";
	    }

	    return "Draw!";
	}
	
}
