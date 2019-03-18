package groppedev.jug.meeting111.dipatterns.f;

import javax.inject.Inject;
import javax.inject.Named;

import groppedev.jug.meeting111.SpellChecker;
import groppedev.jug.meeting111.Transport;

@Named(value="field-di")
public class Emailer {
	@Inject
	private SpellChecker spellChecker;
	@Inject
	private Transport transport;

	public boolean send(String text) {
		if(spellChecker.check(text)){
			this.transport.send(text);
			return true;
		}
		return false;
	}
}
