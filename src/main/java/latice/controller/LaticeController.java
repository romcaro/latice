package latice.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import latice.model.Color;
import latice.model.Game;
import latice.model.Rack;
import latice.model.Square;
import latice.model.Tile;

public class LaticeController {

    @FXML
    private GridPane gridPane;
    
    @FXML
    private HBox idRackBox;

    private Game game;
    private static final int TILE_SIZE = 80;

    @FXML
    public void initialize() {
        game = new Game("Joueur 1","Joueur 2");
        game.setup();
        game.chooseStartingPlayer();
        
        setImageView(gridPane, 9, 9);
        
        displayRack(game.getCurrentPlayerIndex().getRack());
    }
    
    
    private void displayRack(Rack rack) {
        idRackBox.getChildren().clear(); // on vide d'abord au cas ou on raffraîchit

        for (Tile tile : rack.getRack()) {
            ImageView tileView = new ImageView(getImageForTile(tile));
            tileView.setFitWidth(TILE_SIZE);
            tileView.setFitHeight(TILE_SIZE);
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
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {

                Square square = game.getBoard().getSquare(col, row);

                // Image de fond de la case (normal / soleil / lune)
                ImageView bgView = new ImageView(getImageForSquare(square));
                bgView.setFitWidth(TILE_SIZE);
                bgView.setFitHeight(TILE_SIZE);

                // Si la case contient une tuile, on superpose son image
                if (square.isOccupied()) {
                    ImageView tileView = new ImageView(getImageForTile(square.getTile()));
                    tileView.setFitWidth(TILE_SIZE);
                    tileView.setFitHeight(TILE_SIZE);
                    // Plus tard, on pourra utiliser un StackPane pour superposer bg + tuile
                }

                gridPane.add(bgView, col, row);
            }
        }

    }
}