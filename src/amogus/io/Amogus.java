package amogus.io;

import javafx.scene.canvas.GraphicsContext;

public class Amogus extends Sprite{

	public static int AMOGUS_SIZE = 40;	// default size of each amogus.

	protected Boolean alive;			// will determine if the amogus is alive or not.
	protected int currentSize = 40;		// for elimination purposes.
	protected int taskCount = 0;		// will keep track of number of tasks done by the amogus.
	protected int idealSize = 50;		// for movement purposes.
	protected GraphicsContext gc;		// will be used to redraw the amogus (expansion of amogus).


	// Constructs an instance of an Amogus
	public Amogus(int xPos, int yPos, GameTimer gTimer) {
		super(xPos, yPos);
		this.alive = true;
		this.gc = gTimer.getGC();
	}

	// will be overriden in AmogusPlayer and AmogusEnemy classes to expand the amogi.
	void expandAmogus() {
		System.out.println(">>> Expand sprite");
	}

	//Checks if the Amogus Instance is alive
	Boolean isAlive() {
		return this.alive;
	}

	// Adds int to the amogi's current size and ideal size
	// will also update height and width of the image
	void addSize(int added, int added1){
		this.currentSize += added;
		this.width += added;
		this.height += added;
		this.idealSize += added1;
	}

	// Gets the Ideal size
	int getMySize(){
		return this.idealSize;
	}

	// Increments the task count of the amogus instance
	void setTaskCount() {
		this.taskCount++;
	}

	// Gets the current size
	int getCurrentSize() {
		return this.currentSize;
	}
}
