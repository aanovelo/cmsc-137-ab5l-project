package ph.edu.uplb.ics.srg.dcnapplets;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SinusoidPanel extends Panel implements ActionListener{
	
	PeriodicSignal signal;
	int components;
	
	TextField txtAmplitude = new TextField(2);
	TextField txtFrequency = new TextField(2);
	TextField txtPhase = new TextField(2);
	TextField txtFrequencyComponents = new TextField(2);
	
	Button btnPlot = new Button("Plot");
	
	public SinusoidPanel(PeriodicSignal signal, int components)
	{
		this.signal = signal;
		this.components = components;
		this.add(new Label("Amplitude:"));
		this.add(txtAmplitude);
		this.add(new Label("Frequency:"));
		this.add(txtFrequency);
		this.add(new Label("Phase:"));
		this.add(txtPhase);
		this.add(new Label("Frequency Components:"));
		this.add(txtFrequencyComponents);
		this.add(btnPlot);
		btnPlot.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		signal.setAmplitude(Integer.parseInt(txtAmplitude.getText()));
		signal.setFrequency(Integer.parseInt(txtFrequency.getText()));
		signal.setPhase(Integer.parseInt(txtPhase.getText()));
		components = Integer.parseInt(txtFrequencyComponents.getText());
		repaint();
	}
	
	
	public void setSignal(PeriodicSignal signal)
	{
		this.signal = signal;
	}

	public void paint(Graphics g)
	{

		int xBase   = 30;
        int top     = 40;
        int yScale  = 50;
        int xAxis   = 360;

        int yBase   = top + yScale * 3;
        int x, y, k;
        int oldx=xBase,oldy=-1;
        float sum;

        
        
        
        // first draw the axis
        g.drawLine( xBase, top, xBase, top + 2*yScale*3 );
        g.drawLine( xBase, yBase, xBase + xAxis, yBase );
        
        
        g.setColor(Color.blue);
        for (k=0;k<=6;k++)
        {
        	g.drawLine(xBase, top+(k*yScale), xBase+5, top+(k*yScale)); 
        	g.drawString(Integer.toString(Math.abs(3-k)), xBase-10, 3+top+(k*yScale));
        }
        
        for (k=0;k<=360;k+=90)
        {
        	g.drawLine(xBase+k, yBase-4, xBase+k, yBase+4);
        	g.drawString(Integer.toString(k), xBase+k-2, yBase+15);
        }
        

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
        	
        	y = (int)( yBase - sum * yScale *(int)signal.getAmplitude());
        	
        	
        	if (oldy==-1)
            	g.drawLine( oldx, y, x, y );
            else
            	g.drawLine( oldx, oldy, x, y );
            oldx=x;
            oldy=y;
        }
	}	
}
