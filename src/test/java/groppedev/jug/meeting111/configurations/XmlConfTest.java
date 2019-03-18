package groppedev.jug.meeting111.configurations;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import groppedev.jug.meeting111.Emailer;

@RunWith(JUnit4.class)
public class XmlConfTest 
{
	private AbstractApplicationContext applicationContext;
	@Before
	public void setup()
	{
		this.applicationContext = new ClassPathXmlApplicationContext("conf.xml");
		this.applicationContext.getEnvironment().setActiveProfiles("PRODUCTION");
		this.applicationContext.refresh();
	}
	@After
	public void teardown()
	{
		this.applicationContext.close();
	}
	@Test
	public void configurationTest()
	{
		Emailer emailer = this.applicationContext.getBean(Emailer.class);
		boolean sent = emailer.send("text");
		Assert.assertTrue(sent);
	}
}
