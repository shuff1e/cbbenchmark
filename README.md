# cbbenchmark

Couchbase benchmark for small (1-2 KB) binary documents

Build 

 **mvn clean compile assembly:single**

Generate test dataset

  **java -Xmx2g -cp cbbenchmark-*.jar org.cbbenchmark.Benchmark 127.0.0.1 bucketName bucketPassword 5000000 2000 10 0 32 true cloudcloud 100**

Run benchmark

  **java -Xmx2g -cp cbbenchmark-*.jar org.cbbenchmark.Benchmark 127.0.0.1 bucketName bucketPassword 5000000 2000 10 0 32 false cloudcloud 100**
  
---

Install client

  **ansible-playbook -i client.hosts install_client.yml -e 'host_key_checking=False' -f 10**

Generate test dataset

  **ansible-playbook -i run.hosts run.yml -e isGenerator=true -e loopTimes=100 -e 'host_key_checking=False' -f 10**
  
Run benchmark

  **ansible-playbook -i run.hosts run.yml -e isGenerator=false -e loopTimes=100 -e 'host_key_checking=False' -f 10**
  
Delete client
  
  **ansible-playbook -i client.hosts delete_client.yml -e 'host_key_checking=False' -f 10**
