package latice.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import latice.model.GameBoard;
import latice.model.Square;

public class LaticeController {

    @FXML
    private GridPane gridPane;

    private GameBoard gameBoard;

    private static final int TILE_SIZE = 80;

    @FXML
    public void initialize() {
        gameBoard = new GameBoard(9, 9);
        gameBoard.initSpecialSquares(); //initialisation des cases spéciales 
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
}