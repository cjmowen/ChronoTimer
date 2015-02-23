package csmsquared.channel;

import java.util.ArrayList;
import java.util.List;

import csmsquared.sensor.Sensor;
import csmsquared.sensor.SensorListener;

public class Channel {
	
	private List<ChannelListener> listeners;
	private Sensor sensor;
	private boolean isActive;
	
	public Channel(){
		listeners = new ArrayList<ChannelListener>();
		isActive = false;
	}
	
	/**
	 * Sets whether or not the channel is active. If the sensor is not active,
	 * it cannot fire events.
	 * @param isActive if true, the channel will be active
	 */
	public void setActive(boolean isActive){
		this.isActive = isActive;
	}
	
	/**
	 * Checks if the channel is active
	 * @return true if the channel is active
	 */
	public boolean isActive(){
		return isActive;
	}
	
	/**
	 * Toggles the active state of the Channel
	 */
	public void toggle(){
		isActive = !isActive;
	}
	
	/**
	 * Connects the given Sensor to the Channel
	 * @param sensor the Sensor to be connected to the Channel
	 */
	public void connectSensor(Sensor sensor){
		this.sensor = sensor;
		sensor.addSensorListener(new SensorListener(){

			@Override
			public void sensorTripped() {
				signalReceived();
			}
			
		});
	}
	
	
	public void disconnectSensor(){
		this.sensor = null;
	}
	
	/**
	 * Manually signal the Channel as though a connected Sensor sent a signal.
	 * This notifies all ChannelListeners.
	 */
	public void forceSignal(){
		signalReceived();
	}
	
	/**
	 * Add a listener to the channel
	 * @param l the listener to be added
	 */
	public void addChannelListener(ChannelListener l){
		listeners.add(l);
	}
	
	/**
	 * Remove a listener from the channel
	 * @param l the listener to be removed
	 */
	public void removeChannelListener(ChannelListener l){
		listeners.remove(l);
	}
	
	/**
	 * If the Channel is active, notifies all registered listeners that 
	 * the Channel has received a signal
	 */
	private void signalReceived(){
		if(isActive && listeners != null && !listeners.isEmpty()){
			
			// Call sensorTripped() on all SensorListeners associated with this Sensor
			for(ChannelListener l : listeners){
				l.onSignalReceived(new ChannelEvent(this));
			}
		}
	}
}
