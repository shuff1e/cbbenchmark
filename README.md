# cbbenchmark

Couchbase benchmark for small (1-2 KB) binary documents

Build 

 **mvn clean compile assembly:single**

Generate test dataset

  **java -Xmx2g -cp cbbenchmark-*.jar org.cbbenchmark.Benchmark 127.0.0.1 bucketName bucketPassword 5000000 2000 10 0 32 true cloudcloud 100**

Run benchmark

  **java -Xmx2g -cp cbbenchmark-*.jar org.cbbenchmark.Benchmark 127.0.0.1 bucketName bucketPassword 5000000 2000 10 0 32 false cloudcloud 100**
