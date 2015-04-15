package org.cbbenchmark;

import com.couchbase.client.CouchbaseClient;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class Wgenerator implements Callable<Future> {

    private int keysatrt;
    private int keyend;
    private int sleeptime;
    private String hostname;
    private String value;

    public Wgenerator(int keySatrt, int keyEnd, int sleepTime, String loadValue, String hostName) {
        this.keysatrt = keySatrt;
        this.keyend = keyEnd;
        this.sleeptime = sleepTime;
        this.value = loadValue;
        this.hostname = hostName;
    }

    public Future call() throws Exception {


        final ArrayList<URI> nodes = new ArrayList<URI>();
        nodes.add(URI.create("http://" + hostname + ":8091/pools"));

        CouchbaseClient client = null;
        try {
            client = new CouchbaseClient(nodes, "default", "");

            for (int i = keysatrt; i < keyend; i++) {
                Thread.sleep(sleeptime);
                client.set(String.valueOf(i), 100000, value);
            }

            client.shutdown();

        } catch (IOException e1) {
            e1.printStackTrace();
            System.err.println("Error connecting to Couchbase: " + e1.getMessage());
        }

        return null;
    }
}
