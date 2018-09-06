# cbbenchmark

Couchbase benchmark for small (1-2 KB) binary documents

Build 

 **mvn clean compile assembly:single**

Generate test dataset

  **java -Xmx2g -cp cbbenchmark-*.jar org.cbbenchmark.Benchmark 127.0.0.1 bucketName bucketPassword 5000000 2000 10 0 32 0.1 cloudcloud 100**

Run benchmark

  **java -Xmx2g -cp cbbenchmark-*.jar org.cbbenchmark.Benchmark 127.0.0.1 bucketName bucketPassword 5000000 2000 10 0 32 0.1 cloudcloud 100**
  
---

Install client

  **ansible-playbook -i client.hosts install_client.yml -e 'host_key_checking=False' -f 12**

Generate test dataset

  **ansible-playbook -i run.hosts run.yml -e loopTimes=100 -e 'host_key_checking=False' -f 12**
  
Run benchmark

  **ansible-playbook -i run.hosts run.yml -e loopTimes=100 -e 'host_key_checking=False' -f 12**
  
Delete client
  
  **ansible-playbook -i client.hosts delete_client.yml -e 'host_key_checking=False' -f 12**

---

Tips

  **if you favor pressure than metrics, dont't wait for the response, aka in a async way, but in a risk of your client can't hold the pressure**

  **if you favor metrics than pressure, wait for the response, aka in a sync way**
  
  **ReactiveX Java can aid you wait for the response in a async way, see [RxJava](https://github.com/ReactiveX/RxJava)**
