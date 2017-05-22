#!/bin/bash
for i in {1..10}
do
  PORT=$(( 9900 + $i ))
  echo "Start server port $PORT"
  # mockserver -serverPort $PORT &
  java -Dclojure.core.async.pool-size=20 -server -jar bin/mockservice-0.1.0-SNAPSHOT-standalone.jar $PORT &
done
