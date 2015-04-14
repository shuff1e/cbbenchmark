import com.couchbase.client.CouchbaseClient;
import net.spy.memcached.PersistTo;

import java.util.concurrent.Callable;

public class Worker implements Callable<String> {

    private int key;
    private CouchbaseClient client;
    private boolean writer;
    
    public Worker(int i, CouchbaseClient client, boolean w) {
        this.key = i;
        this.writer = w;
        this.client = client;
        
    }

    public String call() throws Exception {
        try {
            
            
            if (writer) {
                this.client.set(String.valueOf(key), 1000, "couchbase-couchbase-couchbase-couchuchbase-couchbase-couchbase-couchba", PersistTo.ONE);
                Thread.sleep(10);
            } else {
               this.client.getAndTouch(String.valueOf(key), 1000);
            }
            
        } catch (Exception e) {
            System.err.println("Error connecting to Couchbase: " + e.getMessage());
        }
        return "done";
    }
}
