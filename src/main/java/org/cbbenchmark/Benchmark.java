package org.cbbenchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Benchmark {

    /**
     * @param args -> num of keys, sleep time, num threads, hostname, isGenerator, timeout for read request
     * @throws Exception
     */
    public static String value;

    public static void main(String[] args) throws Exception {

        StringBuilder builder = new StringBuilder(2000);
        for (int k = 0; k < 200; k++) {
            builder.append("23couchbaseecouchbasee535674");
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
        final List<Future> futures = new ArrayList<Future>(numKeys);

        if (isGenerator) {
            for (int i = 0; i < numThreads; i++) {
                final Callable<Future> worker = new Wgenerator(i * keys_per_thread, i * keys_per_thread + keys_per_thread, sleepTime, value, hostName);
                futures.add(executor.submit(worker));
            }
        } else {
            for (int i = 0; i < numThreads; i++) {
                final Callable<Future> worker = new Wbenchmark(i * keys_per_thread, i * keys_per_thread + keys_per_thread, sleepTime, value, hostName, timeout);
                futures.add(executor.submit(worker));
            }
        }

//        try {
//            boolean notCompleted;
//            do {
//                notCompleted = false;
//                for (final Future future : futures) {
//                    if (!((Future) future.get()).isDone()) {
//                        notCompleted = true;
//                        break;
//                    }
//                }
//            } while (notCompleted);

            executor.shutdown();

//        } catch (InterruptedException e) {
//            System.out.println("Ow!");
//        }
        System.out.println("Done");
    }
}