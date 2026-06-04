package latice.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class StartMenuController {
	
    private static MediaPlayer backgroundMusic;
    
   	@FXML
	private TextField idPlayer1Field;

	@FXML
	private TextField idPlayer2Field;
	
	@FXML
	private VBox idMainForm;
	
	@FXML
	private VBox idRulesPane;
	
	@FXML
	public void initialize() {
        String path = getClass()
                .getResource("/latice/soundFX/backgroundSound.mp3")
                .toExternalForm();

            Media media = new Media(path);
            backgroundMusic = new MediaPlayer(media);

            backgroundMusic.setVolume(0.50);
            backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundMusic.play();
	}
	
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
	    idRulesPane.setVisible(true);
	    idMainForm.setOpacity(0.3);
	    idMainForm.setDisable(true);
	}
	
	@FXML
	private void handleCloseRules() {
	    idRulesPane.setVisible(false);
	    idMainForm.setOpacity(1.0);
	    idMainForm.setDisable(false);
	}
	
	@FXML
	private void handleQuit() {
	    javafx.application.Platform.exit();
	    System.exit(0);
	}
	
}
