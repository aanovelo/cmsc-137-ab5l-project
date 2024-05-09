package amogus.io;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Instructions {

	private Scene scene;
	private GameStage gameStage;

	public Instructions(int windowWidth, Stage stage){

		Canvas canvas = new Canvas(windowWidth, windowWidth);
		StackPane stackPane = new StackPane();
		this.gameStage = new GameStage();

		// this image will serve as the background of this scene.
		Image img = new Image("images/Instruction_about.gif", GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT, false, true);

		// rectangle object that will serve as the container of the instructions of the game.
		Rectangle rect = new Rectangle(100, 100, 600, 630);
		rect.setOpacity(0.9);
		rect.setStroke(Color.ALICEBLUE);

		// creating a header text.
		Text welcome = new Text("Instructions");

		// text style
		welcome.setFont(Font.font(25));
		welcome.setStroke(Color.YELLOW);
		welcome.setTranslateX(4);
		welcome.setTranslateY(-270);

		// text object containing the instructions of the game.
        Text instructions = new Text("# Move your red/blue character:\n"
        		+ "\t\t> W to go up.\n"
        		+ "\t\t> A to go left.\n"
        		+ "\t\t> S to go down.\n"
        		+ "\t\t> D to go right.\n\n"
        		+ "# Push an enemy color:\n"
        		+ "\t\t> Do as many tasks as you can.\n"
        		+ "\t\t> Make sure that you are bigger than your enemy amogi.\n\n"
        		+ "# Tasks:\n"
        		+ "\t\t> Tasks are scroll looking objects that are scattered around the map.\n"
        		+ "\t\t> Colliding to these tasks will increase your size.\n\n"
        		+ "# Win the game:\n"
        		+ "\t\t> Survive the game for 2 minutes or 120 seconds.\n"
        		+ "\t\t> Do not get eaten by enemy amogus.\n"
        		+ "\t\t>Collect power-ups and use them to your advantage.\n"
        		);

        // text style
        instructions.setFont(Font.font(15));
        instructions.setStroke(Color.WHITE);


        // Positioning of the text.
        instructions.setTranslateX(-3);
        instructions.setTranslateY(20);


		// GIF as background.
		BackgroundImage backgroundImage = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
		Background background = new Background(backgroundImage);
		stackPane.setBackground(background);


		// Creating a confirmation button
		Button confButton = new Button("I understand!");	// instantiating a confirmation button.
		confButton.setTranslateX(5);
		confButton.setTranslateY(280);

		confButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// after clicking "I understand" the player will then be redirected to the starting window.
				displayGame(stage);
			}
		});


		// adding the button and canvas to the stackPane.
		stackPane.getChildren().add(rect);
		stackPane.getChildren().add(welcome);
		stackPane.getChildren().add(instructions);
		stackPane.getChildren().add(canvas);
		stackPane.getChildren().add(confButton);

		this.scene = new Scene(stackPane, windowWidth, windowWidth);
	}

	// this method redirects the user back to the starting scene that contains the buttons New Game, Instruction, and About.
	private void displayGame(Stage stage){
		PauseTransition transition = new PauseTransition(Duration.seconds(1));
		transition.play();

		transition.setOnFinished(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				gameStage.setStage(stage);
			}
		});
	}


	// this method returns the instructions scene.
	Scene getScene(){
		return this.scene;
	}

}
