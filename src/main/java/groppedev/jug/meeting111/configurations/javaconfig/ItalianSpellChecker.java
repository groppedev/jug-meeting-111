package groppedev.jug.meeting111.configurations.javaconfig;

import groppedev.jug.meeting111.SpellChecker;

public class ItalianSpellChecker implements SpellChecker {

	@Override
	public boolean check(String text) {
		return true;
	}
}
