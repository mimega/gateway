#!/bin/bash
for i in {1..10}
do
  PORT=$(( 9900 + $i ))
  echo "Start server port $PORT"
  mockserver -serverPort $PORT &
done
