package csmsquared.sensor;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

public class Sensor {
	// TODO: Handle different sensor types
	private List<SensorListener> listeners;
	private boolean isActive;
	
	
	public Sensor(){
		listeners = new ArrayList<SensorListener>();
		isActive = false;
	}
	
	/**
	 * Sets whether or not the sensor is active. If the sensor is not active,
	 * it cannot fire events.
	 * @param isActive if true, the sensor will be active
	 */
	public void setActive(boolean isActive){
		this.isActive = isActive;
	}
	
	/**
	 * Checks is the sensor is active
	 * @return true if the sensor is active
	 */
	public boolean isActive(){
		return isActive;
	}
	
	/**
	 * Add a listener to the sensor
	 * @param l the listener to be added
	 */
	public void addSensorListener(SensorListener l){
		listeners.add(l);
	}
	
	/**
	 * Remove a listener from the sensor
	 * @param l the listener to be removed
	 */
	synchronized public void removeSensorListener(SensorListener l){
		listeners.remove(l);
	}
	
	/**
	 * Manually trip the sensor
	 */
	public void trip(){
		fireSensorTripped();
	}
	
	/**
	 * Notify all registered listeners of the SensorTripped event
	 */
	private void fireSensorTripped(){
		if(listeners != null && !listeners.isEmpty()){
			
			// Call sensorTripped() on all SensorListeners associated with this Sensor
			for(SensorListener l : listeners){
				l.sensorTripped();
			}
		}
	}
}
