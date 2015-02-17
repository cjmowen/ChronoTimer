package csmsquared.sensor;

import java.util.Enumeration;
import java.util.Vector;

public class Sensor {
	
	// A Vector containing all associated listeners
	private transient Vector<SensorListener> listeners;
	
	private boolean isActive;
	
	
	public Sensor(){
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
	synchronized public void addSensorListener(SensorListener l){
		if(listeners == null)
			listeners = new Vector<SensorListener>();
		listeners.addElement(l);
	}
	
	/**
	 * Remove a listener from the sensor
	 * @param l the listener to be removed
	 */
	synchronized public void removeSensorListener(SensorListener l){
		if(listeners == null)
			listeners = new Vector<SensorListener>();
		listeners.removeElement(l);
	}
	
	/**
	 * Manually trip the sensor
	 */
	public void trip(){
		fireSensorTripped();
	}
	
	/**
	 * Fire a SensorTrippedEvent to all registered listeners
	 * Based on code from http://www.jguru.com/faq/view.jsp?EID=98547
	 */
	@SuppressWarnings("unchecked")
	protected void fireSensorTripped(){
		if(listeners != null && !listeners.isEmpty()){
			// Create the event
			SensorTrippedEvent event = new SensorTrippedEvent(this);
			
			// Copy the listener list in case anyone else adds or removes listeners
			Vector<SensorListener> targets;
			synchronized (this){
				targets = (Vector<SensorListener>) listeners.clone();
			}
			
			// Call sensorTripped() on all SensorListeners associated with this Sensor
			Enumeration<SensorListener> e = targets.elements();
			while(e.hasMoreElements()){
				SensorListener l = (SensorListener) e.nextElement();
				l.sensorTripped(event);
			}
		}
	}
}
