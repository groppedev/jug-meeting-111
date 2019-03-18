package groppedev.jug.meeting111.configurations.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import groppedev.jug.meeting111.SpellChecker;
import groppedev.jug.meeting111.Transport;

@Component
public class Emailer {
	private final SpellChecker spellChecker;
	private final Transport transport;
	@Autowired
	public Emailer(SpellChecker spellChecker, 
			       Transport transport) {
		this.spellChecker = spellChecker;
		this.transport = transport;
	}
	
	public boolean send(String text) {
		if(spellChecker.check(text)){
			this.transport.send(text);
			return true;
		}
		return false;
	}
}