package org.cbbenchmark;

import com.couchbase.client.CouchbaseClient;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class Worker implements Callable<Future> {

    private int key;
    private CouchbaseClient client;
    private boolean writer;
    private int sleepTime;
    private static String value;

    static {
        StringBuilder builder = new StringBuilder(2000);
        for (int k = 0; k < 200; k++) {
            builder.append("couchbaseecouchbasee");
        }
        value = builder.toString();
    }

    public Worker(int i, CouchbaseClient client, boolean w, int sleepTime) {
        this.key = i;
        this.writer = w;
        this.client = client;
        this.sleepTime = sleepTime;
    }

    public Future call() throws Exception {
        try {
            if (writer) {
                Thread.sleep(this.sleepTime);
                return this.client.set(String.valueOf(key), 1000, value);
            } else {
                return this.client.asyncGetAndTouch(String.valueOf(key), 1000);
            }

        } catch (Exception e) {
            System.err.println("Error connecting to Couchbase: " + e.getMessage());
            return null;
        }
    }
}
