package ph.edu.uplb.ics.srg.dcnapplets;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class SinusoidCanvas extends Canvas{

	public void paint(Graphics g)
	{
		g.setColor(Color.blue);
		g.drawOval(90,90,30,30);				
	}
	
}
