package p2p.server.handler;

import java.util.LinkedList;

public class IPTableManager {
	public static void add(LinkedList<String> iptable,String ip) {
		synchronized(iptable) {
			iptable.add(ip);
		}
	}
	
	public static void del(LinkedList<String> iptable,String ip) {
		synchronized(iptable) {
			iptable.remove(ip);
		}
	}
}
