One way of stress testing. Repeatedly send 200 requests from 200 parallel clients. Each request will take 1000 ms to complete (`time=1000`).
```
watch -n 0.1 "ab -n 200 -c 200 'http://localhost:9898/atest?time=1000&response=abc'"
```
