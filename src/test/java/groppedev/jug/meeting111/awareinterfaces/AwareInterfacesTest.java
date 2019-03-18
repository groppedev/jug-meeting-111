package groppedev.jug.meeting111.awareinterfaces;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@RunWith(JUnit4.class)
public class AwareInterfacesTest 
{
	private AbstractApplicationContext ctx;
	@Before
	public void setup()
	{
		this.ctx = new ClassPathXmlApplicationContext("conf.xml");
	}
	@After
	public void teardown()
	{
		this.ctx.close();
	}
	@Test
	public void configurationTest()
	{
		groppedev.jug.meeting111.awareinterfaces.Emailer emailer = 
				(groppedev.jug.meeting111.awareinterfaces.Emailer) this.ctx.getBean("jug.emailer.awareinterfaces");
		emailer.send("text");
	}
}
