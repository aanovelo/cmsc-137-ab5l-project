package application;

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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameStage {

    private Scene gameScene;
    private Scene splashScene;
    private Stage stage;
    private Group root;
    private Canvas canvas;
    private Image icon;

    public static final int WINDOW_HEIGHT = 800;    // height of the game window.
    public static final int WINDOW_WIDTH = 1000;    // width of the game window.

    // the class constructor
    public GameStage() {
        this.root = new Group();
        this.canvas = new Canvas(WINDOW_WIDTH,WINDOW_HEIGHT);
		this.root.getChildren().add(this.canvas);
		this.gameScene = new Scene(root);
    }

    // method to add the stage elements
    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("Pushout");    // name of the game.
        this.initSplash(stage);					// initializes the splash scene.
        stage.setScene(this.splashScene);
        stage.setResizable(false);
        this.stage.show();
    }

    private void initSplash(Stage stage) {

		StackPane root = new StackPane();
		Image img = new Image("application/sprites/welcome-screen.png", 600, 600, false, true);
		Rectangle rect = new Rectangle(100, 100, WINDOW_WIDTH,WINDOW_HEIGHT);
		rect.setOpacity(0);

		// GIF as background.
		BackgroundImage backgroundImage = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
		Background background = new Background(backgroundImage);

		// adding the background image to the root.
		root.setBackground(background);

		// adding the canvas and the vbox to the root.
        root.getChildren().addAll(rect, this.createVBox(stage));

        this.splashScene = new Scene(root, Color.ALICEBLUE);
	}


    // Modified addImage method
    public void addImage(String imagePath, int x, int y, int width, int height) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitWidth(width); // Set the width of the image
        imageView.setFitHeight(height); // Set the height of the image
        root.getChildren().add(imageView);
    }

    // this method returns a vbox containing the buttons for the Game, About, and Instructions.
 	private VBox createVBox(Stage stage) {
     	VBox vbox = new VBox();
         vbox.setAlignment(Pos.BOTTOM_CENTER);
         vbox.setPadding(new Insets(10));
         vbox.setSpacing(8);

         Button b1 = new Button("New Game");
//         Button b2 = new Button("Instructions");
//         Button b3 = new Button("About");

         vbox.getChildren().add(b1);
//         vbox.getChildren().add(b2);
//         vbox.getChildren().add(b3);

         // if the user clicks the New Game button.
         b1.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent e) {
                 setGame(stage);		// changes the scene into the game scene
             }
         });

//         // if the user clicks the Instructions button.
//         b2.setOnMouseClicked(new EventHandler<MouseEvent>() {
//             @Override
//             public void handle(MouseEvent e) {
//                 displayInstructions(stage);		// changes the scene into the game scene
//             }
//         });
//
//         // if the user clicks the about button.
//         b3.setOnAction(new EventHandler<ActionEvent>() {
//             @Override
//             public void handle(ActionEvent e) {
//                 displayAbout(stage);		// changes the scene into the game scene
//             }
//         });
         return vbox;
     }

 	// this method starts the actual game.
 	void setGame(Stage stage) {
         stage.setScene( this.gameScene );
 	}
}
