package groppedev.jug.meeting111.configurations.javaconfig;

import groppedev.jug.meeting111.SpellChecker;
import groppedev.jug.meeting111.Transport;

public class Emailer {
	private final SpellChecker spellChecker;
	private final Transport transport;
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