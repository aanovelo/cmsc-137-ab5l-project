package amogus.io;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class red_player extends Amogus {

	//Image of the Enemy Amogi
	private Image red_player_image = new Image("images/red-player-1.png", this.currentSize, this.currentSize, false, false);

	//The Timer of the Enemy Amogi (used for randomized movement and border checking)
	private GameTimer gTimer;

	// Randomized movement (0-3)
	private int direction;

	// AmogusEnemy Constructor
	public red_player(int x, int y, GameTimer gTimer){
		super(x,y,gTimer);
		this.alive = true;
		this.gTimer = gTimer;
		Random r = new Random();
		this.direction = r.nextInt(4);
		this.loadImage(red_player_image);
		this.currentSize = Amogus.AMOGUS_SIZE;
		this.width = this.currentSize;
		this.height = this.currentSize;
	}

	//method that changes the x position of the enemy amogus
	//Makes sure that enemy amogis are within bounds
	void move(){

        int move = this.direction;

        if(move==0) {

            setDY(-(120/this.getMySize()));
        }

        if(move==1) {


            setDX(-(120/this.getMySize()));
        }

        if(move==2) {

            setDY( (120/this.getMySize()) );
        }

        if(move==3) {

            setDX( (120/this.getMySize()) );
        }

        if (  ( ((this.x + this.dx) <  gTimer.rightLimit) && ((this.x + this.dx) > gTimer.leftLimit ) ) && ( ((this.y + this.dy) > gTimer.upLimit && (this.y + this.dy) < gTimer.downLimit))){
            this.x += this.dx;
            this.y += this.dy;
        }
        else{
            Random r = new Random();
            int decider = r.nextInt(4);
            if(decider == 0){
                this.x = 1200;
                this.y = -1200;
            }
            if(decider == 1){
                this.x = -1200;
                this.y = 1200;
            }
            if(decider == 2){
                this.x = -1200;
                this.y = -1200;
            }
            if(decider == 3){
                this.x = 1200;
                this.y = 1200;
            }
        }
    }

	// Updates currSize, idealsize, and the image of the enemy amogi
	void increaseSize(Amogus amogus) {
		amogus.addSize(this.currentSize, 1);
		amogus.expandAmogus();
	}

	//Checks Collisions between the player and the enemy amogi
	void checkCollision(blue_player player){

		if(this.collidesWith(player) && (this.getMySize() > player.getMySize())){
			player.setAlive(false);
		} else if (this.collidesWith(player) && (this.getMySize() < player.getMySize())){
			this.alive = false;
			this.vanish();
			this.addBlob(player);
			this.increaseSize(player);
		}
	}

	// sets the direction that the enemy amogi will take
	void setDirection(){
		Random r = new Random();
		this.direction = r.nextInt(4);
	}

	//Updates the player score if eaten
	void addBlob(blue_player player) {
		player.setBlobCount();
	}

	// updates the image, allowing the image to get bigger
	@Override
	void expandAmogus() {
		this.red_player_image = new Image("images/Amogus_enemy.gif", this.currentSize, this.currentSize, false, false);
		this.x -= 10;
		this.y -= 10;
	}

	//renders the enemy amogi every nanosecond.
	@Override
	//method to set the image to the image view node
	void render(GraphicsContext gc) {
		gc.drawImage(this.red_player_image, this.x, this.y);
    }
}
