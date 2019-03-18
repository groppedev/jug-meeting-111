package groppedev.jug.meeting111.configurations.annotations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import groppedev.jug.meeting111.Transport;

@Component
@Qualifier(value="ann.transport")
public class SMTPTransport implements Transport {

	@Override
	public void send(String message) {
		System.out.println("Sending message -> " + message);
	}
}
