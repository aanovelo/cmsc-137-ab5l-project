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

		int xBase   = 10;
        int top     = 10;
        int yScale  = 50*(int)signal.getAmplitude();
        int xAxis   = 360;

        int yBase   = top + yScale;
        int x, y;
        int oldx=xBase,oldy=-1;

        // first draw the axis
        g.drawLine( xBase, top, xBase, top + 2*yScale );
        g.drawLine( xBase, yBase, xBase + xAxis, yBase );

        // now plot the graph
        g.setColor( Color.red );

        for( int i=0; i < xAxis; i++ )
        {   
        	x = xBase + i;
            y = (int)( yBase - Math.sin( Math.toRadians(i)*signal.getFrequency() + Math.toRadians(signal.getPhase())) * yScale  );
            if (oldy==-1)
            	g.drawLine( oldx, y, x, y );
            else
            	g.drawLine( oldx, oldy, x, y );
            oldx=x;
            oldy=y;
        }
	}	
}
