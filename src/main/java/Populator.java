import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.internal.ObserveFuture;
import net.spy.memcached.PersistTo;
import net.spy.memcached.internal.OperationFuture;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Populator {

    public static final int NUMTREADS = 2;
    public static final int NUMKEYS = 10000;

    public static void main(String[] args) throws Exception {

        final ArrayList<URI> nodes = new ArrayList<URI>();
        nodes.add(URI.create("http://localhost:8091/pools"));
//        nodes.add(URI.create("http://usvalzcbstg01.inf.videologygroup.com:8091/pools"));

        CouchbaseClient client = null;
        try {
            client = new CouchbaseClient(nodes, "default", "");
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        final ExecutorService executor = Executors.newFixedThreadPool(NUMTREADS);

        final List<Future> futures = new ArrayList<Future>(NUMKEYS);

        for (int i = 0; i < NUMKEYS; i++) {
            final Callable<OperationFuture> worker = new Worker(i, client, true);
            futures.add(executor.submit(worker));
        }

        try {
            boolean notCompleted;
            do {
                notCompleted = false;
                for (final Future future : futures) {
                    if (!((ObserveFuture) future.get()).isDone()) {
                        notCompleted = true;
                        break;
                    }
                }
            } while (notCompleted);

            System.out.println("Data populated");

            client.shutdown();
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println("Ow ow");
        }
    }
}

// http://www.couchbase.com/wiki/display/couchbase/Observe