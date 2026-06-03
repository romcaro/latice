package latice.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartMenuController {
	
	@FXML
	private TextField idPlayer1Field;

	@FXML
	private TextField idPlayer2Field;
	
	@FXML
	private void handleStartGame() throws IOException {
	    String player1Name = idPlayer1Field.getText();
	    String player2Name = idPlayer2Field.getText();

	    if (player1Name.isBlank()) {
	        player1Name = "Player 1";
	    }

	    if (player2Name.isBlank()) {
	        player2Name = "Player 2";
	    }

	    FXMLLoader loader =
	            new FXMLLoader(getClass().getResource("/latice/view/LaticeApp.fxml"));

	    Parent root = loader.load();

	    LaticeController controller = loader.getController();
	    controller.startGame(player1Name, player2Name);

	    Stage stage = (Stage) idPlayer1Field.getScene().getWindow();

	    stage.setScene(new Scene(root));
	    
	    stage.sizeToScene();
	    stage.centerOnScreen();
	}
	
	@FXML
	private void handleRules() {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);

	    alert.setTitle("Rules");
	    alert.setHeaderText("Latice's rules");

	    alert.setContentText("""
	    		- Each player has a rack containing 5 tiles.
	    		- The first tile must be placed on the Moon square.
	    		- Every new tile must be placed adjacent to at least one tile already on the board.
	    		- An adjacent tile must share either its color or its shape with neighboring tiles.
	    		- A tile adjacent to 2 valid neighboring tiles earns 1 point.
	    		- A tile adjacent to 3 valid neighboring tiles earns 2 points.
	    		- A tile adjacent to 4 valid neighboring tiles earns 4 points.
	    		- A tile placed on a Sun square earns 2 additional points.
	    		- The player with the highest score at the end of the game wins.
	    		""");

	    alert.showAndWait();
	}

}
