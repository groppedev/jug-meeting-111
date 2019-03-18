package groppedev.jug.meeting111.provider;

import javax.inject.Provider;

import groppedev.jug.meeting111.Transport;

public class Emailer {
	private final Provider<Transport> transportProvider;
	
	public Emailer(Provider<Transport> transportProvider) {
		this.transportProvider = transportProvider;
	}
	public void send(String text) {
		Transport transport = transportProvider.get();
		transport.send(text);
	}
}
