package groppedev.jug.meeting111.configurations.javaconfig;

import groppedev.jug.meeting111.Transport;

public class SMTPTransport implements Transport {

	@Override
	public void send(String message) {
		System.out.println("Sending message -> " + message);
	}
}
