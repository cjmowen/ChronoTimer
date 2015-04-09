package csmsquared.channel;

public class ChannelEvent {
	int origin;

	public ChannelEvent(int origin){
		this.origin = origin;
	}

	public int getChannel(){
		return origin;
	}
}
