package groppedev.jug.meeting111.dipatterns;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@RunWith(JUnit4.class)
public class DiPatternsTest 
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
	public void constructorBasedDI()
	{
		groppedev.jug.meeting111.dipatterns.c.Emailer emailer =
				applicationContext.getBean(groppedev.jug.meeting111.dipatterns.c.Emailer.class);
		Assert.assertTrue(emailer.send("text"));
	}

	@Test
	public void setterBasedDI()
	{
		groppedev.jug.meeting111.dipatterns.s.Emailer emailer = 
				applicationContext.getBean(groppedev.jug.meeting111.dipatterns.s.Emailer.class);
		Assert.assertTrue(emailer.send("text"));
	}

	@Test
	public void fieldBasedDI()
	{
		groppedev.jug.meeting111.dipatterns.f.Emailer emailer =
				applicationContext.getBean(groppedev.jug.meeting111.dipatterns.f.Emailer.class);
		Assert.assertTrue(emailer.send("text"));
	}
}
