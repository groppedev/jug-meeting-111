package groppedev.jug.meeting111.configurations.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import groppedev.jug.meeting111.SpellChecker;
import groppedev.jug.meeting111.Transport;

@SuppressWarnings("static-method")
@Configuration
@ComponentScan(basePackages="groppedev.jug.meeting111.configurations.javaconfig") 
public class AppConfig 
{
	@Bean
	public Emailer emailer(SpellChecker spellChecker, Transport transport) {
		return new Emailer(spellChecker, transport);
	}
	@Bean
	public SpellChecker spellChecker() {
		return new ItalianSpellChecker();
	}
	@Bean
	public Transport transport() {
		return new SMTPTransport();
	}
	@Bean("transport.proto")
	@Scope("prototype")
	public Transport transportPrototype() 
	{
		return new SMTPTransport();
	}
}
