package csmsquared.channel;

import java.util.ArrayList;
import java.util.List;

import csmsquared.sensor.Sensor;
import csmsquared.sensor.SensorListener;

public class Channel {
	
	private List<ChannelListener> listeners;
	private Sensor sensor;
	private SensorListener sensorListener; // Store the listener so that it can be removed
	private boolean isActive;
	private int channelNumber;
	
	public Channel(int channelNumber){
		listeners = new ArrayList<ChannelListener>();
		isActive = false;
		this.channelNumber = channelNumber;
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
		sensorListener = new SensorListener(){

			@Override
			public void sensorTripped() {
				signalReceived();
			}
			
		};
		
		sensor.addSensorListener(sensorListener);
	}
	
	/**
	 * Disconnects the Sensor from the Channel.
	 */
	public void disconnectSensor(){
		// Sever the connection by removing the listener
		sensor.removeSensorListener(sensorListener);
		this.sensor = null;
	}
	
	/**
	 * Manually signal the Channel as though a connected Sensor sent a signal.
	 * This notifies all ChannelListeners.
	 */
	public void trigger(){
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
				l.onSignalReceived(new ChannelEvent(channelNumber));
			}
		}
	}
}
