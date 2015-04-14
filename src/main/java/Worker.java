import com.couchbase.client.CouchbaseClient;
import net.spy.memcached.PersistTo;
import net.spy.memcached.internal.OperationFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class Worker implements Callable<OperationFuture> {

    private int key;
    private CouchbaseClient client;
    private boolean writer;

    public Worker(int i, CouchbaseClient client, boolean w) {
        this.key = i;
        this.writer = w;
        this.client = client;

    }

    public OperationFuture call() throws Exception {
        try {
            if (writer) {
                return this.client.set(String.valueOf(key), 1000, "couchbase-couchbase-couchbase-couchuchbase-couchbase-couchbase-couchba", PersistTo.ONE);
            } else {
                return this.client.asyncGetAndTouch(String.valueOf(key), 1000);
            }

        } catch (Exception e) {
            System.err.println("Error connecting to Couchbase: " + e.getMessage());
            return null;
        }
    }
}
