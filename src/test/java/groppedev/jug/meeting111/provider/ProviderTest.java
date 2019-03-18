package groppedev.jug.meeting111.provider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@RunWith(JUnit4.class)
public class ProviderTest {

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
	public void providerTest()
	{
		Emailer emailer = this.applicationContext.getBean(Emailer.class);
		emailer.send("text");
		emailer.send("text");
		emailer = this.applicationContext.getBean(Emailer.class);
		emailer.send("text");
	}
}
