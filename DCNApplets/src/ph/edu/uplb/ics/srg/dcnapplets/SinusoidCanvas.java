package ph.edu.uplb.ics.srg.dcnapplets;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class SinusoidCanvas extends Canvas{
	
	PeriodicSignal signal;
	int components;
	
	
	public SinusoidCanvas(PeriodicSignal signal, int components)
	{
		this.signal = signal;
		this.components = components;
	}

	public void paint(Graphics g)
	{

		int xBase   = 10;
        int top     = 10;
        int yScale  = 50*(int)signal.getAmplitude();
        int xAxis   = 360;

        int yBase   = top + yScale;
        int x, y, k;
        int oldx=xBase,oldy=-1;
        float sum;

        // first draw the axis
        g.drawLine( xBase, top, xBase, top + 2*yScale );
        g.drawLine( xBase, yBase, xBase + xAxis, yBase );

        // now plot the graph
        g.setColor( Color.red );

        
        
        for( int i=0; i < xAxis; i++ )
        {   
        	sum=0;
        	x = xBase + i;
        	//y = (int)( yBase - (Math.sin( Math.toRadians(i)*signal.getFrequency() + Math.toRadians(signal.getPhase())) + (Math.sin( Math.toRadians(i)*signal.getFrequency()*3 + Math.toRadians(signal.getPhase())))/3) * yScale  );
            //y = (int)( yBase - Math.cos( Math.toRadians(i)*signal.getFrequency() + Math.toRadians(signal.getPhase()) - Math.toRadians(90)) * yScale  );
                    	
        	for (k=1;k<=components;k+=2)
        	{
        		sum += (Math.sin( Math.toRadians(i)*signal.getFrequency()*k + Math.toRadians(signal.getPhase())))/k;
        	}
        	
        	y = (int)( yBase - sum * yScale);
        	
        	
        	if (oldy==-1)
            	g.drawLine( oldx, y, x, y );
            else
            	g.drawLine( oldx, oldy, x, y );
            oldx=x;
            oldy=y;
        }
	}	
}
