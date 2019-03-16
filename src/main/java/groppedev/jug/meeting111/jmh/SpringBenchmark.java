package groppedev.jug.meeting111.jmh;

import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

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

import groppedev.jug.meeting111.ItalianSpellChecker;
import groppedev.jug.meeting111.SMTPTransport;

/**
 * Compilare il progetto con maven, viceversa non sarà possibile eseguire il benchmark.
 * 1) C:\Users\massi\git\jug-meeting-111>C:\Tools\apache-maven-3.3.9-bin\apache-maven-3.3.9\bin\mvn clean install 
 * "mvn clean install -Dmaven.test.skip=true"
 * 2) Run as java application.
 * 3) Impostare fork(0) per debug
 * @author GROMAS
 */
@SuppressWarnings("static-method")
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class SpringBenchmark 
{
	private AbstractApplicationContext applicationContext;

    @Setup(value=Level.Trial)
    public void setup() throws URISyntaxException
	{
    	applicationContext = new ClassPathXmlApplicationContext("conf.xml");
    }
	
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
    public void fake(Blackhole bh) throws InterruptedException
    {
    	Thread.sleep(2);
    }
    
	public static void main(String...args) throws RunnerException
	{
		Options opt = new OptionsBuilder()
				.mode(Mode.Throughput)
				.timeUnit(TimeUnit.MILLISECONDS)
				.include(SpringBenchmark.class.getSimpleName())
				.forks(1)
				.threads(1)
				.shouldDoGC(true)                 // Esegue un System.gc() deterministico.
				.shouldFailOnError(true)
				.addProfiler(GCProfiler.class)    // Profiler del Garbage Collector.
				.addProfiler(StackProfiler.class) // Analisi dei Thread.
				.build();
		new Runner(opt).run();
	}
}
