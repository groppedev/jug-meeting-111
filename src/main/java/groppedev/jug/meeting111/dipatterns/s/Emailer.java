package groppedev.jug.meeting111.dipatterns.s;

import java.util.Optional;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import groppedev.jug.meeting111.SpellChecker;
import groppedev.jug.meeting111.Transport;

@Named(value="setter-di")
public class Emailer {
	
	@Inject
	public void setRunnable3(Optional<Runnable> runnable3) {
		if(runnable3.isPresent())
		{
			this.runnable3 = runnable3.get();
		}
	}
	@Inject
	public void setRunnable2(@Nullable Runnable runnable2) {
		this.runnable2 = runnable2;
	}

	private SpellChecker spellChecker;
	private Transport transport;
	private Runnable runnable;
	private Runnable runnable2;
	private Runnable runnable3;

	@Autowired(required = false)
	public void setRunnable(Runnable runnable) {
		this.runnable = runnable;
	}
	@Inject
	public void setSpellChecker(SpellChecker spellChecker) {
		this.spellChecker = spellChecker;
	}
	@Inject
	public void setTransport(Transport transport) {
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
