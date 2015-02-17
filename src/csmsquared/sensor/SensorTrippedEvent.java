package csmsquared.sensor;

import java.util.EventObject;

public class SensorTrippedEvent extends EventObject {

	/** Whatever, Eclipse. */
	private static final long serialVersionUID = 1L;

	public SensorTrippedEvent(Object source) {
		super(source);
		// Shouldn't need anything to really happen
	}

}
