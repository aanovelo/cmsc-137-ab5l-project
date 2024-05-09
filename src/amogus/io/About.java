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

public class About {
	private Scene scene;
	private GameStage gameStage = new GameStage();

	public About(int windowWidth, Stage stage){

		Canvas canvas = new Canvas(windowWidth, windowWidth);
		StackPane stackPane = new StackPane();

		// will be used as the background of this scene.
		Image img = new Image("images/Instruction_about.gif", GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT, false, true);

		// will serve as the container of the texts.
		Rectangle rect = new Rectangle(100, 100, 720, 720);
		rect.setOpacity(0.9);
		rect.setStroke(Color.ALICEBLUE);

		// Start of information about the game's developers.
		Text welcome = new Text("About the developers of Pushover");
		welcome.setFont(Font.font(25));
		welcome.setStroke(Color.YELLOW);
		welcome.setTranslateX(4);
		welcome.setTranslateY(-330);


		// text object containing the information about the developers of the game.
        Text authors = new Text("Name: Martinez, Harvey Lloyd A.\n"
        		+ "Student Number: 2021-02039\n"
        		+ "Section: AB5L\n"
        		+ "Motto in Coding: Pag gumana, wag na galawin.\n\n"
        		+"Name: Novelo, Aljon.\n"
        		+"Student Number: 2021 - \n"
        		+"Section: AB5L\n"
        		+"Motto in Coding: \n\n"
				+"Name: Villanueva, Andrei R.\n"
        		+"Student Number: 2021 - 07500\n"
        		+"Section: AB5L\n"
        		+"Motto in Coding: Trial, Error, StackOverflow, and Indian programmer youtuber\n\n"
        		);

        authors.setFont(Font.font(15));
        authors.setStroke(Color.WHITE);
        authors.setTranslateX(-50);
        authors.setTranslateY(-100);

        // Start of references
        Text refTitle = new Text("References");
        refTitle.setFont(Font.font(25));
        refTitle.setStroke(Color.YELLOW);
        refTitle.setTranslateX(4);
        refTitle.setTranslateY(100);

        // text object containing the references used in this mini project.
        Text references = new Text(""
        		);

        references.setFont(Font.font(15));
        references.setStroke(Color.WHITE);

        // Position the text within the rectangle
        references.setTranslateX(0);
        references.setTranslateY(120);

		// GIF as background.
		BackgroundImage backgroundImage = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
		Background background = new Background(backgroundImage);
		stackPane.setBackground(background);


		// Creating a confirmation button
		Button confButton = new Button("Let's go!");	// instantiating a confirmation button.
		confButton.setTranslateX(5);
		confButton.setTranslateY(320);

		confButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// after clicking "I understand" the player will then be redirected to the starting window.
				displayGame(stage);
			}
		});

		// adding the button, texts, and canvas to the stackPane.
		stackPane.getChildren().add(rect);
		stackPane.getChildren().add(welcome);
		stackPane.getChildren().add(authors);
		stackPane.getChildren().add(refTitle);
		stackPane.getChildren().add(references);
		stackPane.getChildren().add(canvas);
		stackPane.getChildren().add(confButton);

		this.scene = new Scene(stackPane, windowWidth, windowWidth);
	}

	// this method displays the original splash screen. (From about scene to the splash screen).
	private void displayGame(Stage stage){
		PauseTransition transition = new PauseTransition(Duration.seconds(1));
		transition.play();
		transition.setOnFinished(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				gameStage.setStage(stage);
			}
		});
	}


	// returns the about scene.
	Scene getScene(){
		return this.scene;
	}
}
