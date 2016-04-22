#!/usr/bin/env bash

ant clean 
ant compile
status=$?
if [[ $status != 0 ]]; then
  exit 1
else
  exit 0
fi

