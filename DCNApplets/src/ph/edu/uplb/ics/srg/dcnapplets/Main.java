package ph.edu.uplb.ics.srg.dcnapplets;

import java.awt.Rectangle;

import javax.swing.JFrame;

public class Main extends JFrame {
	
	public Main(){
		this.setTitle("Data Communications Applets");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setBounds(new Rectangle(640,480));
	}
	
	
	public static void main(String args[])
	{
		new Main();
	}
}
