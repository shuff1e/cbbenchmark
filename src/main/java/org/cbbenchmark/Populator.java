package org.cbbenchmark;

import com.couchbase.client.CouchbaseClient;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class Populator {

    /**
     * @param args -> num of keys, sleep time, hostname
     * @throws Exception
     */
        
    public static void main(String[] args) throws Exception {

        final int numKeys = Integer.valueOf(args[0]);
        final int sleepTime = Integer.valueOf(args[1]);
        final String hostname = args[2];
        final String value = "randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--randomString--";
        
        final ArrayList<URI> nodes = new ArrayList<URI>();
        nodes.add(URI.create("http://" + hostname + ":8091/pools"));

        CouchbaseClient client = null;
        try {
            client = new CouchbaseClient(nodes, "default", "");

            for (int i = 0; i < numKeys; i++) {
                Thread.sleep(sleepTime);
                client.set(String.valueOf(i), 1000, value);
            }
            
            client.shutdown();
            
        } catch (IOException e1) {
            e1.printStackTrace();
            System.err.println("Error connecting to Couchbase: " + e1.getMessage());
        }

        System.out.println("Data populated");

        }
}