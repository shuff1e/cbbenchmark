package org.cbbenchmark;

import com.couchbase.client.CouchbaseClient;
import net.spy.memcached.CASValue;
import net.spy.memcached.internal.OperationFuture;
import net.spy.memcached.transcoders.Transcoder;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Wbenchmark implements Callable<Future> {

    private int keysatrt;
    private int keyend;
    private int sleeptime;
    private String hostname;
    private String value;
    private long timeout;

    public Wbenchmark(int keySatrt, int keyEnd, int sleepTime, String loadValue, String hostName, long timeout) {
        this.keysatrt = keySatrt;
        this.keyend = keyEnd;
        this.sleeptime = sleepTime;
        this.value = loadValue;
        this.hostname = hostName;
        this.timeout = timeout;
    }

    public Future call() throws Exception {

        final ArrayList<URI> nodes = new ArrayList<URI>();
        nodes.add(URI.create("http://" + hostname + ":8091/pools"));

        CouchbaseClient client;

        try {
            client = new CouchbaseClient(nodes, "default", "");
            int n = 0;
            int m = keyend - keysatrt;
            int timeouts = 0;

            for (int i = keysatrt; i < keyend; i++) {
                Thread.sleep(sleeptime);
                final OperationFuture<CASValue<Object>> operationFuture = client.asyncGetAndTouch(String.valueOf(i), getUnixEpochInSeconds(2592000000L));

                try {
                    CASValue<Object> result = operationFuture.get(this.timeout, TimeUnit.MILLISECONDS);
                    if (result.getValue().equals(value)) {
                        n++;
                    }
                } catch (TimeoutException e1) {
                    timeouts++;
                }
            }

            System.out.println("Requested keys: " + m);
            System.out.println("Received keys: " + n);
            System.out.println("Timeouts: " + timeouts);
            client.shutdown();

        } catch (IOException e1) {
            e1.printStackTrace();
            System.err.println("Error connecting to Couchbase: " + e1.getMessage());
        }

        return null;
    }

    public int getUnixEpochInSeconds(final long ttlInMilliseconds) {
        if (ttlInMilliseconds <= 0) {
            return 0;
        } else {
            final long ttlInSeconds = TimeUnit.SECONDS.convert(ttlInMilliseconds, TimeUnit.MILLISECONDS);
            return (int) (ttlInSeconds < 2592000 ? ttlInSeconds : ((System.currentTimeMillis() + ttlInMilliseconds) / 1000L));
        }
    }
}


