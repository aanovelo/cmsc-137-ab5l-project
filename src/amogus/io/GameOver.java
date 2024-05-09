package amogus.io;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class GameOver {
	private Scene scene;

	public GameOver(int food, int blobs, int size, int time, int amogiCount){

		Canvas canvas = new Canvas(GameStage.WINDOW_HEIGHT, GameStage.WINDOW_WIDTH);
		StackPane stackPane = new StackPane();

		// game over background image if the player lost.
		Image lose = new Image("images/GameOver.png", GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT, false, true);

		// game over background image if the player won / survived.
		Image win = new Image("images/GameOver_victory.png", GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT, false, true);

		ArrayList<Text> stat = new ArrayList<Text>(4);	// will contain the prompts in the game over scene.

		// score prompts.
		Text foodStat = new Text("YOU HAVE FINISHED " + food + " TASK/S!");
		Text blobStat = new Text("YOU HAVE BEATEN " + blobs + " ENEMY AMOGI!");
		Text sizeStat = new Text("YOUR FINAL SIZE IS " + size + "!");
		Text timerStat = new Text("YOUR TIME IS " + time + " SECOND/S!");

		// inserting each prompt inside an arraylist.
		stat.add(foodStat);
		stat.add(blobStat);
		stat.add(sizeStat);
		stat.add(timerStat);

		// adding style to each text.
		stat.get(0).setFont(Font.font(18));
		stat.get(0).setStroke(Color.WHITE);
		stat.get(0).setTranslateX(0);
		stat.get(0).setTranslateY(-10);

		stat.get(1).setFont(Font.font(18));
		stat.get(1).setStroke(Color.WHITE);
		stat.get(1).setTranslateX(0);
		stat.get(1).setTranslateY(20);

		stat.get(2).setFont(Font.font(18));
		stat.get(2).setStroke(Color.WHITE);
		stat.get(2).setTranslateX(0);
		stat.get(2).setTranslateY(50);

		stat.get(3).setFont(Font.font(18));
		stat.get(3).setStroke(Color.WHITE);
		stat.get(3).setTranslateX(0);
		stat.get(3).setTranslateY(80);


		// GIF as background.

		// the did not die during the game.
		if (time == 120 || amogiCount == 0) {
			// different background images for a win and loss.
			BackgroundImage backgroundImage = new BackgroundImage(win,
	                BackgroundRepeat.NO_REPEAT,
	                BackgroundRepeat.NO_REPEAT,
	                BackgroundPosition.DEFAULT,
	                BackgroundSize.DEFAULT);
			Background background = new Background(backgroundImage);
			stackPane.setBackground(background);
		} else {	// the player got eaten by a bigger amogi.
			BackgroundImage backgroundImage = new BackgroundImage(lose,
	                BackgroundRepeat.NO_REPEAT,
	                BackgroundRepeat.NO_REPEAT,
	                BackgroundPosition.DEFAULT,
	                BackgroundSize.DEFAULT);
			Background background = new Background(backgroundImage);
			stackPane.setBackground(background);
		}

		// creating an exit button after playing.
		Button exitButton = new Button("Exit");	// instantiating an exit button.
		exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);	// exit the program
			}
		});

		// for positioning purposes.
		exitButton.setTranslateX(3);
		exitButton.setTranslateY(125);

		// adding stat texts.
		for (int i = 0; i < stat.size(); i++) {
			stackPane.getChildren().add(stat.get(i));
		}

		// adding the canvas and the button.
		stackPane.getChildren().add(canvas);
		stackPane.getChildren().add(exitButton);

		this.scene = new Scene(stackPane, GameStage.WINDOW_HEIGHT, GameStage.WINDOW_WIDTH);
	}

	// returns the game over scene.
	Scene getScene(){
		return this.scene;
	}
}
