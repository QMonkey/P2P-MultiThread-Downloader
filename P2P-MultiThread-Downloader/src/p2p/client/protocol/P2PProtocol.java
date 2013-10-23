package p2p.client.protocol;

public enum P2PProtocol {
	ONLINE(0),OUTLINE(1),SEARCH_RESOURCE(2),RESPONSE_SEARCH(3),
	REQUEST_RESOURCE(4),RESPONSE_RESOURCE(5);
	
	private int value;
	private P2PProtocol(int value) {
		this.value = value;
	}
	public int getValue() {
		return this.value;
	}
}
