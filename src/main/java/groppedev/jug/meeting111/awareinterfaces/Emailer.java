package groppedev.jug.meeting111.awareinterfaces;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import groppedev.jug.meeting111.Transport;

public class Emailer implements ApplicationContextAware {
	private ApplicationContext applicationContext;
	public void send(String text) {
		Object t = applicationContext.getBean("jug.transport.awareinterfaces");
		((Transport)t).send("text");
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
