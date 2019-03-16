package groppedev.jug.meeting111.jmh;

import groppedev.jug.meeting111.Emailer;
import groppedev.jug.meeting111.ItalianSpellChecker;
import groppedev.jug.meeting111.SMTPTransport;
import groppedev.jug.meeting111.SpellChecker;
import groppedev.jug.meeting111.Transport;

public class Emailers 
{
	private static final Emailer EMAILER = new Emailer(new ItalianSpellChecker(), new SMTPTransport());
	
	public static Emailer newEmailer(SpellChecker spellChecker, Transport transport)
	{
		return new Emailer(spellChecker, transport);
	} 
	
	public static Emailer emailer()
	{
		return EMAILER;
	}
}
