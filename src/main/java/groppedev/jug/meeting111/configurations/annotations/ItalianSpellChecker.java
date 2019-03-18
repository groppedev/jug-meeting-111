package groppedev.jug.meeting111.configurations.annotations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import groppedev.jug.meeting111.SpellChecker;

@Component
@Qualifier(value="ann.spellchecker")
public class ItalianSpellChecker implements SpellChecker {

	@Override
	public boolean check(String text) {
		return true;
	}
}
