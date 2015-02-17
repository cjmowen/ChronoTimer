package csmsquared.sensor;
import java.util.EventListener;

public interface SensorListener extends EventListener{
	public void sensorTripped(SensorTrippedEvent e);
}
