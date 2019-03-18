package groppedev.jug.meeting111.scopes;

import javax.inject.Provider;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import groppedev.jug.meeting111.SpellChecker;
import groppedev.jug.meeting111.Transport;

@RunWith(JUnit4.class)
@SuppressWarnings({ "unchecked", "static-method" })
public class ScopesTest 
{
	private AbstractApplicationContext applicationContext;

	@Before
	public void setup()
	{
		this.applicationContext = new ClassPathXmlApplicationContext("conf.xml");
	}

	@After
	public void teardown()
	{
		this.applicationContext.close();
	}

	@Test
	public void prototypeProvider()
	{
		// Fixture
		SpellChecker spellChecker = Mockito.mock(SpellChecker.class);
		Provider<Transport> transportProvider = Mockito.mock(Provider.class);
		Transport transport = Mockito.mock(Transport.class);
		// Stubbing
		Mockito.when(spellChecker.check(Mockito.anyString())).thenReturn(true);
		Mockito.when(transportProvider.get()).thenReturn(transport);
		// Test
		Emailer emailer = new Emailer(spellChecker, transportProvider);
		// Assertion
		Assert.assertTrue(emailer.send("text"));
		// Verification.
		Mockito.verify(transport, Mockito.times(1)).send(Mockito.eq("text"));
	}
}