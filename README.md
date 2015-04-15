# cbbenchmark

Couchbase benchmark for small (1-2 KB) binary documents

Build 

 **mvn clean compile assembly:single**

Generate test dataset

  **java -Xmx2g -cp cbbenchmark-*.jar org.cbbenchmark.Benchmark 5000000 4 32 127.0.0.1 true**

Run benchmark

  **java -Xmx2g -cp cbbenchmark-*.jar org.cbbenchmark.Benchmark 50000000 0 32 127.0.0.1 false 10**