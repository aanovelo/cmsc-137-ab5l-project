package amogus.io;

public class PowerUps extends Sprite {

	// Final ints for the powerups
	public static final int DURATION = 5;						// Effect duration
	public static final int CONSUMABLE_SIZE = 20;				// image size
	public static final int POWER_UP_APPEARANCE_INTERVAL = 10;	// Appearance interval
	public static final int POWER_UP_DISAPPEARANCE_DELAY = 5;	// disappearance delay

	// checks status if eaten
	protected Boolean eaten;

	// Power up constructor
	public PowerUps(int xPos, int yPos) {
		super(xPos, yPos);
	}

	// checks of the instance was eaten
	Boolean isEaten() {
		if (this.eaten == true) return true;
		else return false;
	}

	// Toggles the powerup effect on the player and toggles off after 5 seconds
	void effect(blue_player player) {
		System.out.println(">>> PowerUp eaten!");
	}

	// Changes X and Y values to randomize the spawn location
	void setX(int newX){
		this.x = newX;
	}

	void setY(int newY){
		this.y = newY;
	}
}
