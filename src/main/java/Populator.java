import com.couchbase.client.CouchbaseClient;
import net.spy.memcached.PersistTo;

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

    public static final int NUMTREADS = 2;
    public static final int NUMKEYS = 10000;

    public static void main(String[] args) throws Exception {

        final ArrayList<URI> nodes = new ArrayList<URI>();
        nodes.add(URI.create("http://usvalzcbstg01.inf.videologygroup.com:8091/pools"));

        CouchbaseClient client = null;
        try {
            client = new CouchbaseClient(nodes, "default", "");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        

        final ExecutorService executor = Executors.newFixedThreadPool(NUMTREADS);
        int keys_per_thread = NUMKEYS / NUMTREADS;

        final List<Future<String>> futures = new ArrayList<Future<String>>(NUMTREADS);

        for (int i = 0; i < NUMKEYS; i++) {
                final Callable<String> worker = new Worker(i, client, true);
                futures.add(executor.submit(worker));
        }

        try {
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.MINUTES);

          //for (final Future<String> future : futures) {
          //    System.out.println(future.get());
          //}

      } catch (InterruptedException e) {
          System.out.println("Ow ow");
      }
        System.out.println("Data populated");
    }
}

// http://www.couchbase.com/wiki/display/couchbase/Observe