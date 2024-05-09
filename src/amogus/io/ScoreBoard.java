package amogus.io;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreBoard {

	private GraphicsContext gc;				// will be used to create the score board.
	private int starting_y_outer = 720;		// for positioning purposes.

	public ScoreBoard(GraphicsContext gc) {
		this.gc = gc;
	}

	// this method creates a rectangle that will serve as the container of the stats of the player.
	void createScoreBoard() {
		this.gc.setFill(Color.AZURE);
		this.gc.fillRect(GameTimer.OUTER_RECT_X, this.starting_y_outer, GameTimer.BAR_WIDTH_LIMIT, GameTimer.OUTER_HEIGHT);
		this.gc.setStroke(Color.BLACK);
		this.gc.strokeRect(GameTimer.OUTER_RECT_X, this.starting_y_outer, GameTimer.BAR_WIDTH_LIMIT, GameTimer.OUTER_HEIGHT);
	}


	// this method updates the scores on the score board.
	void updateScore(int task, int blob, int size, int time) {

		// for text style.
		this.gc.setFont(new Font("Verdana", 25));
		this.gc.setFill(Color.YELLOW);
		this.gc.setStroke(Color.BLACK);
		this.gc.setLineWidth(2);

		// changing the scores.
		this.gc.strokeText("Task: " + task, 100, GameTimer.TEXT_Y);
		this.gc.strokeText("Amogi: " + blob, 250, GameTimer.TEXT_Y);
		this.gc.strokeText("Size: " + size, 400, GameTimer.TEXT_Y);
		this.gc.strokeText("Time: " + time, 550, GameTimer.TEXT_Y);
		this.gc.fillText("Task: " + task, 100, GameTimer.TEXT_Y);
		this.gc.fillText("Amogi: " + blob, 250, GameTimer.TEXT_Y);
		this.gc.fillText("Size: " + size, 400, GameTimer.TEXT_Y);
		this.gc.fillText("Time: " + time, 550, GameTimer.TEXT_Y);
	}
}
