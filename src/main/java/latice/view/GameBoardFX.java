package latice.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameBoardFX extends Application {

	public static void main(String[] args) {
		launch(args);

	}
 
	@Override
	public void start(Stage primaryStage) throws Exception {

	    FXMLLoader loader =
	            new FXMLLoader(getClass().getResource("StartMenu.fxml"));

	    Parent root = loader.load();

	    Scene scene = new Scene(root);
	    
	    Image icon = new Image(getClass().getResource("/latice/assets/icon.png").toExternalForm());
	    primaryStage.getIcons().add(icon);

	    primaryStage.setTitle("Latice");
	    primaryStage.setScene(scene);
	    primaryStage.setResizable(false);
	    primaryStage.sizeToScene();
	    primaryStage.centerOnScreen();
	    primaryStage.show();
	}
}