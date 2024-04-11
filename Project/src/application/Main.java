package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 1800, 950, Color.ALICEBLUE);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Image gameIcon = new Image("application/sprites/blue-player-1.png");
			primaryStage.setTitle("Pushover");
			primaryStage.getIcons().add(gameIcon);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
