package amogus.io;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameStage {

	private Scene gameScene;
	private Scene splashScene;
	private Stage stage;
	private Group root;
	private Canvas canvas;

	public static final int WINDOW_HEIGHT = 800;		// height of the game window.
	public static final int WINDOW_WIDTH = 800;			// width of the game window.
	public static final int GAME_HEIGHT = 2400;			// height of the whole game.
	public static final int GAME_WIDTH = 2400;			// width of the whole game.

	//the class constructor
	public GameStage() {
		this.root = new Group();
		this.canvas = new Canvas(WINDOW_HEIGHT,WINDOW_WIDTH);
		this.root.getChildren().add(this.canvas);
		this.gameScene = new Scene(root);
	}


	//method to add the stage elements
	public void setStage(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("Pushover");		// name of the game.

		this.initSplash(stage);					// initializes the splash scene.

		stage.setScene(this.splashScene);
		stage.setResizable(false);
		this.stage.show();
	}

	private void initSplash(Stage stage) {

		StackPane root = new StackPane();
		Image img = new Image("images/welcome-screen.png", GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT, false, true);
		Rectangle rect = new Rectangle(100, 100, 400, 200);
		rect.setOpacity(0.5);

		// GIF as background.
		BackgroundImage backgroundImage = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
		Background background = new Background(backgroundImage);

		// adding the background image to the root.
		root.setBackground(background);

		// adding the canvas and the vbox to the root.
        root.getChildren().addAll(rect, this.createCanvas(),this.createVBox(stage));

        this.splashScene = new Scene(root);
	}


	// this method returns the created canvas.
	private Canvas createCanvas() {
    	Canvas canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
        return canvas;
    }


	// this method returns a vbox containing the buttons for the Game, About, and Instructions.
	private VBox createVBox(Stage stage) {
    	VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Button b1 = new Button("New Game");
        Button b2 = new Button("Instructions");
        Button b3 = new Button("About");

        vbox.getChildren().add(b1);
        vbox.getChildren().add(b2);
        vbox.getChildren().add(b3);

        // if the user clicks the New Game button.
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setGame(stage);		// changes the scene into the game scene
            }
        });

        // if the user clicks the Instructions button.
        b2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                displayInstructions(stage);		// changes the scene into the game scene
            }
        });

        // if the user clicks the about button.
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                displayAbout(stage);		// changes the scene into the game scene
            }
        });
        return vbox;
    }

	// this method displays the scene that contains the instructions of the game.
	private void displayInstructions(Stage stage){
		PauseTransition transition = new PauseTransition(Duration.seconds(1));
		transition.play();
		transition.setOnFinished(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				Instructions instruct = new Instructions(WINDOW_WIDTH, stage);
				stage.setScene(instruct.getScene());
			}
		});
	}


	// this method displays the scene that contains the information about the developers of the game.
	private void displayAbout(Stage stage) {
		PauseTransition transition = new PauseTransition(Duration.seconds(1));
		transition.play();
		transition.setOnFinished(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				About about = new About(WINDOW_WIDTH, stage);
				stage.setScene(about.getScene());
			}
		});
	}


	// this method starts the actual game.
	void setGame(Stage stage) {
        stage.setScene( this.gameScene );
        GraphicsContext gc = this.canvas.getGraphicsContext2D();	// we will pass this gc to be able to draw on this Game's canvas

        GameTimer gameTimer = new GameTimer(gc, gameScene, this);
        gameTimer.start();			// this internally calls the handle() method of our GameTimer
	}
}

