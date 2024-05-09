package amogus.io;

import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Invincibility extends PowerUps{


	// Initialization of the haste image
	public static final Image INVIN_IMAGE = new Image("images/Invincibility_Spell.png", PowerUps.CONSUMABLE_SIZE, PowerUps.CONSUMABLE_SIZE, false, false);

	// spawn status ( 0-if they are set to DESPAWN, 1 - if they are set to SPAWN)
	private int invinSpawnStatus;

	//Invincibility constructor
	public Invincibility(int xPos, int yPos) {
		super(xPos, yPos);
		this.loadImage(INVIN_IMAGE);
		this.invinSpawnStatus = 0;
	}

	// Allows the Amogus player to be invincible
	// Turns off after a 5 second delay
	@Override
	void effect(blue_player player) {
		if(this.invinSpawnStatus == 0){
			return;
		}
		else{
			player.setInvinStat();
			PauseTransition delay = new PauseTransition(Duration.seconds(PowerUps.DURATION));
		      delay.setOnFinished(event1 -> {
		    	  player.setInvinStat();
		      });
		      delay.play();
		}
	}

	// checks if the haste instance was eaten
	Boolean isEaten() {
		return this.eaten;
	}


	// Checks collision with player, if invinSpawnStatus = 0 it cannot be eaten
	// else, it can be eaten by the player
	// it applies the effect() if successfully eaten
	void checkCollision(blue_player player){
		if(this.invinSpawnStatus == 0){
			return;
		}
		 if (this.collidesWith(player)){

			this.eaten = true;
			this.visible = false;
			this.effect(player);
			this.vanish();
		}
	}

	// Movement for the adjustment with respect to the player
	void move() {
		// move the Amogus as long as its position is within the bounds of the scene.
		this.x += this.dx;
		this.y += this.dy;

	}

	// Toggles the spawn status of the invincibility spells
	void setSpawnStatus(){
		if(this.invinSpawnStatus == 1){
			this.invinSpawnStatus = 0;
		}
		else{
			this.invinSpawnStatus = 1;
		}
	}

	// gets the spawn status of the invincibility
	public int getSpawnStatus(){
		return this.invinSpawnStatus;
	}



}
