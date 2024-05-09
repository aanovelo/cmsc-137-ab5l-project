package amogus.io;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class blue_player extends Amogus{
	// Name of the player
	private String name;

	// Player location in the map (true X and true Y)
	private Integer playerX;
	private Integer playerY;

	// Power up statuses
	private int hasteMultiplier = 1;		// 1 - normal speed, 2 - boost
	private int invinStatus = 0;			// 0 - vulnerable, 1-invincible

	// Blob score counter
	private int blobCount = 0;

	private GameTimer gTimer;

	// Maintains the player at the center of the screen
	public final static int POS_X = GameStage.GAME_HEIGHT/2;
	public final static int POS_Y = GameStage.GAME_HEIGHT/2;

	// player amogus image
	private Image blue_player_image = new Image("images/blue-player-1.png", this.currentSize, this.currentSize, false, false);

	// Player amogus constructor
	public blue_player(String name, int x, int y, GameTimer gt){
		super(x,y,gt);
		this.playerX = x;
		this.playerY = y;
		this.name = name;
		this.gTimer = gt;
		// this.currentSize = Amogus.AMOGUS_SIZE;
		this.width = this.currentSize;
		this.height = this.currentSize;
		this.loadImage(blue_player_image);
	}

	String getName(){
		return this.name;
	}

	// Player Amogus' movement
	// Allows the adjustments of other sprites location with relation to the player
	void move() {
		gTimer.denyAdjustHoriz();
		gTimer.denyAdjustVert();
		// move the Amogus as long as its position is within the bounds of the scene.
		if (  ( ((this.x + this.dx) <  gTimer.rightLimit) && ((this.x + this.dx) > gTimer.leftLimit ) ) && ( ((this.y + this.dy) > gTimer.upLimit && (this.y + this.dy) < gTimer.downLimit))){

			this.playerX += this.dx;
			this.playerY += this.dy;
			gTimer.setLeftRightBorderForAmogi(this.dx);
			gTimer.setUpDownBorderForAmogi(this.dy);

			gTimer.allowAdjustHoriz();
			gTimer.allowAdjustVert();
		}
		else {
			gTimer.denyAdjustHoriz();
			gTimer.denyAdjustVert();
		}

	}

	// Ends the game
	void die(GameStage stage){
    	this.alive = false;
    }

	// Gets the True X and Y of the Player
	int getPlayerX(){
		return this.playerX;
	}

	int getPlayerY(){
		return this.playerY;
	}

	// Toggles powerups on/off
	void setHasteStat(){

		if(this.hasteMultiplier == 1){
			this.hasteMultiplier = 2;
		}
		else{
			this.hasteMultiplier = 1;
		}
	}

	void setInvinStat(){

		if(this.invinStatus == 0){
			this.invinStatus = 1;
		}
		else{
			this.invinStatus = 0;
		}

	}

	// Gets the current status of the amogi ( if it has powerups activated)
	int getHasteMult(){
		return this.hasteMultiplier;
	}

	int getInvinStat(){
		return this.invinStatus;
	}

	// gets the task and blob count of the player (scores)
	int getTaskCount() {
		return this.taskCount;
	}

	int getBlobCount() {
		return this.blobCount;
	}

	// increments blobcount
	void setBlobCount() {
		this.blobCount++;
	}

	// Sets the player's alive status
	void setAlive(Boolean bool) {
		this.alive = bool;
	}

	// updates the image, allowing the image to get bigger
	@Override
	void expandAmogus() {
		this.blue_player_image = new Image("images/Amogus.gif", this.currentSize, this.currentSize, false, false);
		this.x -= 10;
		this.y -= 10;
	}

	//renders the player amogi every nanosecond.
	@Override
	//method to set the image to the image view node
	void render(GraphicsContext gc) {
		gc.drawImage(this.blue_player_image, this.x, this.y);
    }
}


