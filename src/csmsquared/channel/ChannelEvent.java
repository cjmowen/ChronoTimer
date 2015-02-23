package csmsquared.channel;

public class ChannelEvent {
	Channel origin;
	
	public ChannelEvent(Channel origin){
		this.origin = origin;
	}
	
	public Channel getChannel(){
		return origin;
	}
}
