package p2p.client.protocol;

public enum P2PProtocolHeader {
	ONLINE(0),OUTLINE(1),UPLOAD_RESOURCE_INFO(2),REQUEST_IP(3),
	RESPONSE_IP(4),REQUEST_RESOURCE(5),RESPONSE_RESOURCE(6);
	
	private int value;
	private P2PProtocolHeader(int value) {
		this.value = value;
	}
	public int getValue() {
		return this.value;
	}
}
