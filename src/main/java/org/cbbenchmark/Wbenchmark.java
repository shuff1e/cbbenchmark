package org.cbbenchmark;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
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
    private MetricRegistry registry;

    public Wbenchmark(int keySatrt, int keyEnd, int sleepTime, String loadValue, String hostName, long timeout, MetricRegistry registry) {
        this.keysatrt = keySatrt;
        this.keyend = keyEnd;
        this.sleeptime = sleepTime;
        this.value = loadValue;
        this.hostname = hostName;
        this.timeout = timeout;
        this.registry = registry;
    }

    public Future call() throws Exception {

        final ArrayList<URI> nodes = new ArrayList<URI>();
        nodes.add(URI.create("http://" + hostname + ":8091/pools"));

        CouchbaseClient client;
        final Timer timer = this.registry.timer("timer");
        try {
            client = new CouchbaseClient(nodes, "default", "");

            this.registry.counter("total").inc(keyend - keysatrt);

            for (int i = keysatrt; i < keyend; i++) {
                Thread.sleep(sleeptime);
                final OperationFuture<CASValue<Object>> operationFuture = client.asyncGetAndTouch(String.valueOf(i), getUnixEpochInSeconds(2592000000L));

                CASValue<Object> result = null;
                try (@SuppressWarnings("unused") Timer.Context context = timer.time()) {
                    result = operationFuture.get(this.timeout, TimeUnit.MILLISECONDS);
                } catch (TimeoutException e1) {
                    this.registry.counter("timeout").inc();
                }
                if (result != null && result.getValue().equals(value)) {
                    this.registry.counter("success").inc();
                }
            }

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


