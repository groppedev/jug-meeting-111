package groppedev.jug.meeting111.interfaceinjection;

import groppedev.jug.meeting111.Transport;

public interface MailSender {
	void setTransport(Transport transport);
	void send(String text);
}
