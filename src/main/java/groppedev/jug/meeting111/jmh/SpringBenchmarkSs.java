package groppedev.jug.meeting111.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
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
 * @see http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/
 * @see https://mechanical-sympathy.blogspot.com/2011/11/biased-locking-osr-and-benchmarking-fun.html?m=1
 * @author Groppelli Massimo
 */
@SuppressWarnings({"static-method"})
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@CompilerControl(CompilerControl.Mode.EXCLUDE)
@State(Scope.Benchmark)
public class SpringBenchmarkSs 
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
	public boolean singletonFactory()
	{
		return Emailers.emailer().send("text");
	}
	@Benchmark
	public boolean singletonSpring()
	{
		return ((Emailer)applicationContext.getBean("jug.emailer.singleton")).send("text");
	}
	@Benchmark
	public boolean prototypeFactory()
	{
		return Emailers.newEmailer(new ItalianSpellChecker(), new SMTPTransport()).send("text");
	}
	@Benchmark
	public boolean prototypeSpring()
	{
		return ((Emailer)applicationContext.getBean("jug.emailer.proto")).send("text");
	}
	public static void main(String...args) throws RunnerException
	{
		Options opt = new OptionsBuilder()
				.include(SpringBenchmarkSs.class.getSimpleName())
				.forks(1)
				.jvmArgsAppend("-XX:-UseBiasedLocking")
				.build();
		new Runner(opt).run();
	}
}
