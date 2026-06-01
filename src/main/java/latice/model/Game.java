package latice.model;

import java.util.Random;

public class Game {


    private GameBoard board;
    private Player[] players;
    private int currentPlayerIndex;
    private int cycleCount;

    public Game(String namePlayer1, String namePlayer2) {
        this.board = new GameBoard(9, 9);
        this.board.initSpecialSquares();

        this.players = new Player[]{
            new Player(namePlayer1),
            new Player(namePlayer2)
        };
        
        this.cycleCount = 0;

    }

    public void setup() {
        Pool globalPool = new Pool();
        globalPool.generatePool();
        globalPool.shuffle();

        Pool[] pools = globalPool.splitIntoTwoPools();
        players[0].setPool(pools[0]);
        players[1].setPool(pools[1]);

        players[0].getPool().fillRack(players[0].getRack());
        players[1].getPool().fillRack(players[1].getRack());
    }

    public void chooseStartingPlayer() {
        currentPlayerIndex = new Random().nextInt(players.length);
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public GameBoard getBoard() {
        return board;
    }

    public Player[] getPlayers() {
        return players;
    }
    
    public int getCycleCount() {
        return cycleCount;
    }
    
    public void nextPlayer() {

        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;

        if (currentPlayerIndex == 0) {
            cycleCount++;
        }
    }
}