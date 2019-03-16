package groppedev.jug.meeting111;

public class EmailerMain {

	public static void main(String[] args) {
		Emailer emailer = new Emailer(new EnglishSpellChecker(), new SMTPTransport());
		emailer.send("Testo messaggio");
	}
}
