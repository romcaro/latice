package latice.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import latice.model.Position;
import latice.model.Square;

public class LaticeController {

    @FXML
    private GridPane gridPane;
    
    public static Image loadImage() {
        return new Image(LaticeController.class.getResource("latice/assets/bg_sun.png").toExternalForm());
    }
    
    private void setImageView(GridPane gridPane, int width, int height) {
    	for(int i=0; i < height; i++) {
    		for(int j=0; j < width; j++) {
    			
    			Square square = new Square(new Position(i,j));
    			
    			ImageView imageView = new ImageView(loadImage());
    			
    			gridPane.add(imageView, i, j);
    			
    			
    			
    			
    		}
    	}
    }
    

}
