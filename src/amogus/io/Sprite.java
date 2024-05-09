package amogus.io;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
	protected Image img;		// image of the sprite
	protected int x, y, dx, dy;	// the x and y pos on the map and the change in x and y of the sprite
	protected boolean visible;	// visibility status of the sprite
	protected double width;		// dimensions of the sprite photo / hitbox
	protected double height;

	// sprite constructor
	public Sprite(int xPos, int yPos){
		this.x = xPos;
		this.y = yPos;
		this.visible = true;
	}

	//method to set the object's image
	protected void loadImage(Image img){
		try{
			this.img = img;
	        this.setSize();
		} catch(Exception e){}
	}

	//method to set the image to the image view node
	void render(GraphicsContext gc){
		gc.drawImage(this.img, this.x, this.y);
    }

	//method to set the object's width and height properties
	private void setSize(){

		this.width = Amogus.AMOGUS_SIZE;
	    this.height = Amogus.AMOGUS_SIZE;
	}

	//method that will check for collision of two sprites
	boolean collidesWith(Sprite rect2)	{
		Rectangle2D rectangle1 = this.getBounds();
		Rectangle2D rectangle2 = rect2.getBounds();

		return rectangle1.intersects(rectangle2);
	}
	//method that will return the bounds of an image
	private Rectangle2D getBounds(){
		return new Rectangle2D(this.x, this.y, this.width, this.height);
	}


	//method to return the image
	Image getImage(){
		return this.img;
	}

	//getters of the sprite's location
	int getX() {
    	return this.x;
	}
	int getY() {
    	return this.y;
	}
	//getters of the change in the sprite's location
	int getDX() {
		return this.dx;
	}
	int getDY() {
		return this.dy;
	}

	// checks if the sprite is visible
	boolean isVisible(){
		if(visible) return true;
		return false;
	}

	//setters of the change in the sprite's location
	void setDX(int dx){

		this.dx = dx;
	}
	void setDY(int dy){
		this.dy = dy;
	}

	// sets the visible status to false
	void vanish(){
		this.visible = false;
	}



}
