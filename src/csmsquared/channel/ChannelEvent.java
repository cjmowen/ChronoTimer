package csmsquared.channel;

public class ChannelEvent {
	int origin;	// The channel number that fired the event

	public ChannelEvent(int origin){
		this.origin = origin;
	}

	public int getChannel(){
		return origin;
	}
}
