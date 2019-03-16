package groppedev.jug.meeting111;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@RunWith(JUnit4.class)
public class InjectorBootstrap 
{
	@SuppressWarnings("static-method")
	@Test
	public void test()
	{
		try(AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("conf.xml"))
		{
			Emailer emailer = (Emailer) applicationContext.getBean("jug.emailer");
			emailer.send("text");
		}
	}
}
