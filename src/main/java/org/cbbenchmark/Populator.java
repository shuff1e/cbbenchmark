package org.cbbenchmark;

import com.couchbase.client.CouchbaseClient;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Populator {

    /**
     * @param args -> num of threads, num of keys, sleep time, hostname
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        final int numThreads = Integer.valueOf(args[0]);
        final int numKeys = Integer.valueOf(args[1]);
        final int sleepTime = Integer.valueOf(args[2]);
        final String hostname = args[3];

        final ArrayList<URI> nodes = new ArrayList<URI>();
        nodes.add(URI.create("http://" + hostname + ":8091/pools"));

        CouchbaseClient client = null;
        try {
            client = new CouchbaseClient(nodes, "default", "");
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        final ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numKeys; i++) {
            final Callable<Future> worker = new Worker(i, client, true, sleepTime);
            executor.submit(worker);
        }

        try {
            Thread.sleep(1000000);
            System.out.println("Data populated");

            client.shutdown();
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println("Ow ow");
        }
    }
}