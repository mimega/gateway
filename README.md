# How to use

## Running the server
Make an uberjar. Execute it with more async threads, if you wish. By default the number is something like `2 + core_count` or similar (8 on my machine). Like this:

```
lein uberjar
java -Dclojure.core.async.pool-size=100 -jar target/gateway-0.1.0-SNAPSHOT-standalone.jar
```

## Stress testing the server
One way of stress testing. Repeatedly send 200 requests from 200 parallel clients. Each request will take 1000 ms to complete (`time=1000`).
```
watch -n 0.1 "ab -n 200 -c 200 'http://localhost:9898/atest?time=1000&response=abc'"
```
