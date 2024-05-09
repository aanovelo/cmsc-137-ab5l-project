package amogus.io;

import javafx.scene.image.Image;

public class Task extends Sprite {

	// Task sprite size
	public static final int TASK_SIZE = 20;
	// Task Image
	public final static Image TASK_IMAGE = new Image("images/Task.png",Task.TASK_SIZE,Task.TASK_SIZE,false,false);
	// eaten status of the task
	private Boolean eaten;

	// Task Constructor
	public Task(int xPos, int yPos) {
		super(xPos, yPos);
		this.loadImage(TASK_IMAGE);
	}

	// this method increases the size of amogus that completes/eats this task/food.
	void increaseSize(Amogus amogus) {
		amogus.addSize(10, 1);
		amogus.expandAmogus();
	}

	// this method determines whether this food / task is already eaten / completed.
	Boolean isEaten() {
		return this.eaten;
	}

	// Checks if this task collided with a Amogus instance
	void checkCollision(Amogus amogus){
		 if (this.collidesWith(amogus)){

				this.eaten = true;
				this.visible = false;
				increaseSize(amogus);
				this.vanish();
				this.addTask(amogus);
		}
	}

	// Movement for the adjustment with respect to the player
	void move() {
		// move the Amogus as long as its position is within the bounds of the scene.
		this.x += this.dx;
		this.y += this.dy;

	}

	// Adds task count to the player (score)
	void addTask(Amogus player) {
		player.setTaskCount();
	}

}
