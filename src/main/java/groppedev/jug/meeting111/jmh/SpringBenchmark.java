package groppedev.jug.meeting111.jmh;

import java.util.concurrent.TimeUnit;

import javax.inject.Provider;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import groppedev.jug.meeting111.Emailer;
import groppedev.jug.meeting111.ItalianSpellChecker;
import groppedev.jug.meeting111.SMTPTransport;

/**
 * 1) "mvn clean install -Dmaven.test.skip=true" (No EMBEDDED installation)
 * 2) Run as java application.
 * 
 * - Set fork(0) for debugging
 * 
 * @author Groppelli Massimo
 */
@SuppressWarnings({"static-method", "unchecked"})
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class SpringBenchmark 
{
	private AbstractApplicationContext applicationContext;

	/**
	 * Spring container startup
	 */
	@Setup(value=Level.Trial)
	public void setup()
	{
		applicationContext = new ClassPathXmlApplicationContext("conf.xml");
	}
	/**
	 * Spring container shutdown
	 */
	@TearDown(value=Level.Trial)
	public void teardown()
	{
		applicationContext.close();
	}
	@Benchmark
	public void singletonFactory(Blackhole bh)
	{
		bh.consume(Emailers.emailer());
	}
	@Benchmark
	public void singletonSpring(Blackhole bh)
	{
		bh.consume(applicationContext.getBean("jug.emailer.singleton"));
	}
	@Benchmark
	public void singletonProviderSpring(Blackhole bh)
	{
		bh.consume(((Provider<Emailer>) applicationContext.getBean("jug.emailer.singleton.provider")).get());
	}
	@Benchmark
	public void prototypeFactory(Blackhole bh)
	{
		bh.consume(Emailers.newEmailer(new ItalianSpellChecker(), new SMTPTransport()));
	}
	@Benchmark
	public void prototypeSpring(Blackhole bh)
	{
		bh.consume(applicationContext.getBean("jug.emailer.proto"));
	}
	@Benchmark
	public void prototypeProviderSpring(Blackhole bh)
	{
		bh.consume(((Provider<Emailer>) applicationContext.getBean("jug.emailer.proto.provider")).get());
	}

	public static void main(String...args) throws RunnerException
	{
		Options opt = new OptionsBuilder()
				.mode(Mode.Throughput)
				.timeUnit(TimeUnit.MILLISECONDS)
				.include(SpringBenchmark.class.getSimpleName())
				.forks(1)
				.threads(5)
				.shouldDoGC(true)                 
				.shouldFailOnError(true)
				.addProfiler(GCProfiler.class)
				.addProfiler(StackProfiler.class)
				.build();
		new Runner(opt).run();
	}
}
