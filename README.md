# cbbenchmark

Couchbase benchmark for small (1-2 KB) binary documents

Build 

 **mvn clean compile assembly:single**

Run

  **java -Xmx1g -cp cbbenchmark-*.jar org.cbbenchmark.Populator 1000000 4 8 127.0.0.1**
