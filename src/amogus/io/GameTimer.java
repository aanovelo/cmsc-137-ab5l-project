package amogus.io;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

/*
 * The GameTimer is a subclass of the AnimationTimer class. It must override the handle method.
 */

public class GameTimer extends AnimationTimer{

	private GameStage gameStage;
	private GraphicsContext gc;
	private Scene theScene;
	private blue_player myAmogi;
	private ScoreBoard scoreBoard;
	private ArrayList<red_player> amogi;				// list of enemy amogi spawned
	private ArrayList<Task> taskList;					// list of tasks spawned
	private ArrayList<Haste> haste;						// list of haste spawned
	private ArrayList<Invincibility> invincibility;		// list of invincibility spawned
	private int enemyChangeD;							// int for enemy hange in direction
	private int spriteAdjustHoriz = 0;					// allows/disables other sprite adjust in x axis
	private int spriteAdjustVert = 0;					// allows/disables other sprite adjust in y axis
	private int time = 0;								// will take account of the player's elapsed time.


	public static final int MAX_NUM_ENEMY = 10;			// Final int of max no of enemies
	public static final int MAX_TASK = 50;				// Final int of max no of task in map
	public static final int MAX_HASTE = 2;				// Final int of max no of haste spell
	public static final int MAX_INVIN = 1;				// Final int of max no of invincibility spell

	public static final int BAR_WIDTH_LIMIT = 610;		// default width of the rectangle in score board class.
	public static final int OUTER_RECT_X = 85;			// default x position of the rectangle in score board class.
	public static final int OUTER_HEIGHT = 60;			// default height of the rectangle in Score board class.
	public static final int TEXT_Y = 758;				// default y position of texts in the Score board class.
	public static final int GAME_DURATION_SECONDS = 120;	// Duration of each game (in seconds)

	public  int rightLimit = 2500;						// right border
	public int leftLimit = 0;							// left border
	public int upLimit = 0;								// up border
	public int downLimit = 2500;						// down border


	// Stage image
	private Image background = new Image("images/game_background.jpg", GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT, false, true);


	GameTimer(GraphicsContext gc, Scene theScene, GameStage stage){

		this.gc = gc;
		this.theScene = theScene;
		this.gameStage = stage;

		//Instantiation of the player
		this.myAmogi = new blue_player("Amogusus",379,369, this);
		this.scoreBoard = new ScoreBoard(this.gc);

		//instantiate the ArrayList of enemies, tasks and powerups
		this.amogi = new ArrayList<red_player>();
		this.taskList = new ArrayList<Task>();
		this.haste = new ArrayList<Haste>();
		this.invincibility = new ArrayList<Invincibility>();

		//instantiation of the first durations of the enemy direction movement
		Random random = new Random();
		this.enemyChangeD = random.nextInt(4);

		// Instantiates and Spawns other sprites
		this.spawnEnemy();
		// this.spawnTask();
		// this.spawnHaste();
		// this.spawnInvincibility();

		// Instatiation of the Timelines(timer) that handles appearance and disappearances of the powerups
		// this.hasteTick();
		// this.invincibilityTick();

		// Instantiation of the timer of the whole game (2 minutes)
		// this.gameOverTick();

		// Instantiation of the timer of the player.
		this.timerTick();

		// Instatiation of the Timelines(timer) in which the duration and direction of enemy movement is randomized
		this.enemyMovementTick();

		//call method to handle mouse click event
		this.handleKeyPressEvent();
	}



	@Override
	public void handle(long currentNanoTime) {
		//Allows for the movement of the player
		this.myAmogi.move();

		//Redraws the background images to adjust for the player
		redrawBackgroundImage(this.myAmogi.getPlayerX(), this.myAmogi.getPlayerY());

		//This makes the enemies amogi move.
		// this.moveEnemy(this.myAmogi, this.amogi);

		//Redraws the sprites images to adjust for the player
		this.nonMovingSpriteUpdate();

		//renders the scoreboard
		// this.scoreBoard.createScoreBoard();
		// this.scoreBoard.updateScore(this.myAmogi.getTaskCount(), this.myAmogi.getBlobCount(), this.myAmogi.getCurrentSize(), this.time);;

		// Render funtions shows the illusion of movement

		// renders the player amogi.
		this.myAmogi.render(this.gc);

		// this.renderEnemy();				// renders the enemy amogi.
		// this.renderTask();				// renders the tasks.
		// this.renderHaste();				// renders the haste power-up.
		// this.renderInvincibility();		// renders the invincibility power-up.

		// ========== check collision of the different sprites ============
		for(red_player f: this.amogi){
			// makes sure that Invin does not stack
			if(this.myAmogi.getInvinStat() == 0){
				f.checkCollision(this.myAmogi);
			}
		}

		// for(Task t: this.taskList){
		// 	t.checkCollision(this.myAmogi);

		// 	for(red_player enemy : this.amogi){
		// 		t.checkCollision(enemy);
		// 	}
		// }

		// for (Haste h: this.haste) {
		// 	// makes sure that haste does not stack
		// 	if(this.myAmogi.getHasteMult() == 1){
		// 		h.checkCollision(this.myAmogi);
		// 	}
		// }

		// for (Invincibility i: this.invincibility) {
		// 	i.checkCollision(this.myAmogi);
		// }

		// Ends the game after the player amogi is eaten
		// if(this.myAmogi.isAlive() == false) {
		// 	this.myAmogi.die(gameStage);
		// 	this.stop();
		// }
	}



// -================================== RENDER FUNCTIONS ==================================-
//	=-> allows for the illusion of movement via instantaneous updating
// -========================= RENDER FUNTIONS =========================-

	//method that will render/draw the enemies to the canvas
	// private void renderEnemy() {
	// 	for (red_player f : this.amogi){
	// 		f.render(this.gc);
	// 	}
	// }

	// //method that will render/draw the tasks to the canvas
	// private void renderTask	() {
	// 	// To make sure that the amount of task is == 50
	// 	if(this.taskList.size() < GameTimer.MAX_TASK){
	// 		Random r = new Random();
	// 		int x = r.nextInt(GameStage.GAME_WIDTH);
	// 		int y = r.nextInt(GameStage.GAME_HEIGHT);
	// 		Task repTask = new Task(x,y);
	// 		this.taskList.add(repTask);

	// 	}
	// 	for (Task task : this.taskList){
	// 		task.render(this.gc);

	// 	}
	// }


	// private void renderHaste() {

	// 	for (Haste speed : this.haste){
	// 		// If the spawn status if the haste is 0 (Despawned),
	// 		// adjust accordingly to ensure no collisions with player
	// 		// immediately after spawning
	// 		if(speed.getSpawnStatus() == 0){
	// 			Random r = new Random();
	// 			Random r2 = new Random();
	// 			int x = r.nextInt(400) + this.myAmogi.getX() + 200 - r2.nextInt(800);
	// 	        int y = r.nextInt(400) + this.myAmogi.getY() + 200 - r2.nextInt(800);


	// 	        // makes sure it does not collide with the amogus immediately
	// 	        if( x <= this.myAmogi.getX() + 200 && x >= this.myAmogi.getX() -200){
	// 	        	int decX = r.nextInt(2);
	// 	        	if (decX == 0) {
	// 					x -= 100;
	// 				}
	// 	        	else {
	// 	        		x += 100;
	// 				}

	// 			}
	// 			if( y <= 200 && y >= -200){
	// 				int decY = r.nextInt(2);
	// 	        	if (decY == 0) {
	// 					y -= 100;
	// 				}
	// 	        	else {
	// 	        		y += 100;
	// 				}
	// 			}

	// 			//changes pos every tick
	// 	        speed.setX(x);
	// 			speed.setY(y);

	// 		}
	// 		// Else, adjust accordingly to the player
	// 		else {
	// 			speed.render(this.gc);

	// 		}
	// 	}
	// }

	// private void renderInvincibility() {

	// 	for (Invincibility invin : this.invincibility){
	// 		// If the spawn status if the haste is 0 (Despawned),
	// 		// adjust accordingly to ensure no collisions with player
	// 		// immediately after spawning
	// 		if(invin.getSpawnStatus() == 0){
	// 			Random r = new Random();
	// 			Random r2 = new Random();
	// 			int x = r.nextInt(400) + this.myAmogi.getX() + 200 - r2.nextInt(800);
	// 	        int y = r.nextInt(400) + this.myAmogi.getY() + 200 - r2.nextInt(800);


	// 	        // makes sure it does not collide with the amogus immediately
	// 	        if( x <= this.myAmogi.getX() + 200 && x >= this.myAmogi.getX() -200){
	// 	        	int decX = r.nextInt(2);
	// 	        	if (decX == 0) {
	// 					x -= 100;
	// 				}
	// 	        	else {
	// 	        		x += 100;
	// 				}

	// 			}
	// 			if( y <= 200 && y >= -200){
	// 				int decY = r.nextInt(2);
	// 	        	if (decY == 0) {
	// 					y -= 100;
	// 				}
	// 	        	else {
	// 	        		y += 100;
	// 				}
	// 			}

	// 			//changes pos every tick
	// 	        invin.setX(x);
	// 			invin.setY(y);

	// 		}
	// 		else {
	// 			// Else, adjust accordingly to the player
	// 			invin.render(this.gc);

	// 		}
	// 	}
	// }


//method that will spawn/instantiate enemies at a random x,y location
	// -================================== SPAWN METHODS ==================================-
//		=-> allows for instantiation of the sprites

	private void spawnEnemy(){
		Random r = new Random();
		for(int i=0;i<GameTimer.MAX_NUM_ENEMY;i++){
			int x = r.nextInt(GameStage.GAME_WIDTH);
			int y = r.nextInt(GameStage.GAME_HEIGHT);
			red_player newEnemy = new red_player(x, y, this);
			this.amogi.add(newEnemy);
		}
	}

// method that will spawn / instantiate tasks at random location.

	// private void spawnTask() {
	// 	Random r = new Random();
	// 	for (int i = 0; i < GameTimer.MAX_TASK; i++) {
	// 		int x = r.nextInt(GameStage.GAME_WIDTH);
	// 		int y = r.nextInt(GameStage.GAME_HEIGHT);
	// 		Task task = new Task(x,y);
	// 		this.taskList.add(task);
	// 	}
	// }

	// private void spawnHaste() {
	// 	Random r = new Random();
	// 	Random r2 = new Random();
	// 	for (int i = 0; i < GameTimer.MAX_HASTE; i++) {
	// 		int x = r.nextInt(400) + this.myAmogi.getX() + 200 - r2.nextInt(800);
	//         int y = r.nextInt(400) + this.myAmogi.getY() + 200 - r2.nextInt(800);
	//         if( x <= this.myAmogi.getX() + 200 && x >= this.myAmogi.getX() -200){
	//         	int decX = r.nextInt(2);
	//         	if (decX == 0) {
	// 				x -= 100;
	// 			}
	//         	else {
	//         		x += 100;
	// 			}

	// 		}
	// 		if( y <= 200 && y >= -200){
	// 			int decY = r.nextInt(2);
	//         	if (decY == 0) {
	// 				y -= 100;
	// 			}
	//         	else {
	//         		y += 100;
	// 			}
	// 		}
	//         Haste speed = new Haste(x,y);
	// 		this.haste.add(speed);
	// 	}
	// }

	// private void spawnInvincibility() {
	// 	Random r = new Random();
	// 	Random r2 = new Random();
	// 	for (int i = 0; i < GameTimer.MAX_INVIN; i++) {
	// 		int x = r.nextInt(400) + this.myAmogi.getX() + 200 - r2.nextInt(800);
	//         int y = r.nextInt(400) + this.myAmogi.getY() + 200 - r2.nextInt(800);
	//         if( x <= this.myAmogi.getX() + 200 && x >= this.myAmogi.getX() -200){
	//         	int decX = r.nextInt(2);
	//         	if (decX == 0) {
	// 				x -= 300;
	// 			}
	//         	else {
	//         		x += 300;
	// 			}

	// 		}
	// 		if( y <= 200 && y >= -200){
	// 			int decY = r.nextInt(2);
	//         	if (decY == 0) {
	// 				y -= 300;
	// 			}
	//         	else {
	//         		y += 300;
	// 			}
	// 		}
	//         Invincibility invin = new Invincibility(x,y);
	// 		this.invincibility.add(invin);
	// 	}
	// }

	// -================================== TICK FUNCTIONS ==================================-
	//		=-> allows Timed actions
	// e.g. + Spawning and despawning of powerups
	//		+ random movement and duration of enemy movement

// -========================= TICK FUNTIONS =========================-


	private void enemyMovementTick() {

		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(this.enemyChangeD), event -> {

			// switch directions every tick
		for (red_player enemy : this.amogi){
			enemy.setDirection();
		}
		Random random = new Random();
		this.enemyChangeD = random.nextInt(4);

		}));
	  timeline.setCycleCount(Timeline.INDEFINITE);
	  timeline.play();

	}

	// will count up to 120 seconds (game duration).
	private void gameOverTick() {
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(GameTimer.GAME_DURATION_SECONDS), event -> {
			this.myAmogi.setAlive(false);
		}));
	  timeline.setCycleCount(Timeline.INDEFINITE);
	  timeline.play();

	}

	// will keep track of the time count (in seconds) of the player.
	private void timerTick() {
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
			this.time++;
		}));
	  timeline.setCycleCount(Timeline.INDEFINITE);
	  timeline.play();
	}

// calls the move function of all non moving sprites
// -========================= MOVEMENT UPDATE METHODS =========================-


 	private void nonMovingSpriteUpdate(){
		//Loop through the fishes arraylist
		for(int i = 0; i < this.taskList.size(); i++){
			Task a = this.taskList.get(i);
			if (a.isVisible()) {
				if(this.spriteAdjustHoriz != 1){
					stopSprites();
				}
				if(this.spriteAdjustHoriz != 1){
					stopSprites();
				}
				else{
					a.move();
				}

				a.collidesWith(this.myAmogi);
			} else this.taskList.remove(i);
		}

		for(int j = 0; j < this.haste.size(); j++){
			Haste b = this.haste.get(j);
			if (b.isVisible()) {
				b.move();
				b.collidesWith(this.myAmogi);
			} else this.haste.remove(j);
		}

		for(int k = 0; k < this.invincibility.size(); k++){
			Invincibility c = this.invincibility.get(k);
			if (c.isVisible()) {

				c.move();

				c.collidesWith(this.myAmogi);
			} else this.invincibility.remove(k);
		}
	}


 	// calls the move function for the enemy amogus
 	private void moveEnemy(blue_player myAmogi, ArrayList<red_player> amogi){
		//Loop through the enemy arraylist
		for(int i = 0; i < amogi.size(); i++){
			red_player a = amogi.get(i);
			if(a.isVisible()){
				a.move();
				a.collidesWith(myAmogi);
			} else amogi.remove(i);
		}
	}


 	// Handles User Input
	//method that will listen and handle the key press events
	private void handleKeyPressEvent() {
		this.theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
                moveMyAmogi(code);
			}
		});

		this.theScene.setOnKeyReleased(new EventHandler<KeyEvent>(){
		            public void handle(KeyEvent e){
		            	KeyCode code = e.getCode();
		                stopmyAmogi(code);
		                stopSprites();
		                denyAdjustHoriz();
		                denyAdjustVert();
		            }
		        });
    }


	// Handles Player movement and the corresponding adjustments needed to be made to other sprites
	//method that will move the ship depending on the key pressed
	private void moveMyAmogi(KeyCode ke) {

		if(ke==KeyCode.W) {
			this.myAmogi.setDY(-(120/this.myAmogi.getMySize()) * this.myAmogi.getHasteMult());
			moveOtherSpritesDY((120/this.myAmogi.getMySize()) * this.myAmogi.getHasteMult());
		}

		if(ke==KeyCode.A) {
			this.myAmogi.setDX(-(120/this.myAmogi.getMySize()) * this.myAmogi.getHasteMult());
			moveOtherSpritesDX((120/this.myAmogi.getMySize()) * this.myAmogi.getHasteMult());
		}

		if(ke==KeyCode.S) {
			this.myAmogi.setDY((120/this.myAmogi.getMySize()) * this.myAmogi.getHasteMult());
			moveOtherSpritesDY(-(120/this.myAmogi.getMySize()) * this.myAmogi.getHasteMult());

		}

		if(ke==KeyCode.D) {
			this.myAmogi.setDX((120/this.myAmogi.getMySize()) * this.myAmogi.getHasteMult());
			moveOtherSpritesDX(-(120/this.myAmogi.getMySize()) * this.myAmogi.getHasteMult());
		}

   	}

	//method that will stop the ship's movement; set the ship's DX and DY to 0


	// Stops Player movement
	private void stopmyAmogi(KeyCode ke){
		this.myAmogi.setDX(0);
		this.myAmogi.setDY(0);
	}


	// Adjusts other sprites to the movement of the player
	// to create the illusion of map movement
	//Adjustments to be made resulting from player movement
	private void moveOtherSpritesDX(int changeX){

		if(this.spriteAdjustHoriz == 0){
			stopSprites();
			return;
		}
		else{
			for (red_player a : this.amogi){
				a.setDX(changeX);

			}
			for (Task t : this.taskList){
				t.setDX(changeX);

			}

			for (Haste h : this.haste){
				h.setDX(changeX);

			}

			for (Invincibility i: this.invincibility){
				i.setDX(changeX);

			}
		}


	}

	private void moveOtherSpritesDY(int changeY){
		if(this.spriteAdjustVert == 0){
			stopSprites();
			return;
		}
		else{
			for (red_player a : this.amogi){

				a.setDY(changeY);
			}
			for (Task t : this.taskList){
				t.setDY(changeY);
			}

			for (Haste h : this.haste){

				h.setDY(changeY);
			}

			for (Invincibility i: this.invincibility){
				i.setDY(changeY);
			}
		}


	}


	// Redraws the background to create the illusion of map movement
	void redrawBackgroundImage(int characterX, int characterY) {
		double x = characterX - GameStage.WINDOW_WIDTH / 2;
		double y = characterY - GameStage.WINDOW_HEIGHT / 2;


		// Draw the portion of the image that you want to display
		this.gc.clearRect(0, 0, GameStage.WINDOW_HEIGHT, GameStage.WINDOW_WIDTH);
		this.gc.drawImage(background, x, y, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT, 0, 0, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
	}

	// Stops the movement of other sprites
	private void stopSprites() {
		for (red_player f : this.amogi){
			f.setDX(0);
			f.setDY(0);
		}
		for (Task t : this.taskList){
			t.setDX(0);
			t.setDY(0);
		}
		for (Haste h : this.haste){
			h.setDX(0);
			h.setDY(0);
		}

		for (Invincibility i: this.invincibility){
			i.setDX(0);
			i.setDY(0);
		}
	}


	// Allow/Deny Adjust Horiz/Vert:
	// + In cases when the player hits the border
	// + Adjusts the movement accordingly in cases of border interaction
	void allowAdjustHoriz(){
		this.spriteAdjustHoriz = 1;
	}
	void allowAdjustVert(){
		this.spriteAdjustVert = 1;
	}

    void denyAdjustHoriz(){
		this.spriteAdjustHoriz = 0;
	}
	void denyAdjustVert(){
		this.spriteAdjustVert = 0;
	}

	//-========================= BORDER METHODS =========================-

	void setLeftRightBorderForAmogi(int changeHoriz){
		this.leftLimit -= changeHoriz;
		this.rightLimit -= changeHoriz;

	}

	void setUpDownBorderForAmogi(int changeVert){
		this.upLimit -= changeVert;
		this.downLimit -= changeVert;

	}

	// -========================= GETTER METHODS =========================-

	int getTime() {
		return this.time;
	}

	GraphicsContext getGC() {
		return this.gc;
	}

	int getAmogiSize() {
		return this.amogi.size();
	}
}
