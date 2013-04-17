package ph.edu.uplb.ics.srg.dcnapplets;

public class PeriodicSignal {
	float amplitude;
	float frequency;
	float phase;
	
	public PeriodicSignal(float amplitude,float frequency,float phase)
	{
		this.amplitude = amplitude;
		this.frequency = frequency;
		this.phase = phase;
	}
	
	public float getAmplitude()
	{
		return amplitude;
	}
	
	public float getFrequency()
	{
		return frequency;
	}
	
	public float getPhase()
	{
		return phase;		
	}
}
