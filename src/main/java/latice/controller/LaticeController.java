package latice.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    
    private static final int TILE_SIZE = 80;

    @FXML
    public void initialize() {
    }
    
    @FXML
    private void handleEndTurn() {
        if (gameFinished) {
            return;
        }

        Player currentPlayer = game.getCurrentPlayer();
        currentPlayer.getPool().fillRack(currentPlayer.getRack());
        currentPlayer.setHasPlayedThisTurn(false);

        game.nextPlayer();
        updateCycleCount();

        if (referee.isGameFinished(game)) {
            gameFinished = true;
            showResults();
            return;
        }

        updateCurrentPlayer();
        displayRack(game.getCurrentPlayer().getRack());
        setImageView(gridPane, 9, 9);
        updateScores();
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

        while (!rack.isEmpty()) {
            Tile tile = rack.getRack().get(0);

            rack.removeTile(tile);
            pool.addTile(tile);
        }

        pool.shuffle();
        pool.fillRack(rack);

        handleEndTurn();
    }
    
    public void startGame(String player1Name, String player2Name) {
        game = new Game(player1Name, player2Name);
        game.setup();
        game.chooseStartingPlayer();

        gameBoard = game.getBoard();
        referee = new Referee(gameBoard);

        setImageView(gridPane, 9, 9);
        displayRack(game.getCurrentPlayer().getRack());
        updateScores();
        updateCurrentPlayer();
        updateCycleCount();
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
    
    
    private void displayRack(Rack rack) {
        idRackBox.getChildren().clear(); // on vide d'abord au cas ou on raffraîchit

        for (int i = 0; i < rack.getRack().size(); i++) {

            Tile tile = rack.getRack().get(i);
            int tileIndex = i;
            
            ImageView tileView = new ImageView(getImageForTile(tile));
            tileView.setFitWidth(TILE_SIZE);
            tileView.setFitHeight(TILE_SIZE);
            
            tileView.setOnDragDetected(event -> {
            	Dragboard dragboard = tileView.startDragAndDrop(TransferMode.ANY);
            	
            	ClipboardContent content = new ClipboardContent();
            	content.putString(String.valueOf(tileIndex));
            	dragboard.setContent(content);
            	
                
                Image drawView = tileView.getImage();
                dragboard.setDragView(drawView);
                
                dragboard.setDragViewOffsetX(drawView.getWidth() / 2);
                dragboard.setDragViewOffsetY(drawView.getHeight() / 2);
                
            	
            	event.consume();
            });
            idRackBox.getChildren().add(tileView);
        }
    }
    
    //Retourne l'image de fond d'une case selon son type (SEA, SUN, MOON)
    private Image getImageForSquare(Square square) {
        return switch (square.getType()) {
            case SUN  -> loadImage("/latice/assets/bg_sun.png");
            case MOON -> loadImage("/latice/assets/bg_moon.png");
            default   -> loadImage("/latice/assets/bg_sea.png");
        };
    }
    
    private void updateScores() {
        Player[] players = game.getPlayers();

        idPlayer1Name.setText(players[0].getName());
        idPlayer1Score.setText(String.valueOf(players[0].getScore()));

        idPlayer2Name.setText(players[1].getName());
        idPlayer2Score.setText(String.valueOf(players[1].getScore()));
    }
    
    private void updateCurrentPlayer() {
        idCurrentPlayer.setText(
            "Player : " + game.getCurrentPlayer().getName()
        );
        
        idMessage.setText("");
    }
    
    private void updateCycleCount() {
        idCycleCount.setText("Cycle Count : " + game.getCycleCount());
    }
    
    

    private Image loadImage(String path) {
        return new Image(getClass().getResource(path).toExternalForm());
    }
    
    private Image getImageForTile(Tile tile) {
        String shapeName = tile.getShape().name().toLowerCase();
        String colorLetter = getColorLetter(tile.getColor());      
        String path = "/latice/assets/" + shapeName + "_" + colorLetter + ".png";
        return loadImage(path);
    }

    private String getColorLetter(Color color) {
        return switch (color) {
            case GREEN   -> "g";
            case MAGENTA -> "m";
            case NAVY    -> "n";
            case RED     -> "r";
            case TEAL    -> "t";
            case YELLOW  -> "y";
        };
    }
    
    private void setImageView(GridPane gridPane, int width, int height) {
    	gridPane.getChildren().clear();
    	
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
            	
            	int currentCol = col;
            	int currentRow = row;

                Square square = gameBoard.getSquare(col, row);

                // Image de fond de la case (normal / soleil / lune)
                ImageView bgView = new ImageView(getImageForSquare(square));
                bgView.setFitWidth(TILE_SIZE);
                bgView.setFitHeight(TILE_SIZE);
                
                bgView.setOnDragOver(event -> {
                	if (event.getDragboard().hasString()) {
						event.acceptTransferModes(TransferMode.ANY);
					}
                	
                	event.consume();
                	});

                
                bgView.setOnDragDropped(event -> {
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
                		Square targetSquare = gameBoard.getSquare(currentCol, currentRow);
                		
                		if (referee.isValidMove(game, gameBoard, tile, currentCol, currentRow)) {

                		    int points = referee.calculatePoints(
                		                    gameBoard,
                		                    tile,
                		                    currentCol,
                		                    currentRow
                		            );
                		    
                		    game.getCurrentPlayer().addScore(points);	
                		    game.getCurrentPlayer().addTilesPlayed(); 
                		    game.getCurrentPlayer().setHasPlayedThisTurn(true);
                		    
                		    targetSquare.setTile(tile);
                		    currentRack.removeTile(tile);

                		    updateScores();
                		    displayRack(game.getCurrentPlayer().getRack());
                		    setImageView(gridPane, width, height);

                		    event.setDropCompleted(true);
                		}
						
                	}
                	
                	event.consume();
                });
                

                // Si la case contient une tuile, on superpose son image
                if (square.isOccupied()) {
                    bgView.setImage(getImageForTile(square.getTile()));
                }

                gridPane.add(bgView, col, row);
            }
        }

    }
}