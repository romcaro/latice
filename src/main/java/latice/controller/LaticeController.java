package latice.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import latice.model.Color;
import latice.model.Game;
import latice.model.GameBoard;
import latice.model.Player;
import latice.model.Pool;
import latice.model.Rack;
import latice.model.Referee;
import latice.model.Square;
import latice.model.Tile;

public class LaticeController {

    private static final int TILE_SIZE = 80;
    private static final int BOARD_SIZE = 9;
    private static final int NO_ANIMATION = Integer.MAX_VALUE;

    @FXML
    private GridPane gridPane;

    @FXML
    private HBox idRackBox;

    @FXML
    private Label idPlayer1Name;

    @FXML
    private Label idPlayer1Score;

    @FXML
    private Label idPlayer2Name;

    @FXML
    private Label idPlayer2Score;

    @FXML
    private Label idCurrentPlayer;

    @FXML
    private Label idCycleCount;

    @FXML
    private Label idMessage;

    private GameBoard gameBoard;
    private Referee referee;
    private Game game;
    private boolean gameFinished = false;

    @FXML
    public void initialize() {
    }

    public void startGame(String player1Name, String player2Name) {
        game = new Game(player1Name, player2Name);
        game.setup();
        game.chooseStartingPlayer();

        gameBoard = game.getBoard();
        referee = new Referee(gameBoard);

        refreshGameView(NO_ANIMATION);
    }

    @FXML
    private void handleEndTurn() {
        if (gameFinished) {
            return;
        }

        endCurrentTurn();

        if (referee.isGameFinished(game)) {
            finishGame();
            return;
        }

        refreshGameViewWithDrawAnimation();
    }

    @FXML
    private void handleBuyExtraAction() {
        if (gameFinished) {
            return;
        }

        Player currentPlayer = game.getCurrentPlayer();

        if (!currentPlayer.hasPlayedThisTurn()) {
            showMessage("You must play a tile before buying an extra action.");
            return;
        }

        if (!currentPlayer.spendPoints(2)) {
            showMessage("You need 2 points to buy an extra action.");
            return;
        }

        currentPlayer.setHasPlayedThisTurn(false);

        updateScores();
        showMessage("Extra action bought.");
    }

    @FXML
    private void handleExchangeRack() {
        if (gameFinished) {
            return;
        }

        Player currentPlayer = game.getCurrentPlayer();
        Rack rack = currentPlayer.getRack();
        Pool pool = currentPlayer.getPool();

        exchangeRack(rack, pool);

        handleEndTurn();
    }

    private void endCurrentTurn() {
        game.getCurrentPlayer().setHasPlayedThisTurn(false);

        game.nextPlayer();
        updateCycleCount();
        
        
        idMessage.setText("");
    }

    private void finishGame() {
        gameFinished = true;
        showResults();
    }

    private void exchangeRack(Rack rack, Pool pool) {
        while (!rack.isEmpty()) {
            Tile tile = rack.getRack().get(0);
            rack.removeTile(tile);
            pool.addTile(tile);
        }

        pool.shuffle();
        pool.fillRack(rack);
    }

    private void playTile(Tile tile, Rack rack, Square targetSquare, int col, int row) {
    	int matchPoints = referee.calculateMatchPoints(gameBoard, tile, col, row);
    	int sunPoints = referee.calculateSunPoints(gameBoard, col, row);
    	int points = matchPoints + sunPoints;

        Player currentPlayer = game.getCurrentPlayer();

        currentPlayer.addScore(points);
        currentPlayer.addTilesPlayed();
        currentPlayer.setHasPlayedThisTurn(true);

        targetSquare.setTile(tile);
        rack.removeTile(tile);

        refreshGameView(NO_ANIMATION);

        showPointsAnimation(matchPoints, col, row, 0);
        showPointsAnimation(sunPoints, col, row, 300);
    }

    private void refreshGameView(int animatedFromIndex) {
        updateScores();
        updateCurrentPlayer();
        updateCycleCount();
        displayRack(game.getCurrentPlayer().getRack(), animatedFromIndex);
        displayBoard();
    }

    private void refreshGameViewWithDrawAnimation() {
        Player currentPlayer = game.getCurrentPlayer();

        updateCurrentPlayer();
        updateScores();
        updateCycleCount();
        displayBoard();

        displayRack(currentPlayer.getRack(), NO_ANIMATION);

        int oldRackSize = currentPlayer.getRack().size();

        PauseTransition pause = new PauseTransition(Duration.millis(400));
        pause.setOnFinished(event -> {
            currentPlayer.getPool().fillRack(currentPlayer.getRack());
            displayRack(currentPlayer.getRack(), oldRackSize);
        });
        pause.play();
    }

    private void displayRack(Rack rack, int animatedFromIndex) {
        idRackBox.getChildren().clear();

        for (int i = 0; i < rack.getRack().size(); i++) {
            Tile tile = rack.getRack().get(i);
            int tileIndex = i;

            ImageView tileView = createTileView(tile);

            if (i >= animatedFromIndex) {
                playFadeAnimation(tileView);
            }

            addDragEvent(tileView, tileIndex);

            idRackBox.getChildren().add(tileView);
        }
    }

    private ImageView createTileView(Tile tile) {
        ImageView tileView = new ImageView(getImageForTile(tile));

        tileView.setFitWidth(TILE_SIZE);
        tileView.setFitHeight(TILE_SIZE);

        return tileView;
    }

    private void playFadeAnimation(ImageView tileView) {
        tileView.setOpacity(0);

        FadeTransition fade = new FadeTransition(Duration.millis(250), tileView);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    private void addDragEvent(ImageView tileView, int tileIndex) {
        tileView.setOnDragDetected(event -> {
            Dragboard dragboard = tileView.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putString(String.valueOf(tileIndex));
            dragboard.setContent(content);

            WritableImage dragImage = tileView.snapshot(null, null);

            dragboard.setDragView(dragImage);

            dragboard.setDragViewOffsetX(TILE_SIZE / 2);
            dragboard.setDragViewOffsetY(TILE_SIZE / 2);

            event.consume();
        });
    }

    private void displayBoard() {
        gridPane.getChildren().clear();

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                addSquareToBoard(col, row);
            }
        }
    }

    private void addSquareToBoard(int col, int row) {
        Square square = gameBoard.getSquare(col, row);

        ImageView squareView = new ImageView(getImageForSquare(square));
        squareView.setFitWidth(TILE_SIZE);
        squareView.setFitHeight(TILE_SIZE);

        if (square.isOccupied()) {
            squareView.setImage(getImageForTile(square.getTile()));
        }

        addDropEvents(squareView, col, row);

        gridPane.add(squareView, col, row);
    }

    private void addDropEvents(ImageView squareView, int col, int row) {
        squareView.setOnDragOver(event -> {
            if (event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.ANY);
            }

            event.consume();
        });

        squareView.setOnDragDropped(event -> {
            if (gameFinished) {
                event.setDropCompleted(false);
                event.consume();
                return;
            }

            Dragboard dragboard = event.getDragboard();

            if (dragboard.hasString()) {
                int tileIndex = Integer.parseInt(dragboard.getString());

                Rack currentRack = game.getCurrentPlayer().getRack();
                Tile tile = currentRack.getRack().get(tileIndex);
                Square targetSquare = gameBoard.getSquare(col, row);

                if (referee.isValidMove(game, gameBoard, tile, col, row)) {
                    playTile(tile, currentRack, targetSquare, col, row);
                    event.setDropCompleted(true);
                }
            }

            event.consume();
        });
    }

    private void updateScores() {
        Player[] players = game.getPlayers();

        idPlayer1Name.setText(players[0].getName());
        idPlayer1Score.setText(String.valueOf(players[0].getScore()));

        idPlayer2Name.setText(players[1].getName());
        idPlayer2Score.setText(String.valueOf(players[1].getScore()));
    }
    
    private void showPointsAnimation(int points, int col, int row, int delay) {
        if (points <= 0) {
            return;
        }

        String path = switch (points) {
            case 1 -> "/latice/assets/+1.png";
            case 2 -> "/latice/assets/+2.png";
            case 4 -> "/latice/assets/+4.png";
            default -> null;
        };

        if (path == null) {
            return;
        }

        ImageView pointView = new ImageView(loadImage(path));
        pointView.setFitWidth(60);
        pointView.setPreserveRatio(true);
        pointView.setMouseTransparent(true);

        GridPane.setHalignment(pointView, HPos.CENTER);
        pointView.setTranslateY(-35);

        gridPane.add(pointView, col, row);

        FadeTransition fade = new FadeTransition(Duration.millis(800), pointView);
        fade.setFromValue(1);
        fade.setToValue(0);

        TranslateTransition move = new TranslateTransition(Duration.millis(800), pointView);
        move.setFromY(-35);
        move.setToY(-75);

        ParallelTransition animation = new ParallelTransition(fade, move);
        animation.setDelay(Duration.millis(delay));
        animation.setOnFinished(event -> gridPane.getChildren().remove(pointView));
        animation.play();
    }

    private void updateCurrentPlayer() {
        idCurrentPlayer.setText("Player : " + game.getCurrentPlayer().getName());
    }

    private void updateCycleCount() {
        idCycleCount.setText("Cycle Count : " + game.getCycleCount());
    }

    private void showResults() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Game Results");
        alert.setHeaderText(null);
        alert.setContentText(referee.getResults(game));

        alert.showAndWait();
    }

    private void showMessage(String message) {
        idMessage.setText(message);
    }

    private Image getImageForSquare(Square square) {
        return switch (square.getType()) {
            case SUN -> loadImage("/latice/assets/bg_sun.png");
            case MOON -> loadImage("/latice/assets/bg_moon.png");
            default -> loadImage("/latice/assets/bg_sea.png");
        };
    }

    private Image getImageForTile(Tile tile) {
        String shapeName = tile.getShape().name().toLowerCase();
        String colorLetter = getColorLetter(tile.getColor());

        String path = "/latice/assets/" + shapeName + "_" + colorLetter + ".png";

        return loadImage(path);
    }

    private String getColorLetter(Color color) {
        return switch (color) {
            case GREEN -> "g";
            case MAGENTA -> "m";
            case NAVY -> "n";
            case RED -> "r";
            case TEAL -> "t";
            case YELLOW -> "y";
        };
    }

    private Image loadImage(String path) {
        return new Image(getClass().getResource(path).toExternalForm());
    }
}