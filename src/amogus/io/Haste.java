package amogus.io;

import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Haste extends PowerUps {

	// Initialization of the haste image
	public static final Image HASTE_IMAGE = new Image("images/Haste_Spell.png", PowerUps.CONSUMABLE_SIZE, PowerUps.CONSUMABLE_SIZE, false, false);

	// spawn status ( 0-if they are set to DESPAWN, 1 - if they are set to SPAWN)
	public int hasteSpawnStatus;

	// Haste Constructor
	public Haste(int xPos, int yPos) {
		super(xPos, yPos);
		this.hasteSpawnStatus = 0;
		this.loadImage(HASTE_IMAGE);
	}

	// Allows the Amogus player to double their speed
	// Turns off after a 5 second delay
	@Override
	void effect(blue_player player) {
		if(this.hasteSpawnStatus == 0){
			return;
		}
		else{
			player.setHasteStat();
			PauseTransition delay = new PauseTransition(Duration.seconds(PowerUps.DURATION));
		      delay.setOnFinished(event1 -> {
		    	  player.setHasteStat();

		      });
		      delay.play();
		}
	}

	// Movement for the adjustment with respect to the player
	void move() {
		// move the Amogus as long as its position is within the bounds of the scene.
		this.x += this.dx;
		this.y += this.dy;

	}

	// checks if the haste instance was eaten
	Boolean isEaten() {
		return this.eaten;
	}

	// Checks collision with player, if hasteSpawnStatus = 0 it cannot be eaten
	// else, it can be eaten by the player
	// it applies the effect() if successfully eaten
	void checkCollision(blue_player player){

		if(this.hasteSpawnStatus == 0){
			return;
		}
		 if (this.collidesWith(player)){

			this.eaten = true;
			this.visible = false;
			this.effect(player);
			this.vanish();
		}
	}

	// Toggles the spawn status of the haste spells
	void setSpawnStatus(){
		if(this.hasteSpawnStatus == 1){
			this.hasteSpawnStatus = 0;
		}
		else{
			this.hasteSpawnStatus = 1;
		}
	}

	// gets the spawn status of the haste
	int getSpawnStatus(){
		return this.hasteSpawnStatus;
	}
}
