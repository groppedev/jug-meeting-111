package groppedev.jug.meeting111;

public class Emailer {
	private final SpellChecker spellChecker;
	private final Transport transport;

	public Emailer(SpellChecker spellChecker, Transport transport) {
		this.spellChecker = spellChecker;
		this.transport = transport;
	}
	
	public void send(String text) {
		if(spellChecker.check(text)){
			this.transport.send(text);
		}
	}
}
