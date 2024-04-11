package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	@Override
	public void start(Stage stage){
		GameStage theGameStage = new GameStage();
		Image gameIcon = new Image("application/sprites/blue-player-1.png");
    	stage.getIcons().add(gameIcon);
		theGameStage.setStage(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
