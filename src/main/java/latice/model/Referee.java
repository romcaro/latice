package latice.model;

public class Referee {
	
	private int cycleCount;
	private GameBoard gameBoard;
	private Boolean gameOver;
	
	public Referee(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
		this.cycleCount = 0;
		this.gameOver = false;
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
	
}
