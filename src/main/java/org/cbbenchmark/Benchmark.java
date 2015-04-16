package org.cbbenchmark;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Benchmark {

    public static String value;

    /**
     * @param args -> num of keys, sleep time, num threads, hostname, isGenerator, timeout for read request
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        StringBuilder builder = new StringBuilder(4000);
        for (int k = 0; k < 400; k++) {
            builder.append("23couchbaseecouchbasee535646bv256c24gfdsdg<secdxawsczdxcvcgvdsf74");
        }

        value = builder.toString();

        final int numKeys = Integer.valueOf(args[0]);
        final int sleepTime = Integer.valueOf(args[1]);
        final int numThreads = Integer.valueOf(args[2]);
        final String hostName = args[3];
        final boolean isGenerator = Boolean.valueOf(args[4]);
        final long timeout = Long.valueOf(args[5]);

        final int keys_per_thread = numKeys / numThreads;

        final ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        final MetricRegistry registry = new MetricRegistry();
        registry.timer("timer");
        registry.counter("success");
        registry.counter("total");
        registry.counter("timeout");

        final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(30, TimeUnit.SECONDS);

        if (isGenerator) {
            for (int i = 0; i < numThreads; i++) {
                final Callable<Future> worker = new Wgenerator(i * keys_per_thread, i * keys_per_thread + keys_per_thread, sleepTime, value, hostName);
                executor.submit(worker);
            }
        } else {
            for (int i = 0; i < numThreads; i++) {
                final Callable<Future> worker = new Wbenchmark(i * keys_per_thread, i * keys_per_thread + keys_per_thread, sleepTime, value, hostName, timeout, registry);
                executor.submit(worker);
            }
        }

        executor.shutdown();
        System.out.println("Done");
    }
}