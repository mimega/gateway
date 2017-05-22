# How to use

## Running the gateway
Make an uberjar. Execute it with more async threads, if you wish. By default the number is something like `2 + core_count` or similar (8 on my machine). Like this:

```
lein uberjar
java -Dclojure.core.async.pool-size=100 -jar target/gateway-0.1.0-SNAPSHOT-standalone.jar
```

## Running the services
See `start-services.sh`. You must have `mockserver` installed (available through brew). Or just use something similar. Note that sometimes they get "flooded" with requests. There might be some settings to improve the throughput, but I didn't bother with them.


## Stress testing the server
One way of stress testing. Repeatedly send 50 requests from 4 parallel clients. Each request will be very short.
```
watch -n 0.1 "ab -n 50 -c 4 'http://localhost:9898/atest?time=1000&response=abc'"
```
