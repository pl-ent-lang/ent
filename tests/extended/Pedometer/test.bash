#!/usr/bin/env bash

./gradlew clean 
./gradlew dexPlayDebug
status=$?
if [[ $status != 0 ]]; then
  exit 1
else
  exit 0
fi

