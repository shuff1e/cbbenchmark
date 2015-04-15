package org.cbbenchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Populator {

    /**
     * @param args -> num of keys, sleep time, num threads, hostname
     * @throws Exception
     */
        
    public static void main(String[] args) throws Exception {

        final int numKeys = Integer.valueOf(args[0]);
        final int sleepTime = Integer.valueOf(args[1]);
        final int numThreads = Integer.valueOf(args[2]);
        final String hostName = args[3];

        final int keys_per_thread = numKeys / numThreads;

        final ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        final List<Future> futures = new ArrayList<Future>(numKeys);

        for (int i = 0; i < numThreads; i++) {
            final Callable<Future> worker = new Wpopulator(i * keys_per_thread, i * keys_per_thread + keys_per_thread, sleepTime, hostName);
            futures.add(executor.submit(worker));
        }

        try {
            boolean notCompleted;
            do {
                notCompleted = false;
                for (final Future future : futures) {
                    if (!((Future) future.get()).isDone()) {
                        notCompleted = true;
                        break;
                    }
                }
            } while (notCompleted);
           
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);
            
        } catch (InterruptedException e) {
            System.out.println("Ow ow");
        }
        
        System.out.println("Data populated");
    }
}