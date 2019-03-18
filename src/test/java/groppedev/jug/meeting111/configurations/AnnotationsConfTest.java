package groppedev.jug.meeting111.configurations;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import groppedev.jug.meeting111.configurations.annotations.Emailer;

@RunWith(JUnit4.class)
public class AnnotationsConfTest 
{
	private AbstractApplicationContext ctx;

	@Before
	public void setup()
	{
		this.ctx = new AnnotationConfigApplicationContext("groppedev.jug.meeting111.configurations.annotations");
	}
	@After
	public void teardown()
	{
		this.ctx.close();
	}
	@Test
	public void configurationTest()
	{
		Emailer emailer = this.ctx.getBean(Emailer.class);
		boolean sent = emailer.send("text");
		Assert.assertTrue(sent);
	}
}
