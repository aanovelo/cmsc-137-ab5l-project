package ph.edu.uplb.ics.srg.dcnapplets;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class SinusoidCanvas extends Canvas{
	
	PeriodicSignal signal;
	
	
	public SinusoidCanvas(PeriodicSignal signal)
	{
		this.signal = signal;		
	}

	public void paint(Graphics g)
	{
		/*
		float st,t;
		
		g.setColor(Color.red);
		g.drawLine(0, 250, 480, 250);
		
		g.setColor(Color.red);
		g.drawLine(0, 0, 20, 480);
		
		
		g.setColor(Color.blue);
		for (t=0;t<100;t++)
		{
			st = signal.getAmplitude()*(float)Math.sin(2*(float)Math.PI*signal.getFrequency()+signal.getPhase());
			g.drawLine((int)t, (int)st, (int)t, (int)st);
		}
		*/
		
		int xBase   = 10;
        int top     = 10;
        int yScale  = 50*(int)signal.getAmplitude();
        int xAxis   = 360;

        int yBase   = top + yScale;
        int x, y;

        // first draw the axis
        g.drawLine( xBase, top, xBase, top + 2*yScale );
        g.drawLine( xBase, yBase, xBase + xAxis, yBase );

        // now plot the graph
        g.setColor( Color.red );

        for( int i=0; i < xAxis; i++ )
        {   
        	x = xBase + i;
            y = (int)( yBase - Math.sin( Math.toRadians(i)*signal.getFrequency() + Math.toRadians(signal.getPhase())) * yScale  );
            g.drawLine( x, y, x, y );
        }
	}	
}
