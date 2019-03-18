package groppedev.jug.meeting111.interfaceinjection;

import groppedev.jug.meeting111.Transport;

public class Emailer implements MailSender {
	private Transport transport;
	@Override
	public void setTransport(Transport transport) {
		this.transport = transport;		
	}
	@Override
	public void send(String text) {
		this.transport.send(text);
	}
}
