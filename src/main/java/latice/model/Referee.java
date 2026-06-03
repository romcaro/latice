package latice.model;

public class Referee {

    private static final int MAX_CYCLES = 10;

    private GameBoard gameBoard;

    public Referee(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public boolean isValidMove(Game game, GameBoard board, Tile tile, int col, int row) {
        Square square = board.getSquare(col, row);

        return !hasPlayerAlreadyPlayed(game)
                && !isSquareOccupied(square)
                && isFirstMoveOnMoon(board, square)
                && hasValidNeighbors(board, square, tile);
    }

    public int calculatePoints(GameBoard board, Tile tile, int col, int row) {
        return calculateMatchPoints(board, tile, col, row)
                + calculateSunPoints(board, col, row);
    }

    public int calculateMatchPoints(GameBoard board, Tile tile, int col, int row) {
        Square square = board.getSquare(col, row);
        int matches = board.countMatchingNeighbors(square, tile);

        return switch (matches) {
            case 2 -> 1;
            case 3 -> 2;
            case 4 -> 4;
            default -> 0;
        };
    }

    public int calculateSunPoints(GameBoard board, int col, int row) {
        Square square = board.getSquare(col, row);

        if (square.getType() == SquareType.SUN) {
            return 2;
        }

        return 0;
    }

    public boolean isGameFinished(Game game) {
        return game.getCycleCount() >= MAX_CYCLES
                || hasEmptyRackAndPool(game.getPlayers()[0])
                || hasEmptyRackAndPool(game.getPlayers()[1]);
    }

    public String getResults(Game game) {
        Player[] players = game.getPlayers();

        if (players[0].getTilesPlayed() > players[1].getTilesPlayed()) {
            return players[0].getName() + " wins!";
        }

        if (players[1].getTilesPlayed() > players[0].getTilesPlayed()) {
            return players[1].getName() + " wins!";
        }

        return "Draw!";
    }

    private boolean hasPlayerAlreadyPlayed(Game game) {
        return game.getCurrentPlayer().hasPlayedThisTurn();
    }

    private boolean isSquareOccupied(Square square) {
        return square.isOccupied();
    }

    private boolean isFirstMoveOnMoon(GameBoard board, Square square) {
        return !board.isEmpty() || square.getType() == SquareType.MOON;
    }

    private boolean hasValidNeighbors(GameBoard board, Square square, Tile tile) {
        return board.isEmpty() || board.allNeighborsMatch(square, tile);
    }

    private boolean hasEmptyRackAndPool(Player player) {
        return player.getRack().isEmpty() && player.getPool().isEmpty();
    }
}