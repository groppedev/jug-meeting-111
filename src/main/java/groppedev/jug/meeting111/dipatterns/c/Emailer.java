package groppedev.jug.meeting111.dipatterns.c;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;
import javax.inject.Named;

import groppedev.jug.meeting111.SpellChecker;
import groppedev.jug.meeting111.Transport;

@Named(value="constructor-di")
@Immutable
public class Emailer {
	private final SpellChecker spellChecker;
	private final Transport transport;
	private final Optional<Runnable> runnable;
	public Emailer(SpellChecker spellChecker, Transport transport, Optional<Runnable> runnable) {
		this.spellChecker = spellChecker;
		this.transport = transport;
		this.runnable = runnable;
	}
	public boolean send(String text) {
		if(spellChecker.check(text)){
			this.transport.send(text);
			return true;
		}
		return false;
	}
}
