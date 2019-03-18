package groppedev.jug.meeting111.scopes;

import javax.inject.Provider;

import groppedev.jug.meeting111.SpellChecker;
import groppedev.jug.meeting111.Transport;

public class Emailer {
	private final SpellChecker spellChecker;
	private final Provider<Transport> transportProvider;

	public Emailer(SpellChecker spellChecker, Provider<Transport> transportProvider) {
		this.spellChecker = spellChecker;
		this.transportProvider = transportProvider;
	}
	
	public boolean send(String text) {
		if(spellChecker.check(text)){
			this.transportProvider.get().send(text);
			return true;
		}
		return false;
	}
}
