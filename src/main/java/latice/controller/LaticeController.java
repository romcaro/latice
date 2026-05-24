package latice.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import latice.model.Color;
import latice.model.GameBoard;
import latice.model.Pool;
import latice.model.Rack;
import latice.model.Square;
import latice.model.Tile;

public class LaticeController {

    @FXML
    private GridPane gridPane;
    
    @FXML
    private HBox idRackBox;

    private GameBoard gameBoard;
    private Rack rack;

    private static final int TILE_SIZE = 80;

    @FXML
    public void initialize() {
        gameBoard = new GameBoard(9, 9);
        gameBoard.initSpecialSquares(); //initialisation des cases spéciales
        setImageView(gridPane, 9, 9);
        
        // Initialisation de la pioche et du rack
        Pool pool = new Pool();
        pool.generatePool();
        pool.shuffle();

        rack = new Rack();
        pool.fillRack(rack);

        // affichage du rack
        displayRack();
    }
    
    
    private void displayRack() {
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
            	
            	event.consume();
            }
            );
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
                	Dragboard dragboard = event.getDragboard();
                	
                	if (dragboard.hasString()) {
                		
                		int tileIndex = Integer.parseInt(dragboard.getString());
                		Tile tile = rack.getRack().get(tileIndex);
                		Square targetSquare = gameBoard.getSquare(currentCol, currentRow);
                		
						targetSquare.setTile(tile);
						rack.removeTile(tile);
						displayRack(); 
						setImageView(gridPane, width, height);
							
						event.setDropCompleted(true);
						
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